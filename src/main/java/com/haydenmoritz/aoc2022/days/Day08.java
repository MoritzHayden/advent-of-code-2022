package com.haydenmoritz.aoc2022.days;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static com.haydenmoritz.aoc2022.utils.Utils.*;

public class Day08 implements IDay {
    int dayNumber = 8;
    List<String> dayInput = readFile(dayNumber);
    int maxColSize = 0;
    int maxRowSize = 0;
    List<List<Integer>> grid = new ArrayList<>();

    @Override
    public void solveAll() {
        grid = parseInput(dayInput);
        maxColSize = grid.size();
        maxRowSize = grid.get(0).size();
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        int visibleTrees = 0;

        for (int x = 0; x < grid.size() - 1; x++) {
            for (int y = 0; y < grid.get(x).size(); y++) {
                int currentVal = grid.get(x).get(y);
            }
        }

        return String.valueOf(visibleTrees);
    }

    private String solvePart2() {
        throw new NotImplementedException();
    }

    private List<List<Integer>> parseInput(List<String> input) {
        List<List<Integer>> grid = new ArrayList<>();
        for (String line : input) {
            ArrayList<Integer> row = new ArrayList<>();
            for (char tree : line.toCharArray()) {
                row.add(Character.getNumericValue(tree));
            }
            grid.add(row);
        }
        return grid;
    }

    private boolean atEdge(int x, int y) {
        if (x == 0 || x == maxColSize || y == 0 || y == grid.get(x).get(y) - 1) { return true; }
        return false;
    }
}
