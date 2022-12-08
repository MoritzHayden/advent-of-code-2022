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
        FileNode fileTree = populateTree();
        ArrayList<File> files = new ArrayList<>();
        fileTree.getRoot().inorderTraversal(files);
        files.forEach(file -> System.out.println("DATA: " + file.toString()));

        // System.out.println("TRAVERSED TREE: " + traverseBreadthFirst(fileTree));

        // TODO: Run BFS to calculate answer
        return "";
    }

    private String solvePart2() {
        throw new NotImplementedException();
    }

    private FileNode populateTree() {
        FileNode currentNode = new FileNode(new File("/", true, 0));

        for (String line : dayInput) {
            String[] parsedLine = line.split(" ");
            CommandType lineType = getCommandType(parsedLine);

            if (lineType == CommandType.CD_IN) {
                // Add directory if it does not exist
                File file = new File(parsedLine[2], true, 0);
                if (currentNode.getRoot().countOccurrences(file) == 0) {
                    currentNode = currentNode.addChild(file);
                }
            } else if (lineType == CommandType.CD_UP) {
                // Set current node to parent
                currentNode = currentNode.getParent();
            } else if (lineType == CommandType.CD_ROOT) {
                // Set current node to root
                currentNode = currentNode.getRoot();
            } else if (lineType == CommandType.DIR) {
                // Add directory if it does not exist
                // TODO: For some reason, dirs a and d are being added regardless
                File file = new File(parsedLine[1], true, 0);
                if (currentNode.getRoot().countOccurrences(file) == 0) {
                    currentNode.addChild(file);
                }
            } else if (lineType == CommandType.FILE) {
                // Add file if it does not exist
                File file = new File(parsedLine[1], false, Integer.parseInt(parsedLine[0]));
                if (currentNode.getRoot().countOccurrences(file) == 0) {
                    currentNode.addChild(file);
                }
            } else if (lineType == CommandType.LS) {
                // Do nothing
            } else {
                // Throw exception due to parsing error
                throw new RuntimeException("Parsed unexpected" + lineType.toString().toLowerCase() + " input: " + line);
            }
        }

        FileNode fileTree = currentNode.getRoot();
        ArrayList<File> files = new ArrayList<>();
        fileTree.searchDFS(files);
        System.out.println("Max depth of tree:" + fileTree.getDepth());
        return fileTree;
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
