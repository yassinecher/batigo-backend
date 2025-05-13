package com.batigobackend.batigo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

     private String nomP;

     private String description;

     private String reference;

    // Updated to use a proper Category entity
    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private CategorieProduit categorie;


    @Lob
    @Column(columnDefinition = "LONGTEXT") // for MySQL
    private String imageBase64;
    // New field for quantity
    @Positive(message = "La quantité doit être positive")
    private Integer quantity;

    // New field for price
     private Double price;
    @ManyToOne
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

}
