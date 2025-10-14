package com.hlt.usermanagement.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * Main DTO matching the structure of the UI screens.
 */
@Data
public class FeesSummaryDTO {
    private String academicYear;
    private BigDecimal totalFees;

    // Calculated fields
    private BigDecimal paidAmount;
    private BigDecimal remainingAmount;

    // Details for the "View Details" section (Fees Breakup)
    private List<FeeBreakupItemDTO> breakupDetails;

    // Details for the "Paid" section (Payment History)
    private List<PaymentHistoryDTO> paymentHistory;
}
