package com.example.offerdaysongs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class RecordingDto {
    long id;
    String title;
    String version;
    ZonedDateTime releaseTime;
    SingerDto singer;

    public RecordingDto(long id, String title, String version, ZonedDateTime releaseTime) {
        this.id = id;
        this.title = title;
        this.version = version;
        this.releaseTime = releaseTime;
    }
}
