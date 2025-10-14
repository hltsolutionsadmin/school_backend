package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.FeeBreakupItemDTO;
import com.hlt.usermanagement.dto.FeesSummaryDTO;
import com.hlt.usermanagement.dto.PaymentHistoryDTO;
import com.hlt.usermanagement.model.FeeStructure;
import com.hlt.usermanagement.model.Payment;
import com.hlt.usermanagement.repository.FeeStructureRepository;
import com.hlt.usermanagement.services.FeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class FeeServiceImpl implements FeeService {

    private final FeeStructureRepository feeStructureRepository;

    public FeeServiceImpl(FeeStructureRepository feeStructureRepository) {
        this.feeStructureRepository = feeStructureRepository;
    }

    @Override
    @Transactional(readOnly = true) // Read-only transaction for fetching data
    public FeesSummaryDTO getFeesSummary(Long studentId, String academicYear) throws NoSuchElementException {

        // Fetch FeeStructure. In a real application, the repository method should be
        // optimized here (e.g., using @Query with JOIN FETCH) to load the
        // 'payments' and 'breakupItems' collections eagerly to avoid N+1 issues.
        FeeStructure structure = feeStructureRepository
                .findByStudentIdAndAcademicYear(studentId, academicYear)
                .orElseThrow(() -> new NoSuchElementException(
                        "Fee structure not found for student " + studentId + " in year " + academicYear)
                );

        // This line is added to guarantee collection initialization if the repository
        // didn't use JOIN FETCH and the collections are Lazy.
        // It's a defensive measure, though optimizing the repository is preferred.
        structure.getPayments().size();

        return mapToFeesSummaryDTO(structure);
    }

    /**
     * Maps the JPA Entity to the DTO and performs required calculations.
     */
    private FeesSummaryDTO mapToFeesSummaryDTO(FeeStructure structure) {

        // --- 1. Calculate Financial Summary ---
        // Core logic: Sum up the amounts of all SUCCESS payments.
        BigDecimal paidAmount = structure.getPayments().stream()
                .filter(p -> p.getStatus() == Payment.PaymentStatus.SUCCESS)
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalFees = structure.getTotalFees();
        // Core logic: Remaining = Total - Paid
        BigDecimal remainingAmount = totalFees.subtract(paidAmount);

        // --- 2. Map Breakup Items ---
        List<FeeBreakupItemDTO> breakupDTOs = structure.getBreakupItems().stream()
                .map(item -> {
                    FeeBreakupItemDTO bDto = new FeeBreakupItemDTO();
                    bDto.setDescription(item.getDescription());
                    bDto.setAmount(item.getAmount());
                    return bDto;
                })
                .collect(Collectors.toList());

        // --- 3. Map Payment History ---
        List<PaymentHistoryDTO> paymentDTOs = structure.getPayments().stream()
                // Only show successful payments in the history displayed to the user
                .filter(p -> p.getStatus() == Payment.PaymentStatus.SUCCESS)
                .sorted(Comparator.comparing(Payment::getPaymentDate).reversed()) // Latest payment first
                .map(payment -> {
                    PaymentHistoryDTO pDto = new PaymentHistoryDTO();
                    pDto.setDate(payment.getPaymentDate());
                    pDto.setAmount(payment.getAmount());
                    // Placeholder for a receipt download link
                    pDto.setDownloadUrl("/api/fees/receipt/" + payment.getTransactionReference());
                    return pDto;
                })
                .collect(Collectors.toList());

        // --- 4. Populate Main DTO ---
        FeesSummaryDTO dto = new FeesSummaryDTO();
        dto.setAcademicYear(structure.getAcademicYear());
        dto.setTotalFees(totalFees);
        dto.setPaidAmount(paidAmount);
        dto.setRemainingAmount(remainingAmount);
        dto.setBreakupDetails(breakupDTOs);
        dto.setPaymentHistory(paymentDTOs);

        return dto;
    }
}
