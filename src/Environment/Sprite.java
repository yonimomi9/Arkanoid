package Environment; /**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Environment.Sprite.java
 */
import biuoop.DrawSurface;

/**
 * The interface Environment.Sprite.
 */
public interface Sprite {
    /**
     * Draw on method.
     *
     * @param d the DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed method.
     */
    void timePassed();
}