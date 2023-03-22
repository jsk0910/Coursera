import java.util.*;

public class BruteCollinearPoints {

    private Point[] points;
    private int numOfSeg;
    private LineSegment[] line;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        
        this.points = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();

            for (int j = 0; j < i; j++)
                if (this.points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException();
            this.points[i] = points[i];
        }

        int numOfPoints = points.length;
        
        List<LineSegment> lineSegments = new LinkedList<>();
        Arrays.sort(points);
    }
}