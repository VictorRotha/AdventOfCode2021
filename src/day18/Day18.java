package day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day18 {

    public static void main(String[] args) {

        String path = "src/day18/input.txt";

        List<String> homework = getInput(path);

        String sample = homework.get(0);
        for (int i = 0; i < homework.size()-1; i ++) {
            sample = addSnailfish(sample, homework.get(i+1));
            sample = reduceSnailfish(sample);
        }

        System.out.println("\nnew SnailfishNumber: " + sample);
        System.out.println("Magnitude: " + magnitude(sample));
        System.out.println("Max Magnitude: " + maxMagnitude(homework));

    }

    public static int maxMagnitude(List<String> homework) {

        int maxMag = 0;

        for (int i = 0; i < homework.size(); i++) {

            for ( int j = 0; j < homework.size(); j++) {

                if (i == j) continue;

                String sample = reduceSnailfish(addSnailfish(homework.get(i), homework.get(j)));
                maxMag = Math.max(maxMag, Integer.parseInt(magnitude(sample)));

            }

        }

        return maxMag;
    }

    public static String reduceSnailfish(String sample) {
        String newSN;
        boolean reduced = false;
        while (!reduced)  {

            reduced = true;
            newSN = explode(sample);

            if (!sample.equals(newSN)) {
                sample = newSN;
                reduced = false;
            } else {
                newSN = split(sample);
                if (!sample.equals(newSN)) {
                    sample = newSN;
                    reduced = false;
                }
            }
        }

        return sample;

    }

    public static String magnitude(String sn) {

        int pos = 0;

        ArrayList<String> nList = new ArrayList<>(Arrays.asList(sn.split("[\\[\\],]+")));
        ArrayList<String> bList = new ArrayList<>(Arrays.asList(sn.split("[0-9]+")));

        while (pos != -1) {

            boolean isOpenBracket = false;

            pos = -1;
            int mag = -1;

            for (int i = 0; i < bList.size(); i++) {

                if (bList.get(i).startsWith("]") && isOpenBracket) {

                    int x = Integer.parseInt(nList.get(i - 1));
                    int y = Integer.parseInt(nList.get(i));

                    mag = 3 * x + 2 * y;

                    pos = i;
                    break;

                }

                for (char c : bList.get(i).toCharArray()) {
                    if (c == '[') isOpenBracket = true;
                    else if (c == ']') isOpenBracket = false;
                }

            }

            if (pos != -1) {

                nList.remove(pos);
                nList.set(pos - 1, String.valueOf(mag));

                bList.set(pos, bList.get(pos).substring(1));
                bList.remove(pos - 1);
                String s = bList.get(pos - 2);
                bList.set(pos - 2, s.substring(0, s.length() - 1));
            }
        }

        return zipList(nList, bList);

    }

    public static String addSnailfish(String s1, String s2) {
        return "[" +s1 + "," + s2 + "]";
    }


    public static String split(String sn) {

        ArrayList<String> nList = new ArrayList<>(Arrays.asList(sn.split("[\\[\\],]+")));
        ArrayList<String> bList = new ArrayList<>(Arrays.asList(sn.split("[0-9]+")));


        for (int i = 1; i < nList.size(); i++) {

            int number = Integer.parseInt(nList.get(i));

            if (number > 9) {

                int left = number/2;
                int right = ((number % 2) == 0) ? left : left + 1;

                nList.set(i, String.valueOf(right));
                nList.add(i, String.valueOf((left)));

                bList.set(i, "]" + bList.get(i));
                bList.add(i, ",");
                bList.set(i-1, bList.get(i-1) + "[");

                break;

            }
        }

        return zipList(nList, bList);
    }



    public static String explode(String sn) {


        ArrayList<String> nList = new ArrayList<>(Arrays.asList(sn.split("[\\[\\],]+")));
        ArrayList<String> bList = new ArrayList<>(Arrays.asList(sn.split("[0-9]+")));


        int openBrackets = 0;
        boolean isOpenBracket = false;

        int pos = -1;

        for (int i = 0; i < bList.size(); i++) {

            if (bList.get(i).startsWith("]") && openBrackets > 4 && isOpenBracket) {

                int x = Integer.parseInt(nList.get(i-1));
                int y = Integer.parseInt(nList.get(i));

                if (i > 2) {
                    int left = Integer.parseInt(nList.get(i-2));
                    nList.set(i-2, String.valueOf(left + x));

                }
                if (i < nList.size()-1) {
                    int right = Integer.parseInt(nList.get(i+1));
                    nList.set(i+1, String.valueOf(right + y));
                }

                pos = i;
                break;

            }

            for (char c : bList.get(i).toCharArray()) {
                if (c == '[') {
                    isOpenBracket = true;
                    openBrackets ++;
                } else if (c == ']') {
                    isOpenBracket = false;
                    openBrackets --;
                    }
            }

        }

        if (pos == -1) return sn;

        nList.remove(pos);
        nList.set(pos - 1, "0");

        bList.set(pos, bList.get(pos).substring(1));
        bList.remove(pos - 1);
        String s = bList.get(pos - 2);
        bList.set(pos - 2, s.substring(0 , s.length()-1));

        return zipList(nList, bList);

    }

    public static String zipList(List<String> aList, List<String> bList ) {

        String result = bList.get(0);

        for (int i = 1; i < bList.size(); i++) {
            result += aList.get(i) + bList.get(i);
        }

        return result;

    }

    public static ArrayList<String> getInput(String path) {

        ArrayList<String> rows = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.strip());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;

    }

}
