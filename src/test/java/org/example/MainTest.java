package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

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

    @Test
    void playerActLimiteIzquierdo() {
        Player player = new Player();
        player.setX(2);
        player.setDx(-2);  // Simular movimiento hacia la izquierda
        player.act();
        assertEquals(2, player.getX());
    }

    @Test
    void playerActLimiteDerecho() {
        Player player = new Player();
        player.setX(356);
        player.setDx(2); // Simular movimiento hacia la derecha
        player.act();
        assertEquals(356, player.getX());
    }


    @Test //movimiento normal
    void playerActPosicionNormal() {
        Player player = new Player();
        player.setX(50);
        player.setDx(2);;
        player.act();
        assertEquals(52, player.getX());
    }

    @Test
    void playerActLimiteIzquierdoInvalido() {
        Player player = new Player();
        player.setX(-2);
        player.setDx(-5);
        player.act();
        assertEquals(2, player.getX());
    }

    @Test
    void playerActLimiteDerechoInvalido() {
        Player player = new Player();
        player.setX(500);
        player.setDx(5);
        player.act();
        assertEquals(356, player.getX());
    }

    @Test
    void playerActSinMovimiento() {
        Player player = new Player();
        player.setX(2);
        player.setDx(0);
        player.act();
        assertEquals(2, player.getX());
    }

    @Test
    void playerActLimiteIzquierdoMovimientoMayor() {
        Player player = new Player();
        player.setX(5);
        player.setDx(-10);
        player.act();
        assertEquals(2, player.getX());
    }

    @Test
    void playerActLimiteDerechoMovimientoMayor() {
        Player player = new Player();
        player.setX(350);
        player.setDx(20);
        player.act();
        assertEquals(356, player.getX());
    }
}
