/**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Rectangle.java
 */

package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    // Create a new rectangle with location and width/height.
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point upperRight = new Point(this.width, this.upperLeft.getY());
        Point lowerRight = new Point(this.width, this.height);
        Point lowerLeft = new Point(this.upperLeft.getX(), this.height);
        Line wTop = new Line(this.upperLeft, upperRight);
        Line lRight = new Line(upperRight, lowerRight);
        Line wBottom = new Line(lowerRight, lowerLeft);
        Line lLeft = new Line(lowerLeft, this.upperLeft);
        Line[] rect = new Line[4];
        rect[0] = wTop;
        rect[1] = lRight;
        rect[2] = wBottom;
        rect[3] = lLeft;
        List<Point> inter = new ArrayList<>();
        for (int i = 0; i < rect.length; i++) {
            Point p = rect[i].intersectionWith(line);
            if (p != null) {
                inter.add(p);
            }
        }
        return inter;

    }

    /**
     * Return the width and height of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Sets upper left.
     *
     * @param p the p
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * Returns the upper-right point of the rectangle.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return new Point(this.getUpperLeft().getX() + width, this.getUpperLeft().getY());
    }

    /**
     * Returns the bottom-left point of the rectangle.
     *
     * @return the bottom left
     */
    public Point getBottomLeft() {
        return new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + height);
    }
}
