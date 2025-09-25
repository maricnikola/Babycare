package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.model.models.Fact;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.model.models.enums.Disease;
import com.ftn.sbnz.model.models.enums.SymptomName;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(){
        try{
            KieContainer kc = KnowledgeSessionHelper.createRuleBase();
            KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "bwKsession");

            kSession.insert( new Fact(SymptomName.PALE_SKIN.name(), SymptomName.DYSPNEA.name()) );
            kSession.insert( new Fact(SymptomName.FATIGUE.name(), SymptomName.PALE_SKIN.name()) );
            kSession.insert( new Fact(SymptomName.TACHYCARDIA.name(), SymptomName.FATIGUE.name()) );
            kSession.insert( new Fact(Disease.ANEMIA.name(), SymptomName.TACHYCARDIA.name()) );

            kSession.insert( new Fact(SymptomName.HIGH_FEVER.name(), SymptomName.DYSPNEA.name()) );
            kSession.insert( new Fact(SymptomName.MILD_FEVER.name(), SymptomName.DYSPNEA.name()) );
            kSession.insert( new Fact(SymptomName.MODERATE_FEVER.name(), SymptomName.DYSPNEA.name()) );

            kSession.insert( new Fact(SymptomName.COUGH.name(), SymptomName.HIGH_FEVER.name()) );
            kSession.insert( new Fact(SymptomName.COUGH.name(), SymptomName.MILD_FEVER.name()) );
            kSession.insert( new Fact(SymptomName.COUGH.name(), SymptomName.MODERATE_FEVER.name()) );

            kSession.insert( new Fact(SymptomName.WHEEZING.name(), SymptomName.COUGH.name()) );

            kSession.insert( new Fact(SymptomName.CHEST_PAIN.name(), SymptomName.WHEEZING.name()) );
            kSession.insert( new Fact(Disease.BRONHIOLITIS.name(), SymptomName.CHEST_PAIN.name()) );

            kSession.insert( new Fact(SymptomName.PROLONGED_EXPIRATION.name(), SymptomName.WHEEZING.name()) );
            kSession.insert( new Fact(Disease.ASTHMA.name(), SymptomName.PROLONGED_EXPIRATION.name()) );

            kSession.insert( new Fact(SymptomName.CHEST_PAIN.name(), SymptomName.COUGH.name()) );
            kSession.insert( new Fact(Disease.PNEUMONIA.name(), SymptomName.CHEST_PAIN.name()) );

            System.out.println("Hello World");

            Examination examination = new Examination();
            examination.getSymptoms().add(new Symptom(1L,SymptomName.FATIGUE,1L,1));
            examination.getSymptoms().add(new Symptom(1L,SymptomName.DYSPNEA,1L,1));
            examination.getSymptoms().add(new Symptom(1L,SymptomName.PALE_SKIN,1L,1));
            examination.getSymptoms().add(new Symptom(1L,SymptomName.TACHYCARDIA,1L,1));
            kSession.insert(examination);

            List<String> facts = examination.getSymptoms().
                    stream().map( s -> s.getName().name()).
                    collect(Collectors.toList());

//            boolean bronhiolitis = kSession.getQueryResults("Bronhiolitis", facts)
//                    .iterator()
//                    .hasNext();
//            if (bronhiolitis) {
//                System.out.println("Bronhiolitis found");
//            } else {
//                System.out.println("Bronhiolitis not found");
//            }
//
//            boolean asthma = kSession.getQueryResults("Asthma", facts)
//                    .iterator()
//                    .hasNext();
//            if (asthma) {
//                System.out.println("Asthma found");
//            } else {
//                System.out.println("Asthma not found");
//            }
//
//            boolean pneumonia = kSession.getQueryResults("Pneumonia", facts)
//                    .iterator()
//                    .hasNext();
//            if (pneumonia) {
//                System.out.println("Pneumonia found");
//            } else {
//                System.out.println("Pneumonia not found");
//            }
//
//            boolean anemia = kSession.getQueryResults("Anemia", facts)
//                    .iterator()
//                    .hasNext();
//            if (anemia) {
//                System.out.println("Anemia found");
//            } else {
//                System.out.println("Anemia not found");
//            }

            kSession.fireAllRules();

        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
