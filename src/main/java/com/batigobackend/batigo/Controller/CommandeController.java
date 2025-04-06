package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Commande;
import com.batigobackend.batigo.Service.ICommandeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Commande")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:53570"})
public class CommandeController {

    private final ICommandeService commandeService;

    @GetMapping("/Allcommande")
    public List<Commande> getCommandes() {
        return commandeService.retrieveAllCommandes();
    }

    @GetMapping("/getcommande/{commande-id}")
    public ResponseEntity<Commande> retrieveCommande(@PathVariable("commande-id") Long commandeId) {
        Commande commande = commandeService.retrieveCommande(commandeId);
        if (commande != null) {
            return ResponseEntity.ok(commande);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/addcommande")
    public ResponseEntity<Commande> addCommande(@RequestBody Commande c) {
        Commande createdCommande = commandeService.addCommande(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommande);
    }

    @PutMapping("/modifycommande")
    public ResponseEntity<Commande> modifyCommande(@RequestBody Commande c) {
        Commande updatedCommande = commandeService.modifyCommande(c);
        if (updatedCommande != null) {
            return ResponseEntity.ok(updatedCommande);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/removecommande/{commande-id}")
    public ResponseEntity<Void> removeCommande(@PathVariable("commande-id") Long commandeId) {
        commandeService.removeCommande(commandeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/generatePdf/{commande-id}")
    public ResponseEntity<byte[]> generateCommandePdf(@PathVariable("commande-id") Long commandeId) {
        byte[] pdfBytes = commandeService.generateCommandePdf(commandeId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "commande_" + commandeId + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    @GetMapping("/generateAllPdf")
    public ResponseEntity<byte[]> generateAllCommandesPdf() {
        byte[] pdfBytes = commandeService.generateAllCommandesPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "all_commandes.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCommandeCount() {
        Integer count = commandeService.getCommandeCount();
        return ResponseEntity.ok()
                .body(count);
    }
}