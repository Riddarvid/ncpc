package ncpc.ncpc2022.competition.hill;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hill {
    long[] measurements;
    long solution;

    public static void main(String[] args) throws FileNotFoundException {
        new Hill().run();
    }

    private void run() throws FileNotFoundException {
        parseInput();
        solve();
        printSolution();
    }

    private void parseInput() throws FileNotFoundException {
        /*String filePath = "resources/ncpc/ncpc2022/competition/hill/input3";
        File inputFile = new File(filePath);
        Scanner scanner = new Scanner(inputFile);*/

        //TODO Comment out before submitting!!!
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();
        String heightsString = scanner.nextLine();
        String[] heightsArr = heightsString.split(" ");
        measurements = new long[heightsArr.length];
        for (int i = 0; i < measurements.length; i++) {
            measurements[i] = Long.parseLong(heightsArr[i]);
        }
    }

    private void solve() {
        long maxHeight = 0;

        int index = 0;

        while (measurements[index + 1] < measurements[index]) {
            index++;
        }

        long leftValleyMeasurement = measurements[index];

        while (index < measurements.length) {
            index = getNextPeakIndex(index);
            if (index == -1) {
                break;
            }
            long currentPeakMeasurement = measurements[index];
            index = getNextRightValleyIndex(index);
            long rightValleyMeasurement = measurements[index];

            long leftSlopeHeight = currentPeakMeasurement - leftValleyMeasurement;
            long rightSlopeHeight = currentPeakMeasurement - rightValleyMeasurement;

            long peakHeight = Math.min(leftSlopeHeight, rightSlopeHeight);
            if (peakHeight > maxHeight) {
                maxHeight = peakHeight;
            }

            leftValleyMeasurement = rightValleyMeasurement;
        }

        solution = maxHeight;
    }

    private int getNextRightValleyIndex(int index) {
        index++;
        while (index < measurements.length && !isRightValley(index)) {
            index++;
        }
        if (index == measurements.length) {
            return measurements.length - 1;
        }
        return index;
    }

    private boolean isRightValley(int index) {
        if (index == measurements.length - 1) {
            return true;
        }
        return measurements[index + 1] > measurements[index];
    }

    private int getNextPeakIndex(int index) {
        index++;
        while (index < measurements.length && !isPeak(index)) {
            index++;
        }
        if (index == measurements.length) {
            return -1;
        }
        return index;
    }

    private boolean isPeak(int index) {
        if (index == 0 || index == measurements.length - 1) {
            return false;
        }
        if (index == 1) {
            return measurements[index - 1] <= measurements[index] && measurements[index + 1] <= measurements[index];
        }
        return measurements[index - 1] < measurements[index] && measurements[index + 1] < measurements[index];
    }

    private void printSolution() {
        System.out.println(solution);
    }
}
