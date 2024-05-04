package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Получаем текущую рабочую директорию
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Текущая директория: " + currentDirectory);

        // Создаем объект File, представляющий текущую директорию
        File currentDir = new File(currentDirectory + "/src");

        // Получаем список дочерних папок текущей директории
        File[] childDirectories = currentDir.listFiles(File::isDirectory);

        // Выводим список дочерних папок
        System.out.println("Дочерние папки:");
        for (File dir : childDirectories) {
            System.out.println(dir.getName());
        }
    }
}