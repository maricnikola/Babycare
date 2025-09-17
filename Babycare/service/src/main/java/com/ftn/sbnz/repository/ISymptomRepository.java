package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISymptomRepository extends JpaRepository<Symptom, Long> {
}
