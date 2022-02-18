package com.example.offerdaysongs.dto.requests;

import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.CopyrightPeriod;
import com.example.offerdaysongs.model.Recording;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCopyrightRequest {
    private CopyrightPeriod period;
    private boolean isActive;
    private Company company;
    private Recording recording;
}
