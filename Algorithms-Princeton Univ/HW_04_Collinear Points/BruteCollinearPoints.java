import java.util.*;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private final Point[] points;
    private final int numOfSeg;
    private final LineSegment[] line;

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
        
        for (int p = 0; p < numOfPoints; p++) {
        	for (int q = p+1; q < numOfPoints; q++) {
        		for (int r = q+1; r < numOfPoints; r++) {
        			for (int s = r+1; s < numOfPoints; s++) {
        				if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) && points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])) {
        					lineSegments.add(new LineSegment(points[p], points[s]));
        				}
        			}
        		}
        	}
        }
        
        this.numOfSeg = lineSegments.size();
        this.line = lineSegments.toArray(new LineSegment[numOfSeg]);
    }
    
    public int numberOfSegments() {
    	return numOfSeg;
    }
    
    public LineSegment[] segments() {
    	return Arrays.copyOf(this.line, numberOfSegments());
    }
    
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}