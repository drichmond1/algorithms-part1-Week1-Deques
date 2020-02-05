import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;



public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private int first;
	private int last;
	private Object[] queue;
	
	
    // construct an empty randomized queue
    public RandomizedQueue() {
    	queue = new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return (last - first) < 1;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return (last - first);
    }

    // add the item
    public void enqueue(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("");
    	}
    	//Item newItem = new Node<>(item);
    	
    	
    	if (isEmpty()) {
    		queue[0] = item;
    		first = 0;
    		last = 1;
    		resize();
    	}
    	
    	
    	else if (last < queue.length){
    		queue[last] = item;
    		last ++;
    		
    		if(last == queue.length) {
        		resize();
        	}
    	}
    	
    	else {
    		return;
    	}
    	
    }

    private void resize() {
    	 Object[] tempQueue = new Object [queue.length * 2];
    	 for (int i = 0; i < queue.length; i ++) {
    		 tempQueue[i] = queue[i];
    	 }
		queue = tempQueue;
	}
    
    private void shrink() {
     Object[] tempQueue = new Object[queue.length / 2];
     int x = 0;
   	 for (int i = first; i < last; i ++) {
   		 tempQueue[x] = queue[i];
   		 x ++;
   	 }
   	 first = 0;
   	 last = x;
		queue = tempQueue;
	}	
   	


	// remove and return a random item
    public Item dequeue() {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    	}
    	Item itemToReturn = (Item) queue[first];
    	first ++;
    	
    	if(last - first == (queue.length / 4)) {
    		shrink();
    	}
    	return itemToReturn;
    	
    }
    // return a random item (but do not remove it)
    public Item sample() {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    	}
    	 int index = StdRandom.uniform(first, last);
 
    	 return (Item) queue[index];
    }
    

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
    	return new RandomQueueIter(this);
    }

    // unit testing (required)
    public static void main(String[] args) {
    	RandomizedQueue<String> rand = new RandomizedQueue<>();
    	    	
    	rand.enqueue("a");
    	rand.enqueue("b");
    	rand.enqueue("c");
    	
    	rand.enqueue("d");
    	rand.enqueue("e");
    	rand.enqueue("f");
    	
    	
    	
    	StdOut.println("size " +rand.size());
    	
    	StdOut.println("dequeue " + rand.dequeue());
    	StdOut.println("dequeue " + rand.dequeue());
    	
    	
    	StdOut.println("size " + rand.size());
	
    	StdOut.println("random " + rand.sample());
   
    	
    	Iterator<String> iter = rand.iterator();
    	while(iter.hasNext()) {
    		StdOut.println(iter.next());
    	}
 
    	StdOut.println("is Empty? " + rand.isEmpty());
    	
    }


    
    private class RandomQueueIter implements Iterator<Item>{
    	private Object[] array;
    	private int pointer;
    	
    	
    	public RandomQueueIter(RandomizedQueue<Item> randomizedQueue) {
    	this.array = new Object[randomizedQueue.size()];
    	
    	int counter = 0;
    	for(int x = randomizedQueue.first; x < randomizedQueue.last ; x ++) {
    		array[counter] = randomizedQueue.queue[x];
    		counter ++;
    	}
    	StdRandom.shuffle(array);
    	pointer = 0;
    	}	
		@Override
		public boolean hasNext() {
			return pointer < array.length;
		}


		public void remove() {
	        throw new UnsupportedOperationException("remove");
	    }
		@Override
		public Item next() {
			if (pointer >= array.length) {
	    		throw new NoSuchElementException();
	    	}
			
			Item itemToReturn = (Item) array[pointer];
			pointer ++;
			return itemToReturn;
	    	 	    	 
		}
    }

}
