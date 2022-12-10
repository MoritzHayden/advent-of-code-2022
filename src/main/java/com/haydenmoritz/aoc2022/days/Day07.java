package com.haydenmoritz.aoc2022.days;

import com.haydenmoritz.aoc2022.models.File;
import com.haydenmoritz.aoc2022.models.FileNode;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static com.haydenmoritz.aoc2022.utils.Utils.*;

public class Day07 implements IDay {
    int dayNumber = 7;
    List<String> dayInput = readFile(dayNumber);

    @Override
    public void solveAll() {
        printSolutionMessage(dayNumber, 1, solvePart1());
        printSolutionMessage(dayNumber, 2, solvePart2());
    }

    private String solvePart1() {
        // Create file system POJO
        FileNode fileTreeRoot = populateTree();

        // TODO: Run DFS to calculate answer
        return "";
    }

    private String solvePart2() {
        throw new NotImplementedException();
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
