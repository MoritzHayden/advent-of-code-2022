package com.haydenmoritz.aoc2022.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class Utils {
    public static Stream<String> readFile(int day) {
        try {
            return Files.lines(Path.of(
                    "src/main/java/com/haydenmoritz/aoc2022/input/Day" + convertDayIntToString(day) + ".txt"));
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
}
