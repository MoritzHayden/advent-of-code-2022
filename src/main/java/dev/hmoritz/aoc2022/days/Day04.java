package dev.hmoritz.aoc2022.days;

import dev.hmoritz.aoc2022.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day04 implements IDay {
    int dayNumber = 4;
    List<String> dayInput = Utils.readFile(dayNumber);

    @Override
    public void solveAll() {
        Utils.printSolutionMessage(dayNumber, 1, solvePart1());
        Utils.printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        int containedPairs = 0;
        for (String line : dayInput) {
            String[] sectionAssignments = line.split(",");
            List<Integer> rangeOne = Arrays.stream(sectionAssignments[0].split("-"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Integer> rangeTwo = Arrays.stream(sectionAssignments[1].split("-"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            if (rangeOne.get(0) <= rangeTwo.get(0) && rangeOne.get(1) >= rangeTwo.get(1)) {
                containedPairs++;
            } else if (rangeTwo.get(0) <= rangeOne.get(0) && rangeTwo.get(1) >= rangeOne.get(1)) {
                containedPairs++;
            }
        }

        return String.valueOf(containedPairs);
    }

    private String solvePart2() {
        int overlappingPairs = 0;
        for (String line : dayInput) {
            String[] sectionAssignments = line.split(",");
            List<Integer> rangeOne = Arrays.stream(sectionAssignments[0].split("-"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Integer> rangeTwo = Arrays.stream(sectionAssignments[1].split("-"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int i = rangeOne.get(0); i <= rangeOne.get(1); i++) {
                if (rangeTwo.get(0) <= i && i <= rangeTwo.get(1)) {
                    overlappingPairs++;
                    break;
                }
            }
        }

        return String.valueOf(overlappingPairs);
    }
}
