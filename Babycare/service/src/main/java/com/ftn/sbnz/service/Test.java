package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.model.models.Location;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.model.models.enums.Disease;
import com.ftn.sbnz.model.models.enums.Gender;
import com.ftn.sbnz.model.models.enums.SymptomName;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(){
        try{
            KieContainer kc = KnowledgeSessionHelper.createRuleBase();
            KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "bwKsession");
            kSession.insert( new Location(SymptomName.PALE_SKIN, SymptomName.DYSPNEA) );
            kSession.insert( new Location(SymptomName.FATIGUE, SymptomName.PALE_SKIN ) );
            kSession.insert( new Location(SymptomName.TACHYCARDIA, SymptomName.FATIGUE ) );

            kSession.insert( new Location(SymptomName.HIGH_FEVER, SymptomName.DYSPNEA ) );
            kSession.insert( new Location(SymptomName.MILD_FEVER, SymptomName.DYSPNEA ) );
            kSession.insert( new Location(SymptomName.MODERATE_FEVER, SymptomName.DYSPNEA ) );

            kSession.insert( new Location(SymptomName.COUGH,SymptomName.HIGH_FEVER ) );
            kSession.insert( new Location(SymptomName.COUGH,SymptomName.MILD_FEVER ) );
            kSession.insert( new Location(SymptomName.COUGH,SymptomName.MODERATE_FEVER ) );

            kSession.insert( new Location(SymptomName.WHEEZING, SymptomName.COUGH ) );
            kSession.insert( new Location(SymptomName.PROLONGED_EXPIRATION, SymptomName.WHEEZING ) );

            kSession.insert( new Location(SymptomName.CHEST_PAIN, SymptomName.COUGH ) );

            System.out.println("Hello World");

            Examination examination = new Examination();
            examination.getSymptoms().add(new Symptom(1L,SymptomName.FATIGUE,1L,1));
            examination.getSymptoms().add(new Symptom(1L,SymptomName.DYSPNEA,1L,1));
            examination.getSymptoms().add(new Symptom(1L,SymptomName.PALE_SKIN,1L,1));
            examination.getSymptoms().add(new Symptom(1L,SymptomName.TACHYCARDIA,1L,1));
            kSession.insert(examination);

            boolean bronhiolitis = kSession.getQueryResults("Bronhiolitis", examination)
                    .iterator()
                    .hasNext();
            if (bronhiolitis) {
                System.out.println("Bronhiolitis found");
            } else {
                System.out.println("Bronhiolitis not found");
            }

            boolean asthma = kSession.getQueryResults("Asthma", examination)
                    .iterator()
                    .hasNext();
            if (asthma) {
                System.out.println("Asthma found");
            } else {
                System.out.println("Asthma not found");
            }

            boolean pneumonia = kSession.getQueryResults("Pneumonia", examination)
                    .iterator()
                    .hasNext();
            if (pneumonia) {
                System.out.println("Pneumonia found");
            } else {
                System.out.println("Pneumonia not found");
            }

            boolean anemia = kSession.getQueryResults("Anemia", examination)
                    .iterator()
                    .hasNext();
            if (anemia) {
                System.out.println("Anemia found");
            } else {
                System.out.println("Anemia not found");
            }



            kSession.fireAllRules();

        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
