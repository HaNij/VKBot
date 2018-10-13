package com.huni;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author kirill
 * @version 0.1
 *
 * Класс реализует функцию определения даты и времени.
 */

public class DateAndTimeManager {

    /**
     * Объект для получения текущего даты и времени
     */
    private Calendar calendar;

    /**
     * Объект для форматирование даты и времени
     */
    private SimpleDateFormat dateFormat;

    /**
     * Конструктор по умолчанию предназначенный для локализации по умолчанию (русская локализация)
     */
    public DateAndTimeManager() {
//        Локализация для русского формата времени
        calendar = Calendar.getInstance(new Locale("ru","RU"));

    }

    /**
     * Функция используется для определения дня недели
     * @return String день недели (всё слово) с маленькой буквы
     */
    public String getDayOfWeek() {
        dateFormat = new SimpleDateFormat("EEEE",new Locale("ru","RU"));
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Функция используется для определения текущего времени.
     * @return String текущее время по формату ЧЧ:мм
     */
    public String getTime() {
        dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Функция используется для определения времени дня
     * @return String время дня
     */
    public String getDayTime() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 4 && hour < 12) {
            return "Утро";
        } else if (hour >= 12 && hour < 18) {
            return "День";
        } else if (hour >= 18 && hour < 24) {
            return "Вечер";
        } else if (hour >= 0 && hour < 4) {
            return "Ночь";
        } else return "Wrong time";
    }

    /**
     * Функция используется для определения текущей даты.
     * @return String текущая дата в формате гггг:ММ:дд
     */
    public String getDate() {
        dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.format(calendar.getTime());
    }

}
