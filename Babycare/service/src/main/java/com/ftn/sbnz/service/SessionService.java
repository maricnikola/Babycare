package com.ftn.sbnz.service;

import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionService {
    @Autowired
    KieContainer kieContainer;

    private final Map<Long, KieSession> activeSessions = new ConcurrentHashMap<>();

    public KieSession createSession(Long babyId, String sessionName) {
        KieSession session = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer,sessionName);
        activeSessions.put(babyId, session);
        return session;
    }

    public KieSession getSession(Long babyId) {
        return activeSessions.get(babyId);
    }

    public void removeSession(Long babyId) {
        activeSessions.remove(babyId);
    }

    public boolean hasSession(Long babyId) {
        return activeSessions.containsKey(babyId);
    }


    public Map<Long, KieSession> getAllSessions() {
        return activeSessions;
    }
}
