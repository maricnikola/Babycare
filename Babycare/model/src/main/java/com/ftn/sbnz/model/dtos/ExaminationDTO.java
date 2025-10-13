package com.ftn.sbnz.model.dtos;

import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.model.models.enums.SymptomName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationDTO {
    private LocalDateTime examDate;
    private Double height;
    private Double weight;
    public Double temperature;
    public Integer heartRate;
    public Integer respirationRate;
    public Double erythrocytes;
    public Double crp;
    public Double headCircumference;
    public List<SymptomName> symptoms = new ArrayList<>();
}
