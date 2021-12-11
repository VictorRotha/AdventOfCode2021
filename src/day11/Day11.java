package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day11 {

    public static void main(String[] args) {

        String path = "src/day11/input.txt";

        int[][] grid = getInput(path);

//        for (int[] row : grid) {
//            System.out.println(Arrays.toString(row));
//        }

        int steps = 100;
        int total = 0;
        for (int step = 0 ; step < steps; step++) {

            for (int y = 0; y < grid.length; y++) {
                int[] row = grid[y];
                for (int x = 0; x < row.length; x++) {
                    addEnergy(grid, y, x);
                }
            }

            int flashes = 0;
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (grid[y][x] == -1) {
                        grid[y][x] = 0;
                        flashes++;
                    }
                }
            }

            total += flashes;

            /*

            System.out.println("\nStep " + (step+1));
            System.out.println("flashes " + flashes + " total " + total);

            System.out.println();
            for (int[] row : grid) {
                System.out.println(Arrays.toString(row));
            }

             */

        }

        System.out.printf("total %s flashes after %s steps\n", total, steps );

    }

    public static void addEnergy(int[][] grid, int y, int x) {

        int v = grid[y][x];
        if (v != -1) v++;
        if (v > 9) {
            grid[y][x] = -1;;
            ArrayList<int[]> adjacents = findAdjacents(grid, y, x);
            for (int[] adj : adjacents) {
                addEnergy(grid, adj[0], adj[1]);
            }
        } else {
            grid[y][x] = v;
        }
    }


    public static ArrayList<int[]> findAdjacents(int[][] grid, int y, int x) {

        ArrayList<int[]> result = new ArrayList<>();

        int[] adjX = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
        int[] adjY = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};

        int h = grid.length;
        int w = grid[0].length;

        for (int i = 0; i < 8; i++) {

            int ax = x + adjX[i];
            int ay = y + adjY[i];

            if (ax >= 0 && ay >= 0 && ax < w && ay < h && grid[ay][ax] != -1) {

                result.add(new int[]{ay, ax});
            }
        }

        return result;

    }

    public static int[][] getInput(String path) {

        ArrayList<int[]> lines = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {

                int[] row = new int[line.strip().length()];
                for (int i = 0; i < line.strip().length(); i++) {
                    row[i] = Integer.parseInt(String.valueOf(line.charAt(i)));

                }
                lines.add(row);

            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] result = new int[lines.size()][lines.get(0).length];
        for (int i = 0; i < lines.size(); i++) {
            result[i] = lines.get(i);
        }

        return result;


    }



}
