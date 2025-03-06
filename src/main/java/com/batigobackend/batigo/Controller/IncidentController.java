package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Incidents;
import com.batigobackend.batigo.Service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidents")

public class IncidentController {

    private final IncidentService incidentService;

    @Autowired
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // 🔹 Récupérer tous les incidents
    @GetMapping
    public ResponseEntity<List<Incidents>> getAllIncidents() {
        List<Incidents> incidents = incidentService.findAll();
        return ResponseEntity.ok(incidents);
    }

    // 🔹 Récupérer un incident par ID
    @GetMapping("/{id}")
    public ResponseEntity<Incidents> getIncidentById(@PathVariable int id) {
        Incidents incident = incidentService.findById(id);
        return (incident != null) ? ResponseEntity.ok(incident) : ResponseEntity.notFound().build();
    }

    // 🔹 Ajouter un nouvel incident
    @PostMapping
    public ResponseEntity<Incidents> createIncident(@RequestBody Incidents incident) {
        Incidents newIncident = incidentService.add(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(newIncident);
    }

    // 🔹 Modifier un incident par ID
    @PutMapping("/{id}")
    public ResponseEntity<Incidents> updateIncident(@PathVariable int id, @RequestBody Incidents incident) {
        try {
            Incidents updatedIncident = incidentService.edit(id, incident);
            return ResponseEntity.ok(updatedIncident);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 🔹 Supprimer un incident par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable int id) {
        incidentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
