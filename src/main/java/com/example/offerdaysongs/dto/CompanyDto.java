package com.example.offerdaysongs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CompanyDto {
    long id;
    String name;
    BigDecimal totalBalance;
}
