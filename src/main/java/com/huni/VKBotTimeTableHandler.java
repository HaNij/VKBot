package com.huni;

import org.apache.poi.ss.usermodel.CellType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Класс, который работает с расписанием
 */
public class VKBotTimeTableHandler extends XLSXReader{

    /**
     * Конструктор инициализации.
     *
     * @param filePath    - инициализация пути
     * @param numberSheet - номер листа, который хотим прочитать
     */
    public VKBotTimeTableHandler(String filePath, int numberSheet) throws IOException {
        super(filePath, numberSheet);
    }


    /**
     * Получение списка строки одного предмета
     * @param pair номер пары, которой хотим получить
     * @param calendar передается по Calendar.DAY_OF_WEEK - текущий или заданный (для тестов) день недели
     * @return список в виде:
     * 1) (номер_пары) (тип_недели) (название_пары)
     * 2) (преподаватель_пары)
     */
    public ArrayList<String> readRowTimeTable(int pair, Calendar calendar) {
        return readRowTimeTable(pair,calendar.get(Calendar.DAY_OF_WEEK));
    }


    /**
     * Получение списка строки одного предмета
     * @param pair номер пары, которой хотим получить
     * @param dayWeek номер дня недели
     * @return список в виде:
     * 1) (номер_пары) (тип_недели) (название_пары)
     * 2) (преподаватель_пары)
     */
    public ArrayList<String> readRowTimeTable(int pair,int dayWeek) {
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
        return rasp;
    }
}
