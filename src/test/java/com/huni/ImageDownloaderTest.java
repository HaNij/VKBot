package com.huni;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageDownloaderTest {

    @Test
    void downloadImage() {
        ImageDownloader downloader = new ImageDownloader();
        downloader.setPath("/Users/kirill/Desktop/Study/VKBot/src/main/java/com/huni/zamen.jpg");
        downloader.setUrl("http://www.urtt.ru/phphtml/docs/zamen.jpg");
        downloader.downloadImage();
    }
}