package com.sweettyy;

import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException {

//        getInfoAboutRoots();
//        createAndDelFile();
//        createAndDelFileJSON();
//        createAndDelFileXML();
        createAndDelZIP();
    }


    static void getInfoAboutRoots() {
        String result = "";
        System.out.println("1. Информация о логических дисках устройства:");
        var i = 0;
        for (File f : File.listRoots()) {
            i++;
            result += i + ": ";
            result += FileSystemView.getFileSystemView().getSystemDisplayName(f);
            result += " | ";
            result += FileSystemView.getFileSystemView().getSystemTypeDescription(f);
            result += " | ";
            result += String.format("%.2f", (f.getTotalSpace() / (Math.pow(1024, 3)))) + " GB";
            result += "\n";
        }

        System.out.println(result);
    }
    static void createAndDelFile() throws ParserConfigurationException, IOException {
        System.out.println("2. Создание, заполнение, чтение и удаление файла.");

        customFile nFile = new customFile("File.txt", "String for write to file.");
        nFile.create();
        nFile.write();
        nFile.read();
        nFile.delete();
    }
    static void createAndDelFileJSON() {
        System.out.println("3. Создание, заполнение, чтение и удаление файла в формате JSON.");

        customJSON nFile = new customJSON("File.JSON", "First string", "Second string", 1, true);
        nFile.create();
        nFile.write();
        nFile.read();
        nFile.delete();
    }
    static void createAndDelFileXML() throws ParserConfigurationException, IOException {
        System.out.println("4. Создание, заполнение, чтение и удаление файла в формате XML.");

        customXML nFile = new customXML("File.XML");
        nFile.create();
        nFile.write();
        nFile.read();
        nFile.delete();
    }
    static void createAndDelZIP() throws ParserConfigurationException, IOException {
        System.out.println("2. Создание, заполнение, чтение и удаление файла.");

        customFile nFile = new customFile("File.txt", "String for write to file.");
        customZIP nZIP = new customZIP("Archive.zip");
        nFile.create();
        nFile.write();
        nZIP.createAndPut( nFile.f.getName());
        nFile.Wait("Архив создан, файл в архиве");
        nZIP.unpack();
        nFile.Wait("Архив распакован");
        nFile.read();
        nZIP.delete();
        nFile.delete();
    }
}
