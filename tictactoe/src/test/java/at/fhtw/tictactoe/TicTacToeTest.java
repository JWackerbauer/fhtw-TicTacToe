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

    @Test
    public void testGetValidMovesInitially() {
        assertEquals(9, game.getValidMoves().size());
    }

    @Test
    public void testGetValidMovesAfterPlacement() {
        game.getBoard().place(0, 0, 'X');
        assertEquals(8, game.getValidMoves().size());
    }

    @Test
    public void testExecuteMoveReturnsPlaying() {
        String result = game.executeMove(0, 0);
        assertEquals("playing", result);
        assertEquals('O', game.getCurrentPlayer().getMarker());
    }

    @Test
    public void testExecuteMoveWin() {
        game.executeMove(0, 0);
        game.executeMove(1, 0);
        game.executeMove(0, 1);
        game.executeMove(1, 1);
        String result = game.executeMove(0, 2);
        assertEquals("X wins", result);
    }

    @Test
    public void testExecuteMoveDraw() {
        game.executeMove(0, 0);
        game.executeMove(0, 1);
        game.executeMove(0, 2);
        game.executeMove(1, 1);
        game.executeMove(1, 0);
        game.executeMove(1, 2);
        game.executeMove(2, 1);
        game.executeMove(2, 0);
        String result = game.executeMove(2, 2);
        assertEquals("draw", result);
    }

    @Test
    public void testSaveAndLoadState() throws Exception {
        game.executeMove(1, 1);
        game.executeMove(0, 0);
        String path = "target/test_state.json";
        game.saveState(path);
        TicTacToe loaded = TicTacToe.loadState(path);
        assertEquals('X', loaded.getCurrentPlayer().getMarker());
        assertEquals(' ', loaded.getBoard().getCell(0, 1));
        assertEquals('O', loaded.getBoard().getCell(0, 0));
        assertEquals('X', loaded.getBoard().getCell(1, 1));
        assertEquals("playing", loaded.getStatus());
    }

    @Test
    public void testInitialStatus() {
        assertEquals("playing", game.getStatus());
    }

    @Test
    public void testHasWinnerWithMarker() {
        game.getBoard().place(0, 0, 'O');
        game.getBoard().place(0, 1, 'O');
        game.getBoard().place(0, 2, 'O');
        assertTrue(game.hasWinner('O'));
        assertFalse(game.hasWinner('X'));
    }
}
