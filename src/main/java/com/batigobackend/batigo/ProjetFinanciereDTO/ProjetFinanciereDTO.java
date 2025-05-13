package com.batigobackend.batigo.ProjetFinanciereDTO;

public class ProjetFinanciereDTO {

    private String typeProjet;
    private double budgetEstime;
    private int dureeEstimee;
    private int incidentQualite;
    private int incidentSecurite;
    private int materiauxDefectueux;
    private String conditionsMeteo;

    // Getters
    public String getTypeProjet() {
        return typeProjet;
    }

    public double getBudgetEstime() {
        return budgetEstime;
    }

    public int getDureeEstimee() {
        return dureeEstimee;
    }

    public int getIncidentQualite() {
        return incidentQualite;
    }

    public int getIncidentSecurite() {
        return incidentSecurite;
    }

    public int getMateriauxDefectueux() {
        return materiauxDefectueux;
    }

    public String getConditionsMeteo() {
        return conditionsMeteo;
    }

    // Setters
    public void setTypeProjet(String typeProjet) {
        this.typeProjet = typeProjet;
    }

    public void setBudgetEstime(double budgetEstime) {
        this.budgetEstime = budgetEstime;
    }

    public void setDureeEstimee(int dureeEstimee) {
        this.dureeEstimee = dureeEstimee;
    }

    public void setIncidentQualite(int incidentQualite) {
        this.incidentQualite = incidentQualite;
    }

    public void setIncidentSecurite(int incidentSecurite) {
        this.incidentSecurite = incidentSecurite;
    }

    public void setMateriauxDefectueux(int materiauxDefectueux) {
        this.materiauxDefectueux = materiauxDefectueux;
    }

    public void setConditionsMeteo(String conditionsMeteo) {
        this.conditionsMeteo = conditionsMeteo;
    }

}
