/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * ScoreTrackingListener.java
 */
package Environment;

import java.util.Objects;

/**
 * The type ScoreTrackingListener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public static final int INCREASE = 5;
    public static final int WIN = 200;
    public static final int BONUS = 100;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(INCREASE);
        if (Objects.equals(this.currentScore, WIN)) {
            this.currentScore.increase(BONUS);
        }
    }

    /**
     * Returns the current score.
     *
     * @return the score
     */
    public int getScore() {
        return this.currentScore.getValue();
    }
}
