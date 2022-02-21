package com.example.offerdaysongs.dto;

import com.example.offerdaysongs.model.CopyrightPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CopyrightDto {
    private long id;
    private CopyrightPeriod period;
    private boolean isActive;
    private BigDecimal price;
    private CompanyDto companyDto;
    private RecordingDto recordingDto;
}
