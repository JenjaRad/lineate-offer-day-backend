package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.model.CopyrightPeriod;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface CopyrightRepository extends JpaRepository<Copyright, Long> {
    @EntityGraph(attributePaths = {"company"})
    List<Copyright> findAllByCompanyId(long id);

    List<Copyright> findByPeriod(CopyrightPeriod period);

    @Modifying
    List<Copyright> deleteAllByPeriod(CopyrightPeriod period);

}
