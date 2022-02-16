package com.sweettyy;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class customFile {

    File f; // * Переменная с типом File (класс для работы с файлами)
    Data d = new Data(); // * Произвольные данные

    // * Конструкторы для разных ситуаций
    customFile(String filename, String content){
        f = new File("C:\\Users\\AbsolutMonkey\\Desktop\\" + filename);
        d.WV = content;
    }
    customFile(String filename){
        f = new File("C:\\Users\\AbsolutMonkey\\Desktop\\" + filename);
    }

    // * Функция ожидания нажатия любой кнопки для продолжения работы приложения
    void Wait(String a) {
        Scanner sc = new Scanner(System.in);
        System.out.print(a + ", нажмите Enter для продолжения.");
        String waiting = sc.nextLine();
    }

    // * Функция создания файла
    void create(){
        try {
            boolean created = f.createNewFile(); // * В boolean записывается true, если файл успешно создан
            if (created)
                Wait("Файл создан"); // * Если файл создан, ожидаем дальнейших действий
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); // * Если возникли проблемы, выбрасываем исключение ввода-вывода [1]
        }
    }

    void write() throws ParserConfigurationException, IOException {
        try (FileOutputStream fos = new FileOutputStream(f)) { // * Открываем поток записи для нашего файла
            // * Конструкция типа try-with-resources, в круглых скобках объявляем
            // * переменные, которые далее можем использовать в теле обработки
            // * Конкретно здесь создается экземпляр класса для записи байтов в файл
            byte[] buffer = d.WV.getBytes(); // * Переводим некие данные нашего объекта в последовательность байтов
            // * Происходит запись всей длины массива
            // * байтов в файл со смещением равным нулю
            fos.write(buffer, 0, buffer.length);
            fos.close(); // * Закрываем поток
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); // * [1]
        } finally {
            Wait("Данные записаны в файл");
        }
    }

    void read(){
        try (FileInputStream fin = new FileInputStream(f)) {
            System.out.println("Размер файла: " + fin.available() + " байтов"); // * Выводим размер файла в байтах
            System.out.print("Содержимое: ");
            int i = -1;
            while ((i = fin.read()) != -1) { // * В цикле читаем содержимое файла посимвольно
                System.out.print((char) i); // * Выводим в консоль
            }
            System.out.println("\n");
            fin.close(); // * Закрываем поток
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); // * [1]
        }
    }

    void delete(){
        boolean deleted = f.delete(); // * Удаляем файл методом класса File
        if (deleted)
            Wait("Файл удален"); // * При успешном удалении выводится сообщение в консоль
    }
}
