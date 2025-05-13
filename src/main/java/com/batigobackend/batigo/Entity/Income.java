package com.batigobackend.batigo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Float amount ;
    private Date date;

    private String source;
    private Month month;


    @ManyToOne
    @JoinColumn(name = "projet_id") // Clé étrangère
    private Projet projet;

    public Projet getProjet() {
        return projet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public Month getMonth() {
        return month;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }


}
