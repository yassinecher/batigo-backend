package com.batigobackend.batigo.event;

import com.batigobackend.batigo.Entity.Incidents;
import org.springframework.context.ApplicationEvent;

public class NewIncidentEvent extends ApplicationEvent {

    private final Incidents incident;

    public NewIncidentEvent(Object source, Incidents incident) {
        super(source);
        this.incident = incident;
    }

    public Incidents getIncident() {
        return incident;
    }
}
