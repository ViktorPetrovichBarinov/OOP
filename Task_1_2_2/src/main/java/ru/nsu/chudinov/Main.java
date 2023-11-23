package ru.nsu.chudinov;

import java.util.ArrayList;
import java.util.List;

/**
 * Some text.
 */
public class Main {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();

        // Добавление элементов в список
        myList.add("Элемент 1");
        myList.add("Элемент 2");
        myList.add("Элемент 3");

        List<Integer> myIntList = new ArrayList<>();
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(3);

        System.out.println("Список строк до изменения: " + myList);
        System.out.println("Список целых чисел до изменения: " + myIntList);

        // Вызов метода, изменяющего списки
        modifyLists(myList, myIntList);

        System.out.println("Список строк после изменения: " + myList);
        System.out.println("Список целых чисел после изменения: " + myIntList);
    }

    public static void modifyLists(List<String> stringList, List<Integer> intList) {
        // Ваш код для изменения списков
        stringList.add("Новый элемент");
        intList.add(42);
    }
}






































































