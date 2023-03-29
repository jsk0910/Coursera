import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private final Point[] points;
	private final int numOfSeg;
	private final LineSegment[] line;
	
	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException();
		
		this.points = new Point[points.length];
		Point[] workingSet = new Point[points.length];
		
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) throw new IllegalArgumentException();
			
			for (int j = 0; j < i; j++)
				if (this.points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException();
			
			this.points[i] = points[i];
			workingSet[i] = points[i];
		}
		
		int numOfPoints = points.length;
		
		List<LineSegment> lineSegments = new LinkedList<>();
		List<Point> skipList = new LinkedList<>();
		
		for (int pid = 0; pid < numOfPoints; pid++) {
			Point originP = points[pid];
			
			if (shouldSkip(skipList, originP)) continue;
			
			Arrays.sort(workingSet, 0, numOfPoints, originP.slopeOrder());
			
			int collinearSetSize = 1;
			boolean started = false;
			int segmentIndex = 1;
			
			for (int j = segmentIndex; j < numOfPoints - 1; j++) {
				if (originP.slopeTo(workingSet[j]) == originP.slopeTo(workingSet[j + 1])) {
					collinearSetSize++;
					if (!started) {
						started = true;
						segmentIndex = j;
					}
				} else if (started) break;
			}
			
			if (collinearSetSize >= 3) {
				Point[] collinearSet = new Point[collinearSetSize + 1];
				collinearSet[0] = originP;
				
				for (int i = 0; i < collinearSetSize; i++) {
					collinearSet[i + 1] = workingSet[i + segmentIndex];
				}
				
				Arrays.sort(collinearSet, 0, collinearSetSize + 1);
				lineSegments.add(new LineSegment(collinearSet[0], collinearSet[collinearSetSize]));
				
				for (int i = 0; i <= collinearSetSize; i++) {
					skipList.add(collinearSet[i]);
				}
			}
		}
		this.numOfSeg = lineSegments.size();
		this.line = lineSegments.toArray(new LineSegment[this.numOfSeg]);
	}
	
	private boolean shouldSkip(List<Point> points, Point p) {
		return points.stream().filter(point -> point.compareTo(p) == 0).findFirst().isPresent();
	}
	
	public int numberOfSegments() {
		return this.numOfSeg;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
