package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.requests.CreateCompanyRequest;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.service.CopyrightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/copyright")
@RequiredArgsConstructor
public class CopyrightController {

    private final CopyrightService copyrightService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCopyrightByIdAndCompany(@PathVariable long id, @RequestBody CreateCompanyRequest company) {
        copyrightService.updateCopyrightByCompany(id, company);
    }
}
