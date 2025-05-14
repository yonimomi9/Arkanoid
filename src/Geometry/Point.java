package Geometry; /**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Geometry.Point.java
 */

/**
 * The type Geometry.Point.
 */
public class Point {
    // constructors
    private double x;
    private double y;

    /**
     * Instantiates a new Geometry.Point.
     *
     * @param x the X
     * @param y the Y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between one point to another.
     *
     * @param other type Geometry.Point
     * @return the distance between two points
     */
    public double distance(Point other) {
        return Math.sqrt(((other.x - this.x) * (other.x - this.x)) + ((other.y - this.y) * (other.y - this.y)));
    }

    /**
     * Checks if two points are equal.
     *
     * @param other type Geometry.Point
     * @return true if equal, false if not.
     */
    public boolean equals(Point other) {
        // if point received as param is null
        if (other == null) {
            return false;
        }
        // if points are equal
        if (this == other) {
            return true;
        }
        return Double.compare(other.x, this.x) == 0 && Double.compare(other.y, this.y) == 0;
    }

    /**
     * Gets X.
     *
     * @return the Y
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets Y.
     *
     * @return the Y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set Points' X value.
     *
     * @param x Double type
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set Points' Y value.
     *
     * @param y Double type
     */
    public void setY(double y) {
        this.y = y;
    }
}
