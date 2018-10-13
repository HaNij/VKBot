package com.huni;

public class TestDateAndTimeManager {
    public static void main(String[] args) {
        DateAndTimeManager manager = new DateAndTimeManager();
        TimerManager timerManager = new TimerManager(10);
        System.out.println(manager.getDate());
        System.out.println(manager.getTime());
        System.out.println(manager.getDayTime());
        System.out.println(manager.getDayOfWeek());
    }
}
