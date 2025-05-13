package com.batigobackend.batigo.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idF;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 5, max = 100, message = "Le nom doit contenir entre 5 et 100 caractères")
    private String nom;

    @NotBlank(message = "L'adresse est obligatoire")
    @Size(min = 10, max = 500, message = "L'adresse doit contenir entre 10 et 500 caractères")
    private String adresse;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Size(min = 8, max = 8, message = "Le numéro de téléphone doit contenir 8 chiffres")
    private String tel;

    @NotBlank(message = "La matricule est obligatoire")
    @Size(min = 5, max = 500, message = "La matricule doit contenir entre 5 et 500 caractères")
    private String matricule;

    private Date dateCreation=new Date();

    @NotBlank(message = "Le statut est obligatoire")
    @Size(min = 2, max = 100, message = "Le statut doit contenir entre 2 et 100 caractères")
    private String statut;

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore

    private List<Produit> produits;
}
