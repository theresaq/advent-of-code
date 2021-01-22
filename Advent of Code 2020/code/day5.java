package p1;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Advent of Code 2020 day 5.
 * 
 * @author Theresa
 * @version 1.0
 */
public class codeDay5 {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan;

        fileScan = new Scanner(
                new File("src/adventcodeday5.txt"));
        ArrayList<String> seats = new ArrayList<String>();
        
        int biggest = 0;
        int biggestIndex = 0;
        while (fileScan.hasNext()) {
            seats.add(fileScan.next());
        }

        boolean[] seatIDs = new boolean[970];
        for (int index = 0; index < seats.size(); index++) {
            int seatID = getRow(seats.get(index)) * 8 + getColumn(seats.get(index));
            seatIDs[seatID] = true;
        }
        
        ArrayList<Integer> missing = new ArrayList<Integer>();
        for (Integer index = 0; index < seats.size(); index++) {
            if (seatIDs[index] == false) {
                missing.add(index);
            }
        }
        
        missing.sort(null);
        for (Integer integer : missing) {
            System.out.print(integer + " ");
        }
        
    }

    private static int getRow(String pass) {
        int lowerLimit = 0;
        int upperLimit = 127;
        
        String rowID = pass.substring(0, 7);
        for (int index = 0; index < rowID.length(); index++) {
            if (pass.charAt(index) == 'F') {
                upperLimit = upperLimit - (upperLimit - lowerLimit + 1) / 2;
            } else {
                lowerLimit = lowerLimit + (upperLimit - lowerLimit + 1) / 2;
            }
        }
        
        if (pass.charAt(6) == 'F') {
            return lowerLimit;
        } else {
            return upperLimit;
        }
    }

    private static int getColumn(String pass) {
        int lowerLimit = 0;
        int upperLimit = 7;
        
        for (int index = 7; index < pass.length(); index++) {
            if (pass.charAt(index) == 'L') {
                upperLimit = upperLimit - (upperLimit - lowerLimit + 1) / 2;
            } else {
                lowerLimit = lowerLimit + (upperLimit - lowerLimit + 1) / 2;
            }
        }
        
        if (pass.endsWith("L")) {
            return lowerLimit;
        } else {
            return upperLimit;
        }
    }
}
