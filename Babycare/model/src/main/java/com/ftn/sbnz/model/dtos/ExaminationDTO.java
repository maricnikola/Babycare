package com.ftn.sbnz.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationDTO {
    private LocalDate examDate;
    private Double height;
    private Double weight;
    public Double temperature;
    public Integer heartRate;
    public Integer respirationRate;
    public Double headCircumference;
}
