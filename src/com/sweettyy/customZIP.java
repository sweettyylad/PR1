package com.sweettyy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class customZIP {

    String name;

    customZIP(String ZIPn){
        name = ZIPn;
    }

    // * Функция создания и заполнения архива файлом
    // * Имя файла принимается в параметрах метода
    void createAndPut( String filename){
        try (
                ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:\\Users\\AbsolutMonkey\\Desktop\\" + this.name)); // * Поток для записи в архив
                FileInputStream fis = new FileInputStream("C:\\Users\\AbsolutMonkey\\Desktop\\" +filename) // * Поток для чтения файла
        ) {
            ZipEntry entry1 = new ZipEntry(filename); // * Создаем запись в ZIP с указанным именем
            zout.putNextEntry(entry1); // * Начинаем запись файла, ставим поток в начало данных записи
            byte[] buffer = new byte[fis.available()]; // * Снова всё в байты
            fis.read(buffer); // * Читаем байты в массив
            zout.write(buffer); // * Записываем байты через поток в нашу запись
            zout.closeEntry(); // * Закрываем поток
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            Files.delete(Paths.get("C:\\Users\\AbsolutMonkey\\Desktop\\" + filename)); // * Удаляем старый файл, оставшийся вне архива
        } catch (IOException x) {
            System.err.println(x);
        }
    }
    void unpack(){
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\Users\\AbsolutMonkey\\Desktop\\" + this.name)))
        {
            ZipEntry entry; // * Создаем запись в ZIP
            String name; // * Для хранения имени файла
            while((entry=zin.getNextEntry())!=null){
                name = entry.getName(); // * Получаем имя записи (файла, лежащего в) ZIP
                // * Открываем поток на запись данных в файл
                FileOutputStream fout = new FileOutputStream("C:\\Users\\AbsolutMonkey\\Desktop\\" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c); // * Записываем в поток
                }
                fout.flush(); // * Выводим данные из потока и помещаем их в файл
                zin.closeEntry(); // * Закрываем поток
                fout.close(); // * Закрываем поток
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    void delete(){
        try {
            Files.delete(Paths.get("C:\\Users\\AbsolutMonkey\\Desktop\\"+this.name)); // * Удаляем архив
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
