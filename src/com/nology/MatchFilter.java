package com.nology;

import java.util.*;

public class MatchFilter {

    public static List<String[]> matchAvailableTimeSlots(List<String[]> person1, List<String[]> person2) {
        List<String[]> ListOfMatches = new ArrayList<>();

        for (String[] timeSlot1 : person1) {
            for (String[] timeSlot2 : person2) {
                if (Arrays.equals(timeSlot1, timeSlot2)) {
                    ListOfMatches.add(timeSlot1);
                }
            }
        }
        return ListOfMatches;
    }

}