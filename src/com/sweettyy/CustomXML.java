package com.sweettyy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

// !!!ALARM IT'S VERY HARD!!!
public class CustomXML extends CustomFile {
    // * Наследуем от того же класса обычного файла
    CustomXML(String filename) {
        super(filename);
    } // * Конструктор

    // * Метод для записи XML-данных (Абстрактного документа) в файл
    void writeDocument(Document document, String path) throws TransformerFactoryConfigurationError, IOException {
        // * Transformer помогает преобразовать виртуальное древо (документ)
        // * в результат, который можно записать в StreamResult
        Transformer trf = null;
        // * DOMSource будет содержать в себе исходное древо документа
        // * для дальнейшей записи в StreamResult
        DOMSource src = null;
        // * Поток для записи в файл
        FileOutputStream fos = null;
        try {
            trf = TransformerFactory.newInstance()
                    .newTransformer(); // * В переменную типа Transformer создается и записывается экземпляр класса Transformer
            src = new DOMSource(document); // * Вызываем конструктор класса DOMSource, переменная будет хранить в себе наше виртуальное дерево
            fos = new FileOutputStream(path); // * Открываем поток по указанному пути
            StreamResult result = new StreamResult(fos); // * Вызов конструктора, создание экземпляра

            // * Преобразование наших данных (дерева с данными) производится через класс
            // * Transformer и записывается в переменную result, которая является экземпляром
            // * класса StreamResult и держателем преобразованных данных
            trf.transform(src, result);
        } catch (TransformerException e) {
            e.printStackTrace(System.out); // * Перехват ошибки произошедшей в процессе преобразования
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            fos.close();
        }
    }

    @Override
    void write() throws ParserConfigurationException, IOException {
        // * Переопределние метода write()
        // * Считываем данные с консоли и записываем их в переменные
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите первую строку: ");
        String fs = sc.nextLine();
        System.out.print("Введите вторую строку: ");
        String ss = sc.nextLine();
        System.out.print("Введите число: ");
        int si = sc.nextInt();
        System.out.print("Введите 1 или 0: ");
        boolean ftf = sc.nextInt() == 1 ? true : false;

        // * Далее записываем данные из переменных в экземпляр нашего класса произвольных данных
        d = new Data(fs,ss,si,ftf);

        // * Типа почистили после себя, можно ещё System.gc() вызвать по приколу, и приедет сборщик мусора
        fs = null; ss = null; si = 0; ftf = false;

        // * Создаем экземпляры классов
        // * DocumentBuilderFactory, DocumentBuilder и Document
        // * которые позволят создать DOM дерево из XML объектов
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElement("data"); // * Создаем корневой тег
        Element fss = doc.createElement("f_string"); // * Создаем тег для строки 1
        Element sss = doc.createElement("s_string"); // * Создаем тег для строки 2
        Element in = doc.createElement("number"); // * Создаем тег для числа
        Element tf = doc.createElement("true_or_false"); // * Создаем тег для логического значения

        fss.setTextContent(d.WV); // * В тег для строки 1 записываем значение свойства WV объекта класса произвольных данных
        sss.setTextContent(d.WV2); // * В тег для строки 2 - свойства WV2
        in.setTextContent(d.SN+""); // * В тег для числа - свойства SN, тут же преобразуя int в string
        tf.setTextContent(d.STF ? "true" : "false"); // * В тег для логического значения - true или false в зависимости от содержимого переменной

        root.appendChild(fss); // * К корню
        root.appendChild(sss); // * присоединяем
        root.appendChild(in); // * все
        root.appendChild(tf); // * элементы
        doc.appendChild(root); // * последовательно (можно и нет, главное соблюдать вложенность там, где надо)

        if (doc != null) // * Отправляем собранный абстрактный документик и путь к нашему файлу
            writeDocument(doc, f.getPath()); // * в функцию для записи данных в этот файл
    }

    // * Перезапись метода delete()
    @Override // * т.к. в данном случае легче было не использовать метод удаления класса File
    void delete() {
        try {
            Files.delete(Paths.get(f.getPath())); // * Удаляем файл
        } catch (IOException x) {
            System.err.println(x); // * [1]
        } finally {
            Wait("Файл удален"); // * Успешное удаление
        }
    }
}
