package dev.hmoritz.aoc2022.days;

import dev.hmoritz.aoc2022.models.Monkey;

import java.util.*;
import java.util.stream.Collectors;

import static dev.hmoritz.aoc2022.utils.Utils.*;

public class Day11 implements IDay {
    int dayNumber = 11;
    List<String> dayInput = readFile(dayNumber);
    List<Monkey> monkeys = new ArrayList<>();

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        // Parse input into Monkey objects
        monkeys = parseMonkeys(dayInput);
        long modSumNum = calculateModSum(monkeys);

        // Steps:
        // For each of 20 rounds
        //   For every item the monkey is holding
        //     Divide worry level by 3 and round down
        //     Perform operation using increaseWorry method
        //     Test for condition
        //     Find monkey with id equal to result of condition
        //     Call catchItem
        //     Remove head queue item from current monkey. If empty, break.
        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.getItems().isEmpty()) {
                    long worryLevel = monkey.getItems().remove();
                    monkey.inspectItem();
                    worryLevel = monkey.increaseWorry(worryLevel);
                    worryLevel = worryLevel / 3L;
                    int monkeyIdToThrowTo = monkey.findMonkeyIdToThrowTo(worryLevel);
                    Monkey monkeyToThrowTo = monkeys.stream().filter(m -> m.getId() == monkeyIdToThrowTo).findFirst().orElse(null);
                    monkeyToThrowTo.catchItem(worryLevel);
                }
            }
        }

        // Calculate monkey business value
        return String.valueOf(calculateMonkeyBusiness(monkeys));
    }

    private String solvePart2() {
        // Parse input into Monkey objects
        monkeys = parseMonkeys(dayInput);
        long modSumNum = calculateModSum(monkeys);

        // Steps:
        // For each of 10000 rounds
        //   For every item the monkey is holding
        //     Perform operation using increaseWorry method
        //     Mod worry level by product of all test condition operands
        //     Test for condition
        //     Find monkey with id equal to result of condition
        //     Call catchItem
        //     Remove head queue item from current monkey. If empty, break.
        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.getItems().isEmpty()) {
                    Long worryLevel = monkey.getItems().remove();
                    monkey.inspectItem();
                    worryLevel = monkey.increaseWorry(worryLevel);
                    worryLevel %= modSumNum;
                    int monkeyIdToThrowTo = monkey.findMonkeyIdToThrowTo(worryLevel);
                    Monkey monkeyToThrowTo = monkeys.stream().filter(m -> m.getId() == monkeyIdToThrowTo).findFirst().orElse(null);
                    monkeyToThrowTo.catchItem(worryLevel);
                }
            }
        }

        // Calculate monkey business value
        return String.valueOf(calculateMonkeyBusiness(monkeys));
    }

    private long calculateMonkeyBusiness(List<Monkey> monkeys) {
        return monkeys.stream()
                .sorted(Comparator.comparingLong(Monkey::getItemsSeen).reversed())
                .mapToLong(m -> m.getItemsSeen())
                .limit(2)
                .reduce(1L, (a, b) -> a * b);
    }

    private long calculateModSum(List<Monkey> monkeys) {
        return monkeys.stream()
                .mapToLong(m -> m.getThrowConditionOperand())
                .reduce(1L, (a, b) -> a * b);
    }

    private List<Monkey> parseMonkeys(List<String> input) {
        List<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 7) {
            int id = Integer.parseInt(input.get(i).split(" ")[1].replace(":", ""));
            Queue<Long> items = Arrays.stream(input.get(i + 1)
                            .trim()
                            .replace("Starting items: ", "")
                            .replace(",", "")
                            .split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toCollection(LinkedList::new));
            String[] parsedOperation = input.get(i + 2).replace("Operation: new = old", "").trim().split(" ");
            Map<String, Integer> operation;
            try {
                operation = Map.of(parsedOperation[0], Integer.parseInt(parsedOperation[1]));
            } catch (NumberFormatException ex) {
                operation = Map.of(parsedOperation[0], -1);
            }
            int throwConditionOperand = Integer.parseInt(input.get(i + 3).replace("Test: divisible by", "").trim());
            int trueThrowId = Integer.parseInt(input.get(i + 4).replace("If true: throw to monkey", "").trim());
            int falseThrowId = Integer.parseInt(input.get(i + 5).replace("If false: throw to monkey", "").trim());
            monkeys.add(new Monkey(id, items, operation, throwConditionOperand, trueThrowId, falseThrowId));
        }

        return monkeys;
    }

    private void printMonkeyItemInspectionCount(int round) {
        System.out.println("== After round " + round + " ==");
        System.out.println("Monkey " + monkeys.get(0).getId() + " inspected items " + monkeys.get(0).getItemsSeen() + " times.");
        System.out.println("Monkey " + monkeys.get(1).getId() + " inspected items " + monkeys.get(1).getItemsSeen() + " times.");
        System.out.println("Monkey " + monkeys.get(2).getId() + " inspected items " + monkeys.get(2).getItemsSeen() + " times.");
        System.out.println("Monkey " + monkeys.get(3).getId() + " inspected items " + monkeys.get(3).getItemsSeen() + " times.\n");
    }
}