/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Level.java
 */
package Environment;

import biuoop.DrawSurface;

/**
 * The class Level.
 * Used to print the right level name when a level runs.
 */
public class Level implements Sprite {
    private final String name;

    public static final int FRAME_W = 800;
    public static final int FRAME_H = 600;

    /**
     * Constructor.
     *
     * @param name text to write on the block.
     */
    public Level(String name) {
        this.name = name;
    }

    /**
     * get the String object.
     *
     * @return String.
     */
    private String getLvlName() {
        return this.name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText((GameLevel.FRAME_W / 2) + 180, 15, this.getLvlName(), 13);
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