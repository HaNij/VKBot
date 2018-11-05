package com.huni;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;

public class ImageDownloader {

    /**
     * Путь, куда нужно сохранять картинку, фото
     */
    private String path;

    /**
     * Ссылка на картинку, фото
     */
    private String url;


    /**
     * Конструктор по умолчанию
     */
    public ImageDownloader() {

    }

    /**
     * Конструктор с указанием пути сохранения фото, и с указанием ссылки на скачивание
     * @param path путь (куда нужно сохранять)
     * @param url ссылка (что нужно скачать)
     */
    public ImageDownloader(String path, String url) {
        this.path = path;
        this.url = url;
    }

    /**
     * Метод скачивания фото, картинки
     */
    public void downloadImage() {
        try(InputStream in = new URL(url).openStream()){
            try {
                Files.copy(in, Paths.get(path));
            } catch (FileAlreadyExistsException e) {
// Если был словлена ошибка, что данный файл существует, то текущий файл удаляется и заменяется на новый
                Files.delete(Paths.get(path));
                System.out.println("file already exist, so it deleted and repasted");
                Files.copy(in, Paths.get(path));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Установка пути
     * @param path путь
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Установка ссылки
     * @param url ссылка
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Получение пути
     * @return путь до файла
     */
    public String getPath() {
        return path;
    }

    /**
     * Получение ссылки
     * @return ссылка
     */
    public String getUrl() {
        return url;
    }
}
