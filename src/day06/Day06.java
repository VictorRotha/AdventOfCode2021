package day06;

import java.util.ArrayList;

public class Day06 {

    public static void main(String[] args) {

        String input = "3,4,3,1,2";

        ArrayList<Integer> fishes = new ArrayList<>();
        for (String s : input.split(",")) {
            fishes.add(Integer.parseInt(s));
        }

        int days = 80;
        for (int day = 0; day < days; day++) {

            int newFishes = 0;
            for (int i = 0; i < fishes.size(); i++) {
                int fish = fishes.get(i);
                if (fish == 0) {
                    fish = 6;
                    newFishes++;
                } else {
                    fish--;
                }
                fishes.set(i, fish);
            }

            for (int i = 0; i < newFishes; i++) {
                fishes.add(8);
            }

        }

        System.out.printf("\nNumber of Fishes after %s days: %s\n", days, fishes.size());

    }
}
