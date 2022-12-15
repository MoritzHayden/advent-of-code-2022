package dev.hmoritz.aoc2022.days;

import dev.hmoritz.aoc2022.utils.Utils;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class Day24 implements IDay {
    int dayNumber = 24;
    List<String> dayInput = Utils.readFile(dayNumber);

    @Override
    public void solveAll() {
        Utils.printSolutionMessage(dayNumber, 1, solvePart1());
        Utils.printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        throw new NotImplementedException();
    }

    private String solvePart2() {
        throw new NotImplementedException();
    }
}
