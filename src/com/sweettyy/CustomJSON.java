package com.sweettyy;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;

public class CustomJSON extends CustomFile {
    // * Класс наследуемый от CustomFile
    CustomJSON(String filename, String content, String content2, int num, boolean adv) {
        super(filename, content); // * Вызываем конструктор класса родителя
        d.WV2 = content2; // * Записываем остальные произвольные данные
        d.SN = num;
        d.STF = adv;
    }

    @Override // * Переопределяем метод записи данных в файл
    void write() {
        Gson gson = new Gson(); // * Создаем экземпляр класса GSON для работы с JSON
        String json = gson.toJson(d); // * Переводим наши произвольные данные в JSON-строку
        try (FileOutputStream fos = new FileOutputStream(f)) { // * Открываем поток записи
            byte[] buffer = json.getBytes(); // * Теперь уже берем длину JSON-строки
            fos.write(buffer, 0, buffer.length); // * И записываем её в файл
            fos.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); // * Проблема
        } finally{
            Wait("Данные записаны в файл"); // * Успешный итог
        }
    }
}
