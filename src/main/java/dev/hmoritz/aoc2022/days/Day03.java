package dev.hmoritz.aoc2022.days;

import dev.hmoritz.aoc2022.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day03 implements IDay {
    int dayNumber = 3;
    List<String> dayInput = Utils.readFile(dayNumber);

    @Override
    public void solveAll() {
        Utils.printSolutionMessage(dayNumber, 1, solvePart1());
        Utils.printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        ArrayList<Integer> priorities = new ArrayList<>();
        for (String rucksack : dayInput) {
            final int mid = rucksack.length() / 2;
            String[] parts = {rucksack.substring(0, mid), rucksack.substring(mid)};
            String compartmentOne = parts[0];
            String compartmentTwo = parts[1];

            for (char item : compartmentOne.toCharArray()) {
                if (compartmentTwo.contains(String.valueOf(item))) {
                    priorities.add(Utils.getValueOfCharacter(item));
                    break;
                }
            }
        }

        return String.valueOf(priorities
                .stream()
                .mapToInt(Integer::intValue)
                .sum());
    }

    private String solvePart2() {
        ArrayList<Integer> priorities = new ArrayList<>();
        for (int i = 0; i < dayInput.size(); i += 3) {
            String rucksackOne = dayInput.get(i);
            String rucksackTwo = dayInput.get(i+1);
            String rucksackThree = dayInput.get(i+2);

            for (char item : rucksackOne.toCharArray()) {
                if (rucksackTwo.contains(String.valueOf(item)) && rucksackThree.contains(String.valueOf(item))) {
                    priorities.add(Utils.getValueOfCharacter(item));
                    break;
                }
            }
        }

        return String.valueOf(priorities
                .stream()
                .mapToInt(Integer::intValue)
                .sum());
    }
}
