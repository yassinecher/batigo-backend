package com.batigobackend.batigo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inspections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Identifiant de l'inspection

    @Column(nullable = false)
    private String responsable;  // Responsable de l'inspection

    @Column(nullable = false)
    private String objet;  // Objet de l'inspection (ex: sécurité, qualité, etc.)

    @Column(nullable = false)
    private LocalDate dateInspection;  // Date de l'inspection

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResultatInspection resultat;

    @ManyToOne
    @JsonIgnore
    Incidents incidents;

}
