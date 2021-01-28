import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * Advent of Code 2020 day 9.
 *
 * @author Theresa
 * @version 1.0
 */
public class day9 {

    /**
     * @param args unused.
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan;
        
        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday9.txt"));

        fileScan.useDelimiter("\r\n");
        
        ArrayList<Long> numbers = new ArrayList<>();
        while (fileScan.hasNext()) {
            numbers.add(Long.valueOf(fileScan.next()));
        }
        
        System.out.println(findWeakness());
        int weaknessIndex = numbers.indexOf(findWeakness());

        ArrayList<Long> available = new ArrayList<>();
        for (int index = 0; index < weaknessIndex; index++) {
            available.add(numbers.get(index));
        }

        //iterate over the possible different number of numbers to add
        for (int length = 2; length <= available.size(); length++) {
            for (int index = 0; index < available.size() - length + 1; index++) {
                ArrayList<Long> numsToAdd = new ArrayList<>();
                numsToAdd.add(available.get(index));
                int iterate = 1;
                while (iterate < length) {
                    numsToAdd.add(available.get(index + iterate));
                    iterate++;
                }
                if (sumOf(numsToAdd).equals(findWeakness())) {
                    System.out.println(largestNum(numsToAdd) + smallestNum(numsToAdd));
                    break;
                }
            }
        }
    }

    private static boolean sumOf(ArrayList<Long> numbers, long num) {
        boolean isSum = false;
        
        for (int index1 = 0; index1 < numbers.size(); index1++) {
            for (Long number : numbers) {
                if ((numbers.get(index1) != number && numbers.get(index1) + number == num)) {
                    isSum = true;
                }
            }
        }
        
        return isSum;
    }
    
    private static Long findWeakness() throws IOException {
        Scanner fileScan;
        
        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday9.txt"));

        fileScan.useDelimiter("\r\n");
        
        ArrayList<Long> numbers = new ArrayList<>();
        while (fileScan.hasNext()) {
            numbers.add(Long.valueOf(fileScan.next()));
        }

        ArrayList<Long> valid = new ArrayList<>();
        for (int index = 25; index < numbers.size(); index++) {
            ArrayList<Long> previous = new ArrayList<>();
            for (int index1 = index - 25; index1 < index; index1++) {
                previous.add(numbers.get(index1));
            }
            
            if (sumOf(previous, numbers.get(index))) {
                valid.add(numbers.get(index));
            }
        }
        
        Long weakness = 0L; 
        int index = 25;
        while (index < numbers.size() && weakness == 0L) {
            if (!valid.contains(numbers.get(index))) {
                weakness = numbers.get(index);
            }
            
            index++;
        }

        return weakness;
    }
    
    private static Long sumOf(ArrayList<Long> numbers) {
        Long sum = 0L;
        for (Long number : numbers) {
            sum += number;
        }
        return sum;
    }
    
    private static Long largestNum(ArrayList<Long> numbers) {
        Long largest = 0L;
        for (Long number : numbers) {
            if (number > largest) {
                largest = number;
            }
        }
        return largest;
    }
    
    private static Long smallestNum(ArrayList<Long> numbers) {
        Long smallest = numbers.get(0);
        for (Long number : numbers) {
            if (number < smallest) {
                smallest = number;
            }
        }
        return smallest;
    }
}
