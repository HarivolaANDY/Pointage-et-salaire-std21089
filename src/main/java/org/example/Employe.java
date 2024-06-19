package org.example;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employe {
    private String nom;
    private String prenom;
    private String numeroMatricule;
    private LocalDate dateNaissance;
    private LocalDate dateEmbauche;
    private LocalDate dateFinContrat;
    private Double salaireBrut;
    private Categorie categorie;

    public Employe(String prenom, String numeroMatricule, LocalDate parse, LocalDate dateNaissance,
                   LocalDate dateEmbauche, LocalDate dateFinContrat, double salaireBrut, Categorie categorie) {
        this.prenom = prenom;
        this.numeroMatricule = numeroMatricule;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.dateFinContrat = dateFinContrat;
        this.salaireBrut = salaireBrut;
        this.categorie = categorie;
    }

    public double calculSalaireNet() {
        return salaireBrut * 0.8;
    }

    public double getTauxHoraire() {
        return categorie.getSalaireParSemaine() / categorie.getHeuresNormales();
    }

    public double calculerSalaireBrut(double heuresNormales, double heuresSupp30, double heuresSupp50, double heuresMaj30, double heuresMaj40, double heuresMaj50) {
        double tauxHoraire = getTauxHoraire();
        double salaireNormales = heuresNormales * tauxHoraire;
        double salaireSupp30 = heuresSupp30 * tauxHoraire * 1.3;
        double salaireSupp50 = heuresSupp50 * tauxHoraire * 1.5;
        double salaireMaj30 = heuresMaj30 * tauxHoraire * 1.3;
        double salaireMaj40 = heuresMaj40 * tauxHoraire * 1.4;
        double salaireMaj50 = heuresMaj50 * tauxHoraire * 1.5;

        return salaireNormales + salaireSupp30 + salaireSupp50 + salaireMaj30 + salaireMaj40 + salaireMaj50;
    }
}
