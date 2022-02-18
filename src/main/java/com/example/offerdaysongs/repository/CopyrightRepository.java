package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.model.CopyrightPeriod;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface CopyrightRepository extends JpaRepository<Copyright, Long> {
    @EntityGraph(attributePaths = {"company"})
    List<Copyright> findAllByCompanyId(long id);

    Copyright findByPeriod(CopyrightPeriod period);

    @Query("update Copyright c set c.company = :company where c.id = :id")
    @Modifying
    void updateCopyrightByIdAndCompany(long id, Company company);

    @Modifying
    List<Copyright> deleteAllByPeriod(CopyrightPeriod period);
}
