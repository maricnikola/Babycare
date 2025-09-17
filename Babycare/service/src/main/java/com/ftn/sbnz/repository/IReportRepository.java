package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReportRepository extends JpaRepository<Report, Long> {
}
