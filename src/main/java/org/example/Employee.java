package org.example;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    private String lastName;
    private String firstName;
    private String employeeNumber;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private LocalDate contractEndDate;
    private Double grossSalary;
    private Category category;

    public Employee(String firstName, String employeeNumber, LocalDate birthDate,
                    LocalDate hireDate, LocalDate contractEndDate, double grossSalary, Category category) {
        this.firstName = firstName;
        this.employeeNumber = employeeNumber;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.contractEndDate = contractEndDate;
        this.grossSalary = grossSalary;
        this.category = category;
    }

    public double calculateNetSalary() {
        return grossSalary * 0.8;
    }

    public double getHourlyRate() {
        return category.getWeeklySalary() / category.getNormalHours();
    }

    public double calculateGrossSalary(double normalHours, double overtime30, double overtime50, double premium30, double premium40, double premium50) {
        double hourlyRate = getHourlyRate();
        double normalSalary = normalHours * hourlyRate;
        double overtimeSalary30 = overtime30 * hourlyRate * 1.3;
        double overtimeSalary50 = overtime50 * hourlyRate * 1.5;
        double premiumSalary30 = premium30 * hourlyRate * 1.3;
        double premiumSalary40 = premium40 * hourlyRate * 1.4;
        double premiumSalary50 = premium50 * hourlyRate * 1.5;
        double total= normalSalary + overtimeSalary30 + overtimeSalary50 + premiumSalary30 + premiumSalary40 + premiumSalary50;

        return total;
    }
}
