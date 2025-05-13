package com.batigobackend.batigo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Incidents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String objet; // ✅ Ajouté

    private String description;
    private LocalDate date;
    private String gravite;
    private String etat;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="incidents")
    @JsonIgnore
    private List<Inspections> inspectionsList;

    @OneToOne(mappedBy = "incident", cascade = CascadeType.ALL)
    private User user;
}