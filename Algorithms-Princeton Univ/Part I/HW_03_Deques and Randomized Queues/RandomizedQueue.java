import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] arr;
	private int n;
	
	public RandomizedQueue() {
		arr = (Item[]) new Object[2];
		n = 0;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (n == arr.length) {
			resize(2 * arr.length);
		}
		if (n == 0) {
			arr[n++] = item;
			return;
		}
		
		int randomIndex = StdRandom.uniformInt(n);
		Item temp = arr[randomIndex];
		arr[randomIndex] = item;
		arr[n++] = temp;
	}
	
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		if (n == arr.length / 4) {
			resize(arr.length / 2);
		}
		
		int randomIndex = StdRandom.uniformInt(n);
		Item item = arr[randomIndex];
		arr[randomIndex] = arr[--n];
		arr[n] = null;
		return item;
	}
	
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return arr[StdRandom.uniformInt(n)];
	}
	
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	
	private void resize(int cap) {
		Item[] temp = (Item[]) new Object[cap];
		
		for (int i = 0; i < n; i++) {
			temp[i] = arr[i];
		}
		
		arr = temp;
	}
	
	public static void main(String[] args) {
		
	}
	
	private class ArrayIterator implements Iterator<Item> {
		private int i;
		private int[] randomIndice;
		public ArrayIterator() {
			i = 0;
			randomIndice = new int[n];
			
			for (int j = 0; j < n; j++) {
				randomIndice[j] = j;
			}
			
			StdRandom.shuffle(randomIndice);
		}
		
		@Override
		public boolean hasNext() {
			return i < n;
		}
		
		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return arr[randomIndice[i++]];
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	
}
