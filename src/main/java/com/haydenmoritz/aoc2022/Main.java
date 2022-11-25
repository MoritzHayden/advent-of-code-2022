package com.haydenmoritz.aoc2022;

import com.haydenmoritz.aoc2022.days.*;
import org.apache.commons.lang3.NotImplementedException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to AoC 2022!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Day> days = Arrays.asList(new Day01(), new Day02(), new Day03(), new Day04(), new Day05(), new Day06(),
                new Day07(), new Day08(), new Day09(), new Day10(), new Day11(), new Day12(), new Day13(), new Day14(),
                new Day15(), new Day16(), new Day17(), new Day18(), new Day19(), new Day20(), new Day21(), new Day22(),
                new Day23(), new Day24(), new Day25());

        while (true) {
            try {
                System.out.print("Enter day to run (0 to quit): ");
                Integer inputDay = Integer.parseInt(reader.readLine()) - 1;
                if (inputDay == -1) {
                    System.out.print("Quitting...\n");
                    return;
                }
                days.get(inputDay).solveAll();
            } catch (NotImplementedException ex) {
                System.out.println("Error: Solution not yet ready\n");
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Error: Invalid input\n");
            } catch (NumberFormatException ex) {
                System.out.println("Error: Invalid input\n");
            } catch (Exception ex) {
                System.out.println("Error: " + ex + "\n");
            }
        }
    }
}
