package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.Disease;
import com.ftn.sbnz.model.models.enums.TreatmentType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Disease disease;
    public LocalDate date;
    public Integer threshold;
}
