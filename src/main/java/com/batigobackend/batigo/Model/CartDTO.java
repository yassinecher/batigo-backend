package com.batigobackend.batigo.Model;

public class CartDTO {
    private Long id;
    private String projectName;
    private String combinedProductNames;  // To store the comma-separated product names
    private String description;
    public CartDTO(String projectName, String combinedProductNames) {
        this.projectName = projectName;
        this.combinedProductNames = combinedProductNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters and Setters
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCombinedProductNames() {
        return combinedProductNames;
    }

    public void setCombinedProductNames(String combinedProductNames) {
        this.combinedProductNames = combinedProductNames;
    }
}
