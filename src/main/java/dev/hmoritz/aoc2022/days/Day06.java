package dev.hmoritz.aoc2022.days;

import java.util.HashSet;
import java.util.List;

import static dev.hmoritz.aoc2022.utils.Utils.*;

public class Day06 implements IDay {
    int dayNumber = 6;
    List<String> dayInput = readFile(dayNumber);

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        return decodeSignal(4);
    }

    private String solvePart2() {
        return decodeSignal(14);
    }

    private String decodeSignal(int markerSize) {
        char[] input = dayInput.get(0).toCharArray();
        for (int i = markerSize-1; i < input.length; i++) {
            HashSet<Character> seenChars = new HashSet<>();
            for (int j = 0; j < markerSize; j++) {
                seenChars.add(input[i-j]);
            }

            if (seenChars.size() == markerSize) {
                return String.valueOf(i+1);
            }
        }
        return null;
    }
}
