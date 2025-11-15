package org.example.Board;
import main.Board;
import main.Commons;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class BoardGameInitTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void gameInitValoresCorrectosPrimerAlienYBoard() { //CP1
        Board board = new Board();
        assertEquals((Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS), board.getAliens().size());
        assertEquals(Commons.ALIEN_INIT_X, board.getAliens().get(0).getX());
        assertEquals(Commons.ALIEN_INIT_Y, board.getAliens().get(0).getY());
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().get(0).getX() - board.getAliens().get(1).getX()));
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().get(0).getY() - board.getAliens().get(Commons.ALIEN_COLUMNS + 1).getY()));
        assertNotEquals(null, board.getPlayer());
        assertNotEquals(null, board.getShot());
    }

    @Test
    void gameInitValoresCorrectosSegundoAlien() { //CP2
        Board board = new Board();
        int index = 1;
        assertEquals(Commons.ALIEN_INIT_X + Commons.ALIEN_SEPARATOR, board.getAliens().get(index).getX());
        assertEquals(Commons.ALIEN_INIT_Y, board.getAliens().get(index).getY());
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().get(index).getX() - board.getAliens().get(2).getX()));
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().get(index).getY() - board.getAliens().get(Commons.ALIEN_COLUMNS + 2).getY()));
    }

    @Test
    void gameInitValoresCorrectosAlienMedio() { //CP3
        Board board = new Board();
        int index = 4;
        assertEquals(Commons.ALIEN_INIT_X+ index *Commons.ALIEN_SEPARATOR, board.getAliens().get(index).getX());
        assertEquals(Commons.ALIEN_INIT_Y + index/Commons.ALIEN_COLUMNS*Commons.ALIEN_SEPARATOR, board.getAliens().get(index).getY());
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().get(index).getX() - board.getAliens().get(index+1).getX()));
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().get(index).getY() - board.getAliens().get(Commons.ALIEN_COLUMNS + index+1).getY()));
    }


    @Test
    void gameInitValoresCorrectosPenultimoAlien() { //CP4
        Board board = new Board();
        int index = board.getAliens().size() - 2;
        assertEquals(Commons.ALIEN_INIT_X + (Commons.ALIEN_COLUMNS-2) * Commons.ALIEN_SEPARATOR, board.getAliens().get(index).getX());
        assertEquals(Commons.ALIEN_INIT_Y + (Commons.ALIEN_ROWS-1) * Commons.ALIEN_SEPARATOR, board.getAliens().get(index).getY());
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().get(index).getX() - board.getAliens().get(index-1).getX()));
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().get(index).getY() - board.getAliens().get(board.getAliens().size()-2-Commons.ALIEN_COLUMNS).getY()));
    }

    @Test
    void gameInitValoresCorrectosUltimoAlien() { //CP5
        Board board = new Board();
        assertEquals(Commons.ALIEN_INIT_X + (Commons.ALIEN_COLUMNS-1) * Commons.ALIEN_SEPARATOR, board.getAliens().getLast().getX());
        assertEquals(Commons.ALIEN_INIT_Y + (Commons.ALIEN_ROWS-1) * Commons.ALIEN_SEPARATOR, board.getAliens().getLast().getY());
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().getLast().getX() - board.getAliens().get(board.getAliens().size() - 2).getX()));
        assertEquals(Commons.ALIEN_SEPARATOR, Math.abs(board.getAliens().getLast().getY() - board.getAliens().get(board.getAliens().size() -1 -Commons.ALIEN_COLUMNS).getY()));
    }
}