import org.example.Category;
import org.example.Employee;
import org.example.Attendance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {
    private Employee rakoto;
    private Attendance attendanceRakoto;

    @BeforeEach
    public void setUp() {
        Category guardian = new Category("Guardian", 56, 110000.0, 0);
        rakoto = new Employee("Rakoto", "G63001", LocalDate.of(1985, 1, 1), LocalDate.of(2019, 1, 1), LocalDate.of(2023, 1, 1), 110000, guardian);
        attendanceRakoto = new Attendance(rakoto);
    }

    @Test
    public void testCalculateTotalHours() {
        attendanceRakoto.addHours(LocalDate.of(2024, 6, 1), 8, false);
        attendanceRakoto.addHours(LocalDate.of(2024, 6, 2), 8, false);
        assertEquals(16, attendanceRakoto.calculateTotalHours());
    }

    @Test
    public void testCalculateOvertimeHours() {
        for (int i = 1; i <= 7; i++) {
            attendanceRakoto.addHours(LocalDate.of(2024, 6, i), 8, false);
        }
        attendanceRakoto.addHours(LocalDate.of(2024, 6, 8), 4, false);
        assertEquals(4, attendanceRakoto.calculateOvertimeHours());
    }

    @Test
    public void testCalculatePremiumHours() {
        attendanceRakoto.addHours(LocalDate.of(2024, 6, 1), 8, true);
        attendanceRakoto.addHours(LocalDate.of(2024, 6, 2), 8, false);
        attendanceRakoto.addHours(LocalDate.of(2024, 6, 8), 8, false);

        double[] premiumHours = attendanceRakoto.calculatePremiumHours();

        assertEquals(10.4, premiumHours[0], 0.01); // prime de nuit à 130%
        assertEquals(11.2, premiumHours[1], 0.01); // prime des heures du dimanche à 140%
        assertEquals(0, premiumHours[2], 0.01);
    }

    @Test
    public void testCalculateGrossSalary() {
        double grossSalary = rakoto.calculateGrossSalary(56, 8, 12, 0, 0, 0);
        assertEquals(165785.71428571426, grossSalary, 0.01);
    }

    @Test
    public void testCalculateNetSalary() {
        rakoto.setGrossSalary(125080.0);
        double netSalary = rakoto.calculateNetSalary();
        assertEquals(100064, netSalary, 0.01);
    }

    @Test
    public void testCalculerSalaireBrutAvecPlusDe20HeuresSupp() {

        double hoursSupp30 = 8;
        double hoursSupp50 = 12;
        double heuresSuppExc = 5; // Exceeding hours not to be paid
        double totalHour = hoursSupp30 + hoursSupp50 + heuresSuppExc;

        double grossSalary = rakoto.calculateGrossSalary(56, hoursSupp30, hoursSupp50, 0, 0, 0);

        double tauxHoraire = rakoto.getHourlyRate();
        double salaireNormales = 56 * tauxHoraire;
        double salarySupp30 = hoursSupp30 * tauxHoraire * 1.3;
        double salarySupp50 = hoursSupp50 * tauxHoraire * 1.5;
        double salaryGrossExpected = salaireNormales + salarySupp30 + salarySupp50;

        assertEquals(salaryGrossExpected, grossSalary, 0.01);
    }
}
