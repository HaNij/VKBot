package com.huni;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Класс чтения файла.xslx файла
 */
public class XLSXReader {

    /**
     * Объект РабочейКнижки (файл Excel).
     */
    private XSSFWorkbook workbook;

    /**
     * Объект для работы с листом Excel файла
     */
    private XSSFSheet sheet;

    /**
     * Объект для получения потока файла
     */
    private FileInputStream fileInputStream;

    /**
     * Объект файла
     */
    private File file;

    /**
     * Путь до файла, который хотим прочитать
     */
    private String filePath;

    /**
     * Конструктор инициализации.
     *
     * @param filePath - инициализация пути
     * @param numberSheet - номер листа, который хотим прочитать
     */
    public XLSXReader(String filePath, int numberSheet) throws IOException {
        this.filePath = filePath;
        file = new File(filePath);
        fileInputStream = new FileInputStream(file);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheetAt(numberSheet);
    }

    /**
     * Функция получения ячейки типа String
     * @param row столбец
     * @param cell ячейка
     * @return строка по столбцу и ячейки
     */
    public String getStringCell(int row, int cell) {
        return sheet.getRow(row).getCell(cell).getStringCellValue();
    }

    /**
     * Функция получения ячейки типа Numeric (числовое) в виде double
     * @param row столбец
     * @param cell ячейка
     * @return строка по столбцу и ячейки
     */
    public double getNumericCell(int row, int cell) {
        return sheet.getRow(row).getCell(cell).getNumericCellValue();
    }

    /**
     * Функция получения ячейки типа Formula (формула) в виде String
     * @param row столбец
     * @param cell ячейка
     * @return строка по столбцу и ячейки
     */
    public String getFormulaCell(int row, int cell) {
        return sheet.getRow(row).getCell(cell).getCellFormula();
    }

    /**
     * Функция получения ячейки типа Boolean
     * @param row столбец
     * @param cell ячейка
     * @return строка по столбцу и ячейки
     */
    public boolean getBooleanCell(int row, int cell) {
        return sheet.getRow(row).getCell(cell).getBooleanCellValue();
    }

    /**
     * Геттер для XSSFWorkbook
     * @return текущий объект XSSFWorkbook
     */
    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    /**
     * Геттер для XSSFSheet
     * @return текущий объект XSSFSheet
     */
    public XSSFSheet getSheet() {
        return sheet;
    }

    /**
     * Геттер для filePath
     * @return String filePath
     */
    public String getFilePath() {
        return filePath;
    }


}