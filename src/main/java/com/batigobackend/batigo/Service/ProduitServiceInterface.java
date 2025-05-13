package com.batigobackend.batigo.Service;


import com.batigobackend.batigo.Entity.Produit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduitServiceInterface {
    Produit saveProduit(Produit produit);
     Page<Produit> getProduits(int page, int size, String search);
     List<Produit>getAll();
    Produit getProduitById(Long id);
    Produit updateProduit(Long id, Produit produit);
    void deleteProduit(Long id);
    // Total number of products
     Long getTotalNumberOfProducts();

    // Total quantity of all products
    Long getTotalQuantityOfAllProducts() ;
    // Average product price
     Double getAverageProductPrice();

    // Total sales value (price * quantity)
     Double getTotalSalesValue();
}
