package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.model.models.enums.Disease;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisService {

    @Autowired
    private KieContainer kieContainer;

    public boolean checkDisease(Disease disease, Examination examination) {
        KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "bwKsession");
        return kSession.getQueryResults(disease.getName(), examination)
                .iterator()
                .hasNext();
    }

}
