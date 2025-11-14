package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;


import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class PlayerKeyReleassedTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test //CP1: Left, movimiento, vivo
    void testSoltarIzquierdaJugadorVivoMoviendo() {
        Player player = new Player();
        player.setDying(false);
        player.setDx(-2); // Simula movimiento a la izquierda

        KeyEvent e = new KeyEvent(new java.awt.Component() {
        }, 0, 0, 0, 37, ' ');
        player.keyReleased(e);

        assertEquals(0, player.getDx());
    }

    @Test // CP2: Right, movimiento, vivo
    void testSoltarDerechaJugadorVivoMoviendo() {
        Player player = new Player();
        player.setDying(false);
        player.setDx(2); // Simula movimiento a la derecha

        KeyEvent e = new KeyEvent(new java.awt.Component() {
            }, 0, 0, 0, 39, ' ');
        player.keyReleased(e);

        assertEquals(0, player.getDx());
    }

    @Test //CP3: Left, no movimiento, vivo
    void testSoltarIzquierdaJugadorVivoSinMovimiento() {
        Player player = new Player();
        player.setDying(false);
        player.setDx(0); // No hay moviento

        KeyEvent e = new KeyEvent(new java.awt.Component(){}, 0, 0, 0, 37, ' ');
        player.keyReleased(e);

        assertEquals(0, player.getDx());
    }

    // CP4: Right, no movimiento, vivo
    @Test
    void testSoltarDerechaJugadorVivoSinMovimiento() {
        Player player = new Player();
        player.setDying(false);
        player.setDx(0); // No hay moviento

        KeyEvent e = new KeyEvent(new java.awt.Component(){}, 0, 0, 0, 39, ' ');
        player.keyReleased(e);

        assertEquals(0, player.getDx());
    }

    //ARREGLAR
    // CP5: Space, vivo, dispara
    @Test
    void testSoltarSpaceJugadorVivo() {
        Player player = new Player();
        Shot shot = new Shot();
        player.setDying(false);

        KeyEvent e = new KeyEvent(new java.awt.Component(){}, 0, 0, 0, KeyEvent.VK_SPACE, ' ');
        player.keyReleased(e);

        assertTrue(player.isVisible());
    }

    // CP6: Otra tecla, vivo
    @Test
    void testSoltarTeclaNoReconocidaVivo() {
        Player player = new Player();
        player.setDying(false);
        player.setDx(2);

        KeyEvent e = new KeyEvent(new java.awt.Component(){}, 0, 0, 0, KeyEvent.VK_A, 'a'); // Tecla no reconocida
        player.keyReleased(e);

        boolean resultado = !player.isDying() && player.getDx() == 2;
        assertTrue(resultado);
    }

    // CP7: Left, no vivo
    @Test
    void testSoltarIzquierdaJugadorMuerto() {
        Player player = new Player();
        player.setDying(true);
        player.setDx(-2);

        KeyEvent e = new KeyEvent(new java.awt.Component(){}, 0, 0, 0, 37, ' ');
        player.keyReleased(e);

        // Si el jugador está muerto, la entrada se ignora
        boolean resultado = player.isDying() && player.getDx() == 0;
        assertTrue(resultado);
    }

    // CP8: Right, no vivo
    @Test
    void testSoltarDerechaJugadorMuerto() {
        Player player = new Player();
        player.setDying(true);
        player.setDx(2);

        KeyEvent e = new KeyEvent(new java.awt.Component(){}, 0, 0, 0, 39, ' ');
        player.keyReleased(e);

        boolean resultado = player.isDying() && player.getDx() == 0;
        assertTrue(resultado);
    }

    // CP9: Space, no vivo
    @Test
    void testSoltarSpaceJugadorMuerto() {
        Player player = new Player();
        player.setDying(true);

        KeyEvent e = new KeyEvent(new java.awt.Component(){}, 0, 0, 0, KeyEvent.VK_SPACE, ' ');
        player.keyReleased(e);

        assertTrue(!player.isVisible());
    }
}