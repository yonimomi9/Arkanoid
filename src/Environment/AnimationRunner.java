/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * AnimationRunner.java
 */

package Environment;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The class AnimationRunner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui the gui
     * @param fps the fps
     */
    public AnimationRunner(GUI gui, int fps) {
        this.framesPerSecond = fps;
        this.gui = gui;
    }

    /**
     * Runs the animation.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            getGUI().show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Returns the Animation's GUI.
     *
     * @return the gui
     */
    public GUI getGUI() {
        return this.gui;
    }
}