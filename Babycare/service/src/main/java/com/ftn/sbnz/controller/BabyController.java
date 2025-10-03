package com.ftn.sbnz.controller;


import com.ftn.sbnz.model.dtos.BabyDTO;
import com.ftn.sbnz.model.dtos.ExaminationDTO;
import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.service.BabyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baby")
public class BabyController {
    @Autowired
    public BabyService babyService;

    @PostMapping()
    public Baby createBaby(@RequestBody BabyDTO babyDTO) {
        return babyService.createBaby(babyDTO);
    }
}
