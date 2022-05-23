package part1.sorting.convex_hull;

import edu.princeton.cs.algs4.Point2D;

import java.util.Arrays;
import java.util.Stack;

/**
 * Fact: can traverse the convex hull by making only counterclockwise turns
 * Fact: The vertices of convex hull appear in increasing order of polar angle with respect to point p with lowest y-coordinate
 */
public class ConvexHull {

    public static void calculate(Point2D[] p) {
        int N = p.length;

        Stack<Point2D> hull = new Stack<>();

        Arrays.sort(p, Point2D.Y_ORDER);
        Arrays.sort(p, p[0].polarOrder());

        hull.push(p[0]);
        hull.push(p[1]);

        for (int i = 2; i < N; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, p[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(p[i]);
        }
    }
}
