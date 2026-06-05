package at.fhtw.tictactoe;

public class Board {
    private char[][] cells;

    public Board() {
        cells = new char[3][3];
        clear();
    }

    public boolean isCellEmpty(int x, int y) {
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            return false;
        }
        return cells[x][y] == ' ';
    }

    public void place(int x, int y, char marker) {
        if (x >= 0 && x <= 2 && y >= 0 && y <= 2) {
            cells[x][y] = marker;
        }
    }

    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    public char getCell(int x, int y) {
        return cells[x][y];
    }
}