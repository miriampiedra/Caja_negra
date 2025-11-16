package org.example.Player;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;


import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class PlayerKeyReleassedTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test //CP1
    void testSoltarIzquierdaJugadorVivo() {
        Player player = new Player();
        player.setDying(false);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyReleased(e);

        assertEquals(-2, player.getDx());
    }

    @Test // CP2
    void testSoltarDerechaJugadorVivo() {
        Player player = new Player();
        player.setDying(false);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyReleased(e);

        assertEquals(2, player.getDx());
    }

    // CP3
    @Test
    void testSoltarSpaceJugadorVivo() {
        Player player = new Player();
        player.setDying(false);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);
        player.keyReleased(e);

        assertEquals(0, player.getDx());

        int xJugador = player.getX();
        int yJugador = player.getY();
        Shot shot = new Shot(xJugador, yJugador);

        assertEquals(xJugador + 6, shot.getX(), "La X del disparo respecto al jugador");
        assertEquals(yJugador - 1, shot.getY(), "La Y del disparo respecto al jugador");
    }

    // CP4
    @Test
    void testSoltarTeclaNoReconocidaVivo() {
        Player player = new Player();
        player.setDying(false);
        player.setX(2);
        player.setDx(-2);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, KeyEvent.CHAR_UNDEFINED); // Tecla no reconocida
        player.keyReleased(e);

        boolean resultado = !player.isDying() && player.getX() == 2;
        assertTrue(resultado);
    }

    // CP5
    @Test
    void testSoltarRJugadorMuerto() {
        Player player = new Player();
        player.setDying(true);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_R, KeyEvent.CHAR_UNDEFINED);
        player.keyReleased(e);

        assertFalse(player.isDying());
    }

    // CP6
    @Test
    void testSoltarOtraTeclaJugadorMuerto() {
        Player player = new Player();
        player.setDying(true);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyReleased(e);

        assertTrue(player.isDying());
    }

}