/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * CountdownAnimation.java
 */
package Environment;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countForm;
    private Counter counter;
    private SpriteCollection gameScreen;
    private long start;
    private long time;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countForm = countFrom;
        this.gameScreen = gameScreen;
        this.counter = new Counter(countFrom);
        this.start = System.currentTimeMillis();
        this.time = (long) ((1000 * numOfSeconds) / (double) countFrom);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        if (System.currentTimeMillis() - this.start < this.time) {
            d.setColor(Color.BLUE);
            d.drawText(385, 320, counter.valtoString(), 60);
        } else {
            this.counter.decrease(1);
            this.start = System.currentTimeMillis();
        }

    }
    @Override
    public boolean shouldStop() {
        if (this.counter.getValue() == 0) {
            return true;
        }
        return false;
    }
}
