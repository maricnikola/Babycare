package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.dtos.ExaminationDTO;
import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.service.BabyService;
import com.ftn.sbnz.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/examination")
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private BabyService babyService;

    @PostMapping(value = "/create/{babyId}", consumes = "application/json", produces = "application/json")
    public void addExamination(@PathVariable Long babyId, @RequestBody ExaminationDTO examinationDTO) {
        Baby baby = babyService.findById(babyId);
        Examination examination = null;
        try {
            examination = examinationService.addExamination(baby, examinationDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        examinationService.addVaccination(baby, examination);
    }
}
