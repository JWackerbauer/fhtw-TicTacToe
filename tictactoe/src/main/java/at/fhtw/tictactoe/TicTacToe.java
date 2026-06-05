package at.fhtw.tictactoe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;
    private String status;

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        board = new Board();
        status = "playing";
    }

    TicTacToe(Player p1, Player p2, Player current, Board board, String status) {
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = current;
        this.board = board;
        this.status = status;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        board.clear();
        currentPlayer = player1;

        while (true) {
            System.out.println("Current Player: " + currentPlayer.getMarker());
            board.print();

            int row = -1, col = -1;
            while (true) {
                System.out.print("row (0-2): ");
                row = scanner.nextInt();
                System.out.print("column (0-2): ");
                col = scanner.nextInt();

                if (row >= 0 && row <= 2 && col >= 0 && col <= 2 && board.isCellEmpty(row, col)) {
                    break;
                }
                System.out.println("Invalid move. Try again.");
            }

            board.place(row, col, currentPlayer.getMarker());

            if (hasWinner()) {
                board.print();
                System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                return;
            }

            if (board.isFull()) {
                board.print();
                System.out.println("It's a draw!");
                return;
            }

            switchCurrentPlayer();
        }
    }

    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean hasWinner() {
        return hasWinner(currentPlayer.getMarker());
    }

    public boolean hasWinner(char marker) {
        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) == marker && board.getCell(i, 1) == marker && board.getCell(i, 2) == marker) {
                return true;
            }
            if (board.getCell(0, i) == marker && board.getCell(1, i) == marker && board.getCell(2, i) == marker) {
                return true;
            }
        }
        if (board.getCell(0, 0) == marker && board.getCell(1, 1) == marker && board.getCell(2, 2) == marker) {
            return true;
        }
        if (board.getCell(0, 2) == marker && board.getCell(1, 1) == marker && board.getCell(2, 0) == marker) {
            return true;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public String getStatus() {
        return status;
    }

    public List<int[]> getValidMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }

    public String executeMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2 || !board.isCellEmpty(row, col)) {
            return status;
        }

        board.place(row, col, currentPlayer.getMarker());

        if (hasWinner(currentPlayer.getMarker())) {
            status = currentPlayer.getMarker() + " wins";
            return status;
        }

        if (board.isFull()) {
            status = "draw";
            return status;
        }

        switchCurrentPlayer();
        return status;
    }

    public void saveState(String path) throws IOException {
        StringBuilder json = new StringBuilder();
        json.append("{\"board\":[");
        for (int i = 0; i < 3; i++) {
            json.append("[");
            for (int j = 0; j < 3; j++) {
                char c = board.getCell(i, j);
                json.append("\"").append(c == ' ' ? " " : String.valueOf(c)).append("\"");
                if (j < 2) json.append(",");
            }
            json.append("]");
            if (i < 2) json.append(",");
        }
        json.append("],\"currentPlayer\":\"").append(currentPlayer.getMarker());
        json.append("\",\"status\":\"").append(status).append("\"}");
        Files.writeString(Paths.get(path), json.toString());
    }

    public static TicTacToe loadState(String path) throws IOException {
        String json = Files.readString(Paths.get(path));

        char[][] cells = new char[3][3];
        int boardStart = json.indexOf("\"board\":[") + 8;
        int row = 0, col = 0;
        boolean expectValue = true;
        for (int i = boardStart; i < json.length() && row < 3; i++) {
            if (json.charAt(i) == '"') {
                if (expectValue) {
                    char val = json.charAt(i + 1);
                    cells[row][col] = (val == ' ') ? ' ' : val;
                    col++;
                    if (col == 3) { col = 0; row++; }
                }
                expectValue = !expectValue;
            }
        }

        int cpStart = json.indexOf("\"currentPlayer\":\"") + 17;
        char currentMarker = json.charAt(cpStart);

        int stStart = json.indexOf("\"status\":\"") + 10;
        int stEnd = json.indexOf("\"", stStart);
        String status = json.substring(stStart, stEnd);

        Board board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] != ' ') {
                    board.place(i, j, cells[i][j]);
                }
            }
        }

        Player p1 = new Player('X');
        Player p2 = new Player('O');
        Player current = (currentMarker == 'X') ? p1 : p2;

        return new TicTacToe(p1, p2, current, board, status);
    }
}
