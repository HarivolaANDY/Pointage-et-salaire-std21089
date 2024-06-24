package org.example;

import lombok.Getter;

@Getter
public class Categorie {
    private String nom;
    private int heuresNormales;
    private double salaireParSemaine;
    private double indemnites;

    public Categorie(String nom, int heuresNormales, double salaireParSemaine, double indemnites) {
        this.nom= nom;
        this.heuresNormales = heuresNormales;
        this.salaireParSemaine = salaireParSemaine;
        this.indemnites = indemnites;
    }
}
