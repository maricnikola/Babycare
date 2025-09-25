package com.ftn.sbnz.model.models.enums;

import lombok.Getter;

@Getter
public enum Disease {
    ESOPHAGEAL_STENOSIS("Esophageal stenosis"),
    ESOPHAGEAL_ATRESIA("Esophageal atresia"),
    PROJECTILE_VOMITING("Projectile vomiting"),
    FAILURE_TO_GAIN_WEIGHT("Failure to gain weight"),
    PALLOR("Pallor"),
    BRONCHIOLITIS("Bronchiolitis"),
    PNEUMONIA("Pneumonia"),
    ASTHMA("Asthma"),
    ANEMIA("Anemia"),
    CONJUNCTIVITIS("Conjunctivitis"),
    PENFIGUS("Penfigus"),
    PHYSIOLOGICAL_JAUNDICE("Physiological jaundice"),
    HEART_DEFECT("Heart defect"),
    URINARY_TRACT_INFECTION("Urinary tract infection");


    private final String name;
    Disease(String name) {
        this.name = name;
    }
}
