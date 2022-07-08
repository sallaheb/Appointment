package com.nology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Appointment one = new Appointment();
        Appointment Two = new Appointment();
        List<Appointment> multipleAppointments = new ArrayList<>();
        Calender kev = new Calender();

        //For Multiple appointments the number of parameters can be increased based on appt. no.
        multipleAppointmentsToCheck(one, Two, multipleAppointments, kev);

        Person kevin = new Person("kevin", kev,new Appointment(),multipleAppointments);
        Person adam = new Person("Adam",new Calender(),new Appointment(), new ArrayList<>());
        Person harry = new Person("harry",new Calender(),new Appointment(), new ArrayList<>());

        PersonOneCalendarAndAppointment(adam);
        PersonTwoCalendarAndAppointment(harry);

         // Testing adam against harry for single appointments
        List<String[]> availabilityOne = adam.getAvailableTimeSlots();
        List<String[]> availabilityTwo = harry.getAvailableTimeSlots();
        FindingMatchingCalendarDates(availabilityOne,availabilityTwo);

        // multiple appointments with Kevin and testing with availability against harry
        KevinsCalenderAndAppointment(kevin,multipleAppointments);
        List<String[]> availabilitykevin = kevin.getAvailableTimeSlots();
        FindingMatchingCalendarDates(availabilitykevin, availabilityTwo);
    }

    private static void KevinsCalenderAndAppointment(Person kevin, List<Appointment>multipleAppointments) {
        String dailybound = kevin.createDailyBounds(9,00,18,00);
        kevin.checkWithinBoundForList(multipleAppointments);
        kevin.checkIsWithinSlotsForMultipleAppointments(multipleAppointments);
        kevin.checkListIsUpdatedForMatchClassFunctionalityForMultipleApp(multipleAppointments);
        kevin.checkWithinAvailableSlotsForMultipleApp(multipleAppointments);

        System.out.println(kevin.getName());
        System.out.println(dailybound);
        System.out.println(kevin.getAppointments());
        System.out.println(kevin.getMeetingDuration());
    }

    private static void multipleAppointmentsToCheck(Appointment one, Appointment Two, List<Appointment> multipleAppointments, Calender kev) {
        // create appointments
        one.setStartTime(15, 0); one.setEndTime();
        Two.setStartTime(13, 0); Two.setEndTime();

        // Add to List
        multipleAppointments.add(one);
        multipleAppointments.add(Two);

       //add appointments to Calendar
        kev.addAllAppointments(multipleAppointments);
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
