package dev.hmoritz.aoc2022.days;

import java.util.*;

import static dev.hmoritz.aoc2022.utils.Utils.*;

public class Day05 implements IDay {
    boolean isTest = false;
    int dayNumber = 5;
    List<String> dayInput = readFile(dayNumber);
    ArrayList<Stack<Character>> stackArr = new ArrayList<>();

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        initializeInput();
        for (String line : dayInput) {
            // Extract instruction integers from input
            int[] instructions = getInstructions(line);

            // Operate on stacks
            useCrateMover9000(instructions);
        }

        // Generate message
        return generateMessage();
    }

    private String solvePart2() {
        initializeInput();
        for (String line : dayInput) {
            // Extract instruction integers from input
            int[] instructions = getInstructions(line);

            // Operate on stacks
            useCrateMover9001(instructions);
        }

        // Generate message
        return generateMessage();
    }

    private int[] getInstructions(String line) {
        String newLine = line.replace("move ", "")
                .replace("from ", "")
                .replace("to ", "");
        return Arrays.stream(newLine.split(" ")).mapToInt(c -> Integer.parseInt(c)).toArray();
    }

    private void useCrateMover9000(int[] instructions) {
        int count = instructions[0];
        int from = instructions[1];
        int to = instructions[2];

        // Operate on stack array
        for (int i = 0; i < count; i++) {
            stackArr.get(to - 1).push(stackArr.get(from - 1).pop());
        }
    }

    private void useCrateMover9001(int[] instructions) {
        int count = instructions[0];
        int from = instructions[1];
        int to = instructions[2];

        ArrayList<Character> buffer = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            buffer.add(stackArr.get(from - 1).pop());
        }
        Collections.reverse(buffer);
        for (char b : buffer) {
            stackArr.get(to - 1).push(b);
        }
    }

    private String generateMessage() {
        String message = "";
        for (Stack<Character> stack : stackArr) {
            message = message + stack.pop();
        }
        return message;
    }

    private void initializeInput() {
        // Hardcoding crate arrangement, because the input is a nightmare
        stackArr.clear();
        if (isTest) {
            Stack<Character> stack1 = new Stack<>();
            stack1.push('Z');
            stack1.push('N');
            stackArr.add(stack1);

            Stack<Character> stack2 = new Stack<>();
            stack2.push('M');
            stack2.push('C');
            stack2.push('D');
            stackArr.add(stack2);

            Stack<Character> stack3 = new Stack<>();
            stack3.push('P');
            stackArr.add(stack3);
        } else {
            Stack<Character> stack1 = new Stack<>();
            stack1.push('D');
            stack1.push('T');
            stack1.push('W');
            stack1.push('F');
            stack1.push('J');
            stack1.push('S');
            stack1.push('H');
            stack1.push('N');
            stackArr.add(stack1);

            Stack<Character> stack2 = new Stack<>();
            stack2.push('H');
            stack2.push('R');
            stack2.push('P');
            stack2.push('Q');
            stack2.push('T');
            stack2.push('N');
            stack2.push('B');
            stack2.push('G');
            stackArr.add(stack2);

            Stack<Character> stack3 = new Stack<>();
            stack3.push('L');
            stack3.push('Q');
            stack3.push('V');
            stackArr.add(stack3);

            Stack<Character> stack4 = new Stack<>();
            stack4.push('N');
            stack4.push('B');
            stack4.push('S');
            stack4.push('W');
            stack4.push('R');
            stack4.push('Q');
            stackArr.add(stack4);

            Stack<Character> stack5 = new Stack<>();
            stack5.push('N');
            stack5.push('D');
            stack5.push('F');
            stack5.push('T');
            stack5.push('V');
            stack5.push('M');
            stack5.push('B');
            stackArr.add(stack5);

            Stack<Character> stack6 = new Stack<>();
            stack6.push('M');
            stack6.push('D');
            stack6.push('B');
            stack6.push('V');
            stack6.push('H');
            stack6.push('T');
            stack6.push('R');
            stackArr.add(stack6);

            Stack<Character> stack7 = new Stack<>();
            stack7.push('D');
            stack7.push('B');
            stack7.push('Q');
            stack7.push('J');
            stackArr.add(stack7);

            Stack<Character> stack8 = new Stack<>();
            stack8.push('D');
            stack8.push('N');
            stack8.push('J');
            stack8.push('V');
            stack8.push('R');
            stack8.push('Z');
            stack8.push('H');
            stack8.push('Q');
            stackArr.add(stack8);

            Stack<Character> stack9 = new Stack<>();
            stack9.push('B');
            stack9.push('N');
            stack9.push('H');
            stack9.push('M');
            stack9.push('S');
            stackArr.add(stack9);
        }
    }
}
