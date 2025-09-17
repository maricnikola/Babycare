package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExaminationRepository extends JpaRepository<Examination, Long> {
}
