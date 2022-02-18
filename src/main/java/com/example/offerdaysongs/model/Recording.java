package com.example.offerdaysongs.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recording {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String version;

    private ZonedDateTime releaseTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Singer singer;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "recording", orphanRemoval = true
    )
    private List<Copyright> copyrights = new ArrayList<>();

    public void addCopyright(Copyright copyright) {
        copyrights.add(copyright);
        copyright.setRecording(this);
    }

    public void removeCopyright(Copyright copyright) {
        copyrights.remove(copyright);
        copyright.setRecording(null);
    }
}
