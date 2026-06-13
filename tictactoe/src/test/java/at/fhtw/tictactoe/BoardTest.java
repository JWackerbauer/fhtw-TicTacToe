package at.fhtw.tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    // isCellEmpty - positiver Test
    @Test
    void testIsCellEmptyTrue() {
        Board board = new Board();
        assertTrue(board.isCellEmpty(0, 0));
    }

    // isCellEmpty - negativer Test
    @Test
    void testIsCellEmptyFalse() {
        Board board = new Board();
        board.place(0, 0, 'X');
        assertFalse(board.isCellEmpty(0, 0));
    }

    // isCellEmpty - ungueltige Koordinaten
    @Test
    void testIsCellEmptyOutOfBounds() {
        Board board = new Board();
        assertFalse(board.isCellEmpty(5, 5));
    }

    // place - positiver Test
    @Test
    void testPlaceValid() {
        Board board = new Board();
        board.place(1, 1, 'X');
        assertEquals('X', board.getCell(1, 1));
    }

    // place - negativer Test (ausserhalb)
    @Test
    void testPlaceOutOfBounds() {
        Board board = new Board();
        board.place(5, 5, 'X');
        // kein Fehler, wird einfach ignoriert
    }

    // getCell - positiver Test
    @Test
    void testGetCell() {
        Board board = new Board();
        board.place(0, 0, 'O');
        assertEquals('O', board.getCell(0, 0));
    }

    // getCell - leeres Feld
    @Test
    void testGetCellEmpty() {
        Board board = new Board();
        assertEquals(' ', board.getCell(0, 0));
    }

    // isFull - positiver Test (Board voll)
    @Test
    void testIsFull() {
        Board board = new Board();
        char[] symbols = {'X','O','X','O','X','O','X','O','X'};
        int idx = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board.place(i, j, symbols[idx++]);
        assertTrue(board.isFull());
    }

    // isFull - negativer Test (Board nicht voll)
    @Test
    void testIsFullFalse() {
        Board board = new Board();
        board.place(0, 0, 'X');
        assertFalse(board.isFull());
    }

    // clear - Test
    @Test
    void testClear() {
        Board board = new Board();
        board.place(0, 0, 'X');
        board.clear();
        assertEquals(' ', board.getCell(0, 0));
    }

    // clear - alle Felder leer nach clear
    @Test
    void testClearAllEmpty() {
        Board board = new Board();
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        board.clear();
        assertTrue(board.isCellEmpty(0, 0));
        assertTrue(board.isCellEmpty(1, 1));
    }
}
