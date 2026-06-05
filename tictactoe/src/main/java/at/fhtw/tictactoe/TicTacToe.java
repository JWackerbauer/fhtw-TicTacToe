package at.fhtw.tictactoe;

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
        int row = 0;
        int col = 0;

        // Prüfen, ob das Feld frei ist und Symbol platzieren (US1)
        if (board.isCellEmpty(row, col)) {
            board.place(row, col, currentPlayer.getMarker());
            board.display();
            System.out.println("Spieler " + currentPlayer.getMarker() + " hat auf Feld (" + row + "," + col + ") gesetzt.");
        }

        switchCurrentPlayer();
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
        return false;
    }
}