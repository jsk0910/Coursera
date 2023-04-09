import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class KdTree {
	private Node root;
	
	private int size = 0;
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public void insert(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		
		if (root == null) {
			root = Node.create(p, 0, null, null);
			size++;
		} else if (!contains(p)) {
			insert(root, p, true);
			size++;
		}
	}
	
	public boolean contains(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		if (isEmpty()) return false;
		return contains(root, p, true);
	}
	
	public void draw() {
		draw(root, new RectHV(0, 0, 1, 1));
	}
	
	public Iterable<Point2D> range(RectHV rc) {
		if (rc == null) throw new IllegalArgumentException();
		List<Point2D> result = new LinkedList<>();
		range(this.root, rc, result);
		return result;
	}
	
	public Point2D nearest(Point2D p) {
		if (p == null) throw new IllegalArgumentException();
		if (isEmpty()) throw new IllegalArgumentException();
		return nearest(p, root.data, root);
	}
	
	private Point2D nearest(Point2D p, Point2D currentNear, Node n) {
		if (n == null) return currentNear;
		
		if (n.nodeLevel % 2 == 0) {
			if (p.x() > n.data.x()) {
				Point2D npn = nearest(p, n.data.distanceSquaredTo(p) < currentNear.distanceSquaredTo(p) ? n.data : currentNear, n.next);
				
				if (npn.distanceSquaredTo(p) > Math.abs(n.data.x() - p.x())) {
					Point2D npp = nearest(p, npn, n.prev);
					return npn.distanceSquaredTo(p) > npp.distanceSquaredTo(p) ? npp : npn;
				} else {
					return npn;
				}
			} else {
				Point2D npp = nearest(p, n.data.distanceSquaredTo(p) < currentNear.distanceSquaredTo(p) ? n.data : currentNear, n.prev);
				
				if (npp.distanceSquaredTo(p) > Math.abs(n.data.x() - p.x())) {
					Point2D npn = nearest(p, npp, n.next);
					return npn.distanceSquaredTo(p) > npp.distanceSquaredTo(p) ? npp : npn;
				} else {
					return npp;
				}
			}
		} else {
			 if (p.y() > n.data.y()) { // check up sub-tree
				 Point2D npu = nearest(p, n.data.distanceSquaredTo(p) < currentNear.distanceSquaredTo(p) ? n.data : currentNear, n.next);
	             
				 if (npu.distanceSquaredTo(p) > Math.abs(n.data.y() - p.y())) {
	            	 Point2D npd = nearest(p, npu, n.prev);
	                 return npu.distanceSquaredTo(p) > npd.distanceSquaredTo(p) ? npd : npu;
	             } else {
	            	 return npu;
	             }
	         } else { // check down sub-tree
	        	 Point2D npd = nearest(p, n.data.distanceSquaredTo(p) < currentNear.distanceSquaredTo(p) ? n.data : currentNear, n.prev);
	        	 
	        	 if (npd.distanceSquaredTo(p) > Math.abs(n.data.y() - p.y())) {
	        		 Point2D npu = nearest(p, npd, n.next);
	        		 return npu.distanceSquaredTo(p) > npd.distanceSquaredTo(p) ? npd : npu;
	        	} else {
	        		return npd;
	            }
	        }
		}
	}
	
	private void range(Node n, RectHV rc, List<Point2D> arr) {
		if (n == null) return;
		
		if (rc.contains(n.data)) arr.add(n.data);
		
		if (n.nodeLevel % 2 == 0) {
			if (rc.xmin() <= n.data.x() && n.data.x() <= rc.xmax()) {
				range(n.prev, rc, arr);
				range(n.next, rc, arr);
			} else if (rc.xmin() > n.data.x()) {
				range(n.next, rc, arr);
			} else {
				range(n.prev, rc, arr);
			}
		} else {
			if (rc.ymin() <= n.data.y() && n.data.y() <= rc.ymax()) {
				range(n.prev, rc, arr);
				range(n.next, rc, arr);
			} else if (rc.ymin() > n.data.y()) {
				range(n.next, rc, arr);
			} else {
				range(n.prev, rc, arr);
			}
		}
	}
	
	private void draw(Node n, RectHV rc) {
		if (n == null) return;
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.02);
		n.data.draw();
		
		StdDraw.setPenRadius(0.001);
		
		if (n.nodeLevel % 2 == 0) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(n.data.x(), rc.ymin(), n.data.x(), rc.ymax());
			draw(n.prev, new RectHV(rc.xmin(), rc.ymin(), n.data.x(), rc.ymax()));
			draw(n.next, new RectHV(n.data.x(), rc.ymin(), rc.xmax(), rc.ymax()));
		} else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(rc.xmin(), n.data.y(), rc.xmax(), n.data.y());
			draw(n.prev, new RectHV(rc.xmin(), rc.ymin(), rc.xmax(), n.data.y()));
			draw(n.next, new RectHV(rc.xmin(), n.data.y(), rc.xmax(), rc.ymax()));
		}
	}
	
	private boolean contains(Node r, Point2D p, boolean useX) {
		Comparator<Point2D> comp = useX ? Point2D.X_ORDER : Point2D.Y_ORDER;
		
		if (r.data.equals(p)) return true;
		if (comp.compare(r.data, p) > 0) {
			if (r.prev == null) {
				return false;
			} else {
				return contains(r.prev, p, !useX);
			}
		} else if (r.next == null) {
			return false;
		} else {
			return contains(r.next, p, !useX);
		}
	}
	
	private void insert(Node r, Point2D p, boolean useX) {
		Comparator<Point2D> comp = useX ? Point2D.X_ORDER : Point2D.Y_ORDER;
		
		if (comp.compare(r.data, p) > 0) {
			if (r.prev == null) {
				r.prev = Node.create(p, r.nodeLevel + 1, null, null);
			} else {
				insert(r.prev, p, !useX);
			}
		} else if (r.next == null) {
			r.next = Node.create(p, r.nodeLevel + 1, null, null);
		} else {
			insert(r.next, p, !useX);
		}
	}
	
	private static class Node {
		private final Point2D data;
		private Node prev;
		private Node next;
		private final int nodeLevel;
		
		private Node(Point2D data, int nodeLevel, Node prev, Node next) {
			this.data = data;
			this.nodeLevel = nodeLevel;
			this.prev = prev;
			this.next = next;
		}
		
		public static Node create(Point2D data, int nodeLevel, Node prev, Node next) {
			return new Node(data, nodeLevel, prev, next);
		}
	}
	
	public static void main(String[] args) {
		KdTree kdTree = new KdTree();

        kdTree.insert(new Point2D(0.7, 0.2)); // A
        kdTree.insert(new Point2D(0.5, 0.4)); // B
        kdTree.insert(new Point2D(0.2, 0.3)); // C
        kdTree.insert(new Point2D(0.4, 0.7)); // D
        kdTree.insert(new Point2D(0.9, 0.6)); // E


        System.out.println(kdTree.nearest(new Point2D(0.078, 0.552)));
        System.out.println(kdTree.nearest(new Point2D(0.684, 0.73)));
	}
}
