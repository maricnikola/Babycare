package com.ftn.sbnz.service;

import com.ftn.sbnz.model.dtos.MonitoringData;
import com.ftn.sbnz.model.events.*;
import com.ftn.sbnz.model.events.enums.TemperatureLevel;
import com.ftn.sbnz.model.models.Factual;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import com.ftn.sbnz.repository.MonitoringDataLoader;
import org.drools.core.event.DefaultAgendaEventListener;
import org.drools.core.event.DefaultRuleRuntimeEventListener;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
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
        Thread producer = new Thread(() -> {
            for (MonitoringData data : monitoringData) {
                VitalSignsEvent event = new VitalSignsEvent(
                        data.getHeartRate(),
                        data.getRespirationRate(),
                        data.isOxygen(),
                        System.currentTimeMillis()
                );

                kSession.insert(event);

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.setDaemon(true);
        producer.start();
        kSession.fireUntilHalt();
    }

    private void attachWebSocket(KieSession kieSession) {
        kieSession.addEventListener(new DefaultRuleRuntimeEventListener() {
            @Override
            public void objectInserted(ObjectInsertedEvent event) {
                Object fact = event.getObject();
                if (fact instanceof AlarmEvent) {
                    String msg = ((AlarmEvent) fact).getMessage();
                    webSocketService.sendToTopic("/topic/alarm", msg);
                } else if (fact instanceof WarningEvent) {
                    String msg = ((WarningEvent) fact).getMessage();
                    webSocketService.sendToTopic("/topic/warning", msg);
                }
            }
        });
    }

}
