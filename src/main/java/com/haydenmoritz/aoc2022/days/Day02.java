package com.haydenmoritz.aoc2022.days;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.haydenmoritz.aoc2022.utils.Utils.*;

public class Day02 implements IDay {
    int dayNumber = 2;
    List<String> dayInput = readFile(dayNumber);
    Map<String, Integer> combos = Map.of(
            "A", 1,
            "B", 2,
            "C", 3,
            "X", 1,
            "Y", 2,
            "Z", 3);

    ArrayList<Integer> scores = new ArrayList<>();

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        for (String line : dayInput) {
            int score = 0;
            String[] plays = line.split(" ");
            int opponentPlay = combos.get(plays[0]);
            int myPlay = combos.get(plays[1]);

            if (opponentPlay == myPlay) {
                score += 3;
            } else if (myPlay == 3 && opponentPlay == 1) {
                score += 0;
            } else if (myPlay == 1 && opponentPlay == 3) {
                score += 6;
            } else if (opponentPlay < myPlay) {
                score += 6;
            }

            score += myPlay;
            scores.add(score);
        }

        return String.valueOf(scores.stream()
                .mapToInt(Integer::intValue)
                .sum());
    }

    private String solvePart2() {
        scores.clear();
        for (String line : dayInput) {
            int score = 0;
            String[] plays = line.split(" ");
            int opponentPlay = combos.get(plays[0]);
            int myPlay = combos.get(plays[1]);

            if (myPlay == 1) {
                // Need to lose
                myPlay = opponentPlay - 1;
                if (myPlay < 1) {
                    score += 3;
                } else {
                    score += myPlay;
                }
                score += 0;
            } else if (myPlay == 2) {
                // Need to draw
                score += opponentPlay;
                score += 3;
            } else if (myPlay == 3) {
                // Need to win
                myPlay = opponentPlay + 1;
                if (myPlay > 3) {
                    score += 1;
                } else {
                    score += myPlay;
                }
                score += 6;
            }

            scores.add(score);
        }

        return String.valueOf(scores.stream()
                .mapToInt(Integer::intValue)
                .sum());
    }
}
