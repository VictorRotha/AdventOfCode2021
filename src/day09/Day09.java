package day09;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day09 {

    public static void main(String[] args) {

        String path = "src/day09/input.txt";

        ArrayList<Integer[]> map = getInput(path);

        List<Integer[]> lowPoints = getLowPoints(map);

        int riskSum  = 0;
        for (Integer[] point : lowPoints) {
            riskSum += 1 + map.get(point[0])[point[1]];
        }

        System.out.println("Risksum: " + riskSum);


        boolean[][] checked = new boolean[map.size()][map.get(0).length];

        ArrayList<Integer> basinSizes = new ArrayList<>();
        for (Integer[] point : lowPoints) {
            basinSizes.add(getBasin(map, point[0], point[1], checked).size());
        }

        Collections.sort(basinSizes);

        int s = basinSizes.size();
        int product = basinSizes.get(s - 1) * basinSizes.get(s - 2) * basinSizes.get(s - 3);

        System.out.println("Product 3 largest basins: " + product);

    }

    public static ArrayList<Integer[]> getBasin(ArrayList<Integer[]> map, int row, int col, boolean[][] checked) {

        ArrayList<Integer[]> basin = new ArrayList<>();

        if (checked[row][col]) return basin;

        basin.add(new Integer[]{row, col});
        checked[row][col] = true;

        List<Integer[]> adj = findValidAdjacents(map, row, col, checked);

        for (Integer[] a : adj) {
            basin.addAll(getBasin(map, a[0], a[1], checked));
        }
        return basin;

    }

    public static List<Integer[]> findValidAdjacents(ArrayList<Integer[]> map, int row, int col, boolean[][] checked) {

        ArrayList<Integer[]> adj = new ArrayList<>();

        int arow;
        int acol;

        if (row > 0) {

            arow = row - 1;
            acol = col;

            if (map.get(arow)[acol] != 9 && !checked[arow][acol]) {
                adj.add(new Integer[] {arow, acol});
            }

        }

        if (row < map.size() - 1) {

            arow = row + 1;
            acol = col;

            if (map.get(arow)[acol] != 9 && !checked[arow][acol]) {
                adj.add(new Integer[] {arow, acol});
            }

        }

        if (col > 0) {

            arow = row;
            acol = col - 1;

            if (map.get(arow)[acol] != 9 && !checked[arow][acol]) {
                adj.add(new Integer[]{arow, acol});
            }
        }

        if (col < map.get(row).length-1) {

            arow = row;
            acol = col + 1;

            if (map.get(arow)[acol] != 9 && !checked[arow][acol]) {
                adj.add(new Integer[]{arow, acol});
            }
        }

        return adj;
    }

    public static List<Integer[]> getLowPoints(ArrayList<Integer[]> map) {

        ArrayList<Integer[]> lowPoints = new ArrayList<>();

        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).length; col++) {

                int value = map.get(row)[col];
                ArrayList<Integer> adj = getAdjacentValues(map, row, col);

                if (value < Collections.min(adj)) lowPoints.add(new Integer[] {row, col});
            }

        }

        return lowPoints;
    }


    public static ArrayList<Integer> getAdjacentValues( ArrayList<Integer[]> map, int row, int col) {

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
