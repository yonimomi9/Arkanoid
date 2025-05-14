/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * HitListener.java
 */

package Environment;

/**
 * The interface HitListener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
