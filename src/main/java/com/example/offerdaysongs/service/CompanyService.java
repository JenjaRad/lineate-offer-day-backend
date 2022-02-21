package com.example.offerdaysongs.service;

import com.example.offerdaysongs.dto.requests.CreateCompanyRequest;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(long id) {
        return companyRepository.getById(id);
    }

    public Company create(CreateCompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        return companyRepository.save(company);
    }

    /**
     * Returns a subtracted balance of company if there's a copyright available and It's been over a month since company has owned it.
     *
     * @param companyId the company for which the balance sheet is calculated.
     * @return a subtracted balance of company. Otherwise a current balance without any changes.
     */
    @Transactional
    public BigDecimal updateCompanyTotalBalance(long companyId) {
        Company foundedCompany = companyRepository.findById(companyId)
                .orElseThrow(IllegalArgumentException::new);
        Copyright currentCopyright = foundedCompany.getCopyright();
        long period = ChronoUnit.MONTHS.between(currentCopyright.getCreationDate(), LocalDate.now());
        if (period > 0) {
            return foundedCompany.getTotalBalance()
                    .subtract(currentCopyright.getPrice());
        }
        return foundedCompany.getTotalBalance();
    }
}
