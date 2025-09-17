package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Therapy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITherapyRepository extends JpaRepository<Therapy, Long> {
}
