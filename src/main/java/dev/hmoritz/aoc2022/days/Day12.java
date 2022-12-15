package dev.hmoritz.aoc2022.days;

import dev.hmoritz.aoc2022.utils.Utils;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class Day12 implements IDay {
    int dayNumber = 12;
    List<String> dayInput = Utils.readFile(dayNumber);

    @Override
    public void solveAll() {
        Utils.printSolutionMessage(dayNumber, 1, solvePart1());
        Utils.printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        // TODO:
        //   1. Parse the input into a graph representation
        //   2. Implement and run Dijkstra's algorithm on the graph
        //   3. Use the ASCII decimal representation to get edge weight (cast char to int): https://www.asciitable.com/
        //     - S = 83
        //     - E = 69
        //     - [a-z] = [97-122]
        throw new NotImplementedException();
    }

    private String solvePart2() {
        throw new NotImplementedException();
    }
}
