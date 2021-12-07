package day06;

import java.util.ArrayList;
import java.util.HashMap;

public class Day06 {

    public static void main(String[] args) {

        String input = "3,4,3,1,2";

        ArrayList<Integer> fishes = new ArrayList<>();
        for (String s : input.split(",")) {
            fishes.add(Integer.parseInt(s));
        }

        int days = 80;
        int allfishes = getAllFishes(days, fishes);

        System.out.printf("\nNumber of Fishes after %3d days: %15d", days, allfishes);

        days = 256;
        long allfishesLong = getAllFishesLong(days, fishes);

        System.out.printf("\nNumber of Fishes after %3d days: %15d", days, allfishesLong);
    }

    public static int getAllFishes(int days, ArrayList<Integer> fishes) {

        fishes = new ArrayList<>(fishes);

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

        return fishes.size();
    }


    public static long getAllFishesLong(int days, ArrayList<Integer> fishes) {

        HashMap<Integer, Long> states = new HashMap<>();
        states.put(0, 0L);
        states.put(1, 0L);
        states.put(2, 0L);
        states.put(3, 0L);
        states.put(4, 0L);
        states.put(5, 0L);
        states.put(6, 0L);
        states.put(7, 0L);
        states.put(8, 0L);

        for (Integer fish : fishes) {
            states.put(fish, states.get(fish)+1);
        }

        for (int i = 0; i < days; i++) {

            long newFish = states.get(0);
            for (int j = 0; j < 8; j++ ) {
                states.put(j, states.get(j + 1));
            }
            states.put(6, states.get(6) + newFish);
            states.put(8, newFish);
        }

        long sum = 0;
        for (int i = 0; i <= 8; i++) {
            sum += states.get(i);
        }

        return sum;
    }


}
