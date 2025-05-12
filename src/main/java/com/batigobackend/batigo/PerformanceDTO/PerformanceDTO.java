package com.batigobackend.batigo.PerformanceDTO;

import java.util.List;

public class PerformanceDTO {

    private String nomDuProjet;
    private List<Double> incomes;
    private List<Double> expenses;
    private double beneficeNet;
    private double scoreDePerformance;

    // Getters et setters
    public String getNomDuProjet() {
        return nomDuProjet;
    }
    public void setNomDuProjet(String nomDuProjet) {
        this.nomDuProjet = nomDuProjet;
    }
    public List<Double> getIncomes() {
        return incomes;
    }
    public void setIncomes(List<Double> incomes) {
        this.incomes = incomes;
    }
    public List<Double> getExpenses() {
        return expenses;
    }
    public void setExpenses(List<Double> expenses) {
        this.expenses = expenses;
    }
    public double getBeneficeNet() {
        return beneficeNet;
    }
    public void setBeneficeNet(double beneficeNet) {
        this.beneficeNet = beneficeNet;
    }
    public double getScoreDePerformance() {
        return scoreDePerformance;
    }
    public void setScoreDePerformance(double scoreDePerformance) {
        this.scoreDePerformance = scoreDePerformance;
    }
}
