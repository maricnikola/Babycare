package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.Method;
import com.ftn.sbnz.model.models.enums.TreatmentType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class Therapy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public Integer dose;
    public Integer frequency;
    public Integer durationDays;
    public Method method;
    public TreatmentType treatmentType;
}
