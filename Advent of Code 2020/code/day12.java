import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Advent of Code 2020 day 12 part one.
 *
 * @author Theresa
 * @version 1.0
 */
public class day12 {
    /** Units in the North direction. */
    private static int northValue;

    /** Units in the South direction. */
    private static int southValue;

    /** Units in the East direction. */
    private static int eastValue;

    /** Units in the West direction. */
    private static int westValue;

    /** Direction the ship is currently facing. 0 is North, 90 is East, 180 is South, 270 is East. */
    private static int direction = 90;

    /**
     * Drives the program.
     *
     * @param args unused.
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan;

        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday12.txt"));
        fileScan.useDelimiter("\r\n");

        while (fileScan.hasNext()) {
            increaseDistance(fileScan.next());
        }

        int manhattanDistance = 0;
        if (southValue > northValue) {
            manhattanDistance += southValue - northValue;
        } else {
            manhattanDistance += northValue - southValue;
        }

        if (eastValue > westValue) {
            manhattanDistance += eastValue - westValue;
        } else {
            manhattanDistance += westValue - eastValue;
        }

        System.out.println(manhattanDistance);
    }

    private static void increaseDistance(String instruction) {
        int units = Integer.parseInt(instruction.substring(1));
        switch (instruction.charAt(0)) {
            case 'N':
                northValue += units;
                break;
            case 'S':
                southValue += units;
                break;
            case 'E':
                eastValue += units;
                break;
            case 'W':
                westValue += units;
                break;
            case 'L':
                rotateShipLeft(units);
                break;
            case 'R':
                rotateShipRight(units);
                break;
            case 'F':
                moveShipForward(units);
                break;
            default:
                break;
        }
    }

    private static void rotateShipLeft(int rotation) {
        direction -= rotation;
        if (direction < 0){
            direction += 360;
        }
    }

    private static void rotateShipRight(int rotation) {
        direction += rotation;
        if (direction >= 360) {
            direction -= 360;
        }
    }

    private static void moveShipForward(int units) {
        switch (direction) {
            case 0:
                northValue += units;
                break;
            case 90:
                eastValue += units;
                break;
            case 180:
                southValue += units;
                break;
            case 270:
                westValue += units;
                break;
            default:
                break;
        }
    }
}
