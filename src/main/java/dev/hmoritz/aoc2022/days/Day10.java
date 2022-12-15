package dev.hmoritz.aoc2022.days;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dev.hmoritz.aoc2022.utils.Utils.*;

public class Day10 implements IDay {
    int dayNumber = 10;
    List<String> dayInput = readFile(dayNumber);
    List<Integer> xRegisters = new ArrayList<>();
    List<Integer> signalStrengths = new ArrayList<>();

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        int cycle = 0;
        int xReg = 1;

        for (String line : dayInput) {
            Map<Instruction, Integer> instruction = parseInstruction(line);

            if (instruction.containsKey(Instruction.NOOP)) {
                // Cycle once
                cycle++;
                runCycle(xRegisters, signalStrengths, cycle, xReg);
            } else if (instruction.containsKey(Instruction.ADDX)) {
                // Cycle one
                cycle++;
                runCycle(xRegisters, signalStrengths, cycle, xReg);

                // Cycle two
                cycle++;
                runCycle(xRegisters, signalStrengths, cycle, xReg);

                // Perform operation
                xReg += instruction.get(Instruction.ADDX);
            }
        }

        return String.valueOf(sumNeededCycles(signalStrengths));
    }

    private String solvePart2() {
        StringBuilder sb = new StringBuilder();
        sb.append("BUCACBUZ (Image generated below ↓)\n");
        for (int i = 0; i < 240; i++) {
            int currentXReg = xRegisters.get(i);

            if (i % 40 == currentXReg - 1 || i % 40 == currentXReg || i % 40 == currentXReg + 1) {
                sb.append("#");
            } else {
                sb.append(".");
            }

            if (i == 39 || i == 79 || i == 119 || i == 159 || i == 199) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private int calculateSignalStrength(int cycle, int xReg) {
        return cycle * xReg;
    }

    private void runCycle(List<Integer> xRegisters, List<Integer> signalStrengths, int cycle, int xReg) {
        xRegisters.add(xReg);
        signalStrengths.add(calculateSignalStrength(cycle, xReg));
    }

    private int sumNeededCycles(List<Integer> signalStrengths) {
        return signalStrengths.get(19) +
                signalStrengths.get(59) +
                signalStrengths.get(99) +
                signalStrengths.get(139) +
                signalStrengths.get(179) +
                signalStrengths.get(219);
    }

    private Map<Instruction, Integer> parseInstruction(String line) {
        String[] splitLine = line.split(" ");
        if (splitLine[0].equals("noop")) return Map.of(Instruction.NOOP, 0);
        if (splitLine[0].equals("addx")) return Map.of(Instruction.ADDX, Integer.parseInt(splitLine[1]));

        throw new RuntimeException("Parsed invalid input");
    }

    enum Instruction {
        NOOP,
        ADDX
    }
}
