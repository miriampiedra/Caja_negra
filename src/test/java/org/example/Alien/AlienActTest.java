package org.example.Alien;
import main.Commons;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AlienActTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void alienActLimiteIzquierdo() { //CP1
        Alien alien = new Alien(0,0);
        alien.act(-1);
        assertEquals(0, alien.getX());
        assertEquals(0,alien.getY());
    }
    @Test
    void alienActCasiLimiteIzquierdo() { //CP2
        Alien alien = new Alien(1,0);
        alien.act(-2);
        assertEquals(0, alien.getX());
        assertEquals(0,alien.getY());
    }

    @Test //CP2
    void alienActLimiteDerecho() { //CP3
        Alien alien = new Alien(Commons.BOARD_WIDTH,0);
        alien.act(1);
        assertEquals(Commons.BOARD_WIDTH, alien.getX());
        assertEquals(0,alien.getY());
    }
    @Test
    void alienActCasiLimiteDerecho() { //CP4
        Alien alien = new Alien(Commons.BOARD_WIDTH-1,0);
        alien.act(2);
        assertEquals(Commons.BOARD_WIDTH, alien.getX());
        assertEquals(0,alien.getY());
    }


    @Test //CP3
    void alienActPosicionNormal() { //CP5
        Alien alien = new Alien(20,0);
        alien.act(-5);
        assertEquals(15, alien.getX());
        assertEquals(0,alien.getY());
    }

}
