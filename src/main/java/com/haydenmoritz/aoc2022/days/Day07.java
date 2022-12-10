package com.haydenmoritz.aoc2022.days;

import com.haydenmoritz.aoc2022.models.File;
import com.haydenmoritz.aoc2022.models.FileNode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.haydenmoritz.aoc2022.utils.Utils.*;

public class Day07 implements IDay {
    int dayNumber = 7;
    List<String> dayInput = readFile(dayNumber);
    Map<File, Integer> directorySizes = new HashMap<>();

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        // Create file system POJO
        FileNode fileTreeRoot = populateTree();
        calculateTotalSize(fileTreeRoot);
        return String.valueOf(sumOfDirectoriesUnderSize(directorySizes, 100001));
    }

    private String solvePart2() {
        int spaceAvailable = 70000000;
        int spaceNeeded = 30000000;
        int spaceUsed = directorySizes.values().stream().mapToInt(Integer::intValue).max().getAsInt();
        int unusedSpace = spaceAvailable - spaceUsed;
        int spaceRequired = spaceNeeded - unusedSpace;
        int directoryToDelete = findSmallestDirectorySizeOverSize(directorySizes, spaceRequired);
        return String.valueOf(directoryToDelete);
    }

    private FileNode populateTree() {
        FileNode currentNode = new FileNode(new File("/", 0));

        for (String line : dayInput) {
            String[] parsedLine = line.split(" ");
            CommandType lineType = getCommandType(parsedLine);

            if (lineType == CommandType.CD_IN) {
                // Move to child directory if found
                File file = new File(parsedLine[2], 0);
                FileNode matchingChild = currentNode.findChild(file);
                if (matchingChild != null) {
                    currentNode = matchingChild;
                } else {
                    throw new RuntimeException("Directory \"" + parsedLine[2] + "\" not found");
                }
            } else if (lineType == CommandType.CD_UP) {
                // Set current node to parent
                currentNode = currentNode.getParent();
            } else if (lineType == CommandType.CD_ROOT) {
                // Set current node to root
                currentNode = currentNode.getRoot();
            } else if (lineType == CommandType.DIR) {
                // Add directory if it does not exist
                File file = new File(parsedLine[1], 0);
                if (!currentNode.childContainsFile(file)) {
                    currentNode.addChild(file);
                }
            } else if (lineType == CommandType.FILE) {
                // Add file if it does not exist
                File file = new File(parsedLine[1], Integer.parseInt(parsedLine[0]));
                if (!currentNode.childContainsFile(file)) {
                    currentNode.addChild(file);
                }
            } else if (lineType == CommandType.LS) {
                // Do nothing
            } else {
                // Throw exception due to parsing error
                throw new RuntimeException("Parsed unexpected " + lineType.toString().toLowerCase() + " input: " + line);
            }
        }

        return currentNode.getRoot();
    }

    private int calculateTotalSize(FileNode root) {
        if (!root.getData().isDirectory()) { return root.getData().getSize(); }
        int totalSize = 0;
        for (FileNode child : root.getChildren()) {
            totalSize += calculateTotalSize(child);
        }
        if (root.getData().isDirectory()) {
            directorySizes.put(root.getData(), totalSize);
        }
        return totalSize + root.getData().getSize();
    }

    private int sumOfDirectoriesUnderSize(Map<File, Integer> directories, int maxSize) {
        return directories
                .values()
                .stream()
                .filter(size -> size < maxSize)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int findSmallestDirectorySizeOverSize(Map<File, Integer> directories, int minSize) {
        return directories
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= minSize)
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getValue).get();
    }

    private CommandType getCommandType(String[] parsedLine) {
        if (parsedLine[0].equals("dir")) {
            return CommandType.DIR;
        } else if (parsedLine[1].equals("ls")) {
            return CommandType.LS;
        } else if (parsedLine[1].equals("cd") && parsedLine[2].equals("..")) {
            return CommandType.CD_UP;
        } else if (parsedLine[1].equals("cd") && parsedLine[2].equals("/")) {
            return CommandType.CD_ROOT;
        } else if (parsedLine[1].equals("cd")) {
            return CommandType.CD_IN;
        } else if (parsedLine[0].matches("\\d+")) {
            return CommandType.FILE;
        } else {
            return CommandType.INVALID;
        }
    }

    enum CommandType {
        CD_ROOT,
        CD_UP,
        CD_IN,
        LS,
        DIR,
        FILE,
        INVALID
    }
}
