/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * ScoreIndicator.java
 */
package Environment;

import biuoop.DrawSurface;

/**
 * The class ScoreIndicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private final Block block;


    /**
     * Constructor.
     *
     * @param b block object.
     */
    public ScoreIndicator(Block b) {
        this.block = b;
        this.score = new Counter();
    }

    /**
     * constructor.
     *
     * @param b block object.
     * @param c counter object.
     */
    public ScoreIndicator(Block b, Counter c) {
        this.block = b;
        this.score = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
        d.drawText(400, 15, "Score: " + this.getScore(), 13);
    }

    /**
     * get the score.
     *
     * @return the counters value.
     */
    public int getScore() {
        return this.score.getValue();
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
