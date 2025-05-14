/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Environment.Block.java
 */

package Environment;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * The type Environment.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color c;

    private List<HitListener> hitListeners;
    /**
     * The constant EDGE_Y_TOP.
     */
    public static final int EDGE_Y_TOP = 20;
    /**
     * The constant EDGE_Y_BOTTOM.
     */
    public static final int EDGE_Y_BOTTOM = 580;
    /**
     * The constant EDGE_X_LEFT.
     */
    public static final int EDGE_X_LEFT = 20;
    /**
     * The constant EDGE_X_RIGHT.
     */
    public static final int EDGE_X_RIGHT = 780;

    /**
     * Instantiates a new Environment.Block.
     *
     * @param rectangle the rectangle
     * @param c         the c
     */
    public Block(Rectangle rectangle, Color c) {
        this.rectangle = rectangle;
        this.c = c;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * Returns the rectangle that is being collided with the ball.
     *
     * @return this.rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * Draws the block on the given DrawSurface.
     * <p>
     * This method receives a DrawSurface object and draws the block's rectangle on it, filling it with
     * the block's color, and drawing its frame with black color.
     * </p>
     *
     * @param d the DrawSurface object to draw the block on.
     **/
    public void drawOn(DrawSurface d) {
        int x1 = (int) this.rectangle.getUpperLeft().getX();
        int y1 = (int) this.rectangle.getUpperLeft().getY();
        int x2 = (int) this.getCollisionRectangle().getWidth();
        int y2 = (int) this.getCollisionRectangle().getHeight();
        d.setColor(this.c);
        d.fillRectangle(x1, y1, x2, y2);
        d.setColor(Color.black);
        d.drawRectangle(x1, y1, x2, y2);
    }
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
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // determine which side of the block was hit and change the velocity accordingly
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // if the ball is being pushed into the wall by the paddle
        if (Objects.equals(collisionPoint, new Point(EDGE_X_LEFT, EDGE_Y_TOP))
                || Objects.equals(collisionPoint, new Point(EDGE_X_RIGHT, EDGE_Y_TOP))
                || Objects.equals(collisionPoint, new Point(EDGE_X_RIGHT, EDGE_Y_BOTTOM))
                || Objects.equals(collisionPoint, new Point(EDGE_X_LEFT, EDGE_Y_BOTTOM))) {
            dy = -Math.abs(dy);
            this.notifyHit(hitter);
            return new Velocity(dx, dy);
        }


        // check for collision with left or right side of block
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() || collisionPoint.getX()
                >= this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()) {
            // check if the ball is actually colliding with the block
            if (collisionPoint.getY() >= this.rectangle.getUpperLeft().getY() && collisionPoint.getY()
                    <= this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()) {
                dx = -dx;
            }
        }

        // if the ball is being pushed into the wall by the paddle
        if (Objects.equals(collisionPoint, new Point(20, 20))
                || Objects.equals(collisionPoint, new Point(780, 20))
                || Objects.equals(collisionPoint, new Point(780, 580))
                || Objects.equals(collisionPoint, new Point(20, 580))) {
            dx = -Math.abs(dx);
            this.notifyHit(hitter);
            return new Velocity(dx, dy);
            // check for collision with top or bottom side of block
        } else if (collisionPoint.getY() <= this.rectangle.getUpperLeft().getY() || collisionPoint.getY()
                >= this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()) {
            // check if the ball is actually colliding with the block
            if (collisionPoint.getX() >= this.rectangle.getUpperLeft().getX() && collisionPoint.getX()
                    <= this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()) {
                dy = -dy;
            }
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * This method isn't necessary at this current stage of the program.
     * However, it is instructed to be written in the Environment.Block class.
     */
    public void timePassed() {
    }

    /**
     * Add the Environment.Sprite (Environment.Block in this case) to the list of sprites in the game.
     * Adds it the collidables list as well.
     *
     * @param g the Environment.Game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes the block from the game (sprite and collidable).
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Notifies all the listeners about a hit event.
     *
     * @param hitter the ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    // Add hl as a listener to hit events.
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    // Remove hl from the list of listeners to hit events.
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
