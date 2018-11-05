package com.huni;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class XLSXReaderTest {

    @Test
    void test() throws IOException {
        XLSXReader reader = new XLSXReader("/Users/kirill/Desktop/Study/VKBot/src/test/java/com/huni/Test.xlsx",0);
        String actual1 = reader.getStringCell(0,0);
        String expected1 = "Test";
        double actual2 = reader.getNumericCell(1,0);
        double expected2 = 1.0d;
        String actual3 = reader.getFormulaCell(2,0);
        String expected3 = "SUM(1,2)";

        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
        assertEquals(expected3, actual3);

    }

}