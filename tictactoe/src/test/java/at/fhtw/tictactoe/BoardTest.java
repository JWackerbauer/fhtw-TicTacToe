package at.fhtw.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testBoardStartsEmpty() {
        assertTrue(board.isCellEmpty(0, 0));
        assertTrue(board.isCellEmpty(1, 1));
        assertTrue(board.isCellEmpty(2, 2));
    }

    @Test
    public void testPlaceMarker() {
        board.place(1, 1, 'X');
        assertFalse(board.isCellEmpty(1, 1));
        assertEquals('X', board.getCell(1, 1));
    }

    @Test
    public void testBoardNotFullInitially() {
        assertFalse(board.isFull());
    }

    @Test
    public void testBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.place(i, j, 'X');
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    public void testClear() {
        board.place(0, 0, 'X');
        board.clear();
        assertTrue(board.isCellEmpty(0, 0));
    }

    @Test
    public void testGetCell() {
        board.place(2, 0, 'O');
        assertEquals('O', board.getCell(2, 0));
        assertEquals(' ', board.getCell(0, 1));
    }
}
