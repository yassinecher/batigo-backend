package com.batigobackend.batigo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter

public class Livrable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type;
    private String responsable; // Assigned user

    @Enumerated(EnumType.STRING)
    private Statut statut;

    private LocalDate dateRemisePrevue;
    private LocalDate dateRemiseReelle;
    private String commentaire;


    @Transient
    public boolean isOverdue() {
        if (dateRemisePrevue == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        // Debug: print out the values to help diagnose issues
        System.out.println("isOverdue() check - Today: " + today
                + ", Expected: " + dateRemisePrevue
                + ", Actual: " + dateRemiseReelle);

        if (dateRemiseReelle == null) {
            // Not delivered yet: overdue if today's date is after expected date
            return today.isAfter(dateRemisePrevue);
        } else {
            // Delivered: overdue if actual delivery date is after expected date
            return dateRemiseReelle.isAfter(dateRemisePrevue);
        }
    }


    // Optionally, add a getter to expose the computed property in JSON responses
    public boolean getOverdue() {
        return isOverdue();
    }


    @ManyToOne
    @JoinColumn(name = "projet_id")
    @JsonIgnoreProperties({"livrables"})
    private Projet projet;

    public Livrable() {
    }

    public Livrable(Long id, String nom, String type, String responsable, Statut statut, LocalDate dateRemisePrevue, LocalDate dateRemiseReelle, String commentaire, Projet projet) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.responsable = responsable;
        this.statut = statut;
        this.dateRemisePrevue = dateRemisePrevue;
        this.dateRemiseReelle = dateRemiseReelle;
        this.commentaire = commentaire;
        this.projet = projet;
    }

    public Livrable(String nom, String type, String responsable, Statut statut, LocalDate dateRemisePrevue, LocalDate dateRemiseReelle, String commentaire, Projet projet, Long id) {
        this.nom = nom;
        this.type = type;
        this.responsable = responsable;
        this.statut = statut;
        this.dateRemisePrevue = dateRemisePrevue;
        this.dateRemiseReelle = dateRemiseReelle;
        this.commentaire = commentaire;
        this.projet = projet;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public LocalDate getDateRemisePrevue() {
        return dateRemisePrevue;
    }

    public void setDateRemisePrevue(LocalDate dateRemisePrevue) {
        this.dateRemisePrevue = dateRemisePrevue;
    }

    public LocalDate getDateRemiseReelle() {
        return dateRemiseReelle;
    }

    public void setDateRemiseReelle(LocalDate dateRemiseReelle) {
        this.dateRemiseReelle = dateRemiseReelle;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}