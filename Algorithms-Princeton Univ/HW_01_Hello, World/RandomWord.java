
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
	public static void main(String[] args) {
		int i = 0;
		String champion = "";
		
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			
			boolean acc = StdRandom.bernoulli(1 / (i+1.0));
			
			if(acc) {
				champion = s;
			}
			i++;
		}
		StdOut.println(champion);
	}
}
