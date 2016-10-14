package ru.innopolis.university.dsa.ga1.task3.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileUtils {
    public static void writeToFile(String data, String filename) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(data);
        }
    }
}
