import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.Collections;

/**
 * Advent of Code 2020 day 10.
 *
 * @author Theresa
 * @version 1.0
 */
public class day10 {

    /**
     * @param args unused.
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan;
        
        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday10.txt"));

        fileScan.useDelimiter("\r\n");
        
        ArrayList<Integer> numbers = new ArrayList<>();
        while (fileScan.hasNext()) {
            numbers.add(Integer.valueOf(fileScan.next()));
        }

        // add jolt of charging outlet
        numbers.add(0);
        Collections.sort(numbers);
        
        // add jolt of built-in adapter
        numbers.add(numbers.get(numbers.size() - 1) + 3);

        int differenceCounter1 = 0;
        int differenceCounter3 = 0;
        
        for (int index = 0; index < numbers.size() - 1; index++) {
            if (numbers.get(index + 1) - numbers.get(index) == 1) {
                differenceCounter1++;
            } else {
                differenceCounter3++;
            }
        }

        System.out.println(differenceCounter1 * differenceCounter3);
    }

}
