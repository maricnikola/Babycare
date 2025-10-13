package com.ftn.sbnz.model.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.time.LocalDateTime;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("2m")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarningEvent {
    private String message;
    private String category; // HR, SPO2, CARDIAC
    private long timestamp;
}
