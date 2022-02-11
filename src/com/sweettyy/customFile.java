package com.sweettyy;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class customFile {

    File f;
    Data d = new Data();

    customFile(String filename, String content){
        f = new File("C:\\Users\\AbsolutMonkey\\Desktop\\" + filename);
        d.WV = content;
    }
    customFile(String filename){
        f = new File("C:\\Users\\AbsolutMonkey\\Desktop\\" + filename);

    }
    void Wait(String a) {
        Scanner sc = new Scanner(System.in);
        System.out.print(a + ", нажмите Enter для продолжения.");
        String waiting = sc.nextLine();
    }

    void create(){
        try {
            boolean created = f.createNewFile();
            if (created)
                Wait("Файл создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void write() throws ParserConfigurationException, IOException {
        try (FileOutputStream fos = new FileOutputStream(f)) {
            byte[] buffer = d.WV.getBytes();
            fos.write(buffer, 0, buffer.length);
            Wait("Данные записаны в файл");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void read(){
        try (FileInputStream fin = new FileInputStream(f)) {
            System.out.println("Размер файла: " + fin.available() + " байтов");
            System.out.print("Содержимое: ");
            int i = -1;
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println("\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void delete(){
        boolean deleted = f.delete();
        if (deleted)
            Wait("Файл удален");
    }
}
