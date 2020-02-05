import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int size;
	
    // construct an empty deque
    public Deque() {
    first = null;
    last = null;
    size = 0;
    }
    
    // is the deque empty?
    public boolean isEmpty() {
    	return first == null;
    }

    // return the number of items on the deque
    public int size() {
    	return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("");
    	}
    	
    	Node<Item> newNode = new Node<>(item);
    	if (isEmpty()) {
    		first = newNode;
    		last = newNode;
    	}
    	else {
    		newNode.setNext(first);
    		newNode.setPrev(null);
    		first = newNode;
    	}
    	size ++;
    }

    // add the item to the back
    public void addLast(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("");
    	}
    	Node<Item> newNode = new Node<>(item);
    	if (isEmpty()) {
    		first = newNode;
    		last = newNode;
    	}
    	else {
    		last.setNext(newNode);
    		newNode.setPrev(last);
    		last = newNode;
    	}
    	size ++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    	}
    	
    	if (size == 1) {
    		Item itemToReturn = first.getItem();
        	first = null;
        	last = null;
        	size = 0;
        	return itemToReturn;
    	}
    	else {
    	Item itemToReturn = first.getItem();
    	first = first.getNext();
    	first.prev = null;
    	size --;
    	return itemToReturn;
    	}
    }

    // remove and return the item from the back
    public Item removeLast() {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    	}
    	
    	if (size == 1) {
    		Item itemToReturn = first.getItem();
        	first = null;
        	last = null;
        	size = 0;
        	return itemToReturn;
    	}
    	else {
 //   		Node<Item> currentNode = first;
//    	while(currentNode.getNext().getNext() != null) {
//    		currentNode = currentNode.next;
//    	}
    	Item itemToReturn = last.getItem();
    	last = last.prev;
    	last.next = null;
    	size --;
    	return itemToReturn;
    	}
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
    	return new DequeIter(first);
    }
    
 
    // unit testing (required)
    public static void main(String[] args) {
    	Deque<Integer> deq = new Deque<Integer>();
    	deq.addFirst(1);
    	deq.addFirst(2);
    	deq.addLast(3);
    	deq.addLast(5);
    	

    	Iterator<Integer> iter = deq.iterator();
    	while(iter.hasNext()) {
    		StdOut.println(iter.next());
    	}


    	StdOut.println("the first element is " + deq.removeFirst());
   	
    	StdOut.println("last last element is " + deq.removeLast());
    	StdOut.println("Size is : " + deq.size());
    	StdOut.println("Is deque empty? " + deq.isEmpty());  	
    }
    
    
     
    private class Node<Item>{
    	private Item item;
    	private Node<Item> next;
    	private Node<Item> prev;
    	
    	public Node(Item item) {
    		this.item = item;
    		this.next = null;
    		this.prev = null;
    	}

		public void setPrev(Node<Item> prev) {
			this.prev = prev;	
		}

		public Item getItem() {
			return item;
		}


		public Node<Item> getNext() {
			return next;
		}

		public void setNext(Node<Item> next) {
			this.next = next;
		}
    	
    	
    }
    
    private class DequeIter implements Iterator<Item>{
    	private Node<Item> current;
    	
    	
    	public DequeIter(Node<Item> node) {
    	this.current = node;
    	}	
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(current == null) {
				throw new NoSuchElementException();
			}
			Item itemToReturn = current.getItem();
			current = current.next;
			return itemToReturn;
		}
		
		public void remove() {
	        throw new UnsupportedOperationException("remove");
	    }
    }

}
