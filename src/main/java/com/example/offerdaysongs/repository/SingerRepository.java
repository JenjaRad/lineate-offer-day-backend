package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SingerRepository extends JpaRepository<Singer, Long>, JpaSpecificationExecutor<Singer> {
}
