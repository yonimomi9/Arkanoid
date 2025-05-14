/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Paddle.java
 */
package Environment;

import Geometry.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private int speed;
    public static final double EPSILON = 0.00001;
    private Color c;

    /**
     * Instantiates a new Environment.Paddle.
     *
     * @param keyboard the keyboard
     * @param rect     the rectangle
     * @param speed    the speed
     * @param c        the color
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, int speed, Color c) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.speed = speed;
        this.c = c;

    }

    /**
     * Returns the paddle (Rectangle type).
     *
     * @return the speed
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Returns the speed of the paddle.
     *
     * @return the speed
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Is instructed to move the paddle left while the left arrow key is being pressed.
     */
    public void moveLeft() {
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        if (x - this.speed >= 20) {
            x -= this.speed;
        }
        Point p = new Point(x, this.getCollisionRectangle().getUpperLeft().getY());
        this.rect.setUpperLeft(p);
    }


    /**
     * Is instructed to move the paddle right while the right arrow key is being pressed.
     */
    public void moveRight() {
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        if (x + this.getCollisionRectangle().getWidth() + this.speed <= 780) {
            x += this.speed;
        }
        Point p = new Point(x, this.getCollisionRectangle().getUpperLeft().getY());
        this.rect.setUpperLeft(p);
    }

    /**
     * Moves the sprite left or right based on keyboard input.
     * <p>
     * Checks if the left or right arrow key is currently pressed on the keyboard and calls
     * the appropriate movement method accordingly.
     * </p>
     */
    public void timePassed() {
        if (keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the sprite on the given DrawSurface object.
     * <p>
     * Calculates the upper-left coordinates of the sprite's rectangle and its width and height, then
     * fills and draws a rectangle using the DrawSurface object and those dimensions and the sprite's color.
     * </p>
     *
     * @param d The DrawSurface object to draw the sprite on.
     */
    public void drawOn(DrawSurface d) {
        int x1 = (int) this.rect.getUpperLeft().getX();
        int y1 = (int) this.rect.getUpperLeft().getY();
        int x2 = (int) this.getCollisionRectangle().getWidth();
        int y2 = (int) this.getCollisionRectangle().getHeight();
        d.setColor(this.c);
        d.fillRectangle(x1, y1, x2, y2);
        d.setColor(Color.black);
        d.drawRectangle(x1, y1, x2, y2);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean onLeftEdgeX = collisionPoint.getX() >= this.rect.getUpperLeft().getX() - EPSILON
                && collisionPoint.getX() <= this.rect.getUpperLeft().getX() + EPSILON;

        boolean onRightEdgeX = collisionPoint.getX() >= this.rect.getUpperRight().getX() - EPSILON
                && collisionPoint.getX() <= this.rect.getUpperRight().getX() + EPSILON;

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Check if collision point is on the left/right edge
        if (onLeftEdgeX || onRightEdgeX) {
            if (collisionPoint.getY() >= this.rect.getUpperLeft().getY() - EPSILON
                    && collisionPoint.getY() <= this.rect.getBottomLeft().getY() + EPSILON) {
                return new Velocity(-dx, dy);
            }
        }

        boolean onTopEdgeY = collisionPoint.getY() >= this.rect.getUpperLeft().getY() - EPSILON
                && collisionPoint.getY() <= this.rect.getUpperLeft().getY() + EPSILON;

        boolean onBottomEdgeY = collisionPoint.getY() >= this.rect.getBottomLeft().getY() - EPSILON
                && collisionPoint.getY() <= this.rect.getBottomLeft().getY() + EPSILON;

        if (onTopEdgeY || onBottomEdgeY) {
            double speed = currentVelocity.getSpeed();
            double regionWidth = this.rect.getWidth() / 5.0;

            double p1Start = this.rect.getUpperLeft().getX();
            double p1End = p1Start + regionWidth;
            double p2End = p1End + regionWidth;
            double p3End = p2End + regionWidth;
            double p4End = p3End + regionWidth;
            double p5End = p4End + regionWidth;

            if (collisionPoint.getX() >= p1Start && collisionPoint.getX() <= p1End) {
                return Velocity.fromAngleAndSpeed(-60, speed);
            } else if (collisionPoint.getX() <= p2End) {
                return Velocity.fromAngleAndSpeed(-30, speed);
            } else if (collisionPoint.getX() <= p3End) {
                return new Velocity(dx, -dy);
            } else if (collisionPoint.getX() <= p4End) {
                return Velocity.fromAngleAndSpeed(30, speed);
            } else if (collisionPoint.getX() <= p5End) {
                return Velocity.fromAngleAndSpeed(60, speed);
            }
        }
        return currentVelocity;
    }


    /**
     * Add this paddle to the game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns the paddle.
     *
     * @return the paddle
     */
    public Rectangle getPaddle() {
        return rect;
    }
}