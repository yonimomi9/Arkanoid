/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Green3.java
 */
package Environment;

import Geometry.Point;
import Geometry.Velocity;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class Green3.
 */
public class Green3 implements LevelInformation {
    public static final int FRAME_W = 800;
    public static final int FRAME_H = 600;
    public static final int LEAST_BLOCKS = 7;
    public static final int MOST_BLOCKS = 12;
    public static final int X_DIFF = 50;
    public static final int Y_DIFF = 25;
    public static final int X_START_POS = 730;
    public static final int Y_START_POS = 200;
    public static final int NUM_BALLS = 2;
    public static final int PADDLE_SPEED = 7;
    public static final int PADDLE_WIDTH = 100;
    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 4;
        List<Velocity> velocity = new LinkedList<>();
        velocity.add(Velocity.fromAngleAndSpeed(25, speed));
        velocity.add(Velocity.fromAngleAndSpeed(335, speed));
        return velocity;
    }
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }
    @Override
    public String levelName() {
        return "Green3";
    }
    @Override
    public Sprite getBackground() {
        return new Background1();
    }
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new LinkedList<>();
        int x = X_START_POS, y = Y_START_POS, k = 0;
        // Create the blocks in the GUI
        Color[] colors = new Color[5];
        colors[k++] = Color.magenta;
        colors[k++] = Color.red;
        colors[k++] = Color.yellow;
        colors[k++] = Color.green;
        colors[k] = Color.blue;
        for (int i = LEAST_BLOCKS; i < MOST_BLOCKS; i++) {
            for (int j = 1; j < i; j++) {
                Block block = new Block(new Geometry.Rectangle(new Point(x, y), X_DIFF, Y_DIFF),
                        colors[i - LEAST_BLOCKS]);
                x = x - X_DIFF;
                blockList.add(block);
            }
            y -= Y_DIFF;
            x = X_START_POS;
        }
        return blockList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}