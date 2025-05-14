package Geometry; /**
 * @author Jonathan Tchebiner
 * yonimomi9@gmail.com
 * Geometry.Line.java
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The type Geometry.Line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Instantiates a new Geometry.Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Geometry.Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        if (this.start == this.end) {
            return 0;
        }
        return start.distance(end);
    }

    /**
     * Middle point.
     *
     * @return the point
     */
// Returns the middle point of the line
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * Start point.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return start;
    }

    /**
     * End point.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double m1, m2, b1, b2, x, y;

        // if one of the points of one line equals to one point of the other line then that's their intersection
        if (this.start.getX() == other.start.getX() && this.start.getY() == other.start.getY()) {
            return true;
        }
        if (this.start.getX() == other.end.getX() && this.start.getY() == other.end.getY()) {
            return true;
        }
        if (this.end.getX() == other.start.getX() && this.end.getY() == other.start.getY()) {
            return true;
        }
        if (this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY()) {
            return true;
        }
        // if one lines contains the other
        if (this.isInfinite() && other.isInfinite()) {
            if (this.isContain(other.end) || this.isContain(other.start)
                    || other.isContain(this.start) || other.isContain(this.end)) {
                return true;
            }
            return false;
        } else if (this.isInfinite()) {
            // if only one line's slope is infinite
            x = x1;
            m2 = (y4 - y3) / (x4 - x3);
            b2 = y3 - m2 * x3;
            y = m2 * x + b2;
        } else if (other.isInfinite()) {
            // if only one line's slope is infinite
            x = x3;
            m1 = (y2 - y1) / (x2 - x1);
            b1 = y1 - m1 * x1;
            y = m1 * x + b1;
        } else {
            // Neither line's slope is infinite, calculate slopes and y-intercepts
            m1 = (y2 - y1) / (x2 - x1);
            m2 = (y4 - y3) / (x4 - x3);
            b1 = y1 - m1 * x1;
            b2 = y3 - m2 * x3;

            // Calculate intersection point
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
            // if slopes are equal
            if (m1 == m2) {
                if (m1 == 0 || (this.start.getY() != other.start.getY())) {
                    return false;
                }
                // if one lines contains the other
                if (this.isContain(other.end) || this.isContain(other.start)
                        || other.isContain(this.start) || other.isContain(this.end)) {
                    return true;
                }
                return false;
            }
        }
        // checks if the intersection point is out of bounds, too big or too small x/y values.
        if ((x < Math.min(x1, x2)) || (x > Math.max(x1, x2)) || (x < Math.min(x3, x4)) || (x > Math.max(x3, x4))) {
            return false;
        }
        if ((y < Math.min(y1, y2)) || (y > Math.max(y1, y2)) || (y < Math.min(y3, y4)) || (y > Math.max(y3, y4))) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the line's slope is infinite.
     *
     * @return True if infinite, false if not.
     */
    public boolean isInfinite() {
        return this.start.getX() - this.end.getX() == 0;
    }

    /**
     * Checks if one line contains another line.
     *
     * @param p the Geometry.Point
     * @return returns true if contains, false if it does not.
     */
    public boolean isContain(Point p) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double m = (y2 - y1) / (x2 - x1);
        double maxX = Math.max(x1, x2);
        double minX = Math.min(x1, x2);
        if (p.getX() < minX || p.getX() > maxX) {
            return false;
        }
        // if slope is the same
        if (x2 - x1 == 0) {
            // if the y's are different
            if ((p.getY() < y1 && p.getY() < y2) || (p.getY() > y1 && p.getY() > y2)) {
                return false;
            }
            return p.getX() == x1;
        } else {
            double b = y1 - m * x1;
            double yOnLine = m * p.getX() + b;
            double minY = Math.min(y1, y2);
            double maxY = Math.max(y1, y2);
            if (yOnLine < minY || yOnLine > maxY) {
                return false;
            }
            return true;
        }
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();

        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        double m1, m2, b1, b2, x = 0, y = 0;
        boolean isInter = isIntersecting(other);
        if (!isInter) {
            return null;
        }
        // if slopes of both lines are infinite
        if (this.isInfinite() && other.isInfinite()) {
            // if one of the points of one line equals to one point of the other line then that's their intersection
            if (this.start.getX() == other.start.getX() && this.start.getY() == other.start.getY()) {
                return this.start;
            }
            if (this.start.getX() == other.end.getX() && this.start.getY() == other.end.getY()) {
                return this.start;
            }
            if (this.end.getX() == other.start.getX() && this.end.getY() == other.start.getY()) {
                return this.end;
            }
            if (this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY()) {
                return this.end;
            }
            // if one lines contains the other
            if (this.isContain(other.end) && this.isContain(other.start)
                    && other.isContain(this.start) && other.isContain(this.end)) {
                return null;
            }
            // if only one line's slope is infinite
        } else if (this.isInfinite()) {
            x = x1;
            m2 = (y4 - y3) / (x4 - x3);
            b2 = y3 - m2 * x3;
            y = m2 * x + b2;
        } else if (other.isInfinite()) {
            x = x3;
            m1 = (y2 - y1) / (x2 - x1);
            b1 = y1 - m1 * x1;
            y = m1 * x + b1;
        } else {
            // Neither line's slope is infinite, calculate slopes and y-intercepts
            m1 = (y2 - y1) / (x2 - x1);
            m2 = (y4 - y3) / (x4 - x3);
            b1 = y1 - m1 * x1;
            b2 = y3 - m2 * x3;

            // Calculate intersection point
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
            if (m1 == m2) {
                /* if one of the points of one line equals to one point of the other
                line then that's their intersection pont*/
                if (this.start.getX() == other.start.getX() && this.start.getY() == other.start.getY()) {
                    return this.start;
                }
                if (this.start.getX() == other.end.getX() && this.start.getY() == other.end.getY()) {
                    return this.start;
                }
                if (this.end.getX() == other.start.getX() && this.end.getY() == other.start.getY()) {
                    return this.end;
                }
                if (this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY()) {
                    return this.end;
                }
                // if one lines contains the other
                if (this.isContain(other.end) || this.isContain(other.start)
                        || other.isContain(this.start) || other.isContain(this.end)) {
                    return null;
                }
            }
        }
        // return the intersection point
        return new Point(x, y);
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        if ((this.start).equals(other.start) && (this.end).equals(other.end)) {
            return true;
        } else if ((this.start).equals(other.end) && (this.end).equals(other.start)) {
            return true;
        }
        return false;
    }


    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point a = rect.getUpperLeft();
        Point b = new Point(rect.getWidth() + rect.getUpperLeft().getX(), rect.getUpperLeft().getY());
        Point c = new Point(rect.getWidth() + rect.getUpperLeft().getX(),
                rect.getHeight() + rect.getUpperLeft().getY());
        Point d = new Point(rect.getUpperLeft().getX(), rect.getHeight() + rect.getUpperLeft().getY());
        Line[] l = new Line[4];
        l[0] = new Line(a, b);
        l[1] = new Line(b, c);
        l[2] = new Line(c, d);
        l[3] = new Line(d, a);
        List<Point> inter = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            Point p = l[i].intersectionWith(new Line(this.start, this.end));
            if (p != null) {
                inter.add(p);
            }
        }
        if (inter.size() == 0) {
            return null;
        } else if (inter.size() == 1) {
            return inter.get(0);
        } else {
            double d1 = inter.get(0).distance(this.start);
            double d2 = inter.get(1).distance(this.start);
            if (d1 < d2) {
                return inter.get(0);
            } else {
                return inter.get(1);
            }
        }
    }
}