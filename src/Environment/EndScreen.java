/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * EndScreen.java
 */
package Environment;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private biuoop.KeyboardSensor keyboard;
    private Counter score;
    private boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param k     the k
     * @param score the score
     */
    public EndScreen(biuoop.KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        for (int i = 30; i > 0; i--) {
            d.setColor(new Color(112 - (2 * i), 0, 30 + (2 * i)));
            d.fillRectangle(0, 0, 800, (600 / 30) * i);
        }
        d.setColor(new Color(255, 157, 157));
        d.drawText(220, (d.getHeight() / 2) - 50, "Game over", 70);
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
