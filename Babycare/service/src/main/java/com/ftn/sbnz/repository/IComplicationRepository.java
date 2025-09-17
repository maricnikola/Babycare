package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Complication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;
@Repository
public interface IComplicationRepository extends JpaRepository<Complication, Long> {
}
