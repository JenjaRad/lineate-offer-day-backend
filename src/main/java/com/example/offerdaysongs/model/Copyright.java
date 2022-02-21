package com.example.offerdaysongs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    private Boolean isActive;

    private BigDecimal price;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recording_id")
    @JsonIgnore
    private Recording recording;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private Company company;

}

