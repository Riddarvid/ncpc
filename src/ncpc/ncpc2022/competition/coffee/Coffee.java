package ncpc.ncpc2022.competition.coffee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Coffee {
    private String lectureString;
    private int solution;

    public static void main(String[] args) throws FileNotFoundException {
        new Coffee().run();
    }

    private void run() throws FileNotFoundException {
        parseInput();
        solve();
        printSolution();
    }

    private void parseInput() throws FileNotFoundException {
        /*String filePath = "resources/ncpc/ncpc2022/competition/coffee/input3";
        File inputFile = new File(filePath);
        Scanner scanner = new Scanner(inputFile);*/

        //TODO Comment out before submitting!!!
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();
        lectureString = scanner.nextLine();
    }

    private void solve() {
        int awakeCount = 0;
        int coffeeCount = 0;

        for (char lecture : lectureString.toCharArray()) {
            if (lecture == '1') {
                //Lecture hall has coffee machine
                awakeCount++;
                coffeeCount = 2;
            } else {
                //Lecture hall does not have coffee machine
                if (coffeeCount > 0) {
                    awakeCount++;
                    coffeeCount--;
                }
            }
        }

        solution = awakeCount;
    }

    private void printSolution() {
        System.out.println(solution);
    }
}