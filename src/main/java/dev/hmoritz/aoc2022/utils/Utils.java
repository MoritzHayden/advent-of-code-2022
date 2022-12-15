package dev.hmoritz.aoc2022.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class Utils {
    public static List<String> readFile(int day) {
        try {
            return Files.lines(Path.of(
                    "src/main/resources/input/Day" + convertDayIntToString(day) + ".txt")).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertDayIntToString(int day) {
        if (0 < day && day < 10) {
            return "0" + day;
        }
        return String.valueOf(day);
    }

    public static void printSolutionMessage(int day, int part, String solution) {
        System.out.println("Solution (Day " + day + "." + part + "): " + solution);
    }

    public static int getValueOfCharacter(char character) {
        return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(character) + 1;
    }

    public static String reverseString(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output = input.charAt(i) + output;
        }
        return output;
    }
}
