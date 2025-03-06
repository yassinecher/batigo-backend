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

    private String description;
    private LocalDate date;

    private String gravite; // Enum pour la gravité de l'incident

    private String etat; // Enum pour suivre l'état de l'incident

    private String responsable; // Nom du responsable de l'incident


    @OneToMany(cascade = CascadeType.ALL, mappedBy="incidents")
    @JsonIgnore
    private List<Inspections> inspectionsList;
}
