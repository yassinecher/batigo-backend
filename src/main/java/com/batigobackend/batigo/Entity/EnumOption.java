package com.batigobackend.batigo.Entity;

public class EnumOption {
    private String value;
    private String label;

    public EnumOption(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
