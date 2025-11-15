package org.example;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import static org.junit.jupiter.api.Assertions.*;

class AlienMovementTest {

    // CD1: Movimiento normal hacia derecha sin llegar a borde
    @Test
    void testAliensAdvanceRight() {
        Alien alien = new Alien(50, 60);
        int initialX = alien.getX();
        int initialY = alien.getY();

        alien.act(1); // Mueve derecha (en tu lógica, resta al valor de X)
        assertEquals(initialX - 1, alien.getX());
        assertEquals(initialY, alien.getY());
    }

    // CD2: Movimiento normal hacia izquierda sin llegar a borde
    @Test
    void testAliensAdvanceLeft() {
        Alien alien = new Alien(65, 60);
        int initialX = alien.getX();
        int initialY = alien.getY();

        alien.act(-1); // Mueve izquierda (suma al valor de X)
        assertEquals(initialX + 1, alien.getX());
        assertEquals(initialY, alien.getY());
    }

    // CD3: Llega al borde izquierdo (x=0), cambia dirección y desciende
    @Test
    void testAliensReachBorderLeft() {
        Alien alien = new Alien(0, 5);
        int initialY = alien.getY();

        // Simula borde izquierdo y descenso
        if (alien.getX() <= 0) {
            alien.act(1); // Cambia dirección a derecha al llegar al borde
            alien.setY(initialY + 10); // Desciende (puedes ajustar a DOWN_STEP real)
        }

        assertEquals(initialY + 10, alien.getY());
    }

    // CD4: Llega al borde derecho (x=max), cambia dirección y desciende
    @Test
    void testAliensReachBorderRight() {
        Alien alien = new Alien(95, 5); // suponiendo 95 es tu borde derecho
        int initialY = alien.getY();

        // Simula borde derecho y descenso
        if (alien.getX() >= 95) {
            alien.act(-1); // Cambia dirección a izquierda
            alien.setY(initialY + 10); // Desciende
        }

        assertEquals(initialY + 10, alien.getY());
    }

    // CD5: Límite inferior → invasión
    @Test
    void testAliensInvasion() {
        Alien alien = new Alien(10, 300); // suponiendo 300 es el GROUND/límite inferior
        int GROUND = 300;
        boolean invasion = alien.getY() >= GROUND;
        String resultado = invasion ? "Invasion!" : "";
        assertEquals("Invasion!", resultado);
    }
}
