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

public class customXML extends customFile{

    customXML(String filename) {
        super(filename);
    }
    void writeDocument(Document document, String path) throws TransformerFactoryConfigurationError, IOException {
        Transformer trf = null;
        DOMSource src = null;
        FileOutputStream fos = null;
        try {
            trf = TransformerFactory.newInstance()
                    .newTransformer();
            src = new DOMSource(document);
            fos = new FileOutputStream(path);

            StreamResult result = new StreamResult(fos);
            trf.transform(src, result);
        } catch (TransformerException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            fos.close();
        }
    }
    @Override
    void write() throws ParserConfigurationException, IOException {

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите первую строку: ");
        String fs = sc.nextLine();
        System.out.print("Введите вторую строку: ");
        String ss = sc.nextLine();
        System.out.print("Введите число: ");
        int si = sc.nextInt();
        System.out.print("Введите 1 или 0: ");
        boolean ftf = sc.nextInt() == 1 ? true : false;

        d = new Data(fs,ss,si,ftf);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElement("data");
        Element fss = doc.createElement("f_string");
        Element sss = doc.createElement("s_string");
        Element in = doc.createElement("number");
        Element tf = doc.createElement("true_or_false");
        fss.setTextContent(d.WV);
        sss.setTextContent(d.WV2);
        in.setTextContent(d.SN+"");
        tf.setTextContent(d.STF ? "true" : "false");

        root.appendChild(fss);
        root.appendChild(sss);
        root.appendChild(in);
        root.appendChild(tf);
        doc.appendChild(root);

        if (doc != null)
            writeDocument(doc, f.getPath());
    }
    @Override
    void delete() {
        try {
            Files.delete(Paths.get(f.getPath()));
        } catch (IOException x) {
            System.err.println(x);
        } finally {
            Wait("Файл удален");
        }
    }
}
