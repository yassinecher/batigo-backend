package com.batigobackend.batigo.event;

import com.batigobackend.batigo.Entity.Incidents;
import com.batigobackend.batigo.Entity.Inspections;
import com.batigobackend.batigo.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener {
    private final NotificationService notificationService;

    @Autowired
    public NotificationEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void handleNewIncidentEvent(NewIncidentEvent event) {
        Incidents incident = event.getIncident();
        notificationService.sendIncidentNotification("Nouvel incident signalé : " + incident.getObjet());
    }

    @EventListener
    public void handleNewInspectionEvent(NewInspectionEvent event) {
        Inspections inspection = event.getInspection();
        notificationService.sendInspectionNotification("Nouvelle inspection prévue le : " + inspection.getDateInspection());
    }
}
