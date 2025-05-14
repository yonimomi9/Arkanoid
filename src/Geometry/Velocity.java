package Geometry; /**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Geometry.Velocity.java
 */

/**
 * The type Velocity.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Geometry.Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructors
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the method returns Geometry.Velocity from speed and angle.
     *
     * @param angle direction in numbers (not radians).
     * @param speed the overall speed.
     * @return new Geometry.Velocity(dx, dy).
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Get dx double and returns it.
     *
     * @return the double
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get dy double and returns it.
     *
     * @return the double
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Returns the speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
    }
}