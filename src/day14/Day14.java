package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Day14 {

    public static void main(String[] args) {

        String path = "src/day14/input_sample.txt";

        Result input = getInput(path);

        HashMap<String, Integer> elements = new HashMap<>();

        ArrayList<String> template = new ArrayList<>();
        for (char c : input.template) {
            String element = String.valueOf(c);
            if (elements.containsKey(element))
                elements.put(element, elements.get(element) + 1);
            else
                elements.put(element, 1);
            template.add(element);
        }

        int steps = 10;
        ArrayList<String> insertion;
        for (int j = 0; j < steps; j++) {

            insertion = new ArrayList<>();

            for (int i = 0; i < template.size() - 1; i++) {
                String p = template.get(i) + template.get(i + 1);

                String element = input.pairs.get(p);
                if (elements.containsKey(element))
                    elements.put(element, elements.get(element) + 1);
                else
                    elements.put(element, 1);

                insertion.add(element);
            }

            for (int i = insertion.size() - 1; i >= 0; i--) {
                template.add(i + 1, insertion.get(i));
            }

        }

        int maxOcc = elements.values().stream().max(Comparator.naturalOrder()).orElse(-1);
        int minOcc = elements.values().stream().min(Comparator.naturalOrder()).orElse(-1);

        System.out.printf("Result after %s steps: %s - %s = %s\n", steps, maxOcc, minOcc, maxOcc - minOcc);

    }


    public static Result getInput(String path) {

        char[] template = null;
        HashMap<String, String> pairs = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                if (row == 0) template = line.strip().toCharArray();

                if (row >= 2) {
                    String[] s = line.strip().split(" -> ");
                    pairs.put(s[0], s[1]);
                }
                row++;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(template, pairs);

    }


    public static class Result {

        char[] template;
        HashMap<String, String> pairs;

        public Result(char[] template, HashMap<String, String> pairs) {

            this.template = template;
            this.pairs = pairs;

        }


    }

}
