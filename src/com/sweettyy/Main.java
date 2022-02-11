package com.sweettyy;

import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException {

//        getInfoAboutRoots();
//        createAndDelFile();
//        createAndDelFileJSON();
//        createAndDelFileXML();
//        createAndDelZIP();
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
        nFile.create();
        nFile.write();
        try (
                ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:\\Users\\AbsolutMonkey\\Desktop\\Archive.zip"));
                FileInputStream fis = new FileInputStream(nFile.f.getPath());
        ) {
            ZipEntry entry1 = new ZipEntry(nFile.f.getName());
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            Files.delete(Paths.get(nFile.f.getPath()));
        } catch (IOException x) {
            System.err.println(x);
        }
        nFile.Wait("Архив создан, файл в архиве");

        try(ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\Users\\AbsolutMonkey\\Desktop\\Archive.zip")))
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){
                name = entry.getName();
                size=entry.getSize();
                FileOutputStream fout = new FileOutputStream("C:\\Users\\AbsolutMonkey\\Desktop\\" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
            nFile.Wait("Архив распакован");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        nFile.read();
        try {
            Files.delete(Paths.get("C:\\Users\\AbsolutMonkey\\Desktop\\Archive.zip"));
        } catch (IOException x) {
            System.err.println(x);
        }
        nFile.delete();
    }
}
