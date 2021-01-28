import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * Advent of Code 2020 day 8.
 *
 * @author Theresa
 * @version 1.0
 */
public class day8 {

    /**
     * @param args unused.
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan;
        
        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday8.txt"));

        fileScan.useDelimiter("\n");
        ArrayList<String> instructions = new ArrayList<>();
        while (fileScan.hasNext()) {
            instructions.add(fileScan.next());
        }
        
        for (int index = 0; index < instructions.size(); index++) {
            Integer line = 0;
            ArrayList<Integer> linesRead = new ArrayList<>();
            ArrayList<String> adjusted = new ArrayList<>();

            for (String string : instructions) {
                adjusted.add(string);
            }

            String stringAtIndex = adjusted.get(index);

            if (stringAtIndex.startsWith("nop")) {
                adjusted.set(index, stringAtIndex.replace("nop", "jmp"));
            } else if (stringAtIndex.startsWith("jmp")) {
                adjusted.set(index, stringAtIndex.replace("jmp", "nop"));
            }

            while (!linesRead.contains(line) && line < adjusted.size() - 1) {
                String nextLine = adjusted.get(line);

                if (nextLine.startsWith("nop")) {
                    linesRead.add(line);
                    line++;
                } else if (nextLine.startsWith("acc")) {
                    linesRead.add(line);
                    line++;
                } else {
                    linesRead.add(line);
                    Integer move = Integer.valueOf(nextLine.substring(5, nextLine.length()-1));
                    if (nextLine.charAt(4) == '+') {
                        line += move;
                    } else {
                        line -= move;
                    }
                }

                if (line == instructions.size() - 1) {
                    System.out.println(accumulator(adjusted));
                }
            }

        }
         
        
    }

    private static int accumulator(ArrayList<String> instructions) {
        int accumulator = 0;
        Integer line = 0;
        
        ArrayList<Integer> linesRead = new ArrayList<>();
        while (!linesRead.contains(line) && line < instructions.size() - 1) {
            String nextLine = instructions.get(line);
            Integer move = Integer.valueOf(nextLine.substring(5, nextLine.length()-1));
            if (nextLine.startsWith("nop")) {
                linesRead.add(line);
                line++;
            } else if (nextLine.startsWith("acc")) {
                linesRead.add(line);
                line++;
                if (nextLine.charAt(4) == '+') {
                    accumulator += move;
                } else {
                    accumulator -= move;
                }
            } else {
                linesRead.add(line);
                if (nextLine.charAt(4) == '+') {
                    line += move;
                } else {
                    line -= move;
                }

            }
        }
        
        return accumulator;
    }
}
