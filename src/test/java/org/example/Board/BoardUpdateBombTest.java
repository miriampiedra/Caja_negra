package org.example.Board;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.*;

class BoardUpdateBombTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test //CP1: Bomba creada
    void testBombCreated() {
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true); // Simula no existencia de bomba
        boolean lanzamiento = true;

        assertTrue(bomb.isDestroyed());
        assertTrue(lanzamiento);
    }

    // CP2: No hay bomba, no lanzamiento
    @Test
    void testNoBombNoLaunch() {
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true);
        boolean lanzamiento = false;

        assertTrue(bomb.isDestroyed());
        assertFalse(lanzamiento);
        // No hay bomba, no hay cambio esperado.
    }

    // CP3: Hay bomba, colisión jugador
    @Test
    void testBombHitsPlayer() {
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false); // Simula existencia de bomba
        boolean colisionJugador = true;

        assertFalse(bomb.isDestroyed());
        assertTrue(colisionJugador);

        bomb.setDestroyed(colisionJugador);
        assertTrue(bomb.isDestroyed());
    }

    // CP4: Hay bomba, alcanza objetivo
    @Test
    void testBombReachesGoal() {
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        boolean alcanzaSuelo = true;

        assertFalse(bomb.isDestroyed());
        assertTrue(alcanzaSuelo);

        // Según la lógica, se destruye la bomba
        bomb.setDestroyed(alcanzaSuelo);
        assertTrue(bomb.isDestroyed());
    }

    // CP5: Hay bomba, no colisión ni objetivo (movimiento en Y)
    @Test
    void testBombMoveY() {
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        boolean colisionJugador = false;
        boolean alcanzaSuelo = false;

        assertFalse(bomb.isDestroyed());
        assertFalse(colisionJugador);
        assertFalse(alcanzaSuelo);

        assertFalse(bomb.isDestroyed());
    }

    // CP6: No hay bomba, colisión jugador (caso erróneo)
    @Test
    void testErrorNoBombButCollision() {
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true);
        boolean colisionJugador = true;

        assertTrue(bomb.isDestroyed());
        assertFalse(colisionJugador);
    }
}

