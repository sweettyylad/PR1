package com.sweettyy;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;

public class customJSON extends customFile{

    customJSON(String filename, String content, String content2, int num, boolean adv) {
        super(filename, content);
        d.WV2 = content2;
        d.SN = num;
        d.STF = adv;
    }

    @Override
    void write() {
        Gson gson = new Gson();
        String json = gson.toJson(d);
        try (FileOutputStream fos = new FileOutputStream(f)) {
            byte[] buffer = json.getBytes();
            fos.write(buffer, 0, buffer.length);
            Wait("Данные записаны в файл");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
