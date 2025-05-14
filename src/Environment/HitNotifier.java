/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * HitNotifier.java
 */

package Environment;

/**
 * The interface HitNotifier.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the hl
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);
}
