package com.ftn.sbnz.service;

import com.ftn.sbnz.ServiceApplication;
import com.ftn.sbnz.model.dtos.ExaminationDTO;
import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ExaminationService {

    @Autowired
    private KieContainer kieContainer;

    public Examination addExamination(Baby baby, ExaminationDTO examinationDTO) {
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

//        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");

        InputStream template = ExaminationService.class.getResourceAsStream("/rules/template/symptom-template.drt");
        InputStream data = ExaminationService.class.getResourceAsStream("/rules/template/template.xls");

        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template, 3, 2);

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, org.kie.api.io.ResourceType.DRL);

        KieSession kieSession = kieHelper.build().newKieSession();

        kieSession.insert(baby);
        kieSession.insert(examination);
        kieSession.fireAllRules();
        kieSession.dispose();
        return examination;
    }
    public void addVaccination(Baby baby, Examination examination){
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");

        kieSession.insert(baby);
        kieSession.insert(examination);
        kieSession.fireAllRules();
        kieSession.dispose();

        repository.save(examination);
    }
}
