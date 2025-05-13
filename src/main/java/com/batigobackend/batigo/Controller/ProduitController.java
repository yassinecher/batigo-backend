package com.batigobackend.batigo.Controller;


import com.batigobackend.batigo.Entity.CategorieProduit;
import com.batigobackend.batigo.Entity.Fournisseur;
import com.batigobackend.batigo.Entity.Produit;
import com.batigobackend.batigo.Model.ProduitRequestBody;
import com.batigobackend.batigo.Service.CategorieProduitService;
import com.batigobackend.batigo.Service.FournisseurServiceInterface;
import com.batigobackend.batigo.Service.ProduitServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "*")
public class ProduitController {

    @Autowired
    private ProduitServiceInterface produitService;
    @Autowired
    private CategorieProduitService categorieProduitService;

    @Autowired
    private FournisseurServiceInterface fournisseurService;



    // ✅ Create a new produit
    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody ProduitRequestBody produitRequestBody) {
        CategorieProduit cp=categorieProduitService.findById(produitRequestBody.getCategoryId());
        Fournisseur fournisseur=fournisseurService.getFournisseurById((long) produitRequestBody.getFournisseurId());

        Produit produit=produitRequestBody.getProduit();
        produit.setId(null);
        produit.setCategorie(cp);
        produit.setFournisseur(fournisseur);
        Produit savedProduit = produitService.saveProduit(produit);
        return ResponseEntity.ok(savedProduit);
    }

    // ✅ Get all produits
    @GetMapping
    public ResponseEntity<Page<Produit>> getProduits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String search
    ) {
        Page<Produit> produits = produitService.getProduits(page, size, search);
        return ResponseEntity.ok(produits);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Produit>> getProduitsWithoutPagination(

    ) {
        List<Produit> produits = produitService.getAll();
        return ResponseEntity.ok(produits);
    }

    // ✅ Get produit by ID
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Produit produit = produitService.getProduitById(id);
        return ResponseEntity.ok(produit);
    }

    // ✅ Update produit
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody ProduitRequestBody produitRequestBody) {
        CategorieProduit cp=categorieProduitService.findById(produitRequestBody.getCategoryId());
        Fournisseur fournisseur=fournisseurService.getFournisseurById((long) produitRequestBody.getFournisseurId());
        Produit produit=produitRequestBody.getProduit();
        produit.setCategorie(cp);
        produit.setFournisseur(fournisseur);
        Produit updatedProduit = produitService.updateProduit(id, produit);
        return ResponseEntity.ok(updatedProduit);
    }

    // ✅ Delete produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}
