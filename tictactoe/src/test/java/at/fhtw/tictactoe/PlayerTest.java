package at.fhtw.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void testPlayerMarker() {
        Player p = new Player('X');
        assertEquals('X', p.getMarker());
    }

    @Test
    public void testPlayerMarkerO() {
        Player p = new Player('O');
        assertEquals('O', p.getMarker());
    }
}
