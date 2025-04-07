package com.batigobackend.batigo.Repository;

 import com.batigobackend.batigo.Entity.Produit;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.stereotype.Repository;

 import java.util.List;

@Repository

public interface ProduitRepository extends JpaRepository<Produit, Long> {
 Page<Produit> findByNomPContainingIgnoreCaseOrReferenceContainingIgnoreCase(String nomP, String reference, Pageable pageable);
 // Calculate total quantity of all products
 @Query("SELECT SUM(p.quantity) FROM Produit p")
 Long sumQuantity();

 // Calculate average price of products
 @Query("SELECT AVG(p.price) FROM Produit p")
 Double averagePrice();

 // Calculate total sales value (price * quantity)
 @Query("SELECT SUM(p.price * p.quantity) FROM Produit p")
 Double sumSalesValue();
 List<Produit> findByQuantityLessThanEqual(int quantite);

}
