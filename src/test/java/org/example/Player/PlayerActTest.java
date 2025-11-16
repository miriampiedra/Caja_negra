package org.example.Player;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import static org.junit.jupiter.api.Assertions.*;

class PlayerActTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test //tutorial
    void crearAlienPosicionCorrectamente() {
        int valorX = 10;
        int valorY = 10;
        Alien alien = new Alien(valorX, valorY);
        boolean resultado = alien.getX() == valorX && alien.getY() == valorY;
        assertTrue(resultado);
    }

    @Test //CP1
    void playerActLimiteIzquierdo() {
        Player player = new Player();
        player.setX(2);
        player.setDx(-2);  // Simular movimiento hacia la izquierda
        player.act();
        assertEquals(2, player.getX());
    }

    @Test //CP2
    void playerActLimiteDerecho() {
        Player player = new Player();
        player.setX(356);
        player.setDx(2); // Simular movimiento hacia la derecha
        player.act();
        assertEquals(356, player.getX());
    }


    @Test //CP3
    void playerActPosicionNormal() {
        Player player = new Player();
        player.setX(50);
        player.setDx(2);;
        player.act();
        assertEquals(52, player.getX());
    }

    @Test //CP4
    void playerActLimiteIzquierdoInvalido() {
        Player player = new Player();
        player.setX(-2);
        player.setDx(-5);
        player.act();
        assertEquals(2, player.getX());
    }

    @Test //CP5
    void playerActLimiteDerechoInvalido() {
        Player player = new Player();
        player.setX(500);
        player.setDx(5);
        player.act();
        assertEquals(356, player.getX());
    }

    @Test //CP6
    void playerActSinMovimiento() {
        Player player = new Player();
        player.setX(2);
        player.setDx(0);
        player.act();
        assertEquals(2, player.getX());
    }

    @Test //CP7
    void playerActLimiteIzquierdoMovimientoMayor() {
        Player player = new Player();
        player.setX(5);
        player.setDx(-10);
        player.act();
        assertEquals(2, player.getX());
    }

    @Test //CP8
    void playerActLimiteDerechoMovimientoMayor() {
        Player player = new Player();
        player.setX(350);
        player.setDx(20);
        player.act();
        assertEquals(356, player.getX());
    }
}
