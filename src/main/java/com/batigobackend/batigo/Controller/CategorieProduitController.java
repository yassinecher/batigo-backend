package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.CategorieProduit;
import com.batigobackend.batigo.Service.CategorieProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategorieProduitController {

    @Autowired
    private CategorieProduitService service;

    @PostMapping
    public ResponseEntity<CategorieProduit> create(@RequestBody CategorieProduit categorieProduit) {
        return ResponseEntity.ok(service.save(categorieProduit));
    }

    @GetMapping
    public ResponseEntity<List<CategorieProduit>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieProduit> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieProduit> update(@PathVariable Integer id, @RequestBody CategorieProduit categorieProduit) {
        return ResponseEntity.ok(service.update(id, categorieProduit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
