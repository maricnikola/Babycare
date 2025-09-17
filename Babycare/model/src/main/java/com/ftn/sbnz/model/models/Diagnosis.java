package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.TreatmentType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public Date date;
    public Integer threshold;
}
