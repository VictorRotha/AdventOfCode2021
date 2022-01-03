package day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day17 {


    public static void main(String[] args) {

        String path = "src/day17/input.txt";
        int[] target = getInput(path);

        System.out.println(Arrays.toString(target));

        int vy = - target[2] - 1;

        System.out.println("vy " + vy);
        System.out.println("max y for this vy " + vy * (vy + 1) / 2);

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

            System.out.println("vx " + vx + " vy " + vy + " ty " + ty  + " tx " + tx);
            if (tx > target[1]) {
                vy--;
            } else
                break;
        }

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
