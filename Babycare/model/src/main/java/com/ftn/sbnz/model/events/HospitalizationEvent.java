package com.ftn.sbnz.model.events;

import com.ftn.sbnz.model.events.enums.HospitalizationReason;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("7d")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalizationEvent {
    private HospitalizationReason reason;
    private long timestamp;

}