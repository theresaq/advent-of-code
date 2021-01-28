import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * Advent of Code 2020 day 6.
 *
 * @author Theresa
 * @version 1.0
 */
public class day6 {

    /**
     * @param args unused.
     */
    public static void main(String[] args) throws IOException {
        ArrayList<String> forms = new ArrayList<>();
        Scanner fileScan;
        
        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday6.txt"));
        fileScan.useDelimiter("\n\r");
        while (fileScan.hasNext()) {
            forms.add(fileScan.next());
        }

        int sum = 0;
        for (String form : forms) {
            int unique = uniqueLetters(form);
            sum = sum + unique;
        }
        
        System.out.println(sum);
    }
    
    private static int uniqueLetters(String string) {
        char[] chars = new char[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int ind = 0; ind < alphabet.length(); ind++) {
            chars[ind] = alphabet.charAt(ind);
        }
        
        Scanner stringScan = new Scanner(string);
        stringScan.useDelimiter("\r");
        ArrayList<String> people = new ArrayList<>();
        while (stringScan.hasNext()) {
            people.add(stringScan.next());
        }

        for (int num = 0; num < people.size(); num++) {
            for (int index1 = 0; index1 < chars.length; index1++) {
                if (!(people.get(num)).contains("" + chars[index1])) {
                    chars[index1] = ' ';
                }
            }
        }
        
        int counter = 0;
        for (char ch : chars) {
            if (ch != ' ') {
                counter++;
            }
        }

        return counter;
    }

}
