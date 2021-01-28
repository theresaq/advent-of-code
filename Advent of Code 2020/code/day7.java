import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * Advent of Code 2020 day 7.
 * Second part of day 7 still being worked on.
 *
 * @author Theresa
 * @version 1.0
 */
public class day7 {

    /**
     * @param args unused.
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan;
        
        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday7.txt"));

        fileScan.useDelimiter("\n");
        ArrayList<String> bags = new ArrayList<>();
        while (fileScan.hasNext()) {
            bags.add(fileScan.next());
        }
        
        int counter = 0;
        ArrayList<String> goldContains = new ArrayList<>();
        for (String bag : bags) {
            if (bag.startsWith("shiny gold")) {
                ArrayList<String> contains = containsColors(bag);
                ArrayList<Integer> containsNum = getNumbers(bag);
                
                for (String color : contains) {
                    goldContains.add(color);
                    for (String eachBag : bags) {
                        if (eachBag.startsWith(color)) {
                            ArrayList<Integer> containsNum1 = getNumbers(eachBag);
                            int bagSum = 0;
                            for (Integer number : containsNum1) {
                                bagSum += number;
                            }
                            int bagIndex = contains.indexOf(color);
                            counter = counter + bagSum * containsNum.get(bagIndex);
                        }
                    }
                }

                for (int index = 0; index < containsNum.size(); index++) {
                    counter = counter + containsNum.get(index);
                }
            }
        }
        
        ArrayList<Integer> containsNum2 = new ArrayList<>();
        ArrayList<String> contains1 = new ArrayList<>();
        for (String color : goldContains) {
            for (String bag : bags) {
                if (bag.startsWith(color)) {
                    contains1.addAll(containsColors(bag));
                    containsNum2.addAll(getNumbers(bag));
                }
            }
        }
        for (String color1 : contains1) {
            for (String eachBag : bags) {
                
                if (eachBag.startsWith(color1)) {
                    int bagSum = 0;
                    for (Integer number : getNumbers(eachBag)) {
                        bagSum += number;
                    }
                    int bagIndex = contains1.indexOf(color1);
                    counter = counter + bagSum * containsNum2.get(bagIndex);
                }
            }
        }

        for (int index = 0; index < containsNum2.size(); index++) {
            counter = counter + containsNum2.get(index);
        }

        System.out.println(counter);
    }
    
    private static String getBagColor(String string) {
        int colorIndex = string.indexOf("bag");
        String color = string.substring(0, colorIndex - 1);
        return color;
    }

    private static ArrayList<Integer> getNumbers(String string) {
        ArrayList<Integer> nums = new ArrayList<>();
        
        String regex = "[0-9]+";
        for (int index = 0; index < string.length(); index++) {
            if (("" + string.charAt(index)).matches(regex)) {
                nums.add(Integer.parseInt("" + string.charAt(index)));
            }
        }

        return nums;
    }
    
    private static ArrayList<String> containsColors(String string) {
        ArrayList<Integer> indices = new ArrayList<>();
        
        String regex = "[0-9]+";
        for (int index = 0; index < string.length(); index++) {
            if (("" + string.charAt(index)).matches(regex)) {
                indices.add(index);
            }
        }

        ArrayList<String> colors = new ArrayList<>();
        for (Integer index : indices) {
            int endIndex = string.indexOf("bag", index);
            colors.add(string.substring(index + 2, endIndex - 1));
        }
        
        return colors;
    }
}
