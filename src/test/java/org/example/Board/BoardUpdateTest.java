package org.example.Board;

import main.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class BoardUpdateTest {

    private void invokeUpdate(Board board) {
        try {
            Method m = Board.class.getDeclaredMethod("update");
            m.setAccessible(true);
            m.invoke(board);
        } catch (Exception e) {
            fail("No se pudo invocar Board.update() por reflexión: " + e.getMessage());
        }
    }

    // B1: deaths < 24 → el juego continúa, no debe ponerse "Game won!"
    @Test
    void testUpdateGameContinuesWhenNotAllAliensDestroyed() {
        Board board = new Board();

        board.setDeaths(23);
        board.setInGame(true);
        board.setMessage("Game Over");

        invokeUpdate(board);

        assertTrue(board.isInGame(), "El juego debería seguir en marcha");
        assertNotEquals("Game won!", board.getMessage(),
                "No debería mostrarse 'Game won!' si aún no han muerto todos los aliens");
    }

    // B2: deaths == 24 → el juego termina y mensaje "Game won!"
    @Test
    void testUpdateGameEndsExactlyWhenAllAliensDestroyed() {
        Board board = new Board();

        board.setDeaths(24);
        board.setInGame(true);
        board.setMessage("Game Over");

        invokeUpdate(board);

        assertFalse(board.isInGame(), "El juego debería marcarse como terminado");
        assertEquals("Game won!", board.getMessage(),
                "Al destruir todos los aliens debería mostrarse 'Game won!'");
    }

    // B4: juego ya terminado antes de llamar a update()
    @Test
    void testUpdateWhenGameAlreadyFinished() {
        Board board = new Board();

        board.setDeaths(10);
        board.setInGame(false);
        board.setMessage("Game won!");

        invokeUpdate(board);

        assertFalse(board.isInGame(), "El juego debería seguir terminado");
        assertEquals("Game won!", board.getMessage(),
                "El mensaje de fin de partida no debería cambiar");
    }
}
