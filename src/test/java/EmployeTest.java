import org.example.Categorie;
import org.example.Employe;
import org.example.Pointage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeTest {
    private Employe rakoto;
    private Pointage pointageRakoto;

    @BeforeEach
    public void setUp() {
        Categorie gardien = new Categorie("Gardien", 56, 110000.0, 0);
        rakoto = new Employe("Rakoto", "G63001", LocalDate.of(1985, 1, 1), LocalDate.of(2019, 1, 1), LocalDate.of(2023, 1, 1), 110000, gardien);
        pointageRakoto = new Pointage(rakoto);
    }

    @Test
    public void testCalculerTotalHeures() {
        pointageRakoto.ajouterHeures(LocalDate.of(2024, 6, 1), 8, false);
        pointageRakoto.ajouterHeures(LocalDate.of(2024, 6, 2), 8, false);
        assertEquals(16, pointageRakoto.calculerTotalHeures());
    }

    @Test
    public void testCalculerHeuresSupplementaires() {
        for (int i = 1; i <= 7; i++) {
            pointageRakoto.ajouterHeures(LocalDate.of(2024, 6, i), 8, false);
        }
        pointageRakoto.ajouterHeures(LocalDate.of(2024, 6, 8), 4, false);
        assertEquals(4, pointageRakoto.calculerHeuresSupplementaires());
    }

    @Test
    public void testCalculerHeuresMajorees() {
        pointageRakoto.ajouterHeures(LocalDate.of(2024, 6, 1), 8, true); // Nuit (HM30)
        pointageRakoto.ajouterHeures(LocalDate.of(2024, 6, 2), 8, false); // Jour normal
        pointageRakoto.ajouterHeures(LocalDate.of(2024, 6, 8), 8, false); // Dimanche (HM40)

        double[] heuresMajorees = pointageRakoto.calculerHeuresMajorees();

        assertEquals(10.4, heuresMajorees[0], 0.01); // Vérifie les heures de nuit majorées à 130%
        assertEquals(11.2, heuresMajorees[1], 0.01); // Vérifie les heures du dimanche majorées à 140%
        assertEquals(0, heuresMajorees[2], 0.01);
    }

    @Test
    public void testCalculerSalaireBrut() {
        double salaireBrut = rakoto.calculerSalaireBrut(56, 8, 12, 0, 0, 0);
        assertEquals(165785.71428571426, salaireBrut, 0.01);
    }

    @Test
    public void testCalculerSalaireNet() {
        rakoto.setSalaireBrut(125080.0);
        double salaireNet = rakoto.calculSalaireNet();
        assertEquals(100064, salaireNet, 0.01);
    }
}
