package com.batigobackend.batigo.Repository;

 import com.batigobackend.batigo.Entity.CategorieProduit;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.stereotype.Repository;

@Repository
public interface CategorieProduitRepository extends JpaRepository<CategorieProduit, Integer> {
 // Calculate total number of products in a category
 @Query("SELECT COUNT(p) FROM Produit p WHERE p.categorie.id = ?1")
 Long countProductsInCategory(Integer categoryId);

 // Count categories with no products
 @Query("SELECT COUNT(c) FROM CategorieProduit c WHERE SIZE(c.produits) = 0")
 Long countCategoriesWithNoProducts();

 // Find most popular category based on the number of products
 @Query("SELECT c.nom FROM CategorieProduit c WHERE SIZE(c.produits) = (SELECT MAX(SIZE(c.produits)) FROM CategorieProduit c)")
 String findMostPopularCategory();
}