package com.hlt.usermanagement.dto;

import com.hlt.usermanagement.dto.enums.FeeStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeDTO {

    private Long id;

    public static final String ACADEMIC_ID_REQUIRED = "Academic ID is required";
    public static final String STUDENT_ID_REQUIRED = "Student ID is required";
    public static final String AMOUNT_REQUIRED = "Amount is required";
    public static final String AMOUNT_GT_ZERO = "Amount must be greater than 0";
    public static final String DUE_DATE_REQUIRED = "Due date is required";
    public static final String STATUS_REQUIRED = "Fee status is required";
    public static final String NOTES_MAX = "Notes cannot exceed 500 characters";

    @NotNull(message = ACADEMIC_ID_REQUIRED)
    private Long academicId;

    @NotNull(message = STUDENT_ID_REQUIRED)
    private Long studentId;

    @NotNull(message = AMOUNT_REQUIRED)
    @DecimalMin(value = "0.0", inclusive = false, message = AMOUNT_GT_ZERO)
    private BigDecimal amount;

    @NotNull(message = DUE_DATE_REQUIRED)
    private LocalDate dueDate;

    private LocalDate paidDate;

    @NotNull(message = STATUS_REQUIRED)
    private FeeStatus status;

    @Size(max = 500, message = NOTES_MAX)
    private String notes;

    private Integer installmentNo;
}
