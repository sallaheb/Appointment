package com.nology;


import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Calender {
    private List<Appointment> appointments = new ArrayList<>();
    private LocalTime localTimeBoundStart;
    private LocalTime localTimeBoundEnd;
    private LocalTime[] dailyBound = new LocalTime[]{};

    public Calender() {
    }

    public LocalTime getLocalTimeBoundStart() {
        return localTimeBoundStart;
    }

    public void setLocalTimeBoundStart(int HourIn24hrFormat, int minutes) {
        try {
            if (minutes > 59 ) { throw new RuntimeException("Enter a number between 0 and 59");}
            if (HourIn24hrFormat > 24 || HourIn24hrFormat == 24 ) { throw new RuntimeException("Enter the correct hour between 00 and 23");}
            this.localTimeBoundStart = LocalTime.of(HourIn24hrFormat,minutes);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public LocalTime getLocalTimeBoundEnd() {
        return localTimeBoundEnd;
    }

    public void setLocalTimeBoundEnd(int HourIn24hrFormat, int minutes) {
        try {
            if (minutes > 59 ) { throw new RuntimeException("Enter a number between 0 and 59");}
            if (HourIn24hrFormat > 24 || HourIn24hrFormat == 24 ) { throw new RuntimeException("Enter the correct hour between 00 and 23");}
            this.localTimeBoundEnd = LocalTime.of(HourIn24hrFormat,minutes);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Appointment> getSortedAppointments() {
        return appointments.stream()
                .sorted(Comparator.comparing(Appointment::getStartTime)).collect(Collectors.toList());
    }

    public void setDailyBound() {
        this.dailyBound = new LocalTime[] {localTimeBoundStart, localTimeBoundEnd};
    }

    public String getDailyBound() {
        return "dailyBound=" + Arrays.toString(dailyBound);
    }

    public void addAllAppointments(List<Appointment> appointment) {
        appointments.addAll(appointment);
    }



    @Override
    public String toString() {
        return "Calender{" +
                "appointments=" + appointments +
                ", localTimeBoundStart=" + localTimeBoundStart +
                ", localTimeBoundEnd=" + localTimeBoundEnd +
                ", dailyBound=" + Arrays.toString(dailyBound) +
                '}';
    }


}
