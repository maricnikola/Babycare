package com.ftn.sbnz.service;

import com.ftn.sbnz.model.dtos.BabyDTO;
import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.repository.IBabyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BabyService {
    @Autowired
    private IBabyRepository babyRepository;

    public Baby findById(Long id) {
        Optional<Baby> baby = babyRepository.findById(id);
        if(baby.isEmpty()) return null;
        return baby.get();
    }

    public Baby createBaby(BabyDTO babyDTO) {
        Baby baby = new Baby();
        baby.setFirstName(babyDTO.getFirstName());
        baby.setLastName(babyDTO.getLastName());
        baby.setBirthDate(babyDTO.getBirthDate());
        baby.setGender(babyDTO.getGender());
        baby.setHeight(babyDTO.getHeight());
        baby.setWeight(babyDTO.getWeight());
        baby.setDiagnoses(new ArrayList<>());
        baby.setExaminations(new ArrayList<>());
        baby.setVaccinations(new ArrayList<>());

        return babyRepository.save(baby);
    }
    public List<BabyDTO> getAllBabies() {
        return babyRepository.findAll()
                .stream()
                .map(BabyDTO::new)
                .collect(Collectors.toList());
    }
}
