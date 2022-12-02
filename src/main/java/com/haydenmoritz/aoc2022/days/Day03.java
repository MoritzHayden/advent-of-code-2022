package com.haydenmoritz.aoc2022.days;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

import static com.haydenmoritz.aoc2022.utils.Utils.*;

public class Day03 implements IDay {
    int dayNumber = 3;
    List<String> dayInput = readFile(dayNumber);

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        throw new NotImplementedException();
    }

    private String solvePart2() {
        throw new NotImplementedException();
    }
}
