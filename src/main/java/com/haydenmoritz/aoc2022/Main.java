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
        List<Day> days = Arrays.asList(new Day01());

        while (true) {
            try {
                System.out.print("Enter day to run (0 to quit): ");
                Integer inputDay = Integer.parseInt(reader.readLine()) - 1;
                if (inputDay < 0) {
                    System.out.print("Quitting...");
                    return;
                }
                days.get(inputDay).solveAll();
            } catch(ArrayIndexOutOfBoundsException ex) {
                System.out.println("Error: Solution not yet ready\n");
            } catch (NotImplementedException ex) {
                System.out.println("Error: Solution not yet ready\n");
            } catch (NumberFormatException ex) {
                System.out.println("Error: Invalid input\n");
            } catch (Exception ex) {
                System.out.println("Error: " + ex + "\n");
            }
        }
    }
}
