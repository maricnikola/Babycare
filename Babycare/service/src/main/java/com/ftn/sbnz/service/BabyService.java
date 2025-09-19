package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.repository.IBabyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BabyService {
    @Autowired
    private IBabyRepository babyRepository;

    public Baby findById(Long id) {
        Optional<Baby> baby = babyRepository.findById(id);
        if(baby.isEmpty()) return null;
        return baby.get();
    }
}
