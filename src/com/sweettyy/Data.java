package com.sweettyy;

public class Data { // * Наш класс с произвольными данными для записи их в файлы

    String WV; // * Строка
    String WV2; // * Строка 2
    int SN; // * Число
    boolean STF; // * Логическое значение

    // * Конструкторы
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

    // * Вывод содержимого
    void displayData() {
        System.out.println("Содержимое объекта:");
        System.out.print("Строка 1 - " + WV + "\n");        System.out.print("Строка 2 - " + WV2 + "\n");
        System.out.print("Число - " + SN + "\n");
        System.out.print("Утверждение - " + STF + "\n");
    }
}