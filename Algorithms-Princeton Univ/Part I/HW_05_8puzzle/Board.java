import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;

public class Board {
	
	private int[] board;
	private int offset = 1;
	private int width;
	
	public Board(int[][] tiles) {
		if (tiles == null) throw new IllegalArgumentException();
		if (tiles.length != tiles[0].length) throw new IllegalArgumentException();
		
		width = tiles.length;
		board = new int[width * width];
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				board[xyTo1d(i, j)] = tiles[i][j];
			}
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append(width + "\n");
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				s.append(String.format("%2d ", board[xyTo1d(i, j)]));
			}
			s.append("\n");
		}
		
		return s.toString();
	}
	
	public int dimension() {
		return width;
	}
	
	public int hamming() {
		int sum = 0;
		
		for (int i = 0; i < board.length; i++) {
			if (board[i] != i + offset && board[i] != 0) sum++;
		}
		return sum;
	}
	
	public int manhattan() {
		int sum = 0;
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		
		for (int i = 0; i < board.length; i++) {
			if (board[i] != i + offset && board[i] != 0) {
				x1 = xyFrom1d(i)[0];
				y1 = xyFrom1d(i)[1];
				
				x2 = xyFrom1d(board[i] - offset)[0];
				y2 = xyFrom1d(board[i] - offset)[1];
				
				sum = sum + ( Math.abs(x1 - x2) + Math.abs(y1 - y2));
			}
		}
		
		return sum;
	}
	
	public boolean isGoal() {
		for (int i = 0; i < board.length - offset; i++) {
			if (board[i] != i+1) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		
		if (y.getClass() != this.getClass()) return false;
		Board that = (Board) y;
		
		if (that.dimension() != this.dimension()) return false;
		
		for (int i = 0; i < board.length; i++) {
			if (this.board[i] != that.board[i]) return false;
		}
		
		return true;
	}
	
	public Iterable<Board> neighbors() {
		Queue<Board> neighbors = new Queue<Board>();
		
		int emptyIndex;
		int row = 0, col = 0;
		int up = 0, right = 0, down = 0, left = 0;
		
		ArrayList<Integer> tiles = new ArrayList<Integer>();
		
		for (emptyIndex = 0; emptyIndex < board.length; emptyIndex++) {
			if (board[emptyIndex] == 0) {
				col = xyFrom1d(emptyIndex)[0];
				row = xyFrom1d(emptyIndex)[1];
				
				if (checkBound(row - 1, col)) {
					up = xyTo1d(row - 1, col);
					tiles.add(up);
				}
				if (checkBound(row, col + 1)) {
					right = xyTo1d(row, col + 1);
					tiles.add(right);
				}
				if (checkBound(row + 1, col)) {
					down = xyTo1d(row + 1, col);
					tiles.add(down);
				}
				if (checkBound(row, col - 1)) {
					left = xyTo1d(row - 1, col - 1);
					tiles.add(left);
				}
				
				break;
			}
		}
		
		for (int i = 0; i < tiles.size(); i++) {
			int[] temp1d;
			int[][] temp2d;
			
			temp1d = copy1dTo1d(board);
			exch1d(temp1d, emptyIndex, tiles.get(i));
			temp2d = copy1dTo2d(temp1d, this.width);
			Board tempBoard = new Board(temp2d);
			neighbors.enqueue(tempBoard);
		}
		
		return neighbors;
	}
	
	public Board twin() {
		int row = 0, col = 0;
		int up = 0, right = 0, down = 0, left = 0;
		
		int[][] twin2d = new int[width][width];
		int[] twin1d = new int[board.length];
		
		for (int i = 0; i < board.length; i++) twin1d[i] = board[i];
		
		for (int i = 0; i < twin1d.length; i++) {
			if (twin1d[i] != 0) {
				col = xyFrom1d(i)[0];
				row = xyFrom1d(i)[1];
				
				if (checkBound(row - 1, col)) {
					up = xyTo1d(row - 1, col);
					if (twin1d[up] != 0) {
						exch1d(twin1d, i, up);
						break;
					}
				}
				if (checkBound(row, col + 1)) {
					right = xyTo1d(row, col + 1);
					if (twin1d[right] != 0) {
						exch1d(twin1d, i, right);
						break;
					}
				}
				if (checkBound(row + 1, col)) {
					down = xyTo1d(row + 1, col);
					if (twin1d[down] != 0) {
						exch1d(twin1d, i, down);
						break;
					}
				}
				if (checkBound(row, col - 1)) {
					left = xyTo1d(row, col - 1);
					if (twin1d[left] != 0) {
						exch1d(twin1d, i, left);
						break;
					}
				}
			}
		}
		
		twin2d = copy1dTo2d(twin1d, this.width);
		
		return new Board(twin2d);
	}
	
	private int xyTo1d(int row, int col) {
		return ((col) % width) + (width * (row)); 
	}
	
	private int[] xyFrom1d(int i) {
		int[] xy = new int[2];
		xy[0] = i % width;
		xy[1] = i / width;
		return xy;
	}
	
	private void exch1d(int[] arr, int i, int j) {
		int temp = 0;
		
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private int[][] copy1dTo2d(int[] arr, int width) {
		int[][] arr2D = new int[width][width];
		
		for (int i = 0; i < arr.length; i++) {
			int x = xyFrom1d(i)[0];
			int y = xyFrom1d(i)[1];
			arr2D[y][x] = arr[i];
		}
		
		return arr2D;
	}
	
	private int[] copy1dTo1d(int[] a) {
		int[] b = new int[a.length];
		
		if (a.length != b.length) return null;
		
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}
		
		return b;
	}
	
	private boolean checkBound(int row, int col) {
		if (row < 0 || row >= width || col < 0 || col >= width) return false;
		return true;
	}
	
	public static void main(String[] args) {
		
	}
}
