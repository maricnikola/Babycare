package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.ExaminationType;
import com.ftn.sbnz.model.models.enums.Gender;
import com.ftn.sbnz.model.models.enums.VaccinationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Baby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    public Gender gender;
    public Double height;
    public Double weight;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Diagnosis> Diagnoses;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Vaccination> vaccinations;

    @OneToMany
    private List<Examination> examinations;

    public Examination getLastExamination() {
        return examinations.stream().max(Comparator.comparing(Examination::getExamDate)).orElse(null);
    }

    public Boolean hasVaccination(VaccinationType type) {
        return true;
    }

    public int getAgeInDays(){
        return Period.between(birthDate, LocalDate.now()).getDays();
    }
}
