package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class PlayerKeyPressedTest {

    @BeforeEach
    void setUp() { }

    @AfterEach
    void tearDown() { }

    // CP1: Tecla LEFT, jugador vivo → dx negativo, mueve izquierda
    @Test
    void testPulsarIzquierdaJugadorVivo() {
        Player player = new Player();
        player.setDying(false);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(e);

        assertEquals(-2, player.getDx());
    }

    // CP2: Tecla RIGHT, jugador vivo → dx positivo, mueve derecha.
    @Test
    void testPulsarDerechaJugadorVivo() {
        Player player = new Player();
        player.setDying(false);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(e);

        assertEquals(2, player.getDx());
    }

    // CP3: Tecla SPACE, jugador vivo
    @Test
    void testPulsarSpaceJugadorVivo() {
        Player player = new Player();
        player.setDying(false);

        // Simula el evento de pulsar SPACE
        KeyEvent e = new java.awt.event.KeyEvent(new java.awt.TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(e);

        // Comprobación 1: dx NO cambia (Player no reacciona a SPACE por diseño actual)
        assertEquals(0, player.getDx());

        // Comprobación 2: simulamos la creación de un disparo como si Player disparara
        int xJugador = player.getX();
        int yJugador = player.getY();
        Shot shot = new Shot(xJugador, yJugador);

        assertEquals(xJugador + 6, shot.getX(), "La X del disparo respecto al jugador");
        assertEquals(yJugador - 1, shot.getY(), "La Y del disparo respecto al jugador");
    }



    // CP4: Tecla no válida (A), jugador vivo → no cambia dx, ignora
    @Test
    void testPulsarTeclaNoReconocidaVivo() {
        Player player = new Player();
        player.setDying(false);
        player.setDx(0);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(e);

        assertEquals(0, player.getDx());
    }

    // CP5: Tecla R, jugador muerto → no hace nada, dx sin cambio
    @Test
    void testPulsarReiniciarJugadorMuerto() {
        Player player = new Player();
        player.setDying(true);
        player.setDx(0);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_R, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(e);

        assertEquals(0, player.getDx());
        assertFalse(player.isDying());
    }

    // CP6: Tecla LEFT, jugador muerto → no debe moverse
    @Test
    void testPulsarIzquierdaJugadorMuerto() {
        Player player = new Player();
        player.setDying(true);
        player.setDx(0);

        KeyEvent e = new KeyEvent(new TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(e);

        // Si el jugador está muerto, dx se queda en 0
        assertEquals(0, player.getDx());
    }
}
