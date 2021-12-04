package day04;

import java.util.HashMap;

public class Board {

    HashMap<Integer, Integer[]> data = new HashMap<>();

    int[] rowSum = new int[5];
    int[] colSum = new int[5];

    boolean winner = false;


    public int getUnmarkedSum() {
        int sum = 0;
        for (Integer key : data.keySet()) {
            if (data.get(key)[2] == 0) {
                sum += key;
            }
        }
        return sum;
    }

    public void reset() {

        rowSum = new int[5];
        colSum = new int[5];

        for (Integer key : data.keySet()) {
            data.get(key)[2] = 0;
        }

    }

    public void printGrid() {
        int[][] grid = new int[5][5];
        for (Integer key : data.keySet()) {
            Integer[] value = data.get(key);
            grid[value[0]][value[1]] = key;
        }

        for (int[] i : grid) {
            for (int v : i) {
                System.out.printf(" %2d", v);
            }
            System.out.println();
        }
        System.out.println();




    }



}
