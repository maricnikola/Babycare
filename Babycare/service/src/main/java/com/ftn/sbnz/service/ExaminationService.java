package com.ftn.sbnz.service;

import com.ftn.sbnz.model.dtos.ExaminationDTO;
import com.ftn.sbnz.model.dtos.FiredRuleDTO;
import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.model.models.enums.SymptomName;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import com.ftn.sbnz.repository.IBabyRepository;
import com.ftn.sbnz.repository.IExaminationRepository;
import org.drools.core.event.DefaultAgendaEventListener;
import org.drools.core.event.DefaultRuleRuntimeEventListener;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
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
    @Autowired
    private IBabyRepository babyRepository;
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
        examination.setErythrocytes(examinationDTO.getErythrocytes());
        examination.setCrp(examinationDTO.getCrp());
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
        attachDiagnosisWebSocket(kieSession);
        kieSession.insert(examination);
        kieSession.insert(baby);
        kieSession.fireAllRules();
        kieSession.dispose();
        repository.save(examination);
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
                Vaccination vaccination = event.getMatch().getObjects().stream()
                        .filter(Vaccination.class::isInstance)
                        .map(Vaccination.class::cast)
                        .findFirst()
                        .orElse(null);
                Therapy therapy = event.getMatch().getObjects().stream()
                        .filter(Therapy.class::isInstance)
                        .map(Therapy.class::cast)
                        .findFirst()
                        .orElse(null);
//                if(vaccination != null) webSocketService.sendToTopic("/topic/rules/vaccination", vaccination);
//                if(therapy != null) webSocketService.sendToTopic("/topic/rules/therapy", therapy);
            }
        });
    }
    private void attachDiagnosisWebSocket(KieSession kieSession){
        kieSession.addEventListener(new DefaultRuleRuntimeEventListener() {
            @Override
            public void objectInserted(ObjectInsertedEvent event) {
                Object fact = event.getObject();
                if (fact instanceof Diagnosis) {
                    Diagnosis diagnosis = (Diagnosis) fact;
                    if(diagnosis != null) webSocketService.sendToTopic("/topic/rules/diagnosis", diagnosis);
                }else if(fact instanceof Vaccination){
                    Vaccination vaccination = (Vaccination) fact;
                    if(vaccination != null) webSocketService.sendToTopic("/topic/rules/vaccination", vaccination);
                }else if(fact instanceof  Treatment){
                    Treatment treatment = (Treatment) fact;
                    if(treatment != null) webSocketService.sendToTopic("/topic/rules/treatment", treatment);
                }
            }
        });
    }


}
