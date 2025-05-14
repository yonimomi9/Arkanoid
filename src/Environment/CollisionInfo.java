/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * CollisionInfo.java
 */

package Environment;

import Geometry.Point;

/**
 * The type CollisionInfo.
 */
public class CollisionInfo {
    private Point collision;
    private Collidable obj;

    /**
     * make a new object from a collision point and object.
     *
     * @param collision the point of collision.
     * @param obj       the object collided.
     */
    public CollisionInfo(Point collision, Collidable obj) {
        this.collision = collision;
        this.obj = obj;
    }

    /**
     * get the point at which the collision occurs.
     *
     * @return Geometry.Point. point
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * get the collidable object involved in the collision.
     *
     * @return a Environment.Collidable object.
     */
    public Collidable collisionObject() {
        return this.obj;
    }
}
