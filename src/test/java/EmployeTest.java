import org.example.Categorie;
import org.example.Employe;
import org.example.Pointage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeTest {
    @Test
    public void testCalculerTotalHeures() {
        Categorie gardien = new Categorie("gardien", 8, 15000, 15000);
        Employe rakoto = new Employe("Rakoto", "Jean", LocalDate.parse("1995-02-25"), LocalDate.of(1995, 2, 25), LocalDate.of(2019, 1, 1), LocalDate.of(2020, 1, 1), 100000, gardien);
        Pointage pointageRakoto = new Pointage(rakoto);

        for (int i = 1; i <= 30; i++) {
            LocalDate date = LocalDate.of(2024, 6, i);
            pointageRakoto.ajouterHeures(date, 8, false);
        }

        assertEquals(240, pointageRakoto.calculerTotalHeures());
    }

    @Test
    public void testCalculerHeuresMajorees() {
        Categorie gardien = new Categorie("Gardien", 56, 110000, 0);
        Employe rakoto = new Employe("Rakoto", "Jean", LocalDate.parse("1995-02-25"), LocalDate.of(1985, 1, 1), LocalDate.of(2020, 1, 1), null, 110000, gardien);
        Pointage pointageRakoto = new Pointage(rakoto);

        for (int i = 1; i <= 30; i++) {
            LocalDate date = LocalDate.of(2024, 6, i);
            pointageRakoto.ajouterHeures(date, 8, true);
        }

        double[] heuresMajorees = new double[]{pointageRakoto.calculerHeuresMajorees()};
        assertEquals(240 * 1.3, heuresMajorees[0], 0.01);
    }
}
