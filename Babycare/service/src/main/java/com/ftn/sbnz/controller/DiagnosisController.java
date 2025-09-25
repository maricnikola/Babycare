package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.enums.Disease;
import com.ftn.sbnz.service.BabyService;
import com.ftn.sbnz.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private BabyService babyService;

    @PostMapping
    public boolean checkDisease(@RequestParam Disease disease,
                                @RequestParam Long babyId) {
        Baby baby = babyService.findById(babyId);
        return diagnosisService.checkDisease(disease, baby.getLastExamination());
    }
}
