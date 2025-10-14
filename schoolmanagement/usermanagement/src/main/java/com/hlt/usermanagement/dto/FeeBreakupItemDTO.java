package com.hlt.usermanagement.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class FeeBreakupItemDTO {
    private String description;
    private BigDecimal amount;
}