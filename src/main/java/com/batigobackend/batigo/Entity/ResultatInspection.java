package com.batigobackend.batigo.Entity;

public enum ResultatInspection {
    EN_COURS_INSPECTION("En cours d'inspection"),
    NON_INSPECTE("Non inspecté"),
    INSPECTION_RESOLUE("Inspection résolue");

    private final String label;

    ResultatInspection(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
