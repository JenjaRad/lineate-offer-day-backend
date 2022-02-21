package com.example.offerdaysongs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CopyrightPeriod {

    @JsonFormat(pattern = "dd-M-yyyy hh:mm:ss", timezone = "UTC", locale = "uk_UA")
    @Column(name = "FROM_DATE")
    private Timestamp fromDate;

    @JsonFormat(pattern = "dd-M-yyyy hh:mm:ss", timezone = "UTC", locale = "uk_UA")
    @Column(name = "TO_DATE")
    private Timestamp toDate;
}