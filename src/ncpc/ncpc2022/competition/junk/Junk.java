package ncpc.ncpc2022.competition.junk;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Junk {
    private Set<Point> scooters;
    private Point player;
    private Point depot;
    private List<String> solution;

    public static void main(String[] args) throws FileNotFoundException {
        new Junk().run();
    }

    private void run() throws FileNotFoundException {
        parseInput();
        solve();
        printSolution();
    }

    private void parseInput() throws FileNotFoundException {
        /*String filePath = "resources/ncpc/ncpc2022/competition/junk/input2";
        File inputFile = new File(filePath);
        Scanner scanner = new Scanner(inputFile);*/

        //TODO Comment out before submitting!!!
        Scanner scanner = new Scanner(System.in);

        int nScooters = Integer.parseInt(scanner.nextLine());
        scooters = new HashSet<>();

        String playerAndDepotString = scanner.nextLine();
        String[] tokens = playerAndDepotString.split(" ");
        player = new Point(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        depot = new Point(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));

        for (int i = 0; i < nScooters; i++) {
            String scooterString = scanner.nextLine();
            String[] scooterTokens = scooterString.split(" ");
            scooters.add(new Point(Integer.parseInt(scooterTokens[0]), Integer.parseInt(scooterTokens[1])));
        }
    }

    private void solve() {
        solution = new ArrayList<>();
        //printMap();

        while (!scooters.isEmpty()) {
            Point scooter = scooters.iterator().next();
            moveScooterToDepot(scooter);
        }
    }

    private void moveScooterToDepot(Point scooter) {
        moveScooterToXAxis(scooter);
        moveScooterToYAxis(scooter);
    }

    private void moveScooterToXAxis(Point scooter) {
        if (scooter.y < depot.y) {
            //Scooter is below the y-axis
            moveScooterToXAxisUpward(scooter);
        } else {
            //Scooter is above or at y-axis
            moveScooterToXAxisDownward(scooter);
        }
    }

    private void moveScooterToYAxis(Point scooter) {
        //Must be run after moving the scooter to the x-axis
        if (scooter.x < depot.x) {
            //Scooter is left of depot
            movePlayer(Direction.LEFT);
            movePlayerToY(depot.y);
            movePlayerToX(depot.x - 1);
        } else {
            //Scooter is right of depot
            movePlayer(Direction.RIGHT);
            movePlayerToY(depot.y);
            movePlayerToX(depot.x + 1);
        }
    }

    private void moveScooterToXAxisDownward(Point scooter) {
        movePlayerNextToPoint(scooter, Direction.UP);
        movePlayerToY(depot.y + 1);
    }

    private void moveScooterToXAxisUpward(Point scooter) {
        movePlayerNextToPoint(scooter, Direction.DOWN);
        movePlayerToY(depot.y - 1);
    }

    private void movePlayer(Direction direction) {
        switch (direction) {
            case RIGHT:
                player.translate(1, 0);
                solution.add("right");
                break;
            case LEFT:
                player.translate(-1, 0);
                solution.add("left");
                break;
            case UP:
                player.translate(0, 1);
                solution.add("up");
                break;
            case DOWN:
                player.translate(0, -1);
                solution.add("down");
                break;
        }
        if (scooters.contains(player)) {
            Point scooter = new Point(player);
            moveScooter(scooter, direction);
        }
        //printMap();
        System.out.println();
    }

    private void moveScooter(Point scooter, Direction direction) {
        scooters.remove(scooter);
        switch (direction) {
            case RIGHT:
                scooter.translate(1, 0);
                break;
            case LEFT:
                scooter.translate(-1, 0);
                break;
            case UP:
                scooter.translate(0, 1);
                break;
            case DOWN:
                scooter.translate(0, -1);
                break;
        }
        if (scooters.contains(scooter)) {
            Point otherScooter = new Point(scooter);
            moveScooter(otherScooter, direction);
        }
        scooters.add(scooter);
        if (scooter.equals(depot)) {
            scooters.remove(scooter);
        }
    }

    private void movePlayerNextToPoint(Point scooter, Direction side) {
        int targetX;
        int targetY;

        switch (side) {
            case RIGHT:
                targetX = scooter.x + 1;
                targetY = scooter.y;
                break;
            case LEFT:
                targetX = scooter.x - 1;
                targetY = scooter.y;
                break;
            case UP:
                targetX = scooter.x;
                targetY = scooter.y + 1;
                break;
            case DOWN:
                targetX = scooter.x;
                targetY = scooter.y - 1;
                break;
            default:
                throw new IllegalStateException();
        }

        if (side == Direction.RIGHT || side == Direction.LEFT) {
            if (player.y == scooter.y) {
                movePlayer(Direction.UP);
            }
            movePlayerToX(targetX);
            movePlayerToY(targetY);
        } else {
            if (player.x == scooter.x) {
                movePlayer(Direction.RIGHT);
            }
            movePlayerToY(targetY);
            movePlayerToX(targetX);
        }
    }

    private void movePlayerToX(int targetX) {
        if (player.x < targetX) {
            while (player.x < targetX) {
                movePlayer(Direction.RIGHT);
            }
        } else {
            while (player.x > targetX) {
                movePlayer(Direction.LEFT);
            }
        }
    }

    private void movePlayerToY(int targetY) {
        if (player.y < targetY) {
            while (player.y < targetY) {
                movePlayer(Direction.UP);
            }
        } else {
            while (player.y > targetY) {
                movePlayer(Direction.DOWN);
            }
        }
    }

    /*private void printMap() {
        for (int y = 10; y >= 0; y--) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x <= 10; x++) {
                Point p = new Point(x, y);
                if (scooters.contains(p)) {
                    sb.append('s');
                } else if (player.equals(p)) {
                    sb.append('P');
                } else if (depot.equals(p)) {
                    sb.append('D');
                } else {
                    sb.append('#');
                }
            }
            System.out.println(sb);
        }
    }*/

    private void printSolution() {
        for (String s : solution) {
            System.out.println(s);
        }
    }

    private enum Direction {
        RIGHT, LEFT, UP, DOWN
    }
}
