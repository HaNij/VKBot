package com.huni;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VKBotTimeTableReaderTest {

    private String filePath = "/home/kirill/IdeaProjects/VKBot/src/main/java/com/huni/rasp1009.xlsx";
    private String macFilePath = "/Users/kirill/Desktop/Study/VKBot/src/main/java/com/huni/rasp1009.xlsx";

    @Test
    void readAllRowTimeTable() throws IOException {
        VKBotTimeTableReader handler = new VKBotTimeTableReader(filePath,25);
        ArrayList<String> actual = handler.readAllRowTimeTable(1);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("3 в/н Web-ориентированное программирование (дистанционно), Рогов А.Ю. 304");
        expected.add("4 в/н Web-ориентированное программирование (дистанционно), Рогов А.Ю. 304");
        expected.add("5 в/н Организация, принципы построения и функционирования компьютерных сетей, Котова Ю.Г. 205");
        expected.add("6 в/н Основы программирования и баз данных, Рогов А.Ю. 304");

        assertEquals(expected,actual);

    }
}