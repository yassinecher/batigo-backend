package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Inspections;
import com.batigobackend.batigo.Service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inspections")
public class InspectionsController {

    private final InspectionService inspectionService;

    @Autowired
    public InspectionsController(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    @GetMapping
    public ResponseEntity<List<Inspections>> getAllInspections() {
        List<Inspections> inspections = inspectionService.findAll();
        return ResponseEntity.ok(inspections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inspections> getInspectionById(@PathVariable int id) {
        Inspections inspection = inspectionService.findById(id);
        return (inspection != null) ? ResponseEntity.ok(inspection) : ResponseEntity.notFound().build();
    }

    @GetMapping("/incidents/{id}/inspections")
    public List<Inspections> getInspectionsByIncidentId(@PathVariable int id) {
        return inspectionService.findAllByIncidentId(id);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Inspections> createInspection(@PathVariable int id, @RequestBody Inspections inspection) {
        Inspections createdInspection = inspectionService.add(inspection, id);
        return ResponseEntity.ok(createdInspection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inspections> updateInspection(@PathVariable int id, @RequestBody Inspections inspection) {
        System.out.println("===== [PUT /inspections/" + id + "] =====");
        System.out.println("🔹 Données reçues :");
        System.out.println("ID (body) : " + inspection.getId());
        System.out.println("Responsable : " + inspection.getResponsable());
        System.out.println("Objet : " + inspection.getObjet());
        System.out.println("Date : " + inspection.getDateInspection());
        System.out.println("Résultat : " + inspection.getResultat());
        System.out.println("Incident ID : " + (inspection.getIncidents() != null ? inspection.getIncidents().getId() : "null"));

        try {
            Inspections updatedInspection = inspectionService.edit(id, inspection);
            return (updatedInspection != null) ? ResponseEntity.ok(updatedInspection) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable int id) {
        inspectionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}