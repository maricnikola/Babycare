package com.ftn.sbnz.service;

import com.ftn.sbnz.model.events.SymptomEvent;
import com.ftn.sbnz.model.events.TemperatureEvent;
import com.ftn.sbnz.model.events.VaccinationEvent;
import com.ftn.sbnz.model.events.VitalSignsEvent;
import com.ftn.sbnz.model.events.enums.SymptomType;
import com.ftn.sbnz.model.events.enums.TemperatureLevel;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(){
        try{
            KieContainer kc = KnowledgeSessionHelper.createRuleBase();
//            KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "bwKsession");
//
//            kSession.insert( new Fact(SymptomName.PALE_SKIN.name(), SymptomName.DYSPNEA.name()) );
//            kSession.insert( new Fact(SymptomName.FATIGUE.name(), SymptomName.PALE_SKIN.name()) );
//            kSession.insert( new Fact(SymptomName.TACHYCARDIA.name(), SymptomName.FATIGUE.name()) );
//            kSession.insert( new Fact(Disease.ANEMIA.name(), SymptomName.TACHYCARDIA.name()) );
//
//            kSession.insert( new Fact(SymptomName.HIGH_FEVER.name(), SymptomName.DYSPNEA.name()) );
//            kSession.insert( new Fact(SymptomName.MILD_FEVER.name(), SymptomName.DYSPNEA.name()) );
//            kSession.insert( new Fact(SymptomName.MODERATE_FEVER.name(), SymptomName.DYSPNEA.name()) );
//
//            kSession.insert( new Fact(SymptomName.COUGH.name(), SymptomName.HIGH_FEVER.name()) );
//            kSession.insert( new Fact(SymptomName.COUGH.name(), SymptomName.MILD_FEVER.name()) );
//            kSession.insert( new Fact(SymptomName.COUGH.name(), SymptomName.MODERATE_FEVER.name()) );
//
//            kSession.insert( new Fact(SymptomName.WHEEZING.name(), SymptomName.COUGH.name()) );
//
//            kSession.insert( new Fact(SymptomName.CHEST_PAIN.name(), SymptomName.WHEEZING.name()) );
//            kSession.insert( new Fact(Disease.BRONHIOLITIS.name(), SymptomName.CHEST_PAIN.name()) );
//
//            kSession.insert( new Fact(SymptomName.PROLONGED_EXPIRATION.name(), SymptomName.WHEEZING.name()) );
//            kSession.insert( new Fact(Disease.ASTHMA.name(), SymptomName.PROLONGED_EXPIRATION.name()) );
//
//            kSession.insert( new Fact(SymptomName.CHEST_PAIN.name(), SymptomName.COUGH.name()) );
//            kSession.insert( new Fact(Disease.PNEUMONIA.name(), SymptomName.CHEST_PAIN.name()) );
//
//
//            Examination examination = new Examination();
//            examination.getSymptoms().add(new Symptom(1L,SymptomName.FATIGUE,1L,1));
//            examination.getSymptoms().add(new Symptom(1L,SymptomName.DYSPNEA,1L,1));
//            examination.getSymptoms().add(new Symptom(1L,SymptomName.PALE_SKIN,1L,1));
////            examination.getSymptoms().add(new Symptom(1L,SymptomName.TACHYCARDIA,1L,1));
//            kSession.insert(examination);
//
//            List<String> facts = examination.getSymptoms().
//                    stream().map( s -> s.getName().name()).
//                    collect(Collectors.toList());
//
//            boolean bronhiolitis = kSession.getQueryResults(Disease.BRONHIOLITIS.getName(), facts)
//                    .iterator()
//                    .hasNext();
//            if (bronhiolitis) {
//                System.out.println("Bronhiolitis found");
//            } else {
//                System.out.println("Bronhiolitis not found");
//            }
//
//            boolean asthma = kSession.getQueryResults(Disease.ASTHMA.getName(), facts)
//                    .iterator()
//                    .hasNext();
//            if (asthma) {
//                System.out.println("Asthma found");
//            } else {
//                System.out.println("Asthma not found");
//            }
//
//            boolean pneumonia = kSession.getQueryResults(Disease.PNEUMONIA.getName(), facts)
//                    .iterator()
//                    .hasNext();
//            if (pneumonia) {
//                System.out.println("Pneumonia found");
//            } else {
//                System.out.println("Pneumonia not found");
//            }
//
//            boolean anemia = kSession.getQueryResults(Disease.ANEMIA.getName(), facts)
//                    .iterator()
//                    .hasNext();
//            if (anemia) {
//                System.out.println("Anemia found");
//            } else {
//                System.out.println("Anemia not found");
//            }
//
//            kSession.fireAllRules();
            KieSession k1Session = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc,"postVaccinationCepKsession");
            SessionPseudoClock pseudoClock = k1Session.getSessionClock();
            System.out.println("Hello world");
            k1Session.insert(new VaccinationEvent(pseudoClock.getCurrentTime()));
            pseudoClock.advanceTime(25, TimeUnit.HOURS);
//            k1Session.insert(new SymptomEvent(SymptomType.PAIN, pseudoClock.getCurrentTime()));
            k1Session.insert(new TemperatureEvent(TemperatureLevel.LOW,pseudoClock.getCurrentTime()));
            k1Session.fireAllRules();

        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
