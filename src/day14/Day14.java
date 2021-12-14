package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Day14 {

    public static void main(String[] args) {

        String path = "src/day14/input.txt";

        Result input = getInput(path);

        pairInsertion(input, 10);
        pairInsertion(input, 40);

    }


    public static void addtoHashMap(HashMap<String, Long> map, String element, long value) {

        if (map.containsKey(element))
            map.put(element, map.get(element) + value);
        else
            map.put(element, value);

    }

    public static void pairInsertion(Result input, int steps) {

        HashMap<String, Long> pairs = new HashMap<>();
        HashMap<String, String[]> rules = input.rules;

        String firstElement = input.template[0] + "";

        for (int i = 0; i < input.template.length - 1; i++) {
            String p = String.valueOf(input.template[i]) + input.template[i+1] ;
            addtoHashMap(pairs, p, 1L);
        }

        HashMap<String, Long> np;
        for (int j = 0; j < steps; j++) {
            np = new HashMap<>();
            for (String p : pairs.keySet()) {
                addtoHashMap(np, rules.get(p)[0], pairs.get(p));
                addtoHashMap(np, rules.get(p)[1], pairs.get(p));
            }
            pairs = np;
        }

        HashMap<String, Long> elements = new HashMap<>();
        elements.put(firstElement, 1L);
        for (String p : pairs.keySet()) {
            addtoHashMap(elements, p.charAt(1) + "", pairs.get(p));
        }
        long maxOcc = elements.values().stream().max(Comparator.naturalOrder()).orElse(-1L);
        long minOcc = elements.values().stream().min(Comparator.naturalOrder()).orElse(-1L);

        System.out.printf("Result after %s steps: %s - %s = %s\n", steps, maxOcc, minOcc, maxOcc - minOcc);
    }



    public static Result getInput(String path) {

        char[] template = null;
        HashMap<String, String[]> rules = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                if (row == 0) template = line.strip().toCharArray();

                if (row >= 2) {
                    String[] s = line.strip().split(" -> ");
                    rules.put(s[0], new String[] {s[0].charAt(0) + s[1], s[1] + s[0].charAt(1)});
                }
                row++;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(template, rules);
    }


    public static class Result {

        char[] template;
        HashMap<String, String[]> rules;

        public Result(char[] template, HashMap<String, String[]> rules) {

            this.template = template;
            this.rules = rules;

        }


    }

}
