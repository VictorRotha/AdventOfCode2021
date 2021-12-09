package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Day09 {

    public static void main(String[] args) {

        String path = "src/day09/input.txt";

        ArrayList<Integer[]> map = getInput(path);


        ArrayList<Integer> lowValues = new ArrayList<>();

//        for (Integer[] row : map) System.out.println(Arrays.toString(row));
//        System.out.println();

        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).length; col++) {

                int value = map.get(row)[col];
                ArrayList<Integer> adj = getAdjacents(map, row, col);

                if (value < Collections.min(adj)) lowValues.add(value);

//                System.out.printf("%s  %s  %s %s  %s  %s\n", row, col, v, adj, Collections.min(adj), ((islowest) ? "lowpoint" : ""));

            }
        }

        int risksum = 0;
        for (int low : lowValues) risksum += 1 + low;

        System.out.println("risksum   " + risksum);

    }


    public static ArrayList<Integer> getAdjacents( ArrayList<Integer[]> map, int row, int col) {

        ArrayList<Integer> adj = new ArrayList<>();

        if (row > 0)
            adj.add(map.get(row - 1)[col]);
        if (row < map.size() - 1)
            adj.add(map.get(row + 1)[col]);
        if (col > 0)
            adj.add(map.get(row)[col - 1]);
        if (col < map.get(row).length-1)
            adj.add(map.get(row)[col + 1]);

        return adj;

    }



    public static ArrayList<Integer[]> getInput(String path) {

        ArrayList<Integer[]> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null) {

                int l = line.strip().length();
                char[] chars = line.strip().toCharArray();
                Integer[] row = new Integer[l];
                for (int i = 0; i < l; i++) {
                    row[i] = Integer.parseInt(String.valueOf(chars[i]));
                }
                result.add(row);

            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;





    }
}
