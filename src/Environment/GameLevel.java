/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * GameLevel.java
 */
package Environment;

import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.List;
import java.util.Objects;

/**
 * The type Environment.Game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    private Counter remainingBlocks;

    private Counter remainingBalls;

    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;

    private ScoreTrackingListener stl;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation info;
// magic numbers
    public static final int BALL_SIZE = 5;
    public static final int FRAME_W = 800;
    public static final int FRAME_H = 600;
    public static final int LEAST_BLOCKS = 7;
    public static final int MOST_BLOCKS = 12;
    public static final int X_DIFF = 50;
    public static final int Y_DIFF = 25;
    public static final int X_START_POS = 730;
    public static final int Y_START_POS = 200;
    public static final int BORDER_D1 = 20;
    public static final int BORDER_D2 = 800;
    public static final int PADDLE_X = 350;
    public static final int PADDLE_Y = 560;
    public static final int PADDLE_H = 20;
    public static final int DEATH_REG_Y = 620;
    public static final int BORDER_X = 780;
    public static final int BLOCKS = 40;
    public static final int BALLS = 2;
    public static final int SCORE = 0;
    public static final int FPS = 60;

    /**
     * Instantiates a new Environment.Game.
     */
    public GameLevel() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(BLOCKS);
        this.remainingBalls = new Counter(BALLS);
        this.scoreCounter = new Counter(SCORE);
        this.runner = new AnimationRunner(new GUI("Arkanoid", 800, 600), FPS);
        this.keyboard = this.runner.getGUI().getKeyboardSensor();
    }

    /**
     * Instantiates a new Game level.
     *
     * @param info    the info
     * @param counter the counter
     * @param gui     the gui
     */
    public GameLevel(LevelInformation info, Counter counter, GUI gui) {
        this.scoreCounter = counter;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.stl = new ScoreTrackingListener(this.scoreCounter);
        this.info = info;
        this.gui = gui;
        this.runner = new AnimationRunner(gui, FPS);
        this.remainingBalls = new Counter(info.numberOfBalls());
        this.remainingBlocks = new Counter(info.numberOfBlocksToRemove());
        this.keyboard = this.runner.getGUI().getKeyboardSensor();
    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Gets animation runner.
     *
     * @return the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return this.runner;
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Remove collidable object from game.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        List<Collidable> collidable = this.environment.getCollidable();
        for (int i = 0; i < collidable.size(); i++) {
            if (Objects.equals(collidable.get(i), c)) {
                (this.environment.getCollidable()).remove(collidable.get(i));
            }
        }
    }

    /**
     * Removes sprite from game.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Add a collidable object into the list of the collidables.
     *
     * @param c the Environment.Collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite to the list of sprites.
     *
     * @param s the Environment.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Initializes the game environment by creating and adding the game objects.
     * <p>
     * This method initializes the game environment by creating and adding the game objects
     * such as balls, blocks, borders, and a paddle to the game. The method sets the initial
     * velocity of the balls and adds them to the game environment. It also creates and adds
     * the four border blocks, and creates and adds a paddle to the game. Finally, the method
     * creates and adds multiple blocks to the game, each with a unique color.
     * </p>
     */
    public void initialize() {
        addSprite(this.getLvlInfo().getBackground());
        initializeFrameBlocks();
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        Point start = new Point(((double) FRAME_W / 2) - ((double) this.getLvlInfo().paddleWidth() / 2),
                (FRAME_W - ((double) BORDER_D1 / 2)));
        for (int i = 0; i < this.getLvlInfo().numberOfBlocksToRemove(); i++) {
            Block b = this.getLvlInfo().blocks().get(i);
            b.addHitListener(blockRemover);
            b.addHitListener(stl);
            b.addToGame(this);
        }
        for (int i = 0; i < this.getLvlInfo().numberOfBalls(); i++) {
            int x = FRAME_W / 2;
            int y = PADDLE_Y - 90;
            Ball ball = new Ball(new Point(x, y), BALL_SIZE, Color.DARK_GRAY);
            ball.setVelocity(this.getLvlInfo().initialBallVelocities().get(i));
            ball.setVelocity(-ball.getVelocity().getDx(), -ball.getVelocity().getDy());
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
        this.paddle = new Paddle(keyboard, new Rectangle(this.getPaddleUpperLeft(),
                this.getLvlInfo().paddleWidth(), PADDLE_H),
                this.getLvlInfo().paddleSpeed(), Color.LIGHT_GRAY);
        this.paddle.addToGame(this);

        Block b = new Block(new Rectangle(new Point(35, 0), FRAME_W - 70, 20), Color.yellow);
        ScoreIndicator si = new ScoreIndicator(b, this.scoreCounter);
        si.addToGame(this);
        Level lvl = new Level(this.getLvlInfo().levelName());
        lvl.addToGame(this);
    }

    /**
     * Initializes the frame blocks.
     *
     * @return the paddle upper left
     */
    public Point getPaddleUpperLeft() {
        if (Objects.equals(this.getLvlInfo().levelName(), "Wide Easy")) {
            return new Point(PADDLE_X - 285, PADDLE_Y);
        }
        return new Point(PADDLE_X, PADDLE_Y);
    }

    /**
     * Initialize frame blocks.
     */
    public void initializeFrameBlocks() {
        int k = 0;
        Rectangle[] arr = new Rectangle[4];
        arr[k++] = new Rectangle(new Point(0, DEATH_REG_Y), BORDER_D2, BORDER_D1);
        arr[k++] = new Rectangle(new Point(BORDER_X, 0), BORDER_D1, BORDER_D2);
        arr[k++] = new Rectangle(new Point(0, 0), BORDER_D1, BORDER_D2);
        arr[k] = new Rectangle(new Point(0, 0), BORDER_D2, BORDER_D1);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        Block[] block = new Block[4];
        for (int i = 0; i < block.length; i++) {
            block[i] = new Block(arr[i], Color.gray);
            if (arr[i].getUpperLeft().getY() == DEATH_REG_Y || arr[i].getWidth() == 2 || arr[i].getHeight() == 2) {
                block[i].addHitListener(ballRemover);
            }
            block[i].addToGame(this);
        }
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.runner.getGUI().getKeyboardSensor().isPressed("p")
                || this.runner.getGUI().getKeyboardSensor().isPressed("P")
                || this.runner.getGUI().getKeyboardSensor().isPressed("פ")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        this.paddle.timePassed();
    }
    @Override
    public boolean shouldStop() {
        if (this.remainingBalls.getValue() == 0) {
            return true;
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.scoreCounter.increase(100);
            return true;
        }
        return false;
    }

    /**
     * Gets lvl info.
     *
     * @return the lvl info
     */
    public LevelInformation getLvlInfo() {
        return this.info;
    }

    /**
     * Runs the game animation loop at a fixed rate of 60 frames per second.
     * <p>
     * This method updates and displays the game sprites in each iteration of the loop.
     * The loop is controlled by a Sleeper object that ensures a constant frame rate.
     * </p>
     */
    public void run() {
        this.keyboard = this.runner.getGUI().getKeyboardSensor();
        Point start = new Point(((double) FRAME_W / 2) - ((double) this.getLvlInfo().paddleWidth() / 2),
                (FRAME_W - ((double) BORDER_D1 / 2)));
        if (this.getLvlInfo().levelName().equals("Direct Hit")) {
            start = new Point(((double) FRAME_W / 2), start.getY());
        }
        this.paddle = new Paddle(keyboard, new Rectangle(start, this.getLvlInfo().paddleWidth(),
                PADDLE_H),
                this.getLvlInfo().paddleSpeed(), Color.BLUE);
        this.paddle.addToGame(this);
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.runner.run(this);

    }
}