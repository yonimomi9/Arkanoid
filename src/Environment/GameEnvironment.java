/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * GameEnvironment.java
 */
package Environment;

import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {

    private List<Collidable> collidable;

    /**
     * Gets collidable.
     *
     * @return the collidable
     */
    public List<Collidable> getCollidable() {
        return this.collidable;
    }

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidable = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.collidable.add(c);
    }

    /**
     * Removes collidable.
     *
     * @param c the c
     */
    public void remove(Collidable c) {
        this.collidable.remove(c);
    }

    /**
     * <p>
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * </p>
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidablesList = new ArrayList<>(collidable);

        // Calculate the closest intersection point to the start of the trajectory
        Point closestIntersection = null;
        Collidable closestCollidable = null;

        for (Collidable c : collidablesList) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());

            if (intersection != null) {
                if (closestIntersection == null || trajectory.start().distance(intersection)
                        < trajectory.start().distance(closestIntersection)) {
                    closestIntersection = intersection;
                    closestCollidable = c;
                }
            }
        }

        if (closestIntersection != null && closestCollidable != null) {
            return new CollisionInfo(closestIntersection, closestCollidable);
        } else {
            return null;
        }
    }

}