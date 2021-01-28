import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Advent of Code 2020 day 1.
 *
 * @author Theresa
 * @version 1.0
 */
public class day1 {
    /**
     * Drives the program.
     *
     * @param args unused.
     */
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner fileScan;

        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday1.txt"));
        while (fileScan.hasNext()) {
            numbers.add(Integer.parseInt(fileScan.next()));
        }

        System.out.println(findProductPart1(numbers));
        System.out.println(findProductPart2(numbers));
    }

    private static int findProductPart1(ArrayList<Integer> numbers) {
        int entry = 0;
        ArrayList<Integer> differences = new ArrayList<>();

        for (Integer number: numbers) {
            differences.add(2020 - number);
            if (differences.contains(number) && number != 2020 / 2) {
                entry = number;
            }
        }

        return entry * (2020 - entry);
    }

    private static int findProductPart2(ArrayList<Integer> numbers) {
        int product = 1;
        ArrayList<Integer> differences = new ArrayList<>();

        for (Integer number: numbers) {
            differences.add(2020 - number);
        }

        for (int index1 = 0; index1 < numbers.size(); index1++) {
            for (int index2 = index1 + 1; index2 < numbers.size(); index2++) {
                int number1 = numbers.get(index1);
                int number2 = numbers.get(index2);
                if (differences.contains(number1 + number2) && number1 != number2) {
                    product *= number1 * number2 * (2020 - number1 - number2);
                    return product;
                }
            }
        }

        return product;
    }
}
