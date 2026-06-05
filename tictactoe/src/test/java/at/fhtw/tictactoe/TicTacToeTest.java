package at.fhtw.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicTacToeTest {

    private TicTacToe game;

    @BeforeEach
    public void setUp() {
        game = new TicTacToe();
    }

    @Test
    public void testInitialCurrentPlayerIsX() {
        assertEquals('X', game.getCurrentPlayer().getMarker());
    }

    @Test
    public void testSwitchCurrentPlayer() {
        assertEquals('X', game.getCurrentPlayer().getMarker());
        game.switchCurrentPlayer();
        assertEquals('O', game.getCurrentPlayer().getMarker());
        game.switchCurrentPlayer();
        assertEquals('X', game.getCurrentPlayer().getMarker());
    }

    @Test
    public void testNoWinnerOnEmptyBoard() {
        assertFalse(game.hasWinner());
    }

    @Test
    public void testWinByRow() {
        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(0, 1, 'X');
        game.getBoard().place(0, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    public void testWinByColumn() {
        game.getBoard().place(1, 0, 'O');
        game.getBoard().place(1, 1, 'O');
        game.getBoard().place(1, 2, 'O');
        game.switchCurrentPlayer();
        assertTrue(game.hasWinner());
    }

    @Test
    public void testWinByDiagonal() {
        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(1, 1, 'X');
        game.getBoard().place(2, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    public void testWinByAntiDiagonal() {
        game.getBoard().place(0, 2, 'O');
        game.getBoard().place(1, 1, 'O');
        game.getBoard().place(2, 0, 'O');
        game.switchCurrentPlayer();
        assertTrue(game.hasWinner());
    }

    @Test
    public void testNoWinWhenNotThreeInARow() {
        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(0, 1, 'X');
        game.getBoard().place(1, 0, 'O');
        assertFalse(game.hasWinner());
    }

    @Test
    public void testBoardReady() {
        assertNotNull(game.getBoard());
    }
}
