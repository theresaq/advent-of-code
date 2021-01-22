package p1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * @author Theresa
 *
 */
public class codeDay8 {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan;
        
        fileScan = new Scanner(
                new File("src/adventcodeday8.txt"));

        fileScan.useDelimiter("\n");
        ArrayList<String> instructions = new ArrayList<String>();
        while (fileScan.hasNext()) {
            instructions.add(fileScan.next());
        }
        
        for (int index = 0; index < instructions.size(); index++) {
            Integer line = 0;
            ArrayList<Integer> linesRead = new ArrayList<Integer>();
            ArrayList<String> adjusted = new ArrayList<String>();

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
                    if (nextLine.charAt(4) == '+') {
                        line += Integer.valueOf(nextLine.substring(5, nextLine.length() - 1));
                    } else {
                        line -= Integer.valueOf(nextLine.substring(5, nextLine.length() - 1));
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
        
        ArrayList<Integer> linesRead = new ArrayList<Integer>();
        while (!linesRead.contains(line) && line < instructions.size() - 1) {
            String nextLine = instructions.get(line);
            if (nextLine.startsWith("nop")) {
                linesRead.add(line);
                line++;
            } else if (nextLine.startsWith("acc")) {
                linesRead.add(line);
                line++;
                if (nextLine.charAt(4) == '+') {
                    accumulator += Integer.valueOf(nextLine.substring(5, nextLine.length()-1));
                } else {
                    accumulator -= Integer.valueOf(nextLine.substring(5, nextLine.length()-1));
                }

            } else {
                linesRead.add(line);
                if (nextLine.charAt(4) == '+') {
                    line += Integer.valueOf(nextLine.substring(5, nextLine.length()-1));
                } else {
                    line -= Integer.valueOf(nextLine.substring(5, nextLine.length()-1));
                }

            }
        }
        
        return accumulator;
    }
}
