package com.ftn.sbnz.model.dtos;

import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BabyDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private Double height;
    private Double weight;
    public BabyDTO(Baby baby) {
        if (baby != null) {
            this.id = baby.getId();
            this.firstName = baby.getFirstName();
            this.lastName = baby.getLastName();
            this.birthDate = baby.getBirthDate();
            this.gender = baby.getGender();
            this.height = baby.getHeight();
            this.weight = baby.getWeight();
        }
    }
}
