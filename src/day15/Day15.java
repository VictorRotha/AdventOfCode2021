package day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day15 {


    public static void main(String[] args) {

        String path = "src/day15/input.txt";
        ArrayList<int[]> grid = getInput(path);

        Point[][] pGrid = generateMap(grid);

        Point[][] bigGrid = generateBigMap(grid);

        dijkstra(pGrid);
        dijkstra(bigGrid);

    }


    public static Point[][] generateMap(ArrayList<int[]> grid) {

        Point[][] pGrid = new Point[grid.size()][];
        for (int y = 0; y < grid.size(); y++) {
            Point[] row = new Point[grid.get(0).length];
            for (int x = 0; x < grid.get(0).length; x++) {
                Point p = new Point(x, y, grid.get(y)[x]);
                row[x] = p;
            }
            pGrid[y] = row;
        }

        return pGrid;
    }


    public static Point[][] generateBigMap(ArrayList<int[]> grid) {

        int sizeX = grid.get(0).length;
        int sizeY = grid.size();

        Point[][] pGrid = new Point[grid.size() * 5][];

        for (int y = 0; y < pGrid.length; y++) {
            Point[] row = new Point[sizeX * 5];
            for (int x = 0; x < sizeX * 5; x++) {
                int ox = (x % sizeX);
                int oy = (y % sizeY);

                int risk = grid.get(oy)[ox] + (x / sizeX) + y / sizeY;
                if (risk > 9) risk = (risk % 10) + 1;

                row[x] = new Point(x, y, risk);
            }
            pGrid[y] = row;
        }

    return pGrid;
    }


    public static void dijkstra(Point[][] pGrid) {

        ArrayList<Point> points = new ArrayList<>();

        for (Point[] row : pGrid) points.addAll(Arrays.asList(row));

        Point current = pGrid[0][0];
        current.totalrisk = 0;
        Point target = pGrid[pGrid.length-1][pGrid[0].length-1];

        System.out.println("start: " + current + " target: " + target + " points: " + points.size());

        ArrayList<Point> neighbours;

        while (!points.isEmpty()) {

            int lowestrisk = Integer.MAX_VALUE;
            for (Point p : points) {
                if (p.totalrisk < lowestrisk) {
                    lowestrisk = p.totalrisk;
                    current = p;
                }
            }

            if (current == target) {
                System.out.println("lowest risk " + current.totalrisk);
                break;
            }

            points.remove(current);

            neighbours = new ArrayList<>();

            int[] nxs = new int[] {1, 0, 0, -1};
            int[] nys = new int[] {0, 1, -1, 0};

            int nx, ny;

            for (int i = 0; i < 4; i++) {
                nx = current.x + nxs[i];
                ny = current.y + nys[i];

                if (nx >= 0 && ny >= 0 && nx < pGrid[0].length && ny < pGrid.length) {
                    neighbours.add(pGrid[ny][nx]);
                }
            }

            neighbours.removeIf(point -> !points.contains(point));

            for (Point n : neighbours)
                   n.totalrisk = Math.min(current.totalrisk + n.risk, n.totalrisk);

        }
    }



    public static ArrayList<int[]> getInput(String path) {

        ArrayList<int[]> rows = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.chars().map(c -> c - '0').toArray());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;

    }


    public static class Point {

        int x;
        int y;

        int risk;
        int totalrisk;


        public Point(int x, int y, int risk) {
            this.x = x;
            this.y = y;
            this.risk = risk;
            this.totalrisk = Integer.MAX_VALUE;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", this.x, this.y);
        }
    }


}
