package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day10 {

    public static void main(String[] args) {

        String path = "src/day10/input.txt";
        List<String> lines = getInput(path);

        HashMap<Character, Character> pairs = new HashMap<>();
        pairs.put('(', ')');
        pairs.put('[', ']');
        pairs.put('{', '}');
        pairs.put('<', '>');

        HashMap<Character, Integer> points = new HashMap<>();
        points.put(')', 3);
        points.put(']', 57);
        points.put('}', 1197);
        points.put('>', 25137);

        int errorScore = 0;

        for (String line : lines) {

            ArrayList<Character> openBrackets = new ArrayList<>();

            for (int i = 0; i < line.length(); i++) {
                char current = line.charAt(i);
                char expected = 'x';
                if (!openBrackets.isEmpty())
                    expected = pairs.get(openBrackets.get(openBrackets.size()-1));

                if (pairs.containsKey(current)) {
                    openBrackets.add(current);
                } else if (current == expected) {
                    openBrackets.remove(openBrackets.size() - 1);

                } else {
//                    System.out.println("Unexpected Char " + current + " at position " + i + " (expected " + expected + ") in line " + line);
                    errorScore += points.get(current);
                    break;
                }

            }
        }

        System.out.println("Error Score " + errorScore);

    }



    public static List<String> getInput(String path) {

        ArrayList<String> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null) {

                result.add(line.strip());


            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
