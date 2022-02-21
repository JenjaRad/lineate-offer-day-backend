package com.example.offerdaysongs.dto.requests;

import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.CopyrightPeriod;
import com.example.offerdaysongs.model.Recording;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCopyrightRequest {
    private CopyrightPeriod period;
    private boolean isActive;
    private BigDecimal price;
    private Company company;
    private Recording recording;
}
