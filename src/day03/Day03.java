package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day03 {


    public static void main(String[] args) {

        String path = "src/day03/input_sample.txt";
        int b = 5;

        List<Integer> report = getInput(path);

        int[] count = new int[b];



        for (int n : report) {
            for (int j = 0; j < b; j++) {
                count[j] += n & 1;
                n = n >> 1;
            }

        }

        int gamma = 0;
        int epsilon = 0;
        for (int j = 0; j < b; j++) {
            if (count[j] > report.size() / 2) {
                gamma += Math.pow(2, j);
            } else {
                epsilon += Math.pow(2, j);
            }

        }

        System.out.printf("reort size %s, binary length %s\ngamma %s, epsilon %s, product %s \n",
                report.size(), b, gamma, epsilon, gamma*epsilon);

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
