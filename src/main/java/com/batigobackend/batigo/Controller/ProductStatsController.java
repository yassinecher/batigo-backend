package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Service.CategorieProduitService;
import com.batigobackend.batigo.Service.ProduitServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")

public class ProductStatsController {

    @Autowired
    private ProduitServiceInterface produitService;

    @Autowired
    private CategorieProduitService categorieProduitService;
    // Endpoint to get total number of products
    @GetMapping("/produits/total")
    public Long getTotalNumberOfProducts() {
        return produitService.getTotalNumberOfProducts();
    }

    // Endpoint to get total quantity of all products
    @GetMapping("/produits/total-quantity")
    public Long getTotalQuantityOfAllProducts() {
        return produitService.getTotalQuantityOfAllProducts();
    }

    // Endpoint to get average product price
    @GetMapping("/produits/average-price")
    public Double getAverageProductPrice() {
        return produitService.getAverageProductPrice();
    }

    // Endpoint to get total sales value (price * quantity)
    @GetMapping("/produits/total-sales")
    public Double getTotalSalesValue() {
        return produitService.getTotalSalesValue();
    }

    // Endpoint to get total number of categories
    @GetMapping("/stats/categories/total")
    public Long getTotalNumberOfCategories() {
        return categorieProduitService.getTotalNumberOfCategories();
    }

    // Endpoint to get total number of products in a category
    @GetMapping("/categories/total-products")
    public Long getTotalNumberOfProductsPerCategory(@RequestParam Integer categoryId) {
        return categorieProduitService.getTotalNumberOfProductsPerCategory(categoryId);
    }

    // Endpoint to get categories with no products
    @GetMapping("/categories/no-products")
    public Long getCategoriesWithNoProducts() {
        return categorieProduitService.getCategoriesWithNoProducts();
    }

    // Endpoint to get the most popular category (based on number of products)
    @GetMapping("/categories/most-popular")
    public ResponseEntity<Map<String, String>> getMostPopularCategory() {
        String popularCategory = categorieProduitService.getMostPopularCategory();
        Map<String, String> response = new HashMap<>();
        response.put("category", popularCategory);

        return ResponseEntity.ok(response);  // Return the response as JSON
    }
}
