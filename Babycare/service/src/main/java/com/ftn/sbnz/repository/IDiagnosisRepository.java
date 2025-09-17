package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiagnosisRepository extends JpaRepository<Diagnosis, Long> {
}
