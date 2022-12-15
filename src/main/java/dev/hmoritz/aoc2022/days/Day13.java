package dev.hmoritz.aoc2022.days;

import dev.hmoritz.aoc2022.utils.Utils;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Stack;

public class Day13 implements IDay {
    int dayNumber = 13;
    List<String> dayInput = Utils.readFile(dayNumber);

    @Override
    public void solveAll() {
        Utils.printSolutionMessage(dayNumber, 1, solvePart1());
        Utils.printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        // Iterate over all packet pairs
        for (int i = 0; i < dayInput.size(); i += 3) {
            // Generate left packet stack
            String leftLine = dayInput.get(i);
            Stack<Character> leftStack = new Stack<>();
            leftLine.chars().mapToObj(c -> (char) c).map(leftStack::add).toArray();

            // Generate right packet stack
            String rightLine = dayInput.get(i + 1);
            Stack<Character> rightStack = new Stack<>();
            rightLine.chars().mapToObj(c -> (char) c).map(rightStack::add).toArray();

            // TODO: Use stacks to determine actual packet length
        }

        return "";
    }

    private String solvePart2() {
        throw new NotImplementedException();
    }
}
