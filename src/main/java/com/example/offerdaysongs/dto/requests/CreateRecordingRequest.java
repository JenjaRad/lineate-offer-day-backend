package com.example.offerdaysongs.dto.requests;

import com.example.offerdaysongs.model.Singer;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CreateRecordingRequest {
    String title;
    String version;
    ZonedDateTime releaseTime;
    Singer singer;
}
