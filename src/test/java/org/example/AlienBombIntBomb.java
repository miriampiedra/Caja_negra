package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlienBombIntBomb {
        @BeforeEach
        void setup(){
        }

        @AfterEach
        void teardown() {
        }

        @Test //CP1 Las coordenadas son validas
    void CoordenadasBombaValidas() {
        int valorX = 10;
        int valorY = 10;
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setX(valorX);
        bomb.setY(valorY);
        boolean resultado = bomb.getX() == valorX && bomb.getY() == valorY;
        assertTrue(resultado);
    }

    @Test //CP2 La coordenada X de la bomba es superior al limite superior de la pantalla
    void CoordenadasXBombaSuperiorAlRango() {
        int valorX = 400;
        int valorY = 10;
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setX(valorX);
        bomb.setY(valorY);
        boolean resultado = bomb.getX() == valorX && bomb.getY() == valorY;
        assertTrue(resultado);
    }

    @Test //CP3 La coordenada X de la bomba es inferior a 0
    void CoordenadasXBombaInferiorA0() {
        int valorX = -10;
        int valorY = 10;
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setX(valorX);
        bomb.setY(valorY);
        boolean resultado = bomb.getX() == valorX && bomb.getY() == valorY;
        assertTrue(resultado);
    }

    @Test //CP4 La coordenada Y de la bomba es superior al limite superior de la pantalla
    void CoordenadasYBombaSuperiorAlRango() {
        int valorX = 10;
        int valorY = 400;
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setX(valorX);
        bomb.setY(valorY);
        boolean resultado = bomb.getX() == valorX && bomb.getY() == valorY;
        assertTrue(resultado);
    }

    @Test //CP5 La coordenada Y de la bomba es inferior a 0
    void CoordenadasYBombaInferiorA0() {
        int valorX = 10;
        int valorY = -10;
        Alien alien = new Alien(10, 10);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setX(valorX);
        bomb.setY(valorY);
        boolean resultado = bomb.getX() == valorX && bomb.getY() == valorY;
        assertTrue(resultado);
    }
}
