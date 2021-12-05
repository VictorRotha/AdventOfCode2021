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

         System.out.println("Part 01 Overlaps: " + getOverlaps(grid));

//        grid = getPart02Grid(lines);
//        grid = getPart02Grid_2(lines);

         addDiagonals(grid, lines);

         System.out.println("Part 02 Overlaps: " + getOverlaps(grid));

    }


    public static ArrayList<Integer> getPartList(int x1, int x2) {

        ArrayList<Integer> xVals = new ArrayList<>();

        if (x1 < x2) {
            for (int x = x1; x <= x2; x++) xVals.add(x);
        } else if (x1 > x2) {
            for (int x = x1; x >= x2; x--) xVals.add(x);
        }

        return xVals;
    }


    public static void addDiagonals(int[][] grid, ArrayList<Integer[]> lines) {

        for (Integer[] line : lines) {

            int x1 = line[0];
            int y1 = line[1];
            int x2 = line[2];
            int y2 = line[3];

            if (x1 == x2 || y1 == y2) continue;

            ArrayList<Integer> xVals = getPartList(x1, x2);
            ArrayList<Integer> yVals = getPartList(y1, y2);

            for (int i = 0; i < xVals.size(); i++) {
                grid[yVals.get(i)][xVals.get(i)]++;
            }
        }
    }


    public static int[][] getPart02Grid_2(ArrayList<Integer[]> lines ) {

        int[][] grid = new int[maxY+1][maxX+1];

        for (Integer[] line : lines) {

            int x1 = line[0];
            int y1 = line[1];
            int x2 = line[2];
            int y2 = line[3];

            ArrayList<Integer> xVals = getPartList(x1, x2);
            ArrayList<Integer> yVals = getPartList(y1, y2);

            if (xVals.isEmpty()) {
                for (int i = 0; i < yVals.size(); i++) {
                    xVals.add(x1);
                }
            }

            if (yVals.isEmpty()) {
                for (int i = 0; i < xVals.size(); i++) {
                    yVals.add(y1);
                }
            }

            for (int i = 0; i < xVals.size(); i++) {
                grid[yVals.get(i)][xVals.get(i)]++;
            }

        }

        return grid;
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


    public static int[][] getPart02Grid(ArrayList<Integer[]> lines ) {

        int[][] grid = new int[maxY+1][maxX+1];

        for (Integer[] line : lines) {

            int x1 = line[0];
            int y1 = line[1];
            int x2 = line[2];
            int y2 = line[3];

            int y;
            if (x1 != x2 && y1 != y2 ) {
                y = y1;
                if (x1 < x2) {
                    for (int x = x1; x <= x2; x++) {
                        grid[y][x] += 1;
                        if (y1 < y2)
                            y++;
                        else
                            y--;
                    }
                } else {
                    for (int x = x1; x >= x2; x--) {
                        grid[y][x] += 1;
                        if (y1 < y2)
                            y++;
                        else
                            y--;
                    }
                }
            } else {

                x1 = Math.min(line[0], line[2]);
                y1 = Math.min(line[1], line[3]);
                x2 = Math.max(line[0], line[2]);
                y2 = Math.max(line[1], line[3]);

                for (int x = x1; x <= x2; x++) {
                    for (int yy = y1; yy <= y2; yy++) {
                        grid[yy][x] += 1;
                    }
                }
            }

        }

        return grid;
    }


    public static int getOverlaps(int[][] grid) {
        int result = 0;
        for (int x = 0; x < maxX + 1; x ++) {
            for (int y = 0; y < maxY + 1; y++ ) {
                if (grid[y][x] > 1) result ++;
            }
        }
        return result;
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
                String[] sLine;
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
