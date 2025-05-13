package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Projet;
import com.batigobackend.batigo.Model.ProjetDTO;
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
    public ResponseEntity<List<ProjetDTO>> getAllProjects(
            @RequestParam(required = false) String etat,
            @RequestParam(required = false) String projetType,
            @RequestParam(required = false) Boolean archived) {
        return ResponseEntity.ok(projetService.getAllProjects(etat, projetType, archived));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDTO> getProjectById(@PathVariable Integer id) {
        return ResponseEntity.ok(projetService.getProjectById(id));
    }




    @PostMapping("/add")
    public Projet add(@RequestBody Projet projet) {

        return projetService.add(projet);
    }

    @PostMapping
    public Projet addp(@RequestBody Projet projet) {

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





}
