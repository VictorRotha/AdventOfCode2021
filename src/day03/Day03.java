package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day03 {


    public static void main(String[] args) {

        String path = "src/day03/input.txt";
        int b = 12;

        List<Integer> report = getInput(path);

        part01(report, b);

        int ox = getRating(report, b, "ox");
        int co = getRating(report, b, "co");

        System.out.printf("Part02\noxygen %s co2 %s\nlife support %s\n\n", ox, co, ox*co);

        int oxr = getRatingRecursive(report, b-1, "ox").get(0);
        int cor = getRatingRecursive(report, b-1, "co").get(0);

        System.out.printf("Part02 recursive\noxygen %s co2 %s\nlife support %s\n", oxr, cor, oxr*cor);


        }

    public static int getRating(List<Integer> report, int bLength, String mode) {

        int pos = bLength-1;
        List<Integer> sample = report;
        List<Integer> nextSample = new ArrayList<>();
        while (pos >= 0) {
            int criteria = getMostCommon(sample, pos);
            for (int number : sample) {
                int bit = ((number & (int) Math.pow(2, pos)) > 0) ? 1 : 0;
                if ((mode.equals("ox") && criteria == bit) || (mode.equals("co") && criteria != bit) ) {
                    nextSample.add(number);
                }
            }

            if (nextSample.size() == 1) {
                return nextSample.get(0);
            }

            sample = nextSample;
            nextSample = new ArrayList<>();
            pos--;

        }

        return -1;
    }


    public static List<Integer> getRatingRecursive(List<Integer> sample, int pos, String mode) {

            int criteria = getMostCommon(sample, pos);
            List<Integer> nextSample = new ArrayList<>();
            for (int number : sample) {
                int bit = ((number & (int) Math.pow(2, pos)) > 0) ? 1 : 0;
                if ((mode.equals("ox") && criteria == bit) || (mode.equals("co") && criteria != bit)) {
                    nextSample.add(number);
                }
            }
            if (nextSample.size() == 1 || pos == 0) return nextSample;

            return getRatingRecursive(nextSample, pos - 1, mode);

    }


    public static int getMostCommon(List<Integer> sample, int pos) {

        int result = 0;

        for (int n: sample) {
            int p = (int) Math.pow(2, pos);
            if ((n&p) > 0)  result++;
        }

        if (result >= sample.size() - result)
            return 1;
        else
            return 0;
    }


    public static void part01(List<Integer> report, int bLength) {

        int[] count = new int[bLength];

        for (int n : report) {
            for (int j = 0; j < bLength; j++) {
                count[j] += n & 1;
                n = n >> 1;
            }
        }

        int gamma = 0;
        int epsilon = 0;
        for (int j = 0; j < bLength; j++) {
            if (count[j] > report.size() / 2) {
                gamma += Math.pow(2, j);
            } else {
                epsilon += Math.pow(2, j);
            }

        }

        System.out.printf("Part01\ngamma %s epsilon %s\npower consumption %s \n\n",
                gamma, epsilon, gamma*epsilon);
    }


    public static List<Integer> getInput(String path) {

        ArrayList<Integer> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(Integer.parseInt(line, 2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
