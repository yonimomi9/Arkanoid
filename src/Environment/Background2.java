/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Background2.java
 */
package Environment;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class Background2.
 * Responsible for drawing the background of Direct Hit Level.
 */
public class Background2 implements Sprite {
    private SpriteCollection bg;

    /**
     * Instantiates a new Background 2.
     */
    public Background2() {
        this.bg = new SpriteCollection();
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (int i = 30; i > 0; i--) {
            d.setColor(new Color(i, i, i));
            d.fillRectangle(0, 0, 800, (600 / 30) * i);
        }
        d.setColor(Color.BLUE);
        d.drawCircle(400, 165, 130);
        d.drawCircle(400, 165, 90);
        d.drawCircle(400, 165, 50);
        d.drawLine(200, 165, 380, 165);
        d.drawLine(420, 165, 600, 165);
        d.drawLine(400, 0, 400, 145);
        d.drawLine(400, 195, 400, 340);
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