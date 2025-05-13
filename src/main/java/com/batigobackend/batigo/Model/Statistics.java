package com.batigobackend.batigo.Model;

public class Statistics {

    private double totalIncomes;
    private double totalExpenses;
    private double balance;

    public Statistics(double totalIncomes, double totalExpenses, double balance) {
        this.totalIncomes = totalIncomes;
        this.totalExpenses = totalExpenses;
        this.balance = balance;
    }

    // Getters et Setters
    public double getTotalIncomes() {
        return totalIncomes;
    }

    public void setTotalIncomes(double totalIncomes) {
        this.totalIncomes = totalIncomes;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

