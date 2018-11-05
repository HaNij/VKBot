package com.huni;

import org.apache.poi.ss.usermodel.CellType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


/**
 * Класс, который работает с расписанием
 */
public class VKBotTimeTableReader extends XLSXReader{

    /**
     * Конструктор инициализации.
     *
     * @param filePath    - инициализация пути
     * @param numberSheet - номер листа, который хотим прочитать
     */
    public VKBotTimeTableReader(String filePath, int numberSheet) throws IOException {
        super(filePath, numberSheet);
    }

    /* Чистые списки
    * 1) Список с номерами парами по порядку
    * 2) Список с названиями предметов по порядку
    * 3) Список с преподавателями по порядку
    * 4) Список вид дня недели
    *
    * Допустим хотим получить расписание на вторник:
    * for (i = 1; i <= 7; i++) {
    *   System.out.println(nomer().get(i) + " пара " + den().get(i) + " " + para().get(i) + ", " + prepod().get(i));
    * }
    * Какой хотим вывод:
    * 3 пара н Архитекутра аппаратных средств, Поликарпова С.В 213
    * 4 пара в/н Организация, принципы построения и функционирования компьютерных сетей, Котова Ю.Г 117
    * 5 пара в/н Организация, принципы построения и функционирования компьютерных сетей, Котовая Ю.Г 114
    *
    * Как это сделать?
    *   Разбить сырые списки на подсписки.
    */


    /**
     * Получение "сырого" списка всего столбца дня недели
     * @param dayWeek день недели
     * @return "сырой" список со всеми предметами на определённый день недели
     */
    public ArrayList<String> readAllRowTimeTable(int dayWeek) {
        ArrayList<String> allrow = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            allrow.addAll(readRowTimeTable(i,dayWeek));
        }
        return allrow;
    }

    /**
     * Получение "сырого" списка строки одного предмета
     * @param pair номер пары, которой хотим получить
     * @param dayWeek номер дня недели
     * @return список в виде:
     * 1) (номер_пары) (тип_недели) (название_пары)
     * 2) (преподаватель_пары)
     */
    private ArrayList<String> readRowTimeTable(int pair,int dayWeek) {
//        Список, который хранит предмет, в которой находится название предмета и преподаватель предмета
        ArrayList<String> rasp = new ArrayList<>();
//        Формула нахождения начала столбца в Excel предмета
        int RowStart = (17 + 4*(pair - 1)) - 1;
//        Формула нахождения конца столбца в Excel предмета
        int RowEnd = ((17 + 4*(pair - 1)) - 1) + 3;
//        Формула нахождения начала ячейки в Excel предмета
        int CellStart = (3+3*(dayWeek-1)) - 1;
//        Формула нахождения конца ячейки в Excel предмета
        int CellEnd = ((3+3*(dayWeek-1)) - 1) + 2;
//        Цикл получения добавления предмета в список
        for (int i = RowStart; i <= RowEnd; i++) {
            for (int n = CellStart; n <= CellEnd; n++) {
//                Если строка не пустая то идём дальше
                if (getSheet().getRow(i).getCell(n).getCellTypeEnum() != CellType.BLANK) {
//                    Выборка по типу недели
                    switch (getStringCell(i,1)) {
//                        если нижняя неделя
                        case "н":
//                            i-2 т.к ячейка "н" находится на 2 ячейки выше номера пары
                            rasp.add((int) getNumericCell(i-2,0)
                                    + " "
                                    + getStringCell(i,1)
                                    + " "
                                    + getStringCell(i,n));
                            break;
//                            если верхняя неделя
                        case "в":
//                            Если следующая ячейка пустая - значит вся ячейка предмета - это и по верхней недели и по нижней недели
                            if (getSheet().getRow(i+1).getCell(n).getCellTypeEnum() == CellType.BLANK) {
                                rasp.add((int) getNumericCell(i,0)
                                        + " "
                                        + "в/н"
                                        + " "
                                        + getStringCell(i,n));
                            } else {
                                rasp.add((int) getNumericCell(i,0)
                                        + " "
                                        + getStringCell(i,1)
                                        + " "
                                        + getStringCell(i,n));
                            }
                            break;
//                            по дефолту всё другое - это инициалы преподов
                        default:
                            rasp.add(getStringCell(i,n));
                            break;
                    }
                }
            }
        }

        // перенос преподавателя к названию предмету и прочему в один элемент списка
        // попарно суммируем элементы и удаляем ненужные, пока список не кончиться
        for(int i = 0;i < rasp.size(); i++) {
            rasp.set(i,rasp.get(i) + ", " +rasp.remove(i+1));
        }

        return rasp;
    }
}
