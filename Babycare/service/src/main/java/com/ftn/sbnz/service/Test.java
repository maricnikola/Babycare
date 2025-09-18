package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.Baby;
import com.ftn.sbnz.model.models.Examination;
import com.ftn.sbnz.model.models.enums.Gender;
import com.ftn.sbnz.model.util.KnowledgeSessionHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(){
        try{
            KieContainer kc = KnowledgeSessionHelper.createRuleBase();
            KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "test-session");

            System.out.println("Hello World");

            Baby baby = new Baby(1L,"Nikola","Maric",LocalDate.now().minusDays(30L),
                    Gender.MALE,50.0,2.5,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());

            Examination examination = new Examination();
            examination.setExamDate(LocalDate.now());
            examination.setHeight(51.8);
            examination.setWeight(3.2);
            examination.setHeartRate(150);
            examination.setTemperature(36.5);
            examination.setRespirationRate(30);

            baby.getExaminations().add(examination);

            kSession.insert(baby);
            kSession.insert(examination);

            kSession.fireAllRules();

        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
