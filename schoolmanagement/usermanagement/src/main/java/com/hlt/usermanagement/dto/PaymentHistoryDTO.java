package com.hlt.usermanagement.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentHistoryDTO {
    private LocalDate date;
    private BigDecimal amount;
    private String downloadUrl; // Link for the receipt download
}
