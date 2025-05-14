/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Animation.java
 */

package Environment;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Each frame of the animation, do the following (specific to each object).
     *
     * @param d the drawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Returns if the animation of the current object should stop.
     *
     * @return the boolean
     */
    boolean shouldStop();
}