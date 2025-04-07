package com.batigobackend.batigo.Service;

 import com.batigobackend.batigo.Entity.Produit;
 import com.batigobackend.batigo.Repository.ProduitRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Pageable;
 import org.springframework.stereotype.Service;
 import java.util.List;

@Service
 public class ProduitService implements ProduitServiceInterface {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Page<Produit> getProduits(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);

        if (search != null && !search.isEmpty()) {
            return produitRepository.findByNomPContainingIgnoreCaseOrReferenceContainingIgnoreCase(search, search, pageable);
        }

        return produitRepository.findAll(pageable);
    }

    @Override
    public List<Produit> getAll() {
        return produitRepository.findAll();
    }

    @Override
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + id));
    }

    @Override
    public Produit updateProduit(Long id, Produit updatedProduit) {
        Produit existingProduit = getProduitById(id);

        existingProduit.setNomP(updatedProduit.getNomP());
        existingProduit.setDescription(updatedProduit.getDescription());
        existingProduit.setReference(updatedProduit.getReference());
        existingProduit.setCategorie(updatedProduit.getCategorie());
         existingProduit.setImageBase64(updatedProduit.getImageBase64());
        existingProduit.setPrice(updatedProduit.getPrice());
        existingProduit.setQuantity(updatedProduit.getQuantity());
        return produitRepository.save(existingProduit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    // Total number of products
    @Override

    public Long getTotalNumberOfProducts() {
        return produitRepository.count();
    }

    @Override
    public Long getTotalQuantityOfAllProducts() {
        return produitRepository.sumQuantity();
    }
    @Override
    public Double getAverageProductPrice() {
        return produitRepository.averagePrice();
    }
    @Override
    public Double getTotalSalesValue() {
        return produitRepository.sumSalesValue();
    }
}
