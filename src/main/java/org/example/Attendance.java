package org.example;

import lombok.AllArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Attendance {
    private Employee employee;
    private List<WorkHour> workHours;

    public Attendance(Employee employee) {
        this.employee = employee;
        this.workHours = new ArrayList<>();
    }

    public void addHours(LocalDate date, int hours, boolean night) {
        workHours.add(new WorkHour(date, hours, night));
    }

    public int calculateTotalHours() {
        return workHours.stream().mapToInt(WorkHour::getHours).sum();
    }

    public int calculateOvertimeHours() {
        int normalHours = employee.getCategory().getNormalHours();
        int totalHours = calculateTotalHours();
        return Math.max(0, totalHours - normalHours);
    }

    public double[] calculatePremiumHours() {
        double[] premiumHours = new double[3];
        for (WorkHour wh : workHours) {
            LocalDate date = wh.getDate();
            int hours = wh.getHours();
            if (wh.isNight()) {
                premiumHours[0] += hours * 1.3; // Travaille la nuit (PH30)
            } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                premiumHours[1] += hours * 1.4; // Travaille le dimanche (PH40)
            } else if (isHoliday(date)) {
                premiumHours[2] += hours * 1.5; // Traville le weekend (PH50)
            }
        }
        return premiumHours;
    }

    private boolean isHoliday(LocalDate date) {
        return date.equals(LocalDate.of(2024, 6, 17)) || date.equals(LocalDate.of(2024, 6, 25)) || date.equals(LocalDate.of(2024, 6, 26));
    }
}
