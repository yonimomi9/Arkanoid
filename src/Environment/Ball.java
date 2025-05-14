/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Ball.java
 */

package Environment;

import Geometry.Velocity;
import Geometry.Line;
import Geometry.Point;
import biuoop.DrawSurface;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
    // constructors
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment game;

    /**
     * Instantiates a new Environment.Ball.
     *
     * @param center type Geometry.Point
     * @param r      the radius, type int
     * @param color  type java.awt.Color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) this.getX(), (int) this.getY(), this.r);
    }

    /**
     * Moves the ball one step in its current direction of travel, ensuring that it stays within the boundaries
     * of the screen. If the ball reaches the edge of the screen, its velocity is reversed in the appropriate
     * direction to keep it within bounds. (Works the same as moveOneStep method).
     */
    public void timePassed() {
        double nextX = this.getCenter().getX() + this.getVelocity().getDx();
        double nextY = this.getCenter().getY() + this.getVelocity().getDy();
        boolean hitCorner = false;
        if (this.getVelocity().getDy() == 0) {
            this.setVelocity(this.getVelocity().getDx(), 1);
        }
        if (this.getVelocity().getDx() == 0) {
            this.setVelocity(1, this.getVelocity().getDy());
        }
        double endX = this.getCenter().getX() + this.getVelocity().getDx();
        double endY = this.getCenter().getY() + this.getVelocity().getDy();
        Line trajectory = new Line(this.getCenter(), new Point(endX, endY));
        CollisionInfo collision = this.getGameEnv().getClosestCollision(trajectory);

        if (collision != null) {
            Point collide = collision.collisionPoint();
            Collidable obj = collision.collisionObject();
            this.setVelocity(obj.hit(this, collide, this.getVelocity()));
            nextX = this.getCenter().getX() + this.getVelocity().getDx();
            nextY = this.getCenter().getY() + this.getVelocity().getDy();

            if (nextY + this.r >= 560 && nextY - this.r <= 580) {
                nextY = 560 - this.r;
                this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
            }
            if (nextY - this.r > 580) {
                nextY = 560 - this.r;
                this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
            }
            /* if the balls' collision point reaches to one of the blocks then
            the ball will go in the block for a short time and get out */
            if (nextX - this.r <= 20) { // left edge of the screen
                nextX = 20 + this.r;
                if (nextY + this.r >= 580) { // bottom edge of the screen
                    nextY = 580 - this.r;
                    this.setVelocity(-this.getVelocity().getDx(), -this.getVelocity().getDy());
                    hitCorner = true;
                } else if (nextY - this.r <= 20) {
                    nextY = 20 + this.r;
                    this.setVelocity(-this.getVelocity().getDx(), -this.getVelocity().getDy());
                    hitCorner = true;
                }
            } else if (nextX + this.r >= 780) {
                nextX = 780 - this.r;
                if (nextY + this.r >= 620) { // bottom edge of the screen
                    nextY = 620 + this.r;
                    this.setVelocity(this.getVelocity().getDx(), this.getVelocity().getDy());
                    hitCorner = true;
                } else if (nextY - this.r <= 20) {
                    nextY = 20 + this.r;
                    this.setVelocity(-this.getVelocity().getDx(), -this.getVelocity().getDy());
                    hitCorner = true;
                }
            }
        }
        if (hitCorner) {
            this.center = new Point(nextX, nextY);
        } else {
            this.center = this.getVelocity().applyToPoint(this.getCenter());
        }
    }

    /**
     * Removes sprite (ball) from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * Returns the X value of the center of the ball.
     *
     * @return the X
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the Y value of the center of the ball.
     *
     * @return the Y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return r the radius.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the center point of the ball.
     *
     * @return the center point.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius
     */
    public int getRadius() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color type java.awt.color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets game environment.
     *
     * @param game the game
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.game = game;
    }


    /**
     * Sets the velocity.
     *
     * @param v type Geometry.Velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Creates new velocity by two doubles dx and dy.
     *
     * @param dx the dx value
     * @param dy the dy value
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Returns the Geometry.Velocity value of a ball.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * get the Environment.GameEnvironment object.
     *
     * @return Environment.GameEnvironment object.
     */
    private GameEnvironment getGameEnv() {
        return this.game;
    }

    /**
     * Moves the ball one step in its current direction of travel, ensuring that it stays within the boundaries
     * of the screen. If the ball reaches the edge of the screen, its velocity is reversed in the appropriate
     * direction to keep it within bounds. (Works the same as timePassed method).
     */
    public void moveOneStep() {
        if (this.getVelocity().getDy() == 0) {
            this.setVelocity(this.getVelocity().getDx(), 1);
        }
        if (this.getVelocity().getDx() == 0) {
            this.setVelocity(1, this.getVelocity().getDy());
        }
        double endX = this.getCenter().getX() + this.getVelocity().getDx();
        double endY = this.getCenter().getY() + this.getVelocity().getDy();
        Line trajectory = new Line(this.getCenter(), new Point(endX, endY));
        CollisionInfo collision = this.getGameEnv().getClosestCollision(trajectory);
        if (collision != null) {
            Point collide = collision.collisionPoint();
            Collidable obj = collision.collisionObject();
            this.setVelocity(obj.hit(this, collide, this.getVelocity()));
        }
        this.center = this.getVelocity().applyToPoint(this.getCenter());
    }


    /**
     * This method moves the center of the ball one step using its current velocity.
     * <p>
     * It also handles collision with the edges of a rectangular area with dimensions
     * of 500x500 pixels and a margin of 50 pixels from each edge. If the ball hits any
     * of the edges, its velocity is inverted in the corresponding direction.
     * The method does not return any value and only modifies the internal state of the object.
     * </p>
     */
    public void moveOneStepGray() {
        double nextX = this.getCenter().getX() + this.getVelocity().getDx();
        double nextY = this.getCenter().getY() + this.getVelocity().getDy();

        // Check if the ball is within the rectangle
        if (nextX - this.r < 50) { // left edge of the screen
            nextX = 50 + this.r;
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        } else if (nextX + this.r > 500) {
            nextX = 500 - this.r;
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
        if (nextY - this.r < 50) { // top edge of the screen
            nextY = 50 + this.r;
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        } else if (nextY + this.r > 500) {
            nextY = 500 - this.r;
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }

        // Update the ball's center point
        this.center = new Point(nextX, nextY);
    }


    /**
     * This method moves the center of the ball one step using its current velocity.
     * <p>
     * It also handles collision with the edges of a rectangular area with dimensions
     * of 150x150 pixels, starting from 450x450 to 600x600. If the ball hits any
     * of the edges, its velocity is inverted in the corresponding direction.
     * The method does not return any value and only modifies the internal state of the object.
     * </p>
     */
    public void moveOneStepYellow() {
        double nextX = this.getCenter().getX() + this.getVelocity().getDx();
        double nextY = this.getCenter().getY() + this.getVelocity().getDy();

        // Check if the ball is within the rectangle
        if (nextX - this.r < 450) { // left edge of the screen
            nextX = 450 + this.r;
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        } else if (nextX + this.r > 600) { // right edge of the screen
            nextX = 600 - this.r;
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
        if (nextY - this.r < 450) { // top edge of the screen
            nextY = 450 + this.r;
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        } else if (nextY + this.r > 600) { // bottom edge of the screen
            nextY = 600 - this.r;
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }

        // Update the ball's center point
        this.center = new Point(nextX, nextY);
    }

    /**
     * Add the Environment.Sprite (Environment.Ball in this case) to the list of sprites in the game.
     *
     * @param g the Environment.Game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}