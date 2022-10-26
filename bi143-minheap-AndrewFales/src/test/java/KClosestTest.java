import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class KClosestTest {
    private List<Point> points = new ArrayList<>();
    @BeforeEach
    public void init() {
        points.add(new Point(1, 3));
        points.add(new Point(-1, 2));
        points.add(new Point(3, 2));
        points.add(new Point(0, 7));
        points.add(new Point(11, 6));
        points.add(new Point(-10, 13));
        points.add(new Point(-3, 4));
    }

    @Test
    public void singleClosest() {
        List<Point> nearest = KClosest.find(points, new Point(2, 2), 1);
        assertEquals(points.get(2), nearest.get(0));
    }

    @Test
    public void threeClosest() {
        List<Point> nearest = KClosest.find(points, new Point(2.1, 0), 3);
        assertEquals(points.get(2), nearest.get(0));
        assertEquals(points.get(0), nearest.get(1));
        assertEquals(points.get(1), nearest.get(2));
    }
}
