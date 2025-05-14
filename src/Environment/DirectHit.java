/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * DirectHit.java
 */
package Environment;

import Geometry.Point;
import Geometry.Velocity;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type DirectHit.
 */
public class DirectHit implements LevelInformation {
    public static final int FRAME_W = 800;
    public static final int FRAME_H = 600;
    public static final int LEAST_BLOCKS = 7;
    public static final int MOST_BLOCKS = 12;
    public static final int X_DIFF = 50;
    public static final int Y_DIFF = 25;
    public static final int X_START_POS = 730;
    public static final int Y_START_POS = 200;
    public static final int NUM_BALLS = 1;
    public static final int PADDLE_SPEED = 7;
    public static final int PADDLE_WIDTH = 100;

    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 3;
        List<Velocity> velocity = new LinkedList<>();
        velocity.add(Velocity.fromAngleAndSpeed(360, speed));
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
        return "Direct Hit";
    }
    @Override
    public Sprite getBackground() {
        return new Background2();
    }
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new LinkedList<>();
        Block b = new Block(new Geometry.Rectangle(new Point(385, 150), 30, 30), Color.RED);
        blockList.add(b);
        return blockList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
