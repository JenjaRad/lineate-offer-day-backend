package com.example.offerdaysongs.service;

import com.example.offerdaysongs.dto.requests.CreateRecordingRequest;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.model.Singer;
import com.example.offerdaysongs.repository.RecordingRepository;
import com.example.offerdaysongs.repository.SingerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class RecordingService {
    private final RecordingRepository recordingRepository;
    private final SingerRepository singerRepository;

    public RecordingService(RecordingRepository recordingRepository,
                            SingerRepository singerRepository) {
        this.recordingRepository = recordingRepository;
        this.singerRepository = singerRepository;
    }

    @Transactional
    public Recording create(CreateRecordingRequest request) {
        Recording recording = new Recording();
        recording.setTitle(request.getTitle());
        recording.setVersion(request.getVersion());
        recording.setReleaseTime(request.getReleaseTime());
        var singerDto = request.getSinger();
        if (singerDto != null) {
            var singer = singerRepository.findById(singerDto.getId())
                    .orElseGet(() -> {
                        var temp = new Singer();
                        temp.setName(singerDto.getName());
                        return singerRepository.save(temp);
                    });
            recording.setSinger(singer);
        }
        return recordingRepository.save(recording);
    }

    public List<Recording> getAll() {
        return recordingRepository.findAll();
    }

    public Recording getById(long id) {
        return recordingRepository.getById(id);
    }


    /**
     * Calculate recording price base on copyrights that belongs to it.
     *
     * @param recordingId the current recording for check.
     * @return the total amount of money that user needs to pay for using this recording.
     */
    public BigDecimal calculateRecordingPrice(long recordingId) {
        Recording recording = recordingRepository.findById(recordingId)
                .orElseThrow(IllegalArgumentException::new);
        List<Copyright> copyrights = recording.getCopyrights();

        return copyrights.stream()
                .map(Copyright::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
