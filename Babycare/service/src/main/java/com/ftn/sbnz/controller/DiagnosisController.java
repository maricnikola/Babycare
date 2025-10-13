package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.model.models.enums.Disease;
import com.ftn.sbnz.service.BabyService;
import com.ftn.sbnz.service.DiagnosisService;
import com.ftn.sbnz.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private BabyService babyService;

    @GetMapping
    public ResponseEntity<?> checkDisease(@RequestParam Disease disease, @RequestParam Long babyId) {

        if (disease == null || babyId == null) {
            return ResponseEntity.badRequest().body("Disease and babyId are required");
        }

        Baby baby = babyService.findById(babyId);
        if (baby == null) {
            return ResponseEntity.notFound().build();
        }
        Examination examination = examinationService.findLastExaminationForBaby(babyId);
        if (examination == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "No examination found for this baby"));
        }
        LocalDateTime twoDaysAgo = LocalDateTime.now().minusDays(2);
        if (examination.getExamDate().isBefore(twoDaysAgo)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Last examination must not be older than 2 days"));
        }
        boolean hasDisease = diagnosisService.checkDisease(disease, examination);
        return ResponseEntity.ok()
                .body(Map.of("hasDisease", hasDisease, "disease", disease.name()));
    }
}
