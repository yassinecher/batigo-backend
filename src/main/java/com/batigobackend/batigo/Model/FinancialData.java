package com.batigobackend.batigo.Model;

import com.batigobackend.batigo.Entity.Projet;
import jakarta.persistence.*;

@Entity
public class FinancialData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double income;
    private double expense;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "projet_id", referencedColumnName = "id", nullable = false)
    private Projet projet;
    public Long getProjetId() {
        return projet != null ? projet.getId() : null;
    }
    // Getters et setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getIncome() {
        return income;
    }
    public void setIncome(double income) {
        this.income = income;
    }
    public double getExpense() {
        return expense;
    }
    public void setExpense(double expense) {
        this.expense = expense;
    }
    public Projet getProjet() {
        return projet;
    }
    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
