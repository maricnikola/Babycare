package com.ftn.sbnz.model.events;

import com.ftn.sbnz.model.events.enums.SymptomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("48h")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SymptomEvent {
    private SymptomType type;
    private long timestamp;
}
