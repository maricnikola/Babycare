package com.ftn.sbnz.model.dtos;

import com.ftn.sbnz.model.models.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BabyDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private Double height;
    private Double weight;
}
