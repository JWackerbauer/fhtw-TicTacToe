package at.fhtw.tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    // getMarker - positiver Test X
    @Test
    void testGetMarkerX() {
        Player player = new Player('X');
        assertEquals('X', player.getMarker());
    }

    // getMarker - positiver Test O
    @Test
    void testGetMarkerO() {
        Player player = new Player('O');
        assertEquals('O', player.getMarker());
    }
}
