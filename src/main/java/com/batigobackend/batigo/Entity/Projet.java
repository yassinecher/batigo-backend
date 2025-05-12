package com.batigobackend.batigo.Entity;

import com.batigobackend.batigo.Model.FinancialData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private ProjetType projetType;

    private String adresse;

    @JsonIgnore
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)

    private List<Income> incomes;


    @JsonIgnore // Prevents serialization of the expenses list
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public  ProjetType getProjetType() {
        return projetType;
    }

    public void setProjetType(ProjetType projetType) {
        this.projetType = projetType;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<FinancialData> financialDataList;
    public List<FinancialData> getFinancialDataList() {
        return financialDataList;
    }

    public void setFinancialDataList(List<FinancialData> financialDataList) {
        this.financialDataList = financialDataList;
    }
}

