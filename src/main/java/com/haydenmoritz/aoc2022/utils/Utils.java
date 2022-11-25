package com.haydenmoritz.aoc2022.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Utils {
    public static List<String> readFile(int day) {
        String dayStr = convertDayIntToString(day);
        List<String> contents;

        try (Stream<String> stream = Files.lines(Path.of(
                "src/main/java/com/haydenmoritz/aoc2022/input/Day" + dayStr + ".txt"))) {
            contents = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return contents;
    }

    public static String convertDayIntToString(int day) {
        if (0 < day && day < 10) {
            return "0" + day;
        }

        return String.valueOf(day);
    }
}
