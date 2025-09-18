package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
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
}
