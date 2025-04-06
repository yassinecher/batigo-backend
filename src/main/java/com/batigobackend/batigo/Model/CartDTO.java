package com.batigobackend.batigo.Model;

public class CartDTO {
    private String projectName;
    private String combinedProductNames;  // To store the comma-separated product names

    public CartDTO(String projectName, String combinedProductNames) {
        this.projectName = projectName;
        this.combinedProductNames = combinedProductNames;
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
