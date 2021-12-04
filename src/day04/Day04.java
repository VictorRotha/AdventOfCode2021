package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04 {

    public static List<Integer> draws = null;
    public static int lastChecked = -1;

    public static void main(String[] args) {

        String path = "src/day04/input.txt";

        List<Board> boards = getInput(path);

        System.out.println(draws.toString() + "\n");

        //Part 01

        Board winner = getWinnerBoard(boards);

        if (winner == null) {
            System.out.println("Nobody wins !");
            return;
        }

        int sum = winner.getUnmarkedSum();

        System.out.println("WINNER");
        winner.printGrid();
        System.out.println("rowSums: " + Arrays.toString(winner.rowSum));
        System.out.println("colSums: " + Arrays.toString(winner.colSum));

        System.out.printf("\nsum %s last %s product %s\n", sum, lastChecked, sum*lastChecked);


        //Part 02

        for (Board b : boards) b.reset();

        List<Board> lastWinners = lastWinnerRecursive(boards, 0, draws.size()-1 );
        Board lastWinner = lastWinners.get(0);

        sum = lastWinner.getUnmarkedSum();

        System.out.println("\nLAST WINNER");
        winner.printGrid();
        System.out.println("rowSums: " + Arrays.toString(lastWinner.rowSum));
        System.out.println("colSums: " + Arrays.toString(lastWinner.colSum));

        System.out.printf("\nsum %s last %s product %s\n", sum, lastChecked, sum*lastChecked);

    }


    public static List<Board> lastWinnerRecursive(List<Board> boards, int drawPos, int maxPos) {

        Integer draw = draws.get(drawPos);
        ArrayList<Board> newSample = new ArrayList<>();

        for (Board b : boards) {
            if (b.data.containsKey(draw)) {
                b.data.get(draw)[2] = 1;
                int r = b.data.get(draw)[0];
                int c = b.data.get(draw)[1];

                b.rowSum[r]++;
                b.colSum[c]++;

                if (b.rowSum[r] == 5 || b.colSum[c] == 5) {
                    b.winner = true;
                }
            }

            if (!b.data.containsKey(draw) || !b.winner) newSample.add(b);
        }

        if (newSample.size() == 0 || drawPos == maxPos) {
            lastChecked = draw;
            return boards;
        } else
            return lastWinnerRecursive(newSample, drawPos + 1, maxPos);

    }

    public static Board getWinnerBoard(List<Board> boards) {

        for (Integer draw : draws) {

            for (Board b : boards) {
                if (b.data.containsKey(draw)) {
                    b.data.get(draw)[2] = 1;
                    int r = b.data.get(draw)[0];
                    int c = b.data.get(draw)[1];

                    b.rowSum[r]++;
                    b.colSum[c]++;

                    if (b.rowSum[r] == 5 || b.colSum[c] == 5) {
                        lastChecked = draw;
                        return b;
                    }
                }
            }

        }

    return null;

    }

    public static List<Board> getInput(String path) {

        ArrayList<Board> boards = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            Board board = null;
            int currentRow = 0;
            while ((line = br.readLine()) != null) {
                if (draws == null) {
                    draws = new ArrayList<>();
                    for (String s : line.strip().split(",")) {
                        draws.add(Integer.parseInt(s));
                    }
                    continue;
                }

                if (line.strip().length() == 0) {
                    if (board != null) boards.add(board);
                    board = new Board();
                    currentRow = 0;
                    continue;
                }

                if (board != null) {
                    String[] row = line.strip().split(" +");
                    for (int i = 0; i < row.length; i++) {
                        int key = Integer.parseInt(row[i]);
                        Integer[] value = new Integer[]{currentRow, i, 0};
                        board.data.put(key, value);
                    }
                    currentRow++;
                }

            }

            if (board != null) boards.add(board);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return boards;

    }
}
