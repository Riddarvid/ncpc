package ncpc.other;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Greater {
    private long a;
    private long b;

    private boolean greater;

    public static void main(String[] args) throws FileNotFoundException {
        new Greater().run();
    }

    private void run() throws FileNotFoundException {
        parseInput();
        solve();
        printSolution();
    }

    private void parseInput() throws FileNotFoundException {
        /*String filePath = "resources/ncpc/other/greater/input1";
        File inputFile = new File(filePath);
        Scanner scanner = new Scanner(inputFile);*/

        //TODO Comment out before submitting!!!
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] numbers = input.split(" ");
        a = Long.parseLong(numbers[0]);
        b = Long.parseLong(numbers[1]);
    }

    private void solve() {
        greater = a > b;
    }

    private void printSolution() {
        if (greater) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}