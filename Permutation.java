import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation
{
    public static void main(final String[] array) {
    	
        int k = Integer.parseInt(array[0]);
        
        
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
       
        while (!StdIn.isEmpty()) {

        	randomizedQueue.enqueue(StdIn.readString());
        }
 
        Iterator<String> iterator = randomizedQueue.iterator();
        int counter = 0;
        while (iterator.hasNext() && counter < k) {
            StdOut.println(iterator.next());
            counter++;
        }
        
    }
}