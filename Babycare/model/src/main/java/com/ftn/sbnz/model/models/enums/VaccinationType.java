package com.ftn.sbnz.model.models.enums;

import lombok.Getter;

@Getter
public enum VaccinationType {
    EUVAX_B("Hepatitis B"),
    BCG("Tuberkuloza"),
    PENTAXIM("DTP + Polio + Hib"),
    SYNFLORIX("Pneumokok");

    private final String name;
    VaccinationType(String name) {
        this.name = name;
    }
}
