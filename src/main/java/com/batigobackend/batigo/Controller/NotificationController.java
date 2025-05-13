package com.batigobackend.batigo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @GetMapping
    public List<String> getAllNotifications() {
        return List.of(
                "Nouvel incident signalé à " + LocalDateTime.now(),
                "Inspection prévue demain",
                "Gravité élevée détectée dans un incident"
        );
    }
}