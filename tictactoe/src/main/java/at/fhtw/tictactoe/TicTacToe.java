package at.fhtw.tictactoe;
import java.util.Scanner;
public class TicTacToe {
    // Private Attribute laut Diagramm
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    // Konstruktor
    public TicTacToe() {
        this.board = new Board();
        this.player1 = new Player('X');
        this.player2 = new Player('O');
        this.currentPlayer = player1; // Spieler 1 (X) beginnt
    }

    // Startet die Spielsteuerung
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Current Player: " + currentPlayer.getMarker());
            board.display();
            System.out.print("row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("column (0-2): ");
            int col = scanner.nextInt();

            if (board.isCellEmpty(row, col)) {
                board.place(row, col, currentPlayer.getMarker());
                board.display();

                if (hasWinner()) {
                    System.out.println("Spieler " + currentPlayer.getMarker() + " hat gewonnen!");
                    gameOver = true;
                } else if (board.isFull()) {
                    System.out.println("Unentschieden!");
                    gameOver = true;
                } else {
                    switchCurrentPlayer();
                }
            } else {
                System.out.println("Feld ist schon besetzt! Nochmal versuchen.");
            }
        }
        Scanner playAgain = new Scanner(System.in);
        System.out.print("Nochmal spielen? (j/n): ");
        String answer = playAgain.nextLine();
        if (answer.equals("j")) {
            board.clear();
            currentPlayer = player1;
            start();
        }
    }

    // Wechselt den aktuellen Spieler (private)
    private void switchCurrentPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    // Prüft, ob es einen Gewinner gibt (private)
    private boolean hasWinner() {
        // Zeilen prüfen
        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) != ' ' &&
                    board.getCell(i, 0) == board.getCell(i, 1) &&
                    board.getCell(i, 1) == board.getCell(i, 2)) {
                return true;
            }
        }
        // Spalten prüfen
        for (int i = 0; i < 3; i++) {
            if (board.getCell(0, i) != ' ' &&
                    board.getCell(0, i) == board.getCell(1, i) &&
                    board.getCell(1, i) == board.getCell(2, i)) {
                return true;
            }
        }
        // Diagonalen prüfen
        if (board.getCell(0, 0) != ' ' &&
                board.getCell(0, 0) == board.getCell(1, 1) &&
                board.getCell(1, 1) == board.getCell(2, 2)) {
            return true;
        }
        if (board.getCell(0, 2) != ' ' &&
                board.getCell(0, 2) == board.getCell(1, 1) &&
                board.getCell(1, 1) == board.getCell(2, 0)) {
            return true;
        }
        return false;
    }}