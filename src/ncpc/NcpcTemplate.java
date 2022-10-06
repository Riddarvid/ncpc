package ncpc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NcpcTemplate {
    public static void main(String[] args) throws FileNotFoundException {
        new NcpcTemplate().run();
    }

    private void run() throws FileNotFoundException {
        parseInput();
        solve();
        printSolution();
    }

    private void parseInput() throws FileNotFoundException {
        String filePath = "resources/ncpc/ncpc2022/practice1/adolescentarchitecture/input1";
        File inputFile = new File(filePath);
        Scanner scanner = new Scanner(inputFile);

        //TODO Comment out before submitting!!!
        //Scanner scanner = new Scanner(System.in);
    }

    private void solve() {

    }

    private void printSolution() {

    }
}
