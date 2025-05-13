package com.batigobackend.batigo.event;

import com.batigobackend.batigo.Entity.Inspections;
import org.springframework.context.ApplicationEvent;

public class NewInspectionEvent extends ApplicationEvent {
    private final Inspections inspection;

    public NewInspectionEvent(Object source, Inspections inspection) {
        super(source);
        this.inspection = inspection;
    }

    public Inspections getInspection() {
        return inspection;
    }
}
