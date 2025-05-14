/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Background3.java
 */
package Environment;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class Background3.
 * Responsible for drawing the background of Wide Easy Level.
 */
public class Background3 implements Sprite {
    private SpriteCollection bg;

    /**
     * Instantiates a new Background 3.
     */
    public Background3() {
        this.bg = new SpriteCollection();
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (int i = 30; i > 0; i--) {
            d.setColor(new Color(i, 154 + i, 255 - i));
            d.fillRectangle(0, 0, 800, (600 / 30) * i);
        }
        for (int i = 0; i < 60; i++) {
            d.setColor(new Color(255, 241, 128));
            d.drawLine(150, 150, i * 13, 300);
        }
        d.setColor(new Color(255, 241, 128));
        d.fillCircle(150, 150, 75);
        d.setColor(new Color(255, 223, 41));
        d.fillCircle(150, 150, 65);
        d.setColor(new Color(255, 221, 0));
        d.fillCircle(150, 150, 55);
        d.setColor(new Color(189, 103, 8));
        d.drawText(118, 125, "Have a", 20);
        d.drawText(118, 155, "Great", 25);
        d.drawText(123, 190, "Day", 30);

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