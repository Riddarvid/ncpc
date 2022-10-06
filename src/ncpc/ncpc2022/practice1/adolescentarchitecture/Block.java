package ncpc.ncpc2022.practice1.adolescentarchitecture;

public class Block implements Comparable<Block> {
    private final Shape shape;
    private final int size;

    public Block(String nextLine) {
        String[] tokens = nextLine.split(" ");

        if (tokens[0].equals("cube")) {
            shape = Shape.CUBE;
        } else {
            shape = Shape.CYLINDER;
        }

        size = Integer.parseInt(tokens[1]);
    }

    @Override
    public int compareTo(Block other) {
        if (this.shape == other.shape) {
            return this.size - other.size;
        }

        if (this.shape == Shape.CUBE) {
            //Other is cylinder
            if (cylinderFitsInCube(other, this)) {
                return 1;
            }
            if (cubeFitsInCylinder(this, other)) {
                return -1;
            }
        } else {
            //Other is cube
            if (cubeFitsInCylinder(other, this)) {
                return 1;
            }
            if (cylinderFitsInCube(this, other)) {
                return -1;
            }
        }
        throw new IllegalStateException();
    }

    private boolean cylinderFitsInCube(Block cylinder, Block cube) {
        int diameter = cylinder.size * 2;
        return diameter <= cube.size;
    }

    private boolean cubeFitsInCylinder(Block cube, Block cylinder) {
        double halfSide = cube.size / 2.0;
        double squareRadius = Math.sqrt(halfSide * halfSide * 2);
        return squareRadius <= cylinder.size;
    }

    private enum Shape {
        CUBE, CYLINDER
    }

    @Override
    public String toString() {
        return shape.toString() + " " + size;
    }
}
