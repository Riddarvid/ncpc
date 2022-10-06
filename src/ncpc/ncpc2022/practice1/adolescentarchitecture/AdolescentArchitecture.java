package ncpc.ncpc2022.practice1.adolescentarchitecture;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AdolescentArchitecture {
    private List<Block> blocks;
    private boolean impossible;

    public static void main(String[] args) throws FileNotFoundException {
        new AdolescentArchitecture().run();
    }

    private void run() throws FileNotFoundException {
        parseInput();
        solve();
        printSolution();
    }

    private void parseInput() throws FileNotFoundException {
        /*String filePath = "resources/ncpc/ncpc2022/practice1/adolescentarchitecture/input3";
        File inputFile = new File(filePath);
        Scanner scanner = new Scanner(inputFile);*/

        //TODO Comment out before submitting!!!
        Scanner scanner = new Scanner(System.in);

        int nBlocks = Integer.parseInt(scanner.nextLine());

        blocks = new ArrayList<>();
        for (int i = 0; i < nBlocks; i++) {
            blocks.add(new Block(scanner.nextLine()));
        }
    }

    private void solve() {
        try {
            Collections.sort(blocks);
        } catch (IllegalStateException e) {
            impossible = true;
        }
    }

    private void printSolution() {
        if (impossible) {
            System.out.println("impossible");
        } else {
            for (Block block : blocks) {
                System.out.println(block.toString());
            }
        }
    }
}
