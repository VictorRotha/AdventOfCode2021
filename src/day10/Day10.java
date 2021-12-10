package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

        HashMap<Character, Integer> errorPoints = new HashMap<>();
        errorPoints.put(')', 3);
        errorPoints.put(']', 57);
        errorPoints.put('}', 1197);
        errorPoints.put('>', 25137);

        int errorScore = 0;

        HashMap<Character, Integer> autoPoints = new HashMap<>();
        autoPoints.put('(', 1);
        autoPoints.put('[', 2);
        autoPoints.put('{', 3);
        autoPoints.put('<', 4);

        ArrayList<Long> autoScores = new ArrayList<>();

        for (String line : lines) {

            ArrayList<Character> openBrackets = new ArrayList<>();
            boolean corrupt = false;

            for (int i = 0; i < line.length(); i++) {
                char current = line.charAt(i);
                char expected = 'x';
                if (!openBrackets.isEmpty())
                    expected = pairs.get(openBrackets.get(openBrackets.size()-1));

                if (pairs.containsKey(current)) {
                    openBrackets.add(current);
                } else if (current == expected) {
                    openBrackets.remove(openBrackets.size() - 1);

                } else{
                    errorScore += errorPoints.get(current);
                    corrupt = true;
                    break;
                }
            }

            if (!corrupt) {

                long autoScore = 0;
                for (int i = openBrackets.size()-1; i >= 0; i--) {
                    autoScore = autoScore * 5 + autoPoints.get(openBrackets.get(i));
                }
                autoScores.add(autoScore);
            }
        }

        System.out.println("Error  Score: " + errorScore);

        Collections.sort(autoScores);
        System.out.println("Middle Score: " + autoScores.get(autoScores.size() / 2));

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
