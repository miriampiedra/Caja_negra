package org.example.Alien;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import static org.junit.jupiter.api.Assertions.*;

class AlienInitTest {

    // CP1: x=0, y=0 → Alien inicializado correctamente en (0,0)
    @Test
    void alienInit_CP1() {
        Alien alien = new Alien(0, 0);
        assertEquals(0, alien.getX());
        assertEquals(0, alien.getY());
    }

    // CP2: x=0, y=1 → Alien inicializado correctamente en (0,1)
    @Test
    void alienInit_CP2() {
        Alien alien = new Alien(0, 1);
        assertEquals(0, alien.getX());
        assertEquals(1, alien.getY());
    }

    // CP3: x=1, y=1 → Alien inicializado correctamente en (1,1)
    @Test
    void alienInit_CP3() {
        Alien alien = new Alien(1, 1);
        assertEquals(1, alien.getX());
        assertEquals(1, alien.getY());
    }

    // CP4: x=359, y=0 → x fuera de límites
    @Test
    void alienInit_CP4() {
        Alien alien = new Alien(359, 0);
        assertEquals(358, alien.getX());
        assertEquals(0, alien.getY());
    }

    // CP5: x=0, y=10 → y fuera de límites
    @Test
    void alienInit_CP5() {
        Alien alien = new Alien(0, 351);
        assertEquals(0, alien.getX());
        assertEquals(350, alien.getY());
    }

    // CP6: x=-1, y=3 → x inválido, se ajusta a 0
    @Test
    void alienInit_CP6() {
        Alien alien = new Alien(-1, 3);
        assertEquals(0, alien.getX());
        assertEquals(3, alien.getY());
    }

    // CP8: x=2, y=-1 → y inválido, se ajusta a 0
    @Test
    void alienInit_CP8() {
        Alien alien = new Alien(2, -1);
        assertEquals(2, alien.getX());
        assertEquals(0, alien.getY());
    }

    // CP10: x=6, y=7 → Alien inicializado correctamente en (6,7)
    @Test
    void alienInit_CP10() {
        Alien alien = new Alien(6, 7);
        assertEquals(6, alien.getX());
        assertEquals(7, alien.getY());
    }

    // CP11: x=5, y=4 → Alien inicializado correctamente en (5,4)
    @Test
    void alienInit_CP11() {
        Alien alien = new Alien(5, 4);
        assertEquals(5, alien.getX());
        assertEquals(4, alien.getY());
    }
}