package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.model.models.Fact;
import com.ftn.sbnz.model.models.enums.Disease;
import com.ftn.sbnz.model.models.enums.SymptomName;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiagnosisService {

    @Autowired
    private KieContainer kieContainer;

    private static final List<Fact> KNOWLEDGE_GRAPH = List.of(

            new Fact(SymptomName.PALE_SKIN.name(), SymptomName.DYSPNEA.name()),
            new Fact(SymptomName.FATIGUE.name(), SymptomName.PALE_SKIN.name()),
            new Fact(SymptomName.TACHYCARDIA.name(), SymptomName.FATIGUE.name()),
            new Fact(Disease.ANEMIA.name(), SymptomName.TACHYCARDIA.name()),

            new Fact(SymptomName.HIGH_FEVER.name(), SymptomName.DYSPNEA.name()),
            new Fact(SymptomName.MILD_FEVER.name(), SymptomName.DYSPNEA.name()),
            new Fact(SymptomName.MODERATE_FEVER.name(), SymptomName.DYSPNEA.name()),
            new Fact(SymptomName.COUGH.name(), SymptomName.HIGH_FEVER.name()),
            new Fact(SymptomName.COUGH.name(), SymptomName.MILD_FEVER.name()),
            new Fact(SymptomName.COUGH.name(), SymptomName.MODERATE_FEVER.name()),
            new Fact(SymptomName.WHEEZING.name(), SymptomName.COUGH.name()),
            new Fact(SymptomName.CHEST_PAIN.name(), SymptomName.WHEEZING.name()),
            new Fact(Disease.BRONHIOLITIS.name(), SymptomName.CHEST_PAIN.name()),

            new Fact(SymptomName.PROLONGED_EXPIRATION.name(), SymptomName.WHEEZING.name()),
            new Fact(Disease.ASTHMA.name(), SymptomName.PROLONGED_EXPIRATION.name()),

            new Fact(SymptomName.CHEST_PAIN.name(), SymptomName.COUGH.name()),
            new Fact(Disease.PNEUMONIA.name(), SymptomName.CHEST_PAIN.name())
    );

    public boolean checkDisease(Disease disease, Examination examination) {
        KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "bwKsession");

        KNOWLEDGE_GRAPH.forEach(kSession::insert);

        return kSession.getQueryResults(disease.getName(), examination)
                .iterator()
                .hasNext();
    }

}
