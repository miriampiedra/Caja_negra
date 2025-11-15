package org.example;

import main.Board;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardUpdateTest {

    private static final int PLAYER_MOVEMENT = 2;
    private static final int ALIEN_WIDTH = 12;

    @Test
    void testUpdate_PlayerMovement() {
        Board board = new Board();
        Player player = board.getPlayer();
        int initialX = player.getX();

        player.setDx(PLAYER_MOVEMENT);

        board.update();

        assertEquals(initialX + PLAYER_MOVEMENT, player.getX(), "La X del jugador debe aumentar al llamar a update.");
    }

    @Test
    void testUpdate_AlienMovement() {
        Board board = new Board();
        Alien firstAlien = board.getAliens().get(0);
        int initialX = firstAlien.getX();

        board.update();

        assertNotEquals(initialX, firstAlien.getX(), "La X del alien debe cambiar al llamar a update.");
    }

    @Test
    void testUpdate_ShotAlienCollision() {
        Board board = new Board();
        List<Alien> aliens = board.getAliens();
        Shot shot = board.getShot();

        Alien alienToHit = aliens.get(0);
        alienToHit.setX(100);
        alienToHit.setY(100);

        shot.setX(100 + ALIEN_WIDTH / 2);
        shot.setY(100);
        shot.setVisible(true);
        board.update();

        assertTrue(alienToHit.isDying(), "El alien debe estar 'muriendo' (destruido) tras la colisión.");
        assertFalse(shot.isVisible(), "El disparo debe volverse invisible (destruido) tras la colisión.");
    }
}