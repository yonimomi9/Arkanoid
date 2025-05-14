/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * PrintingHitListener.java
 */

package Environment;

/**
 * The type PrintingHitListener.
 */
public class PrintingHitListener implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
