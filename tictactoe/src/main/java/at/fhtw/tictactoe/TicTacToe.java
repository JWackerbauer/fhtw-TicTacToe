package at.fhtw.tictactoe;

import java.util.Scanner;

public class TicTacToe {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        board = new Board();
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
        char m = currentPlayer.getMarker();

        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) == m && board.getCell(i, 1) == m && board.getCell(i, 2) == m) {
                return true;
            }
            if (board.getCell(0, i) == m && board.getCell(1, i) == m && board.getCell(2, i) == m) {
                return true;
            }
        }

        if (board.getCell(0, 0) == m && board.getCell(1, 1) == m && board.getCell(2, 2) == m) {
            return true;
        }
        if (board.getCell(0, 2) == m && board.getCell(1, 1) == m && board.getCell(2, 0) == m) {
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
}
