package com.ftn.sbnz.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoringData {
    private int heartRate;
    private int respirationRate;
    private boolean isOxygen;
}
