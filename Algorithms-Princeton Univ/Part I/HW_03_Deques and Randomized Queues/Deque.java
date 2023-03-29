import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private int n; // size
	private Node first;
	private Node last;
	
	public Deque() {
		n = 0;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		
		if (last == null) {
			last = first;
		} else {
			first.next.prev = first;
		}
		
		n++;
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.prev = oldLast;
		
		if (first == null) {
			first = last;
		} else {
			last.prev.next = last;
		}
		
		n++;
	}
	
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		Item item = first.item;
		
		n--;
		
		if (isEmpty()) {
			last = null;
			first = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		
		return item;
	}
	
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		Item item = last.item;
		
		n--;
		
		if (isEmpty()) {
			last = null;
			first = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
	}
	
	private class ListIterator implements Iterator<Item> {
		
		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			Item item = current.item;
			current = current.next;
			
			return item;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		
	}
}
