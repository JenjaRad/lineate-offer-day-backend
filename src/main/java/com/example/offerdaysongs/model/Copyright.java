package com.example.offerdaysongs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Copyright {
    @Id
    private long id;

    @Embedded
    private CopyrightPeriod period;

    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recording_id")
    private Recording recording;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Company company;
}

