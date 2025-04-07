package com.batigobackend.batigo.Service;



import com.batigobackend.batigo.Entity.CategorieProduit;

import java.util.List;
 public interface CategorieProduitService {
    CategorieProduit save(CategorieProduit categorieProduit);

    List<CategorieProduit> findAll();

    CategorieProduit findById(Integer id);

    CategorieProduit update(Integer id, CategorieProduit categorieProduit);

    void delete(Integer id);

    String getMostPopularCategory();
    Long getCategoriesWithNoProducts();
    Long getTotalNumberOfProductsPerCategory(Integer categoryId);
    Long getTotalNumberOfCategories();
 }
