package day13;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day13 {

    public static void main(String[] args) {

        String path = "src/day13/input.txt";
        Result result = getInput(path);

        HashSet<Point> points = result.points;
        List<String[]> instructions = result.instructions;

        for (int i = 0; i < instructions.size(); i++)  {
            points = fold(points, instructions.get(i));
            if (i == 0)
                System.out.printf("%s visible dots after first fold.\n\n", points.size());
        }
        printSheet(points);

    }

    public static void printSheet(HashSet<Point> points) {
        int maxX = -1;
        int maxY = -1;
        for (Point p : points) {
            maxX = Math.max(maxX, p.x);
            maxY = Math.max(maxY, p.y);
        }
        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                System.out.print(((points.contains(new Point(x, y)) ? "# " : "  ")));
            }
            System.out.println();
        }

    }

    public static HashSet<Point> fold(HashSet<Point> points, String[] instruction) {

        HashSet<Point> nps= new HashSet<>();
        int v;

        for (Point p : points) {
            v = Integer.parseInt(instruction[1]);
            if ("y".equals(instruction[0]) && p.y > v)
                nps.add(new Point(p.x, v + v - p.y));
            else if ("x".equals(instruction[0]) && p.x > v)
                nps.add(new Point(v + v - p.x, p.y));
            else
                nps.add(p);
        }

        return nps;
    }


    public static Result getInput(String path) {

        HashSet<Point> points = new HashSet<>();
        ArrayList<String[]> intructions = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("fold")) {

                    String[] s = line.strip().split(" ");
                    intructions.add(s[2].split("="));

                } else if (!line.equals("")) {

                    String[] s = line.strip().split(",");
                    points.add(new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1])));

                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(points, intructions);
    }


    public static class Result {

        HashSet<Point> points;
        List<String[]> instructions;

        public Result(HashSet<Point> points, List<String[]> instructions) {
            this.points = points;
            this.instructions = instructions;
        }
    }

}
