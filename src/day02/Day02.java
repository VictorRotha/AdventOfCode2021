package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day02 {

    public static void main(String[] args) {

        String path = "src/day02/input.txt";

        int x = 0;
        int z = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                int value = Integer.parseInt(s[1]);
                switch (s[0]) {
                    case "up":
                        z -= value;
                        break;
                    case "down":
                        z += value;
                        break;
                    case "forward":
                        x += value;
                        break;
                    default:
                        System.out.println("unknown command in " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("x %s z %s product %s", x, z, x*z);

    }
}
