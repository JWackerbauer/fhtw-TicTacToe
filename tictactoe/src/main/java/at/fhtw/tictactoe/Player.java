package at.fhtw.tictactoe;

public class Player {
    // Attribut für das Symbol (z.B. 'X' oder 'O')
    private char marker;

    // Konstruktor
    public Player(char marker) {
        this.marker = marker;
    }

    // Getter-Methode für das Symbol
    public char getMarker() {
        return marker;
    }
}