package org.example.Player;
import main.Commons;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerInitPlayerTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void playerInitEnPosicionCorrecta(){
        Player player = new Player();
        assertEquals(Commons.BOARD_WIDTH/2, player.getX());
        assertEquals(Commons.GROUND-10, player.getY());
    }
}
