package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.VaccinationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public VaccinationType vaccineName;
    public LocalDate date;
    public Integer dose;
}
