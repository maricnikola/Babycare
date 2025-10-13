package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.TreatmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public TreatmentType treatmentType;
    public Integer durationDays;
    @OneToMany
    public Set<Therapy> therapies = new HashSet<>();
}
