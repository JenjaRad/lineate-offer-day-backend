package com.example.offerdaysongs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
@Getter
@NoArgsConstructor
public class CopyrightPeriod {

    @JsonFormat(pattern = "dd-M-yyyy hh:mm:ss", timezone = "UTC", locale = "uk_UA")
    private Timestamp from;

    @JsonFormat(pattern = "dd-M-yyyy hh:mm:ss", timezone = "UTC", locale = "uk_UA")
    private Timestamp to;
}