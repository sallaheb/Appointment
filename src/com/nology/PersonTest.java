package com.nology;

import org.junit.Test;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void checkAppointmentStartDateIsBeforeEndDate() {
        Person adam = new Person("Adam",new Calender(),new Appointment());
        adam.createDailyBounds(9,00, 18, 0);

       Appointment adamAppt = adam.scheduleAnAppointmentWithinDailyBound(15,0);


        assertTrue(adamAppt.getStartTime().isBefore(adamAppt.getEndTime()));

    }

    @Test
    public void expectedEndTimeAndSetDuration(){
        Person adam = new Person("Adam",new Calender(),new Appointment());
        adam.createDailyBounds(9,00, 18, 0);

        Appointment adamAppt = adam.scheduleAnAppointmentWithinDailyBound(15,0);
        LocalTime expectedEndTime = LocalTime.of(15,30);
        String durationExpected ="MeetingDuration(min)=[30]";

        assertEquals(adamAppt.getEndTime(),expectedEndTime);
        assertEquals(adamAppt.getDuration(),durationExpected);

    }

    @Test
    public void checkAppointmentIsWithinBound() {
        Person adam = new Person("Adam",new Calender(),new Appointment());
        adam.createDailyBounds(9,00, 18, 0);
        adam.scheduleAnAppointmentWithinDailyBound(15,0);

        Boolean appointmentIsWithinBound =  adam.checkWithinBound();

        assertTrue(appointmentIsWithinBound);
    }

    @Test
    public void checkIsWithinSlots() {
        Person adam = new Person("Adam",new Calender(),new Appointment());
        adam.createDailyBounds(9,00, 18, 0);
        adam.scheduleAnAppointmentWithinDailyBound(15,0);

        Boolean appointmentIsWithinTheHalfHourSlots =  adam.checkIsWithinSlots();

        assertTrue(appointmentIsWithinTheHalfHourSlots);
    }


    @Test
    public void isAppointmentRemovedFromAvailableSlots(){
        Person adam = new Person("Adam",new Calender(),new Appointment());
        adam.createDailyBounds(9,00, 18, 0);
        adam.scheduleAnAppointmentWithinDailyBound(15,0);


        assertTrue((adam.checkIsRemovedAfter()));
    }

    @Test
    public void checkAvailableSlotsIsUpdated() {
        Person adam = new Person("Adam",new Calender(),new Appointment());
        adam.createDailyBounds(9,00, 18, 0);
        adam.getTimeSlots();


        int BeforeAppointmentBooking = adam.getAvailableTimeSlots().size();
        adam.scheduleAnAppointmentWithinDailyBound(15,0);
        //method call will create a duplicate so subtract from expected value : 17.
        // This method was also called within the above scheduleAnAppointment method above
        int afterAppointmentBooking = adam.getAvailableTimeSlots().size()-17;


        assertEquals(BeforeAppointmentBooking,18);
        assertEquals(afterAppointmentBooking,17);
    }

    @Test
    public void checkListIsUpdatedForMatchClassFunctionality() {

        Person adam = new Person("Adam",new Calender(),new Appointment());
        adam.createDailyBounds(9,00, 18, 0);
        adam.scheduleAnAppointmentWithinDailyBound(15,0);

        Boolean ListIsUpdated =  adam.checkListIsUpdatedForMatchClassFunctionality();

        assertTrue(ListIsUpdated);
    }

    @Test
    public void checkAvailabilityIsUpdatedForMatchClassFunctionality() {
        Person adam = new Person("Adam",new Calender(),new Appointment());
        adam.createDailyBounds(9,00, 18, 0);
        adam.scheduleAnAppointmentWithinDailyBound(15,0);

        Boolean AvailabilityIsUpdated =  adam.checkListIsUpdatedForMatchClassFunctionality();

        assertTrue(AvailabilityIsUpdated);
    }

}