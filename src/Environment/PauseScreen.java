/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * EndScreen.java
 */
package Environment;

import biuoop.DrawSurface;

/**
 * The class PauseScreen.
 */
public class PauseScreen implements Animation {
    private biuoop.KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the keyboard sensor
     */
    public PauseScreen(biuoop.KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        int width = d.getWidth();
        int height = d.getHeight();

        // Gradient background using 40 horizontal stripes with a blue fade
        int sections = 40;
        int stripeHeight = height / sections;
        for (int i = 0; i < sections; i++) {
            int blue = 100 + (i * 4); // from 100 to 260+
            if (blue > 255) blue = 255;
            d.setColor(new java.awt.Color(0, 0, blue));
            d.fillRectangle(0, i * stripeHeight, width, stripeHeight);
        }

        // Background box for text
        int boxWidth = 400;
        int boxHeight = 150;
        int boxX = (width - boxWidth) / 2;
        int boxY = height / 2 - boxHeight / 2;


        // Text
        d.setColor(java.awt.Color.WHITE);
        d.drawText(width / 2 - 200, height / 2 - 10, "Game Paused", 58);
        d.drawText(width / 2 - 220, height / 2 + 40, "Press SPACE to continue...", 36);

        // Draw pause icon (after box so it’s visible)
        d.setColor(java.awt.Color.WHITE);
        int barWidth = 10;
        int barHeight = 60;
        int gap = 15;
        int iconX = width / 2 - barWidth - gap / 2;
        int iconY = boxY - 80; // move it up above the box
        d.fillRectangle(iconX, iconY, barWidth, barHeight);
        d.fillRectangle(iconX + barWidth + gap, iconY, barWidth, barHeight);

        if (this.keyboard.isPressed(biuoop.KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
