package com.batigobackend.batigo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter

public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFinPrevue;
    private LocalDate dateFinReelle;
    private BigDecimal budget;
    private LocalDateTime lastUpdated; // ✅ Track the last time progress was updated

    @Transient
    private String scheduleStatus; // ✅ Used for real-time schedule analysis
    @Enumerated(EnumType.STRING)
    private Etat etat;

    private String responsable;
    private boolean archived = false;
    private boolean approved = false;

    private int progress = 0; // ✅ Automatically calculated based on budget & duration
    private boolean terminate= false; // ✅ Flag to indicate if the project is terminated
    private int expectedProgress = 0;
    @JsonIgnore
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livrable> livrables = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProjetType projetType;

    private String adresse;

    @JsonIgnore
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)

    private List<Income> incomes;


    @JsonIgnore // Prevents serialization of the expenses list
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Cart> carts;

    public Projet() {
    }

    public Projet(String nom, String description, LocalDate dateDebut, LocalDate dateFinPrevue, LocalDate dateFinReelle, BigDecimal budget, LocalDateTime lastUpdated, String scheduleStatus, Etat etat, String responsable, boolean archived, boolean approved, int progress, int expectedProgress, List<Livrable> livrables, boolean terminate) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFinPrevue = dateFinPrevue;
        this.dateFinReelle = dateFinReelle;
        this.budget = budget;
        this.lastUpdated = lastUpdated;
        this.scheduleStatus = scheduleStatus;
        this.etat = etat;
        this.responsable = responsable;
        this.archived = archived;
        this.approved = approved;
        this.progress = progress;
        this.expectedProgress = expectedProgress;
        this.livrables = livrables;
        this.terminate = terminate;
    }

    public Projet(Long id, String nom, String description, LocalDate dateDebut, LocalDate dateFinPrevue, LocalDate dateFinReelle, BigDecimal budget, LocalDateTime lastUpdated, String scheduleStatus, Etat etat, String responsable, boolean archived, boolean approved, int progress, int expectedProgress, List<Livrable> livrables, boolean terminate) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFinPrevue = dateFinPrevue;
        this.dateFinReelle = dateFinReelle;
        this.budget = budget;
        this.lastUpdated = lastUpdated;
        this.scheduleStatus = scheduleStatus;
        this.etat = etat;
        this.responsable = responsable;
        this.archived = archived;
        this.approved = approved;
        this.progress = progress;
        this.expectedProgress = expectedProgress;
        this.livrables = livrables;
        this.terminate = terminate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFinPrevue() {
        return dateFinPrevue;
    }

    public void setDateFinPrevue(LocalDate dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }

    public LocalDate getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(LocalDate dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getExpectedProgress() {
        return expectedProgress;
    }

    public void setExpectedProgress(int expectedProgress) {
        this.expectedProgress = expectedProgress;
    }

    public List<Livrable> getLivrables() {
        return livrables;
    }



    public void setLivrables(List<Livrable> livrables) {
        this.livrables = livrables;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
}
