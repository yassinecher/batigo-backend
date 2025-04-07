package com.batigobackend.batigo.Model;

import com.batigobackend.batigo.Entity.Produit;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 @NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProduitRequestBody {
    private Produit produit;
    private int categoryId;
    private int fournisseurId;
}
