package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {

    public static void main(String[] args) {

        String path = "src/day02/input.txt";

        List<String[]> commands = getInput(path);

        part01Rules(commands);

        part02Rules(commands);

    }

    public static void part02Rules(List<String[]> commands) {

        int x = 0;
        int z = 0;
        int aim = 0;

        for (String[] command : commands) {
            int value = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "up":
                    aim -= value;
                    break;
                case "down":
                    aim += value;
                    break;
                case "forward":
                    x += value;
                    z += aim * value;
                    break;
                default:
                    System.out.println("unknown command in " + Arrays.toString(command));
            }
        }

        System.out.printf("Part 02 result: x %s z %s product %s \n", x, z, x * z);

    }


    public static void part01Rules(List<String[]> commands) {

        int x = 0; int z = 0;

        for (String[] command : commands) {
            int value = Integer.parseInt(command[1]);
            switch (command[0]) {
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
                    System.out.println("unknown command in " + Arrays.toString(command));
            }
        }

        System.out.printf("Part 01 result: x %s z %s product %s \n", x, z, x*z);

    }


    public static List<String[]> getInput(String path) {

        List<String[]> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                result.add(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
