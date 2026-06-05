package at.fhtw.tictactoe;

public class Board {
    private static final int SIZE = 3;
    private final char[][] cells;

    public Board() {
        cells = new char[SIZE][SIZE];
        clear();
    }

    public boolean isCellEmpty(int x, int y) {
        return cells[x][y] == ' ';
    }

    public void place(int x, int y, char marker) {
        cells[x][y] = marker;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    public void print() {
        System.out.println("\u2581\u2581\u2581\u2581\u2581\u2581");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(cells[i][j] == ' ' ? " " : cells[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("\u2594\u2594\u2594\u2594");
    }

    public char getCell(int x, int y) {
        return cells[x][y];
    }
}
