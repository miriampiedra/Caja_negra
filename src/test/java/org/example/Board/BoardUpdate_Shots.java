package org.example.Board;

import main.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Shot;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class BoardUpdate_Shots {

    private Board board;

    @BeforeEach
    void setup(){
        board = new Board();
    }

    @AfterEach
    void teardown() {
    }

    private void callUpdateOnce() {
        try {
            var updateMethod = Board.class.getDeclaredMethod("update");
            updateMethod.setAccessible(true);
            updateMethod.invoke(board);
        } catch (Exception e) {}
    }
    // R1 – Disparo no visible: no se realiza ninguna acción.
    @Test
    void R1() {
        Shot shot = new Shot();
        shot.setX(50);
        shot.setY(50);
        shot.die();

        board.setShot(shot);
        List<Alien> aliens = new ArrayList<>();
        aliens.add(new Alien(48, 48));
        board.setAliens(aliens);

        callUpdateOnce();

        assertFalse(board.getShot().isVisible());
        assertEquals(50, board.getShot().getY());
        assertEquals(0, board.getDeaths());
        assertFalse(board.getAliens().get(0).isDying());
    }

    // R2 – Visible, sin colisión y dentro del tablero: se mueve hacia arriba (Y-4).
    @Test
    void R2() {
        Shot shot = new Shot();
        shot.setX(10);
        shot.setY(100);
        board.setShot(shot);

        List<Alien> aliens = new ArrayList<>();
        aliens.add(new Alien(200, 200));
        board.setAliens(aliens);

        callUpdateOnce();

        assertTrue(board.getShot().isVisible());
        assertEquals(96, board.getShot().getY());
        assertEquals(0, board.getDeaths());
        assertFalse(board.getAliens().get(0).isDying());
    }
    // R3 – Visible con colisión: procesa colisión y el disparo se mueve (Y-4).
    @Test
    void R3() {
        Shot shot = new Shot();
        shot.setX(50);
        shot.setY(50);
        board.setShot(shot);

        List<Alien> aliens = new ArrayList<>();
        Alien alien = new Alien(45, 45);
        aliens.add(alien);
        board.setAliens(aliens);

        callUpdateOnce();

        assertTrue(alien.isDying());
        assertEquals(1, board.getDeaths());
        assertTrue(board.getShot().isVisible());
        assertEquals(46, board.getShot().getY());
    }
    // R4 – Visible, sin colisión, nueva Y < 0: se elimina el disparo.
    @Test
    void R4() {
        Shot shot = new Shot();
        shot.setX(10);
        shot.setY(3); // 3 - 4 = -1 < 0
        board.setShot(shot);

        List<Alien> aliens = new ArrayList<>();
        aliens.add(new Alien(200, 200));
        board.setAliens(aliens);

        callUpdateOnce();

        assertFalse(board.getShot().isVisible());
        assertEquals(0, board.getDeaths());
    }
    // R5 – Visible, colisión y además nueva Y < 0: procesa colisión y elimina disparo.
    @Test
    void R5() {
        Shot shot = new Shot();
        shot.setX(50);
        shot.setY(3);
        board.setShot(shot);

        List<Alien> aliens = new ArrayList<>();
        Alien alien = new Alien(45, 0);
        aliens.add(alien);
        board.setAliens(aliens);
        callUpdateOnce();

        assertTrue(alien.isDying());
        assertEquals(1, board.getDeaths());
        assertFalse(board.getShot().isVisible());
    }
}
