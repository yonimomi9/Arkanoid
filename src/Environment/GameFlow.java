/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * GameFlow.java
 */
package Environment;

import biuoop.GUI;

import java.util.List;

/**
 * The class GameFlow.
 */
public class GameFlow {
    private biuoop.KeyboardSensor keyboard;
    private AnimationRunner runner;
    private GUI gui;
    private Counter scoreCounter;

    /**
     * Instantiates a new GameFlow.
     */
    public GameFlow() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.keyboard = this.gui.getKeyboardSensor();
    }

    /**
     * Runs all the levels by order.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean didWin = false;
        this.scoreCounter = new Counter();
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.getScore(), this.gui);
            level.initialize();
            this.runner = level.getAnimationRunner();
            while (level.getRemainingBalls().getValue() > 0
                    && level.getRemainingBlocks().getValue() > 0) {
                level.run();
            }

            if (level.getRemainingBalls().getValue() == 0) {
                didWin = true;
                break;
            }
        }
        if (!didWin) {
            WinScreen win = new WinScreen(this.keyboard, this.scoreCounter);
            this.runner.run(win);
        } else {
            EndScreen end = new EndScreen(this.keyboard, this.scoreCounter);
            this.runner.run(end);
        }
        this.gui.close();
    }

    /**
     * Gets the current score.
     *
     * @return the score
     */
    public Counter getScore() {
        return this.scoreCounter;
    }
}