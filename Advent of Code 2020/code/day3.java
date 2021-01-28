import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Advent of Code 2020 day 3.
 *
 * @author Theresa
 * @version 1.0
 */
public class day3 {
    /**
     * Drives the program.
     *
     * @param args unused.
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan;

        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday3.txt"));
        fileScan.useDelimiter("\r\n");

        ArrayList<String> map = new ArrayList<>();
        while (fileScan.hasNext()) {
            map.add(fileScan.next());
        }

        int product = 1;
        int[] slopes = {1, 3, 5, 7};

        for (int slope : slopes) {
            product *= treeCounter(map, 1, slope);
        }
        product *= treeCounter(map, 2, 1);

        System.out.println(treeCounter(map, 1,3));
        System.out.println(product);
    }

    private static int treeCounter(ArrayList<String> map, int down, int right) {
        int counter = 0;
        int index = 0;
        for (int row = 0; row < map.size(); row += down) {
            String path = map.get(row);
            if (path.charAt(index) == '#') {
                counter++;
            }
            index += right;
            if (index >= path.length()) {
                index = index - path.length();
            }
        }
        return counter;
    }
}
