package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.ExaminationType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public LocalDate examDate;
    public ExaminationType examinationType;
    public Double weight;
    public Double height;
    public Double temperature;
    public Integer heartRate;
    public Integer respirationRate;
    public Double headCircumference;

    @OneToMany
    public List<Symptom> symptoms;
    @OneToMany
    public List<Report> reports;
}
