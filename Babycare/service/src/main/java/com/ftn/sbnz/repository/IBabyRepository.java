package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Baby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBabyRepository extends JpaRepository<Baby,Long> {
}
