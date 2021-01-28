import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Advent of Code 2020 day 4.
 * 
 * @author Theresa
 * @version 1.0
 */
public class day4 {

    /**
     * Drives the program.
     *
     * @param args unused.
     */
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> passports = new ArrayList<>();
        Scanner fileScan;

        fileScan = new Scanner(
                new File("Advent of Code 2020/input/adventcodeday4.txt"));
        fileScan.useDelimiter("\n\r");
        while (fileScan.hasNext()) {
            passports.add(fileScan.next());
        }

        ArrayList<String> complete = new ArrayList<>();
        int valid = 0;
        for (String passport: passports) {
            if (passport.contains("ecl")
                    && passport.contains("pid")
                    && passport.contains("eyr")
                    && passport.contains("hcl")
                    && passport.contains("byr")
                    && passport.contains("iyr")
                    && passport.contains("hgt")) {
                complete.add(passport);
            }
        }
        
        for (String passport : complete) {
            if (checkBYR(passport)
                    && checkIYR(passport)
                    && checkEYR(passport)
                    && checkECL(passport)
                    && checkPID(passport)
                    && checkHCL(passport)
                    && checkHGT(passport)) {
                valid++;
            }
        }

        System.out.println(valid);
        
    }
    
    private static boolean checkBYR(String passport) {
        int byrIndex = passport.indexOf("byr:");
        String birthyear = passport.substring(byrIndex + 4, byrIndex + 8);
        Integer byr = Integer.parseInt(birthyear);
        boolean valid = false;
        if (byr >= 1920 && byr <= 2002) {
            valid = true;
        }
        return valid;
    }
    
    private static boolean checkIYR(String passport) {
        int iyrIndex = passport.indexOf("iyr:");
        String issueyear = passport.substring(iyrIndex + 4, iyrIndex + 8);
        Integer iyr = Integer.parseInt(issueyear);
        boolean valid = false;
        if (iyr >= 2010 && iyr <= 2020) {
            valid = true;
        }
        return valid;
    }
    
    private static boolean checkEYR(String passport) {
        int eyrIndex = passport.indexOf("eyr:");
        String expiryyear = passport.substring(eyrIndex + 4, eyrIndex + 8);
        Integer eyr = Integer.parseInt(expiryyear);
        boolean valid = false;
        if (eyr >= 2020 && eyr <= 2030) {
            valid = true;
        }
        return valid;
    }
    
    private static boolean checkECL(String passport) {
        int eclIndex = passport.indexOf("ecl:");
        String eyeColor = passport.substring(eclIndex + 4, eclIndex + 7);
        boolean valid = false;
        if (eyeColor.equals("amb")
                || eyeColor.equals("blu")
                || eyeColor.equals("brn")
                || eyeColor.equals("gry")
                || eyeColor.equals("grn")
                || eyeColor.equals("hzl")
                || eyeColor.equals("oth")) {
            valid = true;
        }
        return valid;
    }
 
    private static boolean checkPID(String passport) {
        int pidIndex = passport.indexOf("pid:");
        String substring = passport.substring(pidIndex);
        String replaceSpace = substring.replaceAll("[\s\r]", "-");
        int nextIndex = replaceSpace.indexOf("-");
        String passportID = replaceSpace.substring(4, nextIndex);

        boolean valid = false;
        String regex = "[0-9]+";
        
        if (passportID.length() == 9 && passportID.matches(regex)) {
            valid = true;
        }
        return valid;
    }

    private static boolean checkHCL(String passport) {
        int hclIndex = passport.indexOf("hcl:");
        
        String substring = passport.substring(hclIndex);
        String replaceSpace = substring.replaceAll("[\s\r]", "-");
        int nextIndex = replaceSpace.indexOf("-");
        String hairColor = replaceSpace.substring(4, nextIndex);
        String hairColorCode = replaceSpace.substring(5, nextIndex);
        
        boolean valid = false;
        String regex = "[0-9a-f]+";
        if (hairColor.length() == 7 && hairColorCode.matches(regex)
                && hairColor.startsWith("#")) {
            valid = true;
        }
        return valid;
    }

    private static boolean checkHGT(String passport) {
        int hgtIndex = passport.indexOf("hgt:");
        
        String substring = passport.substring(hgtIndex);
        String replaceSpace = substring.replaceAll("[\s\r]", "-");
        int nextIndex = replaceSpace.indexOf("-");
        String height = replaceSpace.substring(4, nextIndex);
        
        boolean valid = false;
        String heightNum = height.substring(0, height.length() - 2);
        Integer hgt = Integer.parseInt(heightNum);
        if (height.endsWith("cm")) {
            if (hgt >= 150 && hgt <= 193) {
                valid = true;
            }
        } else if (height.endsWith("in")) {
            if (hgt >= 59 && hgt <= 76) {
                valid = true;
            }
        }
        return valid;
    }
}


    
