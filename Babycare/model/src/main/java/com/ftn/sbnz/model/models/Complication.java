package com.ftn.sbnz.model.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Complication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;

    @ManyToMany
    public List<Symptom> symptoms;

    @ManyToOne
    public Vaccination vaccination;
}
