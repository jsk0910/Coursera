import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {
	private final Set<Point2D> points = new TreeSet<>();
	
	public PointSET() {
		
	}
	
	public boolean isEmpty() {
		return points.isEmpty();
	}
	
	public int size() {
		return points.size();
	}
	
	public void insert(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		if (!contains(p)) points.add(p);
	}
	
	public boolean contains(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		return points.contains(p);
	}
	
	public void draw() {
		for (Point2D p : points) {
			p.draw();
		}
	}
	
	public Iterable<Point2D> range(RectHV rc) {
		if (rc == null) throw new IllegalArgumentException();
		List<Point2D> answer = new LinkedList<>();
		
		for (Point2D p : points) {
			if (rc.contains(p)) {
				answer.add(p);
			}
		}
		return answer;
	}
	
	public Point2D nearest(Point2D p) {
		if (isEmpty()) return null;
		double nearPointDistance = Double.MAX_VALUE;
		Point2D nearPoint = p;
		
		for (Point2D point : points) {
			if (p.distanceSquaredTo(point) < nearPointDistance) {
				nearPointDistance = p.distanceSquaredTo(point);
				nearPoint = point;
			}
		}
		
		return nearPoint;
	}
	
	public static void main(String[] args) {
		
	}
}
