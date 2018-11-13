package com.huni;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author kirill
 * @version 1.1
 *
 * Основной класс
 */

public class Main {
    /**
     * Функция форматирования привествия
     * @param text время дня с большой буквы
     * @return форматированное привествие
     * Например: если было переданно "Утро", то возвращает "Доброе утро"
     */
    private static String greetingFormatter(String text) {
        switch (text) {
            case "Утро":
                return "Доброе утро";
            case "День":
                return "Добрый день";
            case "Вечер":
                return "Добрый вечер";
            case "Ночь":
                return "Доброй ночи";
                default:
                    return "Error get time of day";
        }
    }

    public static void main(String[] args) throws IOException {
//        (60*60*1000)*24 = 1ч * 24 = 24ч, т.е каждый день раз в день писать расписание.

        Group group = new Group(0, "token");
        Message message = new Message();
        // Paths.get... - нужен для определения абсолютного пути jar файла, используется для удалённых компьютеров
        VKBotTimeTableReader timeTableReader = new VKBotTimeTableReader(Paths.get(".").toAbsolutePath().normalize().toString() + "/rasp.xlsx",25);
//      Задача написания расписание в заданное время
        TimerTask taskPrint = new TimerTask() {
            @Override
            public void run() {
                DateAndTimeFormatter formatter = new DateAndTimeFormatter();
                message.from(group);
                message.to(137406372);
//                Проверка на воскресенье
                if ((Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1) != 0) {
                    message.text(greetingFormatter(formatter.getDayTime()) + ", сегодня " + formatter.getDate() + " " + formatter.getTime() + ", текущее расписание:").send();
//                Calendar.DAY_OF_WEEK - 1, т.к первым днём недели является воскресенье (так прописано в реализации Calendar), а в России понедельник.
                    Iterator it = timeTableReader.readAllRowTimeTable(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1).iterator();
                    while(it.hasNext()) {
                        message.text(it.next()).send();
                    }
                } else message.text("Сегодня воскресенье! Нет пар!").send();
            }
        };
        Timer timerTaskPrint = new Timer();
//        Установка времени написания расписания
        Calendar calendar = Calendar.getInstance(new Locale("ru","RU"));
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
//       Установка написания расписания в заданное время
        timerTaskPrint.scheduleAtFixedRate(taskPrint, calendar.getTime(), (60*60*1000)*24);
    }

}
