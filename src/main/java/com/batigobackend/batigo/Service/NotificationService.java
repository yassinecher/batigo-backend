package com.batigobackend.batigo.Service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendIncidentNotification(String message) {
        System.out.println("🔔 Notification - Incident : " + message);
    }

    public void sendInspectionNotification(String message) {
        System.out.println("🔔 Notification - Inspection : " + message);
    }
}