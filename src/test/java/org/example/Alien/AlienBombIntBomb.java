package org.example.Alien;

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
        Alien alien = new Alien(valorX, valorY);
        Alien.Bomb bomb = alien.getBomb();
        boolean resultado = bomb.getX() == valorX && bomb.getY() == valorY;
        assertTrue(resultado);
    }

    @Test //CP2 La coordenada X de la bomba es superior al limite superior de la pantalla
    void CoordenadasXBombaSuperiorAlRango() {
        int valorX = 359;
        int valorY = 10;
        Alien alien = new Alien(valorX, valorY);
        Alien.Bomb bomb = alien.getBomb();
        boolean resultado = bomb.getX() == 358 && bomb.getY() == valorY;
        assertTrue(resultado);
    }

    @Test //CP3 La coordenada X de la bomba es inferior a 0
    void CoordenadasXBombaInferiorA0() {
        int valorX = -10;
        int valorY = 10;
        Alien alien = new Alien(valorX, valorY);
        Alien.Bomb bomb = alien.getBomb();
        boolean resultado = bomb.getX() == 0 && bomb.getY() == valorY;
        assertTrue(resultado);
    }

    @Test //CP4 La coordenada Y de la bomba es superior al limite superior de la pantalla
    void CoordenadasYBombaSuperiorAlRango() {
        int valorX = 10;
        int valorY = 351;
        Alien alien = new Alien(valorX, valorY);
        Alien.Bomb bomb = alien.getBomb();
        boolean resultado = bomb.getX() == valorX && bomb.getY() == 350;
        assertTrue(resultado);
    }

    @Test //CP5 La coordenada Y de la bomba es inferior a 0
    void CoordenadasYBombaInferiorA0() {
        int valorX = 10;
        int valorY = -10;
        Alien alien = new Alien(valorX, valorY);
        Alien.Bomb bomb = alien.getBomb();
        boolean resultado = bomb.getX() == valorX && bomb.getY() == 0;
        assertTrue(resultado);
    }
}
