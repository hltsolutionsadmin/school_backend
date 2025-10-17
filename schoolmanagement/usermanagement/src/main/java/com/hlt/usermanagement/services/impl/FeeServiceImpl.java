package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.FeeDTO;
import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.FeeModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.populator.FeePopulator;
import com.hlt.usermanagement.repository.AcademicRepository;
import com.hlt.usermanagement.repository.FeeRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.hlt.usermanagement.services.FeeService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;
    private final AcademicRepository academicRepository;
    private final UserRepository userRepository;
    private final FeePopulator feePopulator;

    @Override
    public FeeDTO createFee(FeeDTO dto) {
        FeeModel fee = mapDtoToModel(dto, null);
        FeeModel saved = feeRepository.save(fee);
        return feePopulator.toDTO(saved);
    }

    @Override
    public FeeDTO updateFee(Long id, FeeDTO dto) {
        FeeModel fee = feeRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.FEES_NOT_FOUND));

        FeeModel updated = mapDtoToModel(dto, fee);
        feeRepository.save(updated);
        return feePopulator.toDTO(updated);
    }

    @Override
    public FeeDTO getFeeById(Long id) {
        FeeModel fee = feeRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.FEES_NOT_FOUND));
        return feePopulator.toDTO(fee);
    }

    @Override
    public void deleteFee(Long id) {
        FeeModel fee = feeRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.FEES_NOT_FOUND));
        feeRepository.delete(fee);
    }

    @Override
    public Page<FeeDTO> getFeesByAcademic(Long academicId, Pageable pageable) {
        AcademicModel academic = getAcademicOrThrow(academicId);
        Page<FeeModel> feesPage = feeRepository.findByAcademic(academic, pageable);
        return convertToDtoPage(feesPage, pageable);
    }

    @Override
    public Page<FeeDTO> getFeesByStudent(Long studentId, Pageable pageable) {
        UserModel student = getStudentOrThrow(studentId);
        Page<FeeModel> feesPage = feeRepository.findByStudent(student, pageable);
        return convertToDtoPage(feesPage, pageable);
    }


    private FeeModel mapDtoToModel(FeeDTO dto, FeeModel existing) {
        AcademicModel academic = getAcademicOrThrow(dto.getAcademicId());
        UserModel student = getStudentOrThrow(dto.getStudentId());

        FeeModel fee = (existing != null) ? existing : new FeeModel();
        fee.setAcademic(academic);
        fee.setStudent(student);
        fee.setAmount(dto.getAmount());
        fee.setDueDate(dto.getDueDate());
        fee.setPaidDate(dto.getPaidDate());
        fee.setStatus(dto.getStatus());
        fee.setNotes(dto.getNotes());
        fee.setInstallmentNo(dto.getInstallmentNo());

        return fee;
    }

    private AcademicModel getAcademicOrThrow(Long academicId) {
        return academicRepository.findById(academicId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));
    }

    private UserModel getStudentOrThrow(Long studentId) {
        return userRepository.findById(studentId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
    }

    private Page<FeeDTO> convertToDtoPage(Page<FeeModel> feesPage, Pageable pageable) {
        List<FeeDTO> dtos = feesPage.getContent()
                .stream()
                .map(feePopulator::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, feesPage.getTotalElements());
    }
}
