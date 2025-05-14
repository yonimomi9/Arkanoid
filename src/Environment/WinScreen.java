/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * WinScreen.java
 */
package Environment;

import biuoop.DrawSurface;

import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Win screen.
 */
public class WinScreen implements Animation {
    private biuoop.KeyboardSensor keyboard;
    private Counter score;
    private boolean stop;

    /**
     * Instantiates a new Win screen.
     *
     * @param k     the k
     * @param score the score
     */
    public WinScreen(biuoop.KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        for (int i = 30; i > 0; i--) {
            d.setColor(new Color(255, 241, 128 - (2 * i)));
            d.fillRectangle(0, 0, 800, (600 / 30) * i);
        }
        d.setColor(new Color(162, 72, 0));
        d.drawText(250, (d.getHeight() / 2) - 50, "You Win!", 70);
        d.drawText(190, (d.getHeight() / 2) + 10, "Your score is: " + this.score.getValue(), 50);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
