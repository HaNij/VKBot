package com.huni;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XLSXReaderTest {

    @Test
    void test() throws FileNotFoundException, IOException {
        XLSXReader reader = new XLSXReader("/home/kirill/IdeaProjects/VKBot/src/test/java/com/huni/Test.xlsx",0);
//        String actual1 = reader.readByRowCell(0,0);
//        String expected1 = "Test";
//        String actual2 = reader.readByRowCell(1,0);
//        String expected2 = "1.0";
//        String actual3 = reader.readByRowCell(2,0);
//        String expected3 = "=ДАТА(2000,1,1)";
//        System.out.println(reader.readByRowCell(25,25));
//
//        assertEquals(expected1,actual1);
//        assertEquals(expected2,actual2);
//        assertEquals(expected3, actual3);
//        assertEquals("Ошибка: данная ячейка не существует",reader.readByRowCell(3,0));

    }

}