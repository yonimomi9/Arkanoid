/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Environment.Collidable.java
 */
package Environment;
import Geometry.Point;
import Geometry.Velocity;
import Geometry.Rectangle;

/**
 * The interface Environment.Collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * This method calculates and returns the new velocity of a ball that has hit a block.
     * <p>
     * If the ball hits the wall, its velocity will be reflected with no change in speed.
     * If the ball hits the left or right side of the block, its x velocity will be reversed.
     * If the ball hits the top or bottom side of the block, its y velocity will be reversed.
     * If the ball is being pushed into the wall by the paddle, its velocity
     * will be reflected and its speed will not be changed.
     * </p>
     *
     * @param hitter the ball which the hit occurs with
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
