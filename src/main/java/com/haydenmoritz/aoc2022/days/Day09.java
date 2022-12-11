package com.haydenmoritz.aoc2022.days;

import org.apache.commons.lang3.NotImplementedException;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.haydenmoritz.aoc2022.utils.Utils.*;

public class Day09 implements IDay {
    int dayNumber = 9;
    List<String> dayInput = readFile(dayNumber);
    Set<String> tailVisitedCoordinates = new HashSet<>();
    Point currentHead = new Point(0, 0);
    Point currentTail = new Point(0, 0);

    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        tailVisitedCoordinates.add(currentTail.toString());

        for (String line : dayInput) {
            String[] parsedLine = line.split(" ");
            Direction lineDirection = getLineDirection(parsedLine[0]);
            int steps = Integer.parseInt(parsedLine[1]);
            simulateMovement(lineDirection, steps);
        }

        return String.valueOf(tailVisitedCoordinates.size());
    }

    private String solvePart2() {
        throw new NotImplementedException();
    }

    private void simulateMovement(Direction direction, int steps) {
        for (int i = 0; i < steps; i++) {
            movePoint(currentHead, direction);
            Direction tailMovementDirection = getTailMovement(currentHead, currentTail);
            movePoint(currentTail, tailMovementDirection);
            tailVisitedCoordinates.add(new Point(currentTail.x, currentTail.y).toString());
        }
    }

    private Direction getTailMovement(Point head, Point tail) {
        if (head.x == tail.x && head.y == tail.y) return Direction.STAY;
        if (head.x == tail.x && head.y == tail.y - 1) return Direction.STAY;
        if (head.x == tail.x && head.y == tail.y + 1) return Direction.STAY;
        if (head.x == tail.x - 1 && head.y == tail.y) return Direction.STAY;
        if (head.x == tail.x + 1 && head.y == tail.y) return Direction.STAY;
        if (head.x == tail.x - 1 && head.y == tail.y - 1) return Direction.STAY;
        if (head.x == tail.x - 1 && head.y == tail.y + 1) return Direction.STAY;
        if (head.x == tail.x + 1 && head.y == tail.y - 1) return Direction.STAY;
        if (head.x == tail.x + 1 && head.y == tail.y + 1) return Direction.STAY;

        // Check vertical lines
        if (head.x == tail.x && head.y == tail.y - 2) return Direction.DOWN;
        if (head.x == tail.x && head.y == tail.y + 2) return Direction.UP;

        // Check horizontal lines
        if (head.x == tail.x - 2 && head.y == tail.y) return Direction.LEFT;
        if (head.x == tail.x + 2 && head.y == tail.y) return Direction.RIGHT;

        // Check diagonal lines
        if (head.x > tail.x && head.y > tail.y) return Direction.UP_RIGHT;
        if (head.x < tail.x && head.y > tail.y) return Direction.UP_LEFT;
        if (head.x > tail.x && head.y < tail.y) return Direction.DOWN_RIGHT;
        if (head.x < tail.x && head.y < tail.y) return Direction.DOWN_LEFT;

        // No other condition should be true
        throw new RuntimeException("Illegal head and/or tail positioning");
    }

    private void movePoint(Point point, Direction direction) {
        if (direction == Direction.UP) {
            point.move(point.x, point.y + 1);
        } else if (direction == Direction.DOWN) {
            point.move(point.x, point.y - 1);
        } else if (direction == Direction.LEFT) {
            point.move(point.x - 1, point.y);
        } else if (direction == Direction.RIGHT) {
            point.move(point.x + 1, point.y);
        } else if (direction == Direction.UP_RIGHT) {
            point.move(point.x + 1, point.y + 1);
        } else if (direction == Direction.UP_LEFT) {
            point.move(point.x - 1, point.y + 1);
        } else if (direction == Direction.DOWN_LEFT) {
            point.move(point.x - 1, point.y - 1);
        } else if (direction == Direction.DOWN_RIGHT) {
            point.move(point.x + 1, point.y - 1);
        } else if (direction == Direction.STAY) {
            point.move(point.x, point.y);
        }
    }

    private Direction getLineDirection(String line) {
        if (line.equals("R")) {
            return Direction.RIGHT;
        } else if (line.equals("U")) {
            return Direction.UP;
        } else if (line.equals("L")) {
            return Direction.LEFT;
        } else if (line.equals("D")) {
            return Direction.DOWN;
        } else {
            throw new RuntimeException("Parsed invalid direction");
        }
    }

    enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN,
        UP_RIGHT,
        UP_LEFT,
        DOWN_LEFT,
        DOWN_RIGHT,
        STAY
    }
}
