package day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day08 {


    public static void main(String[] args) {

        String path = "src/day08/input.txt";

        int count = 0;
        int outSum = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line ;
            while ((line = br.readLine()) != null) {
                String[] s = line.split("\\|");
                String[] display = s[1].strip().split(" ");
                String[] patterns = s[0].strip().split(" ");
                count += countEasyNumbers(display);
                outSum += getOutput(patterns, display);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Easy Numbers: " + count);
        System.out.println("Output Sum:   " + outSum);

    }


    public static int countEasyNumbers(String[] display) {

        int result = 0;

        for (String n: display) {
            if (n.length() == 2 || n.length() == 3 || n.length() == 4 || n.length() == 7 ) {
                result++;
            }
        }

        return result;
    }


    public static int getOutput(String[] patterns, String[] display) {

        String[] digits = new String[10];

        for (String p : patterns) {
            if (p.length() == 2) digits[1] = p;
            else if (p.length() == 3) digits[7] = p;
            else if (p.length() == 4) digits[4] = p;
            else if (p.length() == 7) digits[8] = p;
        }

        for (String p : patterns) {
            if (p.length() == 6) {
                if (includes(p, digits[4])) digits[9] = p;
                else if (includes(p, digits[1])) digits[0] = p;
                else digits[6] = p;
            }
        }

        for (String p : patterns) {
            if (p.length() == 5) {
                if (includes(p, digits[1])) digits[3] = p;
                else if (includes(digits[6], p)) digits[5] = p;
                else digits[2] = p;
            }
        }

        System.out.println(Arrays.toString(digits));

        String result = "";

        for (String s : display) {
            for (int i = 0; i < 10; i++) {
                if (equalChars(s, digits[i]))  {
                    result += String.valueOf(i);
                }
            }
        }

        System.out.println(result);
        return Integer.parseInt(result);

    }

    public static boolean equalChars(String s1, String s2) {

        if (s1.length() != s2.length()) return false;

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        return Arrays.equals(c1, c2);
    }

    public static boolean includes(String s1, String s2) {

        for (char c : s2.toCharArray()) {
            if (!s1.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;

    }

}
