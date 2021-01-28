import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Advent of Code 2020 day 2.
 *
 * @author Theresa
 * @version 1.0
 */
public class day2 {
    /**
     * Drives the program.
     *
     * @param args unused.
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan;

        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday2.txt"));
        fileScan.useDelimiter("\r\n");

        int counter1 = 0;
        int counter2 = 0;
        while (fileScan.hasNext()) {
            String input = fileScan.next();
            int indexOfDash = input.indexOf("-");
            int indexOfColon = input.indexOf(":");

            int number1 = Integer.parseInt(input.substring(0, indexOfDash));
            int number2 = Integer.parseInt(input.substring(indexOfDash + 1, indexOfColon - 2));

            char targetChar = input.charAt(indexOfColon - 1);
            String password = input.substring(indexOfColon + 2);

            int charCount = charCounter(password, targetChar);
            if (charCount >= number1 && charCount <= number2) {
                counter1++;
            }

            // check characters at indices number1 and number2, but need to subtract 1 as passwords start with index 1.
            if (password.charAt(number1 - 1) == targetChar ^ password.charAt(number2 - 1) == targetChar) {
                counter2++;
            }
        }

        System.out.println(counter1);
        System.out.println(counter2);
    }

    private static int charCounter (String password, char c) {
        int counter = 0;
        for (int index = 0; index < password.length(); index++) {
            if (password.charAt(index) == c) {
                counter++;
            }
        }
        return counter;
    }
}
