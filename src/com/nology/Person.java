package com.nology;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private Calender calender;
    private Appointment appointment;
    private List<String[]> timeSlots = new ArrayList<>();
    private List<String[]> listOfAppointments = new ArrayList<>();

    public Person(String name, Calender calender, Appointment appointment ) {
        this.name = name;
        this.calender = calender;
        this.appointment = appointment;

    }

    public Person() {
    }

    public String createDailyBounds(int StartHour, int StartMinutes, int EndHour, int EndMinute ){
        calender.setLocalTimeBoundStart(StartHour, StartMinutes);
        calender.setLocalTimeBoundEnd(EndHour, EndMinute);
        calender.setDailyBound();
       return calender.getDailyBound();
    }

    public Appointment scheduleAnAppointmentWithinDailyBound(int StartTimeHour, int StartTimeMinutes){
        appointment.setStartTime(StartTimeHour, StartTimeMinutes);
        appointment.setEndTime();
         checkWithinBound();
         checkIsWithinSlots();
         checkWithinAvailableSlots();
        calender.addAppointment(appointment);
        checkListIsUpdatedForMatchClassFunctionality();
        checkAvailabilityIsUpdatedForMatchClassFunctionality();

        return appointment;
    }

    public Boolean checkWithinBound() {
        if (appointment.getStartTime().isBefore(calender.getLocalTimeBoundStart())) {
            throw new RuntimeException("Appointment is set outside of daily bounds; Please review");
        } else if (appointment.getEndTime().isAfter(calender.getLocalTimeBoundEnd())) {
            throw new RuntimeException("Appointment is set outside of daily bounds; Please review");
        } else {
            return true;
        }

    }

       public Boolean checkIsWithinSlots() {
        List<String[]>timeSlots = getTimeSlots();

        String[] newAppointment = new String[] {String.valueOf(appointment.getStartTime()), String.valueOf(appointment.getEndTime())};

           for (String[] slot: timeSlots) {
               if(Arrays.equals(slot, newAppointment)){
                   return true;
               }
           }
           return false;
       }


    public Boolean checkWithinAvailableSlots() {
        List<String[]>timeSlots = getAvailableTimeSlots();

        String[] newAppointment = new String[] {String.valueOf(appointment.getStartTime()), String.valueOf(appointment.getEndTime())};

        for (String[] slot: timeSlots) {
            if(Arrays.equals(slot, newAppointment)){
                return true;
            }
        }
        throw new RuntimeException("appointment is not within Available slots");
    }

    public Boolean checkIsRemovedAfter() {
        List<String[]>timeSlots = getAvailableTimeSlots();

        String[] newAppointment = new String[] {String.valueOf(appointment.getStartTime()), String.valueOf(appointment.getEndTime())};

        for (String[] slot: timeSlots) {
            if(Arrays.equals(slot, newAppointment)){
                return false;
            }
        }
        return true;
    }

    public Boolean checkListIsUpdatedForMatchClassFunctionality(){
        String[] newAppointment = new String[] {String.valueOf(appointment.getStartTime()), String.valueOf(appointment.getEndTime())};
        List<String[]>timeSlots = getListOfAppointments();

        for (String[] slot: timeSlots) {
            if(Arrays.equals(slot, newAppointment)){
                return true;
            }
        }
        return false;
    }

    public Boolean checkAvailabilityIsUpdatedForMatchClassFunctionality() {
        String[] newAppointment = new String[] {String.valueOf(appointment.getStartTime()), String.valueOf(appointment.getEndTime())};
        List<String[]>Slots = getAvailableTimeSlots();
        for (String[] item: Slots) {
            if(!Arrays.equals(item, newAppointment)){
                return true;
            }
        }

        return false;
    }


    public String getMeetingDuration(){
        return appointment.getDuration();
    }

    public List<String[]> getTimeSlots(){
       int durationIncrement = 0;

        LocalTime newValue = LocalTime.of(0,0);
        LocalTime Temp = calender.getLocalTimeBoundStart();
        newValue = Temp;

        while(newValue.isBefore(calender.getLocalTimeBoundEnd())){
               LocalTime StarTime = calender.getLocalTimeBoundStart().plusMinutes(durationIncrement);
               LocalTime EndTime = StarTime.plusMinutes(30);
               String[] dailyBound = new String[] {String.valueOf(StarTime), String.valueOf(EndTime)};
               timeSlots.add(dailyBound);
               durationIncrement +=30;
            newValue = Temp.plusMinutes(durationIncrement);
        }

        return timeSlots;
    }

    public List<Appointment> getAppointments(){
      return   calender.getAppointments();
    }

    public String getName() {
        return name;
    }

    public List<String[]> getListOfAppointments(){
        // ONLY created to avoid threading issues : decided to create a new String[] to resolve

        List<Appointment> appointments = calender.getAppointments();

        for (int i = 0; i < appointments.size(); i++) {
            LocalTime StartTime = appointments.get(i).getStartTime();
            LocalTime EndTime = appointments.get(i).getEndTime();
            String[] singleAppointment = new String[]{String.valueOf(StartTime), String.valueOf(EndTime)};
            listOfAppointments.add(singleAppointment);
        }

        return listOfAppointments;
    }


    public List<String[]> getAvailableTimeSlots() {

        List<String[]> availableSlots = new ArrayList<>(timeSlots);
        for (String[] timeSlot1 : listOfAppointments) {
            for (String[] timeSlot2 : timeSlots) {
                if (Arrays.equals(timeSlot1, timeSlot2)) {
                    availableSlots.remove(timeSlot2);
                }
            }
        }
        return availableSlots;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", calender=" + calender +
                ", appointment=" + appointment +
                '}';
    }
}
