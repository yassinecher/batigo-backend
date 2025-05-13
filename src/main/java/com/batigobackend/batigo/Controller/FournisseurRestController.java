package com.batigobackend.batigo.Controller;
 import com.batigobackend.batigo.Entity.Fournisseur;
 import com.batigobackend.batigo.Service.FournisseurServiceInterface;
  import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping({"/api/fournisseur"})
@CrossOrigin(origins = "*")

public class FournisseurRestController {
    @Autowired
    private FournisseurServiceInterface fournisseurService;


    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return this.fournisseurService.getAllFournisseurs();
    }
   @GetMapping({"/getFournisseurById/{idF}"})
    public Fournisseur getFournisseurById(@PathVariable("idF") Long idF) {
        return this.fournisseurService.getFournisseurById(idF);
    }
    @PutMapping({"/updateFournisseur"})
    public Fournisseur updateFournisseur(@RequestBody Fournisseur four) {
        return this.fournisseurService.updateFournisseur(four);
    }
    @DeleteMapping({"/deleteFournisseur/{idF}"})
    public void deleteFournisseur(@PathVariable("idF") Long idF) {
        this.fournisseurService.deleteFournisseur(idF);
    }

    @PostMapping("/add")
    public ResponseEntity<Fournisseur> addFournisseur( @RequestBody Fournisseur fournisseur) {
        Fournisseur savedFournisseur = fournisseurService.addFournisseur(fournisseur);
        return ResponseEntity.ok(savedFournisseur);
    }




}
