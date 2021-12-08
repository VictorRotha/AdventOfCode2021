package day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day08 {


    public static void main(String[] args) {

        String path = "src/day08/input.txt";

        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line ;
            while ((line = br.readLine()) != null) {
                String[] display = line.split("\\|")[1].strip().split(" ");
                count += countNumbers(display);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Result: " + count);

    }

    public static int countNumbers(String[] display) {
        int result = 0;

        for (String n: display) {
            if (n.length() == 2 || n.length() == 3 || n.length() == 4 || n.length() == 7 ) {
                result++;
            }
        }

        return result;
    }



}
