package com.haydenmoritz.aoc2022.days;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.haydenmoritz.aoc2022.utils.Utils.*;

public class Day01 implements IDay {
    int dayNumber = 1;
    List<String> dayInput = readFile(dayNumber);
    ArrayList<Integer> elfCalories = new ArrayList<>();

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        int currentCalories = 0;

        for (String item : dayInput) {
            try {
                currentCalories = currentCalories + Integer.parseInt(item);
            } catch (NumberFormatException ex) {
                elfCalories.add(currentCalories);
                currentCalories = 0;
            }
        }

        return elfCalories.stream()
                .max(Integer::compare)
                .get()
                .toString();
    }

    private String solvePart2() {
        return elfCalories.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.summingInt(Integer::intValue))
                .toString();
    }
}
