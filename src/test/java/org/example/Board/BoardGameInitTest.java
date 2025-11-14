package org.example.Board;
import main.Board;
import main.Commons;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class BoardGameInitTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void gameInitValoresCorrectos(){
        Board board = new Board();
       /* assertTrue(Commons.ALIEN_ROWS>0);
        assertTrue(Commons.ALIEN_COLUMNS>0);
        assertTrue(Commons.ALIEN_INIT_X>=0 && Commons.ALIEN_INIT_X<=Commons.BOARD_WIDTH);
        assertTrue(Commons.ALIEN_INIT_Y>=0 && Commons.ALIEN_INIT_Y<=Commons.BOARD_HEIGHT);
        assertTrue(Commons.ALIEN_SEPARATOR>0);
        */
        assertEquals((Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS), board.getAliens().size());
        assertEquals(Commons.ALIEN_INIT_X, board.getAliens().getFirst().getX());
        assertEquals(Commons.ALIEN_INIT_Y, board.getAliens().getFirst().getY());
        assertEquals(Commons.ALIEN_SEPARATOR, board.getAliens().getFirst().getX() - board.getAliens().get(1).getX());
        assertEquals(Commons.ALIEN_SEPARATOR, board.getAliens().getFirst().getY() - board.getAliens().get(Commons.ALIEN_COLUMNS + 1).getX());
    }
}
