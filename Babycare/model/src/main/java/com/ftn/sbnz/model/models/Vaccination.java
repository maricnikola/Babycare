package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.VaccinationType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public VaccinationType vaccineName;
    public LocalDate date;
    public Integer dose;
    public Boolean isVaccinated;
}
