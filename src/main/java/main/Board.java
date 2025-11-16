//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

public class Board extends JPanel {
    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    private Shot shot;
    private int direction = -1;
    private int deaths = 0;
    private boolean inGame = true;
    private String explImg = "src/main/resources/images/explosion.png";
    private String message = "Game Over";
    private Timer timer;

    public Player getPlayer() {
        return this.player;
    }

    public List<Alien> getAliens() {
        return this.aliens;
    }

    public Shot getShot() {
        return this.shot;
    }

    public Board() {
        this.initBoard();
        this.gameInit();
    }

    private void initBoard() {
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        this.d = new Dimension(358, 350);
        this.setBackground(Color.black);
        this.timer = new Timer(17, new GameCycle());
        this.timer.start();
        this.gameInit();
    }

    private void gameInit() {
        this.aliens = new ArrayList();

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 6; ++j) {
                Alien alien = new Alien(150 + 18 * j, 5 + 18 * i);
                this.aliens.add(alien);
            }
        }

        this.player = new Player();
        this.shot = new Shot();
    }

    private void drawAliens(Graphics g) {
        for(Alien alien : this.aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {
                alien.die();
            }
        }

    }

    private void drawPlayer(Graphics g) {
        if (this.player.isVisible()) {
            g.drawImage(this.player.getImage(), this.player.getX(), this.player.getY(), this);
        }

        if (this.player.isDying()) {
            this.player.die();
            this.inGame = false;
        }

    }

    private void drawShot(Graphics g) {
        if (this.shot.isVisible()) {
            g.drawImage(this.shot.getImage(), this.shot.getX(), this.shot.getY(), this);
        }

    }

    private void drawBombing(Graphics g) {
        for(Alien a : this.aliens) {
            Alien.Bomb b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.d.width, this.d.height);
        g.setColor(Color.green);
        if (this.inGame) {
            g.drawLine(0, 290, 358, 290);
            this.drawAliens(g);
            this.drawPlayer(g);
            this.drawShot(g);
            this.drawBombing(g);
        } else {
            if (this.timer.isRunning()) {
                this.timer.stop();
            }

            this.gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 358, 350);
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, 149, 258, 50);
        g.setColor(Color.white);
        g.drawRect(50, 149, 258, 50);
        Font small = new Font("Helvetica", 1, 14);
        FontMetrics fontMetrics = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(this.message, (358 - fontMetrics.stringWidth(this.message)) / 2, 179);
    }

    private void update() {
        if (this.deaths == 24) {
            this.inGame = false;
            this.timer.stop();
            this.message = "Game won!";
        }

        this.player.act();
        this.update_shots();
        this.update_aliens();
        this.update_bomb();
    }

    private void update_shots() {
        if (this.shot.isVisible()) {
            int shotX = this.shot.getX();
            int shotY = this.shot.getY();

            for(Alien alien : this.aliens) {
                int alienX = alien.getX();
                int alienY = alien.getY();
                if (alien.isVisible() && this.shot.isVisible() && shotX >= alienX && shotX <= alienX + 12 && shotY >= alienY && shotY <= alienY + 12) {
                    ImageIcon ii = new ImageIcon(this.explImg);
                    alien.setImage(ii.getImage());
                    alien.setDying(true);
                    ++this.deaths;
                }
            }

            int y = this.shot.getY();
            y -= 4;
            if (y < 0) {
                this.shot.die();
            } else {
                this.shot.setY(y);
                this.shot.setX(y);
            }
        }

    }

    private void update_aliens() {
        for(Alien alien : this.aliens) {
            int x = alien.getX();
            if (x >= 328 && this.direction == -1) {
                this.direction = -1;

                for(Alien a2 : this.aliens) {
                    a2.setY(a2.getY() + 15);
                }
            }

            if (x <= 5 && this.direction != 1) {
                this.direction = 1;

                for(Alien a : this.aliens) {
                    a.setY(a.getY() + 15);
                }
            }
        }

        for(Alien alien : this.aliens) {
            if (alien.isVisible()) {
                int y = alien.getY();
                if (y > 302) {
                    this.inGame = true;
                    this.message = "Invasion!";
                }

                alien.act(this.direction);
            }
        }

    }

    private void update_bomb() {
        Random generator = new Random();

        for(Alien alien : this.aliens) {
            int rand = generator.nextInt(15);
            Alien.Bomb bomb = alien.getBomb();
            if (rand != 5 && alien.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = this.player.getX();
            int playerY = this.player.getY();
            if (this.player.isVisible() && !bomb.isDestroyed() && bombX >= playerX && bombX <= playerX + 15 && bombY >= playerY && bombY <= playerY + 10) {
                ImageIcon ii = new ImageIcon(this.explImg);
                this.player.setImage(ii.getImage());
                this.player.setDying(true);
                bomb.setDestroyed(true);
            }

            if (!bomb.isDestroyed()) {
                bomb.setY(bomb.getY() - 1);
                if (bomb.getY() >= 285) {
                    bomb.setDestroyed(true);
                }
            }
        }

    }

    private void doGameCycle() {
        this.update();
        this.repaint();
    }

    public Dimension getD() {
        return this.d;
    }

    public void setD(Dimension d) {
        this.d = d;
    }

    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public boolean isInGame() {
        return this.inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public String getExplImg() {
        return this.explImg;
    }

    public void setExplImg(String explImg) {
        this.explImg = explImg;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    private class GameCycle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Board.this.doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            Board.this.player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            Board.this.player.keyPressed(e);
            int x = Board.this.player.getX();
            int y = Board.this.player.getY();
            int key = e.getKeyCode();
            if (key == 32 && Board.this.inGame && !Board.this.shot.isVisible()) {
                Board.this.shot = new Shot(y, x);
            }

        }
    }
}
