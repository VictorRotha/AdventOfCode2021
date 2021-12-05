package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 {

    public static int maxX = -1;
    public static int maxY = -1;


    public static void main(String[] args) {

         String path = "src/day05/input.txt";

         ArrayList<Integer[]> lines = getInput(path);

         int[][] grid = getPart01Grid(lines);

         int result = 0;

         for (int x = 0; x < maxX + 1; x ++) {
              for (int y = 0; y < maxY + 1; y++ ) {
                  if (grid[y][x] > 1) result ++;
              }
         }

         System.out.println("Overlap: " + result);
    }


    public static int[][] getPart01Grid(ArrayList<Integer[]> lines ) {

        int[][] grid = new int[maxY+1][maxX+1];

        for (Integer[] line : lines) {

            int x1 = Math.min(line[0], line[2]);
            int y1 = Math.min(line[1], line[3]);
            int x2 = Math.max(line[0], line[2]);
            int y2 = Math.max(line[1], line[3]);

            if (x1 != x2 && y1 != y2 ) continue;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    grid[y][x] += 1;
                }
            }
        }

        return grid;

    }

    public static void printLines(List<Integer[]> lines) {

         for (Integer[] line : lines) {
             System.out.println(Arrays.toString(line));
         }

         System.out.printf("\nmaxX %s maxY %s\n", maxX, maxY);

    }


    public static void printGrid(int[][] grid) {
        for (int[] l : grid) {
            System.out.println(Arrays.toString(l));
        }
        System.out.println();
    }


    public static ArrayList<Integer[]>  getInput(String path) {


       ArrayList<Integer[]> lines = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                String[] sLine = new String[4];
                Integer[] iLine = new Integer[4];

                sLine = line.strip().split("(,| -> )");
                for (int i = 0; i < 4; i++) {
                    iLine[i] = Integer.parseInt(sLine[i]);
                    if (i == 0 | i == 2) {
                        maxX = Math.max(iLine[i], maxX);
                    } else {
                        maxY = Math.max(iLine[i], maxY);
                    }
                }

                lines.add(iLine);
            }
            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
