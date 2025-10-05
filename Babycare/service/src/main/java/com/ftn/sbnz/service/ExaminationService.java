package com.ftn.sbnz.service;

import com.ftn.sbnz.model.dtos.ExaminationDTO;
import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.model.models.Factual;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.model.models.enums.SymptomName;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import com.ftn.sbnz.repository.IExaminationRepository;
import org.drools.core.event.DefaultAgendaEventListener;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExaminationService {

    @Autowired
    private KieContainer kieContainer;
    @Inject
    private IExaminationRepository repository;
    @Autowired
    private WebSocketService webSocketService;

    public Examination addExamination(Baby baby, ExaminationDTO examinationDTO) throws IOException {
        Examination examination = new Examination();
        examination.setExamDate(examinationDTO.getExamDate());
        examination.setHeight(examinationDTO.getHeight());
        examination.setWeight(examinationDTO.getWeight());
        examination.setTemperature(examinationDTO.getTemperature());
        examination.setHeartRate(examinationDTO.getHeartRate());
        examination.setRespirationRate(examinationDTO.getRespirationRate());
        List<Symptom> symptoms = mapToSymptoms(examinationDTO.getSymptoms());
        examination.setSymptoms(symptoms);
        examination.setReports(new ArrayList<>());
        examination.setExamDate(LocalDate.now());
        examination.setBaby(baby);

        baby.getExaminations().add(examination);

        InputStream template = ExaminationService.class.getResourceAsStream("/rules/template/symptom-template.drt");
        InputStream data = ExaminationService.class.getResourceAsStream("/rules/template/template.xls");

        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drlFromTemplate = converter.compile(data, template, 3, 2);

        InputStream staticRules = ExaminationService.class.getResourceAsStream("/rules/test/test.drl");
        String drlStatic = new String(staticRules.readAllBytes(), StandardCharsets.UTF_8);

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drlFromTemplate, org.kie.api.io.ResourceType.DRL);
        kieHelper.addContent(drlStatic, org.kie.api.io.ResourceType.DRL);

        KieSession kieSession = kieHelper.build().newKieSession();

        attachWebSocket(kieSession);
        kieSession.insert(baby);
        kieSession.insert(examination);
        kieSession.fireAllRules();
        kieSession.dispose();
        repository.save(examination);
        webSocketService.sendToTopic("/topic/rules", "RADIII");
        return examination;
    }

    public void addVaccination(Baby baby, Examination examination){
        KieSession kieSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        kieSession.insert(baby);
        kieSession.insert(examination);
        kieSession.fireAllRules();
        kieSession.dispose();

    }

    public static List<Symptom> mapToSymptoms(List<SymptomName> symptomNames) {
        if (symptomNames == null || symptomNames.isEmpty()) {
            return List.of();
        }
        return symptomNames.stream()
                .map(name -> {Symptom symptom = new Symptom(); symptom.setName(name); symptom.setWeight(4L); symptom.setDurationDays(0);
                    return symptom;
                })
                .collect(Collectors.toList());
    }

    public Examination findLastExaminationForBaby(Long babyId) {
        return repository.findTopByBabyIdOrderByExamDateDesc(babyId);
    }

    private void attachWebSocket(KieSession kieSession) {
        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                String rule = event.getMatch().getRule().getName();
                List<Object> facts = event.getMatch().getObjects().stream()
                        .filter(f -> f instanceof Factual)
                        .collect(Collectors.toList());
            }
        });
    }
}
