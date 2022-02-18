package com.sweettyy;

import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static final String DESKTOP = "C:\\Users\\AbsolutMonkey\\Desktop\\";
    public static void main(String[] args) throws ParserConfigurationException, IOException {

        getInfoAboutRoots();
        createAndDelFile();
        createAndDelFileJSON();
        createAndDelFileXML();
        createAndDelZIP();
    }


    static void getInfoAboutRoots() { // * Функция вывода информации о логических дисках
        String result = "";
        System.out.println("1. Информация о логических дисках устройства:");
        var i = 0;
        for (File f : File.listRoots()) {
            i++;
            result += i + ": ";
            result += FileSystemView.getFileSystemView().getSystemDisplayName(f); // * Название диска
            result += " | ";
            result += FileSystemView.getFileSystemView().getSystemTypeDescription(f); // * Тип диска
            result += " | ";
            result += String.format("%.2f", (f.getTotalSpace() / (Math.pow(1024, 3)))) + " GB"; // * Объем диска
            result += "\n";
        }

        System.out.println(result);
    }
    static void createAndDelFile() throws ParserConfigurationException, IOException {
        System.out.println("2. Создание, заполнение, чтение и удаление файла.");
        // * Создание экземпляра пользовательского класса файла + вызов конструктора для задания имени файла и записываемых в него данных
        CustomFile nFile = new CustomFile("File.txt", "String for write to file.");
        nFile.create();
        nFile.write();
        nFile.read();
        nFile.delete();
    }
    static void createAndDelFileJSON() {

        System.out.println("3. Создание, заполнение, чтение и удаление файла в формате JSON.");

        // * Создание экземпляра пользовательского класса JSON файла + вызов конструктора для заполнения данными из экземпляра произвольного класса
        CustomJSON nFile = new CustomJSON("File.JSON", "First string", "Second string", 1, true);
        nFile.create();
        nFile.write();
        nFile.read();
        nFile.delete();
    }
    static void createAndDelFileXML() throws ParserConfigurationException, IOException {

        System.out.println("4. Создание, заполнение, чтение и удаление файла в формате XML.");

        // * Создание экземпляра пользовательского класса XML файла + вызов конструктора для задания имени файла
        CustomXML nFile = new CustomXML("File.XML");
        nFile.create();
        nFile.write();
        nFile.read();
        nFile.delete();
    }
    static void createAndDelZIP() throws ParserConfigurationException, IOException {

        System.out.println("2. Создание, заполнение, чтение и удаление файла.");

        // * Создание экземпляра пользовательского класса файла + вызов конструктора
        CustomFile nFile = new CustomFile("File.txt", "String for write to file.");
        // * Создание экземпляра пользовательского класса архива
        CustomZIP nZIP = new CustomZIP("Archive.zip");
        // * Создание и заполнение файла
        nFile.create();
        nFile.write();
        // * Перемещение файла в архив
        nZIP.createAndPut( nFile.f.getName());
        nFile.Wait("Архив создан, файл в архиве");
        // * Распаковка архива
        nZIP.unpack();
        nFile.Wait("Архив распакован");
        // * Чтение файла
        nFile.read();
        // * Удаление архива и файла
        nZIP.delete();
        nFile.delete();
    }
}
