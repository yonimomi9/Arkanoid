/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * WideEasy.java
 */
package Environment;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    public static final int FRAME_W = 800;
    public static final int FRAME_H = 600;
    public static final int LEAST_BLOCKS = 7;
    public static final int MOST_BLOCKS = 12;
    public static final int X_DIFF = 50;
    public static final int Y_DIFF = 25;
    public static final int X_START_POS = 730;
    public static final int Y_START_POS = 200;
    public static final int NUM_BALLS = 10;
    public static final int PADDLE_SPEED = 2;
    public static final int PADDLE_WIDTH = 650;
    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 2;
        List<Velocity> velocity = new LinkedList<>();
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            velocity.add(Velocity.fromAngleAndSpeed(355 - (i * 20), speed));
        }
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            velocity.add(Velocity.fromAngleAndSpeed(5 + (i * 20), speed));
        }
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
        return "Wide Easy";
    }
    @Override
    public Sprite getBackground() {
        return new Background3();
    }
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new LinkedList<>();
        int x = 25, y = 300;
        Block b;
        for (int i = 0; i < 15; i++) {
            Rectangle r = new Rectangle(new Point(x + (50 * i), y), 50, 25);
            if (i == 0 || i == 1) {
                if (i == 0) {
                    r = new Rectangle(new Point(20, y), 55, 25);
                }
                b = new Block(r, Color.RED);
            } else if (i == 2 || i == 3) {
                b = new Block(r, Color.ORANGE);
            } else if (i == 4 || i == 5) {
                b = new Block(r, Color.YELLOW);
            } else if (i == 6 || i == 7 || i == 8) {
                b = new Block(r, Color.GREEN);
            } else if (i == 9 || i == 10) {
                b = new Block(r, Color.BLUE);
            } else if (i == 11 || i == 12) {
                b = new Block(r, Color.PINK);
            } else {
                if (i == 14) {
                    r = new Rectangle(new Point(725, y), 55, 25);
                }
                b = new Block(r, Color.CYAN);
            }
            blockList.add(b);
        }
        return blockList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
