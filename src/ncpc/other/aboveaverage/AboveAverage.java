package ncpc.other.aboveaverage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AboveAverage {
    private List<List<Integer>> testCases;
    private List<String> solution;

    public static void main(String[] args) throws FileNotFoundException {
        new AboveAverage().run();
    }

    private void run() throws FileNotFoundException {
        parseInput();
        solve();
        printSolution();
    }

    private void parseInput() throws FileNotFoundException {
        /*String filePath = "resources/ncpc/other/aboveaverage/input1";
        File inputFile = new File(filePath);
        Scanner scanner = new Scanner(inputFile);*/

        //TODO Comment out before submitting!!!
        Scanner scanner = new Scanner(System.in);

        int nTests = Integer.parseInt(scanner.nextLine());
        testCases = new ArrayList<>();

        for (int i = 0; i < nTests; i++) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            List<Integer> scores = new ArrayList<>();
            for (int j = 1; j < tokens.length; j++) {
                scores.add(Integer.parseInt(tokens[j]));
            }
            testCases.add(scores);
        }
    }

    private void solve() {
        solution = new ArrayList<>();

        for (List<Integer> testCase : testCases) {
            solveTestCase(testCase);
        }
    }

    private void solveTestCase(List<Integer> testCase) {
        double average = getAverage(testCase);

        int countAboveAverage = 0;
        for (int score : testCase) {
            if (score > average) {
                countAboveAverage++;
            }
        }

        double percentAboveAverage = ((double) countAboveAverage) / ((double) testCase.size()) * 100;
        String percentString = String.format("%.3f", percentAboveAverage) + "%";
        solution.add(percentString);
    }

    private double getAverage(List<Integer> testCase) {
        long sum = 0;
        for (int score : testCase) {
            sum += score;
        }
        return ((double) sum) / ((double) testCase.size());
    }

    private void printSolution() {
        for (String line : solution) {
            System.out.println(line);
        }
    }
}
