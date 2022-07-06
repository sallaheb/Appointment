package com.nology;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Person adam = new Person("Adam",new Calender(),new Appointment());
        Person harry = new Person("harry",new Calender(),new Appointment());

        PersonOneCalendarAndAppointment(adam);
        PersonTwoCalendarAndAppointment(harry);


        List<String[]> availabilityOne = adam.getAvailableTimeSlots();
        List<String[]> availabilityTwo = harry.getAvailableTimeSlots();

        FindingMatchingCalendarDates(availabilityOne,availabilityTwo);
    }

    private static void PersonOneCalendarAndAppointment(Person adam) {
       // Person 1:
        System.out.println(adam.getName()+ ":");
        // create a daily bound within calendar of person
        String dailybound =  adam.createDailyBounds(9,00, 18, 0);
        //create an appointment for person / Duration is already Set to 30 min for each meeting slot
        adam.scheduleAnAppointmentWithinDailyBound(15,0);
        //Get meeting duration
        String meetingDuration = adam.getMeetingDuration();
        //Sout the Output
        System.out.println(dailybound);
        adam.getAppointments().forEach(System.out::println);
        System.out.println(meetingDuration);
    }

    private static void PersonTwoCalendarAndAppointment(Person harry) {
        // Person 2:
        System.out.println(harry.getName()+ ":");
        String dailybound =  harry.createDailyBounds(12,00, 20, 0);
        harry.scheduleAnAppointmentWithinDailyBound(14,0);
        String meetingDuration = harry.getMeetingDuration();

        System.out.println(dailybound);
        harry.getAppointments().forEach(System.out::println);
        System.out.println(meetingDuration);
    }

    private static void FindingMatchingCalendarDates(List<String[]>availabilityOne, List<String[]>availabilityTwo) {
        List<String[]>matchingAvailableAppointments= MatchFilter.matchAvailableTimeSlots(availabilityOne,availabilityTwo);
        System.out.println("Available Match Dates:");
        matchingAvailableAppointments.forEach(s -> System.out.println( Arrays.toString(s)));
    }


}
