package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Day12 {

    public static void main(String[] args) {

        String filepath = "src/day12/input.txt";

        ArrayList<String[]> map = getInput(filepath);

        ArrayList<String> path = new ArrayList<>();
        path.add("start");

        System.out.println("Part 1 possible Paths: " + getPath(path, map));

    }


    public static int getPath(ArrayList<String> path, ArrayList<String[]> map) {

        String last = path.get(path.size()-1);

        int count = 0;

        if (Objects.equals(last, "end")) {
            count ++;

        } else {

            ArrayList<String> neighbours = getValidNeighbours(path, map);

            for (String n : neighbours) {
                ArrayList<String> nPath = new ArrayList<>(path);
                nPath.add(n);
                count += getPath(nPath, map);
            }

        }

        return count;
    }


    public static ArrayList<String> getValidNeighbours(ArrayList<String> path, ArrayList<String[]> map) {

        String last = path.get(path.size()-1);

        ArrayList<String> neighbours = new ArrayList<>();
        for (String[] pair : map) {
            if (pair[0].equals(last)) {
                neighbours.add(pair[1]);
            } else if (pair[1].equals(last)) {
                neighbours.add(pair[0]);
            }
        }

        neighbours.removeIf(n -> n.equals(n.toLowerCase()) && path.contains(n));

        return neighbours;
    }


    public static ArrayList<String[]> getInput(String path) {

        ArrayList<String[]> result = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line.strip().split("-"));

            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
