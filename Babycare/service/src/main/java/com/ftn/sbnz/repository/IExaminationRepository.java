package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IExaminationRepository extends JpaRepository<Examination, Long> {
    Examination findTopByBabyIdOrderByExamDateDesc(Long babyId);
}
