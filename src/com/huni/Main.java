package com.huni;

import java.util.*;

/**
 * @author kirill
 * @version not released alpha 0.1
 *
 * Основной класс
 */

public class Main {
    public static void main(String[] args) {
//        (60*60*1000) - = 1ч, т.е каждый час, проверять расписание
//        (60*60*1000)*24 = 1ч * 24 = 24ч, т.е каждый день раз в день писать расписание.

//        Бот
        VKBot bot = new VKBot();

//        Задача проверки изменений в расписании
        TimerTask taskCheck = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Проверяю расписание " + new DateAndTimeFormatter().getTimeWithSeconds());
//                Код проверки расписания
                System.out.println("Закончил проверять расписание " + new DateAndTimeFormatter().getTimeWithSeconds());
            }
        };
        Timer timerTaskCheck = new Timer();

//        Установка задачи проверки изменений в расписании
        timerTaskCheck.scheduleAtFixedRate(taskCheck,1000, (60*1000)*5);

//        Задача написания расписание в заданное время
        TimerTask taskPrint = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Пишу расписание " + new DateAndTimeFormatter().getTimeWithSeconds());
//                Код для написания расписания
                System.out.println("Закончил писать расписание " + new DateAndTimeFormatter().getTimeWithSeconds());
            }
        };
        Timer timerTaskPrint = new Timer();
//        Установка времени написания расписания
        Calendar calendar = Calendar.getInstance(new Locale("ru","RU"));
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);
//       Установка написания расписания в заданное время
        timerTaskPrint.scheduleAtFixedRate(taskPrint, calendar.getTime(), (60*1000)*5);
    }

}
