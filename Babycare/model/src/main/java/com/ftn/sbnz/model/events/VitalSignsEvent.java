package com.ftn.sbnz.model.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("4m")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSignsEvent {
    private int hr;
    private int spo2;
    private boolean onOxygenTherapy;
    private long timestamp;
}
