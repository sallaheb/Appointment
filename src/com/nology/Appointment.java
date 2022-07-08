package com.nology;

import java.time.Duration;
import java.time.LocalTime;

public class Appointment {
    private LocalTime startTime;
    private LocalTime endTime;
    private final Duration duration;

    public Appointment() {
        this.duration = Duration.ofMinutes(30);
    }

    public void setStartTime(int HourIn24hrFormat, int minutes) {

        try {
            if (minutes > 59 ) { throw new RuntimeException("Enter a number between 0 and 59");}
            if (HourIn24hrFormat > 24 || HourIn24hrFormat == 24 ) { throw new RuntimeException("Enter the correct hour between 00 and 23");}
            this.startTime = LocalTime.of(HourIn24hrFormat,minutes);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setEndTime() {
        this.endTime = startTime.plus(this.duration);
    }

    public LocalTime getEndTime (  ) {
        return endTime;
    }

    public String getDuration() {
        return "MeetingDuration(min)=[" + this.duration.toMinutes() +']';
    }

    @Override
    public String toString() {
        return "Appointment="+"["+startTime+","+" "+endTime+"]";
    }


}
