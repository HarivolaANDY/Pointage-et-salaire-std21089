package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class HeureTravail {
    private LocalDate date;
    private int heures;
    private boolean nuit;

    public LocalDate getDate() {
        return date;
    }

    public int getHeures() {
        return heures;
    }

    public boolean isNuit() {
        return nuit;
    }
}
