package dev.hmoritz.aoc2022.days;

import org.apache.commons.lang3.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static dev.hmoritz.aoc2022.utils.Utils.*;

public class Day12 implements IDay {
    int dayNumber = 12;
    List<String> dayInput = readFile(dayNumber);
    final int START = 83;
    final int END = 69;
    Point startPoint;
    Point endPoint;

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        // Parse the input into a graph representation
        List<List<Integer>> graph = parseGraph(dayInput);
        // TODO: Find shortest path using either Dijkstra's algorithm or BFS
        int shortestPath = findShortestPath(graph, startPoint, endPoint);
        return String.valueOf(shortestPath);
    }

    private String solvePart2() {
        throw new NotImplementedException();
    }

    private List<List<Integer>> parseGraph(List<String> input) {
        // Use ASCII decimal representation for node value
        //  S = 83 | E = 69 | [a-z] = [97-122]
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            List<Integer> row = new ArrayList<>();
            char[] line = input.get(i).toCharArray();
            for (int j = 0; j < line.length; j++) {
                // Add node to row
                int node = line[j];
                row.add(node);

                // Record start and end node coordinates
                if (node == 83) startPoint = new Point(i, j);
                if (node == 69) endPoint = new Point(i, j);
            }
            graph.add(row);
        }
        return graph;
    }

    private int findShortestPath(List<List<Integer>> graph, Point start, Point end) {
        // TODO: STUB
        return 0;
    }
}
