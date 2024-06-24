package org.example;

import lombok.Getter;

@Getter
public class Category {
    private String name;
    private int normalHours;
    private double weeklySalary;
    private double allowances;

    public Category(String name, int normalHours, double weeklySalary, double allowances) {
        this.name = name;
        this.normalHours = normalHours;
        this.weeklySalary = weeklySalary;
        this.allowances = allowances;
    }
}
