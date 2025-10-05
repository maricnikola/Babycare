package com.ftn.sbnz.service;

import com.ftn.sbnz.model.dtos.MonitoringData;
import com.ftn.sbnz.model.events.*;
import com.ftn.sbnz.model.events.enums.TemperatureLevel;
import com.ftn.sbnz.model.models.Factual;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import com.ftn.sbnz.repository.MonitoringDataLoader;
import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MonitoringService {

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private KieContainer kieContainer;


    public void loadDataAsync(String filePath) {
        List<MonitoringData> monitoringData = MonitoringDataLoader.loadFromCSV(filePath);
        KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer,"vitalSingsCepKsession");
        attachWebSocket(kSession);
        SessionPseudoClock pseudoClock = kSession.getSessionClock();
        kSession.setGlobal("clock", pseudoClock);
        for (MonitoringData data : monitoringData) {
            kSession.insert(new VitalSignsEvent(data.getHeartRate(),data.getRespirationRate(), data.isOxygen(), pseudoClock.getCurrentTime()));
            pseudoClock.advanceTime(4, TimeUnit.SECONDS);
            kSession.fireAllRules();
        }
    }

    private void attachWebSocket(KieSession kieSession) {
        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                String rule = event.getMatch().getRule().getName();

                // Hvata AlarmEvent
                List<Object> alarmFacts = event.getMatch().getObjects().stream()
                        .filter(f -> f instanceof AlarmEvent)
                        .collect(Collectors.toList());

                for (Object fact : alarmFacts) {
                    AlarmEvent alarmEvent = (AlarmEvent) fact;
                    System.out.println(alarmEvent);
                    // Opciono: webSocketService.sendToTopic("/topic/alarms", alarmEvent);
                }

                // Hvata WarningEvent
                List<Object> warningFacts = event.getMatch().getObjects().stream()
                        .filter(f -> f instanceof WarningEvent)
                        .collect(Collectors.toList());

                for (Object fact : warningFacts) {
                    WarningEvent warningEvent = (WarningEvent) fact;
                    System.out.println(warningEvent);
                    // Opciono: webSocketService.sendToTopic("/topic/warnings", warningEvent);
                }

                System.out.println("-----------------------------\n\n\n");
            }
        });
    }

}
