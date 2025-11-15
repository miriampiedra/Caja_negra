package org.example;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;
import java.lang.reflect.Constructor;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShotInitShotTest {

    private static final int ASSUMED_H_SPACE = 6;
    private static final int ASSUMED_V_SPACE = 1;

    @Test
    void testInitShot_NormalPosition() throws Exception {
        int playerX = 100;
        int playerY = 300;

        Constructor<Shot> constructor = Shot.class.getConstructor(int.class, int.class);
        Shot shot = constructor.newInstance(playerX, playerY);

        int expectedX = playerX + ASSUMED_H_SPACE;
        int expectedY = playerY - ASSUMED_V_SPACE;

        assertEquals(expectedX, shot.getX(), "La X del disparo debe ser la del jugador más H_SPACE.");
        assertEquals(expectedY, shot.getY(), "La Y del disparo debe ser la del jugador menos V_SPACE.");
    }

    @Test
    void testInitShot_ZeroPosition() throws Exception {
        int playerX = 0;
        int playerY = 0;

        Constructor<Shot> constructor = Shot.class.getConstructor(int.class, int.class);
        Shot shot = constructor.newInstance(playerX, playerY);

        int expectedX = playerX + ASSUMED_H_SPACE;
        int expectedY = playerY - ASSUMED_V_SPACE;

        assertEquals(expectedX, shot.getX(), "La X del disparo debe ser 0 + H_SPACE.");
        assertEquals(expectedY, shot.getY(), "La Y del disparo debe ser 0 - V_SPACE.");
    }
}