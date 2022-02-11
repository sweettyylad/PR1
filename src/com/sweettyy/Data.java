package com.sweettyy;

public class Data {

    String WV;
    String WV2;
    int SN;
    boolean STF;

    Data() {
        WV = "";
        WV2 = "";
        SN = 0;
        STF = false;
    }
    Data(String w){
        WV = w;
        WV2 = "";
        SN = 0;
        STF = false;
    }
    Data(String f, String s, int t, boolean fr) {
        WV = f;
        WV2 = s;
        SN = t;
        STF = fr;
    }

    void displayData() {
        System.out.println("Содержимое объекта:");
        System.out.print("Строка 1 - " + WV + "\n");
        System.out.print("Строка 2 - " + WV2 + "\n");
        System.out.print("Число - " + SN + "\n");
        System.out.print("Утверждение - " + STF + "\n");
    }
}