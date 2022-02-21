package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.RecordingDto;
import com.example.offerdaysongs.dto.requests.CreateRecordingRequest;
import com.example.offerdaysongs.factory.MappingFactory;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.service.RecordingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recordings")
public class RecordingController {
    private static final String ID = "id";
    private final RecordingService recordingService;

    public RecordingController(RecordingService recordingService) {
        this.recordingService = recordingService;
    }

    @GetMapping("/")
    public List<RecordingDto> getAll() {
        return recordingService.getAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id:[\\d]+}")
    public RecordingDto get(@PathVariable(ID) long id) {
        var recording = recordingService.getById(id);
        return convertToDto(recording);
    }

    @GetMapping("/calculate-price/{id}")
    public ResponseEntity<BigDecimal> calculateRecordingPrice(@PathVariable("id") long recordingId) {
        BigDecimal totalPrice = recordingService.calculateRecordingPrice(recordingId);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @PostMapping("/")
    public RecordingDto create(@RequestBody CreateRecordingRequest request) {
        return convertToDto(recordingService.create(request));
    }

    private RecordingDto convertToDto(Recording recording) {
        return MappingFactory.convertToDto(recording);
    }
}
