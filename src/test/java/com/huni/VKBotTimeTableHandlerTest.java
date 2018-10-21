package com.huni;

import org.junit.jupiter.api.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VKBotTimeTableHandlerTest {

    @Test
    void readRowTimeTable() throws IOException {
        VKBotTimeTableHandler handler = new VKBotTimeTableHandler("/home/kirill/IdeaProjects/VKBot/src/main/java/com/huni/rasp1009.xlsx",25);
        Calendar calendar = Calendar.getInstance(new Locale("ru","RU"));

//        Тестирование по ячейки расписания, которая находится по верхней и нижней недели
        ArrayList<String> actual = new ArrayList<>();
        actual.add("3 в/н Web-ориентированное программирование (дистанционно)");
        actual.add("Рогов А.Ю. 304");
        ArrayList<String> expected = handler.readRowTimeTable(3,1);
        assertEquals(expected,actual);

//        Тестирование по ячейки расписания, которая находится только по нижней недели
        ArrayList<String> actual1 = new ArrayList<>();
        actual1.add("3 н Архитектура аппаратных средств");
        actual1.add("Поликарпова С.В. 213");
        ArrayList<String> expected1 = handler.readRowTimeTable(3,2);
        assertEquals(expected1,actual1);

//        Тестирование по ячейки расписания, которая находится только по верхней недели
        ArrayList<String> actual2 = new ArrayList<>();
        actual2.add("5 в Архитектура аппаратных средств");
        actual2.add("Поликарпова С.В. 213");
        ArrayList<String> expected2 = handler.readRowTimeTable(5,6);
        assertEquals(expected2,actual2);

//        Тестирование по ячейки расписания, в которой находится один премдет на верхней, другой на нижней
        ArrayList<String> actual3 = new ArrayList<>();
        actual3.add("4 в Архитектура аппаратных средств");
        actual3.add("Поликарпова С.В. 213");
        actual3.add("4 н Системное администрирование и наладка компьютерных сетей(ДОУ)");
        actual3.add("Уймин А.Г. 104");
        ArrayList<String> expected3 = handler.readRowTimeTable(4,6);
        assertEquals(expected3,actual3);

        /*
        *   Тестирование по календарю
        */

        calendar.set(Calendar.DAY_OF_WEEK, 1);
        ArrayList<String> actual4 = new ArrayList<>();
        actual4.add("3 в/н Web-ориентированное программирование (дистанционно)");
        actual4.add("Рогов А.Ю. 304");
        ArrayList<String> expected4 = handler.readRowTimeTable(3,calendar);
        assertEquals(expected4,actual4);

        calendar.set(Calendar.DAY_OF_WEEK, 2);
        ArrayList<String> actual5 = new ArrayList<>();
        actual5.add("3 н Архитектура аппаратных средств");
        actual5.add("Поликарпова С.В. 213");
        ArrayList<String> expected5 = handler.readRowTimeTable(3,calendar);
        assertEquals(expected5,actual5);

        calendar.set(Calendar.DAY_OF_WEEK, 6);
        ArrayList<String> actual6 = new ArrayList<>();
        actual6.add("5 в Архитектура аппаратных средств");
        actual6.add("Поликарпова С.В. 213");
        ArrayList<String> expected6 = handler.readRowTimeTable(5,calendar);
        assertEquals(expected6,actual6);

        calendar.set(Calendar.DAY_OF_WEEK, 6);
        ArrayList<String> actual7 = new ArrayList<>();
        actual7.add("4 в Архитектура аппаратных средств");
        actual7.add("Поликарпова С.В. 213");
        actual7.add("4 н Системное администрирование и наладка компьютерных сетей(ДОУ)");
        actual7.add("Уймин А.Г. 104");
        ArrayList<String> expected7 = handler.readRowTimeTable(4,calendar);
        assertEquals(expected7,actual7);

    }

    @Test
    void test() throws IOException {
        VKBotTimeTableHandler handler = new VKBotTimeTableHandler("/home/kirill/IdeaProjects/VKBot/src/main/java/com/huni/rasp1009.xlsx",25);
        Iterator it = handler.readRowTimeTable(3,1).iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}