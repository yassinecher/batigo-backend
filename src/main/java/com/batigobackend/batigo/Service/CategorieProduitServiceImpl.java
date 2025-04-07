package com.batigobackend.batigo.Service;


import com.batigobackend.batigo.Entity.CategorieProduit;
import com.batigobackend.batigo.Repository.CategorieProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategorieProduitServiceImpl implements CategorieProduitService {

    @Autowired
    private CategorieProduitRepository repository;

    @Override
    public CategorieProduit save(CategorieProduit categorieProduit) {
        return repository.save(categorieProduit);
    }

    @Override
    public List<CategorieProduit> findAll() {
        return repository.findAll();
    }

    @Override
    public CategorieProduit findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
    }

    @Override
    public CategorieProduit update(Integer id, CategorieProduit updatedCategorie) {
        CategorieProduit existing = findById(id);
        existing.setNom(updatedCategorie.getNom());
        return repository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Long getTotalNumberOfCategories() {
        return repository.count();
    }

    @Override
    public Long getTotalNumberOfProductsPerCategory(Integer categoryId) {
        return repository.countProductsInCategory(categoryId);
    }

    @Override
    public Long getCategoriesWithNoProducts() {
        return repository.countCategoriesWithNoProducts();
    }

    @Override
    public String getMostPopularCategory() {
        return repository.findMostPopularCategory();
    }
}
