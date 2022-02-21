package com.example.offerdaysongs.factory;

import com.example.offerdaysongs.dto.CompanyDto;
import com.example.offerdaysongs.dto.CopyrightDto;
import com.example.offerdaysongs.dto.RecordingDto;
import com.example.offerdaysongs.dto.SingerDto;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.model.Singer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MappingFactory {

    public static CompanyDto convertToDto(Company company) {
        return new CompanyDto(company.getId(), company.getName(), company.getTotalBalance());
    }

    public static CopyrightDto convertToDto(Copyright copyright) {
        Company company = copyright.getCompany();
        Recording recording = copyright.getRecording();
        return new CopyrightDto(
                copyright.getId(),
                copyright.getPeriod(),
                copyright.getIsActive(),
                copyright.getPrice(),
                company != null ? new CompanyDto(company.getId(),
                        company.getName(), company.getTotalBalance()) : null,
                recording != null ? new RecordingDto(recording.getId(),
                        recording.getTitle(),
                        recording.getVersion(),
                        recording.getReleaseTime()) : null);
    }

    public static RecordingDto convertToDto(Recording recording) {
        var singer = recording.getSinger();
        return new RecordingDto(recording.getId(),
                recording.getTitle(),
                recording.getVersion(),
                recording.getReleaseTime(),
                singer != null ? new SingerDto(singer.getId(), singer.getName()) : null);
    }

    public static SingerDto convertToDto(Singer singer) {
        return new SingerDto(singer.getId(), singer.getName());
    }
}
