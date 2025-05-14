/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Background1.java
 */
package Environment;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class Background1.
 * Responsible for drawing the background of Green3 Level.
 */
public class Background1 implements Sprite {
    private SpriteCollection bg;

    /**
     * Instantiates a new Background1.
     */
    public Background1() {
        this.bg = new SpriteCollection();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(400, 35, "Green3", 13);
        for (int i = 30; i > 0; i--) {
            d.setColor(new Color(245, 105 + (2 * i), 179 + (2 * i)));
            d.fillRectangle(0, 0, 800, (600 / 30) * i);
        }
        d.setColor(new Color(243, 41, 149));
        d.fillCircle(240, 400, 170);
        d.setColor(new Color(255, 254, 191));
        d.fillCircle(240, 400, 150);
        d.setColor(new Color(255, 0, 128));
        d.drawText(130, 380, "Shabbat", 60);
        d.drawText(130, 450, "Shalom", 65);
        d.drawLine(130, 390, 350, 390);
        d.drawLine(130, 460, 350, 460);
        d.setColor(new Color(234, 240, 39));
        d.fillCircle(55, 400, 25);
        d.fillCircle(425, 400, 25);
        d.fillCircle(235, 230, 25);
        d.fillCircle(235, 570, 25);
        d.setColor(new Color(152, 127, 0));
        d.fillCircle(55, 400, 10);
        d.fillCircle(425, 400, 10);
        d.fillCircle(235, 230, 10);
        d.fillCircle(235, 570, 10);
        d.setColor(new Color(10, 101, 37));
        d.fillRectangle(600, 320, 20, 250);
        d.fillCircle(635, 445, 30);
        d.setColor(new Color(25, 206, 80));
        d.drawLine(620, 445, 665, 445);
        d.setColor(new Color(13, 127, 47));
        d.drawLine(620, 415, 620, 475);
        d.setColor(Color.RED);
        d.fillCircle(610, 320, 70);
        d.setColor(Color.BLACK);
        d.fillCircle(610, 320, 20);
        this.bg.drawAllOn(d);
    }

    @Override
    public void timePassed() {
        this.bg.notifyAllTimePassed();
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
