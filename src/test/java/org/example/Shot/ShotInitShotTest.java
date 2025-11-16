package org.example.Shot;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ShotInitShotTest {

    /**
     * Utilidad para leer un campo int privado de Shot (H_SPACE, V_SPACE).
     */
    private int getPrivateIntField(Shot shot, String fieldName) {
        try {
            Field f = Shot.class.getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.getInt(shot);
        } catch (Exception e) {
            fail("No se pudo acceder al campo privado " + fieldName + ": " + e.getMessage());
            return 0; // nunca debería llegar aquí por el fail()
        }
    }

    // S1: Disparo desde una posición central del jugador
    @Test
    void testShotInitCentralPosition() {
        int playerX = 100;
        int playerY = 200;

        Shot shot = new Shot(playerX, playerY);

        int H_SPACE = getPrivateIntField(shot, "H_SPACE");
        int V_SPACE = getPrivateIntField(shot, "V_SPACE");

        assertEquals(playerX + H_SPACE, shot.getX(),
                "La X del disparo debe ser X_jugador + H_SPACE");
        assertEquals(playerY - V_SPACE, shot.getY(),
                "La Y del disparo debe ser Y_jugador - V_SPACE");
        assertTrue(shot.isVisible(), "Un disparo recién creado debería ser visible");
    }

    // S2: Disparo desde un valor de X pequeño (cercano al borde izquierdo)
    @Test
    void testShotInitFromLeftSide() {
        int playerX = 10;
        int playerY = 250;

        Shot shot = new Shot(playerX, playerY);

        int H_SPACE = getPrivateIntField(shot, "H_SPACE");
        int V_SPACE = getPrivateIntField(shot, "V_SPACE");

        assertEquals(playerX + H_SPACE, shot.getX());
        assertEquals(playerY - V_SPACE, shot.getY());
        assertTrue(shot.isVisible());
    }

    // S3: Disparo desde un valor de X grande (cercano al borde derecho)
    @Test
    void testShotInitFromRightSide() {
        int playerX = 300;
        int playerY = 250;

        Shot shot = new Shot(playerX, playerY);

        int H_SPACE = getPrivateIntField(shot, "H_SPACE");
        int V_SPACE = getPrivateIntField(shot, "V_SPACE");

        assertEquals(playerX + H_SPACE, shot.getX());
        assertEquals(playerY - V_SPACE, shot.getY());
        assertTrue(shot.isVisible());
    }

    // S4: Robustez, usando una Y “no estándar”
    @Test
    void testShotInitWithDifferentY() {
        int playerX = 150;
        int playerY = 100; // no tiene por qué ser la fila habitual del jugador

        Shot shot = new Shot(playerX, playerY);

        int H_SPACE = getPrivateIntField(shot, "H_SPACE");
        int V_SPACE = getPrivateIntField(shot, "V_SPACE");

        assertEquals(playerX + H_SPACE, shot.getX());
        assertEquals(playerY - V_SPACE, shot.getY());
        assertTrue(shot.isVisible());
    }
}
