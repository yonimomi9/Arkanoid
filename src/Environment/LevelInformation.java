/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * LevelInformation.java
 */
package Environment;

import Geometry.Velocity;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls.
     *
     * @return the number of balls as int.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls().
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the paddle's speed.
     *
     * @return the speed of the paddle as int.
     */
    int paddleSpeed();

    /**
     * Returns the paddle's width.
     *
     * @return the width of the paddle as int.
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size()
     *
     * @return the number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
