package com.ftn.sbnz.service;

import com.ftn.sbnz.ServiceApplication;
import com.ftn.sbnz.model.dtos.ExaminationDTO;
import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import com.ftn.sbnz.repository.IExaminationRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExaminationService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private IExaminationRepository repository;
    public void addExamination(Baby baby, ExaminationDTO examinationDTO) {
        Examination examination = new Examination();
        examination.setExamDate(examinationDTO.getExamDate());
        examination.setHeight(examinationDTO.getHeight());
        examination.setWeight(examinationDTO.getWeight());
        examination.setTemperature(examinationDTO.getTemperature());
        examination.setHeartRate(examinationDTO.getHeartRate());
        examination.setRespirationRate(examinationDTO.getRespirationRate());
        examination.setSymptoms(new ArrayList<>());
        examination.setReports(new ArrayList<>());

        examination.setBaby(baby);

        baby.getExaminations().add(examination);

        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");

        kieSession.insert(baby);
        kieSession.insert(examination);
        kieSession.fireAllRules();
        kieSession.dispose();

        repository.save(examination);
    }
}
