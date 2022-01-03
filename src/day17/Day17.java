package day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day17 {


    public static void main(String[] args) {

        String path = "src/day17/input.txt";
        int[] target = getInput(path);

        System.out.println("Target: " + Arrays.toString(target) + "\n");

        partOne(target);

        partTwo(target);

    }

    public static void partTwo(int[] target) {

        int minVY = target[2];
        int maxVY = - target[2] - 1;

        int counter = 0;

        for (int vy = minVY; vy <= maxVY; vy++) {
            for (int vx = 1; vx <= target[1]; vx++) {
               if (shoot2(vx, vy, target)) {
                   counter++;
               }
            }
        }

        System.out.println("Solution Part Two: " + counter);

    }

    public static boolean shoot2(int vx, int vy, int[] target) {

        int x = 0;
        int y = 0;

        while (true) {

            x += vx;
            y += vy;

            if (vx > 0) vx -= 1; else if (vx < 0) vx += 1;
            vy -= 1;

            if (x >= target[0] && x <= target[1] && y >= target[2] && y <= target[3]) return true;
            if (x > target[1] || y < target[2]) return false;
        }

    }


    public static void partOne(int[] target) {

        int vy = - target[2] - 1;
        int result =0;

        while (true) {

            int tx = 0;
            int vx = 1;
            int ty = - vy - 1;

            while (tx < target[0]) {
                tx = shoot(vx, vy, target, ty);
                if (tx < target[0]) {
                    vx++;
                }
            }

            result = vy * (vy + 1) / 2;

            if (tx > target[1]) {
                vy--;
            } else
                break;
        }

        System.out.println("Solution Part One: " + result);

    }


    public static int shoot(int vx, int vy, int[] target, int ytarget) {

        int x = 0;
        int y = 0;

        while (y > ytarget) {

             x += vx;
             y += vy;

             if (vx > 0) vx -= 1; else if (vx < 0) vx += 1;
             vy -= 1;

        }

        return x;
    }


    public static int[] getInput(String path) {

        int[] result = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();

            String[] s = line.strip().split(": ")[1].split(", ");

            String[] sx = s[0].substring(2).split("\\.\\.");
            String[] sy = s[1].substring(2).split("\\.\\.");

            result = new int[] {
                    Integer.parseInt(sx[0]), Integer.parseInt(sx[1]),
                    Integer.parseInt(sy[0]), Integer.parseInt(sy[1])
            };

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
}
