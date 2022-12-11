package com.haydenmoritz.aoc2022.days;

import java.util.ArrayList;
import java.util.Collections;
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
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        grid = parseInput(dayInput);
        maxColSize = grid.size() - 1;
        maxRowSize = grid.get(0).size() - 1;
        int visibleTrees = 0;

        for (int row = 0; row < grid.size(); row++) {
            for (int col = 0; col < grid.get(row).size(); col++) {
                int height = grid.get(row).get(col);
                if (directionsVisible(height, row, col) > 0) {
                    visibleTrees++;
                }
            }
        }

        return String.valueOf(visibleTrees);
    }

    private String solvePart2() {
        grid = parseInput(dayInput);
        maxColSize = grid.size() - 1;
        maxRowSize = grid.get(0).size() - 1;
        int maxScenicScore = 0;

        for (int row = 0; row < grid.size(); row++) {
            for (int col = 0; col < grid.get(row).size(); col++) {
                // Parse grid on every iteration. This makes it work but I don't know why ¯\_(ツ)_/¯
                grid = parseInput(dayInput);
                int height = grid.get(row).get(col);
                int scenicScore = scenicScore(height, row, col);
                maxScenicScore = Math.max(maxScenicScore, scenicScore);
            }
        }

        return String.valueOf(maxScenicScore);
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

    /**
     * @param height current tree height
     * @param row    current row index
     * @param col    current column index
     * @return how many directions tree is visible from (in range 0-4)
     */
    private int directionsVisible(int height, int row, int col) {
        // Create tree arrays for each direction and compile into master list (left, right, up, down)
        List<Integer> leftTrees = generateLeftTrees(row, col);
        List<Integer> rightTrees = generateRightTrees(row, col);
        List<Integer> upTrees = generateUpTrees(row, col);
        List<Integer> downTrees = generateDownTrees(row, col);
        List<List<Integer>> directions = List.of(leftTrees, rightTrees, upTrees, downTrees);

        // For each direction, check if height is the greatest value
        int visibleDirectionCount = 0;
        for (List<Integer> direction : directions) {
            visibleDirectionCount += isValueGreaterThanAllElements(height, direction);
        }

        return visibleDirectionCount;
    }

    private int scenicScore(int height, int row, int col) {
        // Create tree arrays for each direction and compile into master list (left, right, up, down)
        List<Integer> leftTrees = generateLeftTrees(row, col);
        List<Integer> rightTrees = generateRightTrees(row, col);
        List<Integer> upTrees = generateUpTrees(row, col);
        List<Integer> downTrees = generateDownTrees(row, col);

        // For each direction, calculate scenic score
        int leftScenicScore = calculateDirectionScore(height, leftTrees);
        int rightScenicScore = calculateDirectionScore(height, rightTrees);
        int upScenicScore = calculateDirectionScore(height, upTrees);
        int downScenicScore = calculateDirectionScore(height, downTrees);
        int totalScenicScore = leftScenicScore * rightScenicScore * upScenicScore * downScenicScore;

        return totalScenicScore;
    }

    private int calculateDirectionScore(int value, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (value <= list.get(i)) {
                return i + 1;
            }
        }
        return list.size();
    }

    private int isValueGreaterThanAllElements(int value, List<Integer> list) {
        for (int item : list) {
            if (value <= item) {
                return 0;
            }
        }
        return 1;
    }

    private List<Integer> generateLeftTrees(int row, int col) {
        List<Integer> lefTrees = grid.get(row).subList(0, col);
        Collections.reverse(lefTrees);
        return lefTrees;
    }

    private List<Integer> generateRightTrees(int row, int col) {
        return grid.get(row).subList(col + 1, grid.get(row).size());
    }

    private List<Integer> generateUpTrees(int row, int col) {
        List<Integer> upTrees = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            upTrees.add(grid.get(i).get(col));
        }
        Collections.reverse(upTrees);
        return upTrees;
    }

    private List<Integer> generateDownTrees(int row, int col) {
        List<Integer> downTrees = new ArrayList<>();
        for (int i = row + 1; i < grid.size(); i++) {
            downTrees.add(grid.get(i).get(col));
        }
        return downTrees;
    }
}
