package com.example.offerdaysongs.service;

import com.example.offerdaysongs.dto.requests.CreateCompanyRequest;
import com.example.offerdaysongs.dto.requests.CreateCopyrightRequest;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.model.CopyrightPeriod;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.repository.CompanyRepository;
import com.example.offerdaysongs.repository.CopyrightRepository;
import com.example.offerdaysongs.repository.RecordingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CopyrightService {

    private final CopyrightRepository copyrightRepository;

    private final CompanyRepository companyRepository;

    private final RecordingRepository recordingRepository;

    public List<Copyright> getAll() {
        return copyrightRepository.findAll();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Copyright createCopyright(CreateCopyrightRequest request) {
        var copyright = new Copyright();
        copyright.setPeriod(request.getPeriod());
        var company = request.getCompany();
        var recording = request.getRecording();
        var period = request.getPeriod();
        if (company != null && recording != null) {
            Company currentCompany = getCompany(company);
            Recording currentRecording = getRecording(recording);
            copyright.setCompany(currentCompany);
            copyright.setRecording(currentRecording);
            copyright.setPeriod(period);
        }
        copyright.setActive(true);
        return copyrightRepository.save(copyright);
    }

    private Recording getRecording(Recording recording) {
        return recordingRepository.findById(recording.getId())
                .orElseGet(() -> {
                    var tempRecording = new Recording();
                    tempRecording.setTitle(recording.getTitle());
                    return recordingRepository.save(tempRecording);
                });
    }

    private Company getCompany(Company company) {
        return companyRepository.findById(company.getId())
                .orElseGet(() -> {
                    var tempCompany = new Company();
                    tempCompany.setName(company.getName());
                    return companyRepository.save(tempCompany);
                });
    }

    @Transactional
    public void updateCopyrightByCompany(long id, CreateCompanyRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
        var company = new Company();
        company.setName(request.getName());
        var copyright = copyrightRepository.getById(id);
        company.setCopyright(copyright);
        companyRepository.save(company);
        copyrightRepository.updateCopyrightByIdAndCompany(id, company);
    }

    public List<Copyright> findAllByCompanyId(long id) {
        List<Copyright> copyrightsBySpecificCompany = copyrightRepository.findAllByCompanyId(id);
        return Objects.requireNonNullElse(copyrightsBySpecificCompany, Collections.emptyList());
    }

    public Copyright findCopyrightByPeriod(CopyrightPeriod period) {
        Copyright rightByPeriod = copyrightRepository.findByPeriod(period);
        if (rightByPeriod == null) {
            throw new IllegalArgumentException(String.format("Cannot find copyright by this specific period: %s", period));
        }
        return rightByPeriod;
    }

    @Transactional
    public List<Copyright> deleteAllByPeriod(CopyrightPeriod period) {
        return copyrightRepository.deleteAllByPeriod(period);
    }
}
