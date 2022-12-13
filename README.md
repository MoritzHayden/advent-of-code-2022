# Advent of Code 2022 👨‍💻🎄
My solutions for [Advent of Code 2022](https://adventofcode.com/2022) wrapped in a resilient CLI and built using Maven and Java 17.

## Table of Contents
1. [How To Run](#How-To-Run)
2. [Util Methods](#Util-Methods)
3. [Models](#Models)

### How To Run
1. Clone this repository to your local machine
2. Install any missing dependencies:
   - [Maven](https://maven.apache.org/install.html)
   - [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
3. Create a new run configuration in IntelliJ IDEA:
   - Set `JDK 17` as the JRE
   - Set `Main` as the main class
4. Run the project 🚀

### Util Methods
`Utils.java` contains some commonly used helper methods:

- `List<String> readFile(int day)` returns a `List` containing the input for the provided day. Usage:
    ```java
    List<String> day12Input = readFile(12);
    ```
- `String convertDayIntToString(int day)` returns a two-digit `String` representation of the provided day. Usage:
    ```java
    convertDayIntToString(02) == "02"; // true
    convertDayIntToString(12) == "12"; // true
    ```
- `void printSolutionMessage(int day, int part, String solution)` prints a solution message to standard output. Usage:
  ```java
  printSolutionMessage(12, 1, "42"); // Solution (Day 12.1): 42
  ```
- `getValueOfCharacter(char character)` returns an `int` value for a given character. Usage:
  ```java
  getValueOfCharacter('a') == 1;
  getValueOfCharacter('B') == 28;
  ```
- `reverseString(String input)` returns a reversed `String` of the given input. Usage:
  ```java
  reverseString("Hello World!") == "!dlroW olleH"; // true
  ```

### Models
The `models` directory contains classes that may come in handy:

- `FileNode` can be used to build a tree representing a file system. Each `FileNode` has:
  - `List<FileNode> children`
  - `FileNode parent` (`null` if it is the root)
  - `File data`
- `File` can be used to represent a file in a file system. Each `File` has:
  - `String name`
  - `int size` (`0` if it is a directory)
- `Monkey` can be used to represent a monkey playing [keep away](https://en.wikipedia.org/wiki/Keep_away). Each `Monkey` has:
  - `int id`
  - `Queue<Long> items`
  - `long itemsSeen`
  - `Map<String, Integer> operation`
  - `int throwConditionOperand`
  - `int trueThrowId`
  - `int falseThrowId`
