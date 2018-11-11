package com.huni;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Класс реализует форматирования текущей даты и времени.
 */

public class DateAndTimeFormatter {

    /**
     * Объект для получения текущего даты и времени
     */
    private Calendar calendar;

    /**
     * Объект для форматирование даты и времени
     */
    private SimpleDateFormat dateFormat;

    /**
     * Конструктор по умолчанию, предназначенный для локализации даты и времени (русская локализация по умолчанию)
     */
    public DateAndTimeFormatter() {
        calendar = Calendar.getInstance(new Locale("ru","RU"));

    }

    /**
     * Конструктор, предназначенный для определённой локалицазии даты и времени
     * @param locale - локализация
     */
    public DateAndTimeFormatter(Locale locale) {
        calendar = Calendar.getInstance(locale);
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
     * Функция используется для определения текущего времени в часах и минутах.
     * @return String текущее время по формату ЧЧ:мм
     */
    public String getTime() {
        dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Функция используется для определения текущего времени в часах, минутах и секундах.
     * @return String текущее время по формату ЧЧ:мм:cc
     */

    public String getTimeWithSeconds() {
        dateFormat = new SimpleDateFormat("HH:mm:ss");
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
     * @return String текущая дата в формате день.месяц.год
     */
    public String getDate() {
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Установка calendar
     * @param calendar объект calendar
     */
    public void setDate(Calendar calendar) {
        this.calendar = calendar;
    }
}
