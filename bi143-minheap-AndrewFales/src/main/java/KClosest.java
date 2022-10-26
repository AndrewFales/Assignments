import java.util.*;

public class KClosest {
    private static List<Point> returnPoints = new ArrayList<>();
    public static List<Point> find(List<Point> points, Point p, int k) {
        MinHeap<Point> mh = new MinHeap<>();
        for(int i = 0; i < points.size(); i++){
            Point temp = points.get(i);
            double d = distance(temp, p);
            mh.offer(temp, d);
        }
        returnPoints = new ArrayList<>();
        for(int i = 0; i < k; i++){
            returnPoints.add(mh.poll());
        }
        return returnPoints;
    }

    private static double distance(Point p1, Point p2) {
        double returnVal;
        double x1 = p1.getX();
        double x2 = p2.getX();
        double y1 = p1.getY();
        double y2 = p2.getY();
        returnVal = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        return returnVal;
    }
}

