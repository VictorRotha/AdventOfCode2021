package day01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day01 {


    public static void main(String[] args) {

        List<Integer> input = getInput("src/day01/input.txt");
        int increased = 0;
        int decreased = 0;
        int prev = -1;

        for (int i = 0; i < input.size(); i++) {
            if (i > 0) {
                if (input.get(i) > prev) increased++;
                if (input.get(i) < prev) decreased++;
            }
            prev = input.get(i);

        }

        System.out.printf("total: %s, increased: %s, decreased %s%n", input.size(), increased, decreased);

    }

    public static List<Integer> getInput(String filename) {

        List<Integer> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line;
            while ((line = br.readLine())  != null) {
                result.add(Integer.valueOf(line));
             }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
}
