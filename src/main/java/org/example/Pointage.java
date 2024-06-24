package org.example;

import lombok.AllArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Pointage {
    private Employe employe;
    private List<HeureTravail> heuresTravail;

    public Pointage(Employe employe) {
        this.employe = employe;
        this.heuresTravail = new ArrayList<>();
    }

    public void ajouterHeures(LocalDate date, int heures, boolean nuit) {
        heuresTravail.add(new HeureTravail(date, heures, nuit));
    }

    public int calculerTotalHeures() {
        return heuresTravail.stream().mapToInt(HeureTravail::getHeures).sum();
    }

    public int calculerHeuresSupplementaires() {
        int heuresNormales = employe.getCategorie().getHeuresNormales();
        int totalHeures = calculerTotalHeures();
        return Math.max(0, totalHeures - heuresNormales);
    }

    public double[] calculerHeuresMajorees() {
        double[] heuresMajorees = new double[3];
        for (HeureTravail ht : heuresTravail) {
            LocalDate date = ht.getDate();
            int heures = ht.getHeures();
            if (ht.isNuit()) {
                heuresMajorees[0] += heures * 1.3; // Travail de nuit (HM30)
            } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                heuresMajorees[1] += heures * 1.4; // Travail le dimanche (HM40)
            } else if (isFerie(date)) {
                heuresMajorees[2] += heures * 1.5; // Travail les jours fériés (HM50)
            }
        }
        return heuresMajorees;
    }

    private boolean isFerie(LocalDate date) {
        return date.equals(LocalDate.of(2024, 6, 17)) || date.equals(LocalDate.of(2024, 6, 25)) || date.equals(LocalDate.of(2024, 6, 26));
    }
}
