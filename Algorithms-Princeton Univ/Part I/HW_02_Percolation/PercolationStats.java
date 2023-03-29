import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.IllegalArgumentException;

public class PercolationStats {
	private double[] treshold;
	private int t;
	private double p = 1.96;
	
	public PercolationStats(int n, int trials) {
		if (n < 1 || trials < 1) {
			throw new IllegalArgumentException();
		}
		
		t = trials;
		treshold = new double[t];
		
		for (int i = 0; i < treshold.length; i++) {
			treshold[i] = calcTreshold(n);
		}
	}
	
	private double calcTreshold(int n) {
		double counter = 0;
		int i, j;
		Percolation perc = new Percolation(n);
		
		while (!perc.percolates()) {
			i = StdRandom.uniformInt(n) + 1;
			j = StdRandom.uniformInt(n) + 1;
			
			if (!perc.isOpen(i, j)) {
				counter++;
				perc.open(i, j);
			}
		}
		
		return counter / (n*n);
	}
	
	public double mean() {
		return StdStats.mean(treshold);
	}
	
	public double stddev() {
		return StdStats.stddev(treshold);
	}
	
	public double confidenceLo() {
		return mean() - (p * stddev()) / (Math.sqrt(t));
	}
	
	public double confidenceHi() {
		return mean() + (p * stddev()) / (Math.sqrt(t));
	}
	
	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(a, b);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
	}
}
