package day07;

import java.util.ArrayList;
import java.util.List;

public class Day07 {

    public static void main(String[] args) {

        String input = "16,1,2,0,4,2,7,1,2,14";

        List<Integer> positions = new ArrayList<>();
        int maxPos = -1;
        int minPos = -1;
        for (String s: input.split(",")) {
            int pos = Integer.parseInt(s);
            positions.add(pos);
            maxPos = Math.max(maxPos, pos);
            minPos = (minPos == -1) ? pos : Math.min(minPos, pos);
        }

        int minFuel = -1;
        int resultPos = -1;

        for (int i = minPos; i <= maxPos; i++) {

            int fuel = 0;
            for (int pos : positions) {
                int f = Math.abs(pos - i);
                fuel += f;
            }

            if (fuel < minFuel | minFuel == -1) {
                resultPos = i;
                minFuel = fuel;
            }

        }

        System.out.printf("position %s fuel %s\n", resultPos, minFuel);

    }
}
