package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVaccinationRepository extends JpaRepository<Vaccination,Long> {
}
