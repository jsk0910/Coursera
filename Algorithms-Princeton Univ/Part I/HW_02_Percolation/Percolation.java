import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation {
	private int n;
	private int size;
	private int top, bottom;
	private boolean opened[];
	private int numberOfOpenSites;
	private WeightedQuickUnionUF uf;
	
	public Percolation(int num) {
		if (num <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.n = num;
		size = n * n + 2;
		top = 0;
		bottom = size - 1;
		
		opened = new boolean[size];
		numberOfOpenSites = 0;
		opened[top] = true;
		opened[bottom] = true;
		
		uf = new WeightedQuickUnionUF(size);
	}
	
	public void open(int row, int col) {
		int index = getNodeIndex(row, col);
		
		if (opened[index]) {
			return;
		}
		
		opened[index] = true;
		numberOfOpenSites++;
		
		if (index % n == 1) {
			if (opened[index + 1]) uf.union(index, index + 1);
		} else if (index % n == 0) {
			if (opened[index - 1]) uf.union(index, index - 1);
		} else {
			if (opened[index - 1]) uf.union(index, index - 1);
			if (opened[index + 1]) uf.union(index, index + 1);
		}
		
		if (index <= n) {
			uf.union(top, index);
			if (opened[index + n]) uf.union(index, index + n);
		} else if (index > (n - 1) * n) {
			uf.union(bottom, index);
			if (opened[index - n]) uf.union(index, index - n);
		} else {
			if (opened[index - n]) uf.union(index, index - n);
			if (opened[index + n]) uf.union(index, index + n);
		}
	}
	
	public boolean isOpen(int row, int col) {
		return opened[getNodeIndex(row, col)];
	}
	
	public boolean isFull(int row, int col) {
		return uf.find(top) == uf.find(getNodeIndex(row, col));
	}
	
	public int numberOfOpenSites() {
		return numberOfOpenSites;
	}
	
	public boolean percolates() {
		return uf.find(top) == uf.find(bottom);
	}
	
	private int getNodeIndex(int row, int col) {
		if (row < 0 || row > n || col < 0 || col > n) {
			throw new IllegalArgumentException();
		} else {
			return (row - 1) * n + col;
		}
	}
	
	public static void main(String args[]) {
		
	}
}
