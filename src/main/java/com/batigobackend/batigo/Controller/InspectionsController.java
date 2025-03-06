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

    // 🔹 Récupérer toutes les inspections
    @GetMapping
    public ResponseEntity<List<Inspections>> getAllInspections() {
        List<Inspections> inspections = inspectionService.findAll();
        return ResponseEntity.ok(inspections);
    }

    // 🔹 Récupérer une inspection par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Inspections> getInspectionById(@PathVariable int id) {
        Inspections inspection = inspectionService.findById(id);
        return (inspection != null) ? ResponseEntity.ok(inspection) : ResponseEntity.notFound().build();
    }

    @GetMapping("/incidents/{id}/inspections")
    public List<Inspections> getInspectionsByIncidentId(@PathVariable int id) {
        return inspectionService.findAllByIncidentId(id);
    }

    // 🔹 Ajouter une nouvelle inspection
    @PostMapping("/create/{id}")
        public ResponseEntity<Inspections> createInspection(@PathVariable int id, @RequestBody Inspections inspection) {
        Inspections createdInspection = inspectionService.add(inspection, id);
        return ResponseEntity.ok(createdInspection);
    }

    // 🔹 Modifier une inspection par son ID
    @PutMapping("/{id}")
    public ResponseEntity<Inspections> updateInspection(@PathVariable int id, @RequestBody Inspections inspection) {
        try {
            Inspections updatedInspection = inspectionService.edit(id, inspection);
            return (updatedInspection != null) ? ResponseEntity.ok(updatedInspection) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 🔹 Supprimer une inspection par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable int id) {
        inspectionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
