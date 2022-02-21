package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.CopyrightDto;
import com.example.offerdaysongs.dto.requests.CreateCopyrightRequest;
import com.example.offerdaysongs.factory.MappingFactory;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.model.CopyrightPeriod;
import com.example.offerdaysongs.service.CopyrightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/copyright")
@RequiredArgsConstructor
public class CopyrightController {

    private final CopyrightService copyrightService;

    @PostMapping("/")
    public ResponseEntity<CopyrightDto> createCopyright(@RequestBody CreateCopyrightRequest request) {
        CopyrightDto copyrightDto = convertToDto(copyrightService.createCopyright(request));
        return new ResponseEntity<>(copyrightDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/period")
    @ResponseStatus(HttpStatus.OK)
    public void updateCopyrightByPeriod(@PathVariable long id, @RequestBody CopyrightPeriod period) {
        copyrightService.updateCopyrightByPeriod(id, period);
    }

    @GetMapping("/copyright/period")
    public ResponseEntity<List<CopyrightDto>> findCopyrightByPeriod(@RequestParam Timestamp from, @RequestParam Timestamp to) {
        List<CopyrightDto> copyrightsByPeriod = copyrightService.findCopyrightsByPeriod(new CopyrightPeriod(from, to))
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(copyrightsByPeriod, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CopyrightDto>> getAll() {
        List<CopyrightDto> copyrights = copyrightService.getAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(copyrights, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CopyrightDto>> findAllByCompanyId(@PathVariable long id) {
        List<CopyrightDto> copyrights = copyrightService.findAllByCompanyId(id)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(copyrights, HttpStatus.OK);
    }

    @DeleteMapping("/period")
    public ResponseEntity<List<CopyrightDto>> deleteCopyrightsByPeriod(CopyrightPeriod period) {
        List<CopyrightDto> deletedCopyrights = copyrightService.deleteAllByPeriod(period)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(deletedCopyrights, HttpStatus.OK);
    }


    private CopyrightDto convertToDto(Copyright copyright) {
        return MappingFactory.convertToDto(copyright);
    }
}
