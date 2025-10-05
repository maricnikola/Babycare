package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.enums.Disease;
import com.ftn.sbnz.service.BabyService;
import com.ftn.sbnz.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

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

        if (baby.getLastExamination() == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "No examination found for this baby"));
        }
        LocalDate twoDaysAgo = LocalDate.now().minusDays(2);
        if (baby.getLastExamination().getExamDate().isBefore(twoDaysAgo)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Last examination must not be older than 2 days"));
        }
        boolean hasDisease = diagnosisService.checkDisease(disease, baby.getLastExamination());
        return ResponseEntity.ok()
                .body(Map.of("hasDisease", hasDisease, "disease", disease.name()));
    }
}
