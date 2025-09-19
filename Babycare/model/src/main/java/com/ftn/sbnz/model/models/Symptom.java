package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.SymptomName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Enumerated(EnumType.STRING)
    public SymptomName name;
    public Long weight;
    public Integer durationDays;
}
