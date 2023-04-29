package ncpc.ncpc2022.competition.disc;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Disc {
    private int radius;
    Point solution;

    public static void main(String[] args) throws FileNotFoundException {
        new Disc().run();
    }

    private void run() throws FileNotFoundException {
        parseInput();
        solve();
        printSolution();
    }

    private void parseInput() throws FileNotFoundException {
        /*String filePath = "resources/ncpc/ncpc2022/competition/disc/input3";
        File inputFile = new File(filePath);
        Scanner scanner = new Scanner(inputFile);*/

        //TODO Comment out before submitting!!!
        Scanner scanner = new Scanner(System.in);
        radius = Integer.parseInt(scanner.nextLine());
    }

    private void solve() {
        Point closestPoint = new Point(0, radius + 1000); //Choose big number so it's far away

        for (int x = 0; x <= radius; x++) {
            int y = (int) Math.floor(Math.sqrt(Math.pow(radius, 2) + Math.pow(x, 2)));
            while (distance(x, y) <= radius) {
                y++;
            }

            if (distance(x, y) < distance(closestPoint)) {
                closestPoint = new Point(x, y);
            }
        }

        solution = closestPoint;
    }

    private double distance(Point closestPoint) {
        return distance(closestPoint.x, closestPoint.y);
    }

    private double distance(long x, long y) {
        double distance = Math.sqrt(x * x + y * y);
        return distance;
    }

    private void printSolution() {
        String solutionString = solution.x + " " + solution.y;
        System.out.println(solutionString);
    }
}
