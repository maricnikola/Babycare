package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.events.*;
import com.ftn.sbnz.model.events.enums.SymptomType;
import com.ftn.sbnz.service.SessionService;
import com.ftn.sbnz.service.WebSocketService;
import org.drools.core.event.DefaultRuleRuntimeEventListener;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/postVaccination")
public class PostVaccinationController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    WebSocketService  webSocketService;

    @PostMapping("/{babyId}")
    public void addSymptom(@RequestBody SymptomType symptomType,
                           @PathVariable Long babyId) {
        KieSession session = sessionService.getSession(babyId);
        if (session != null) {
            attachWebSocket(session);
            session.insert(new SymptomEvent(symptomType,System.currentTimeMillis()));
            session.fireAllRules();
        }else{
            session = sessionService.createSession(babyId,"postVaccinationCepKsession");
            attachWebSocket(session);
            session.insert(new VaccinationEvent(System.currentTimeMillis()));
            session.insert(new SymptomEvent(symptomType,System.currentTimeMillis()));
            session.fireAllRules();
        }
        System.out.println("Symptom for baby " + babyId + ": " + symptomType);
    }


    private void attachWebSocket(KieSession kieSession) {
        kieSession.addEventListener(new DefaultRuleRuntimeEventListener() {
            @Override
            public void objectInserted(ObjectInsertedEvent event) {
                Object fact = event.getObject();
                if (fact instanceof HospitalizationEvent) {
                    String msg = ((HospitalizationEvent) fact).getReason().name();
                    webSocketService.sendToTopic("/topic/alarm", msg);
                } else if (fact instanceof TherapyEvent) {
                    String msg = ((TherapyEvent) fact).getTherapy().name();
                    webSocketService.sendToTopic("/topic/therapy", msg);
                }
                System.out.println(fact.toString());
            }
        });
    }
}
