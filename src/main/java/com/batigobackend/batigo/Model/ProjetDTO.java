package com.batigobackend.batigo.Model;

import com.batigobackend.batigo.Entity.Etat;
import com.batigobackend.batigo.Entity.Livrable;
import com.batigobackend.batigo.Entity.ProjetType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProjetDTO {
    private Long id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFinPrevue;
    private LocalDate dateFinReelle;
    private BigDecimal budget;
    private LocalDateTime lastUpdated;
    private String scheduleStatus;
    private Etat etat;
    private String responsable;
    private boolean archived;
    private boolean approved;
    private int progress;
    private int expectedProgress;
    private boolean terminate;
    private ProjetType projetType;
    private String adresse;
    private List<Livrable> livrables;
    private List<CartDTO> carts;
    private BigDecimal totalExpenses;
    private BigDecimal totalIncomes;

    public ProjetDTO() {}
}