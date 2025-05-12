package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Projet;
import com.batigobackend.batigo.Repository.ProjetRepository;
import com.batigobackend.batigo.Service.ProjetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Projet")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjetController {



    private final ProjetService projetService;
    private final ProjetRepository projetRepository;

    public ProjetController(ProjetService projetService, ProjetRepository projetRepository) {
        this.projetService = projetService;
        this.projetRepository = projetRepository;
    }


    @GetMapping
    public List<Projet> index() {
        return projetService.findAll();
    }


    @PostMapping("/add")
    public Projet add(@RequestBody Projet projet) {

        return projetService.add(projet);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Projet> edit(@PathVariable int id, @RequestBody Projet projet) {
        Optional<Projet> existingProjet = projetRepository.findById(id);

        if (existingProjet.isPresent()) {
            Projet updatedProjet = projetService.edit(projet);
            return ResponseEntity.ok(updatedProjet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        projetService.delete(id);

    }


    @GetMapping("/{id}")
    public Projet show(@PathVariable int id) {
        return projetService.findById(id);
    }



}
