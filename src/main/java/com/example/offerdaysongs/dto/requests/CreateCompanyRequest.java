package com.example.offerdaysongs.dto.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCompanyRequest {
    private String name;
    private BigDecimal totalBalance;
}
