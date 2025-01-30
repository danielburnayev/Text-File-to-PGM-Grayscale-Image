import java.util.Iterator;

/**
* A class that serves as a specialized iterator for Images, allowing easy for and while loop navigation.
* @author Daniel Burnayev
* @version 1.0
* @param <T>	The class that will be the type of data this iterator looks through
*/
public class ImageIterator<T extends Comparable<T>> implements Iterator<Node<T>>
{
	/** The 2D doubly linked list that will be iterated through. */
	private Node<T> image;
	
	/** The node that keeps track of the last node accessed. */
	private Node<T> tail = new Node<T>();
	
	/** The node that keeps track of the beginning of a row/column, depending on what the direction is. */
	private Node<T> sectionRoot;
	
	/** The direction the iterator will navigate through. */
	private Direction direction;
    
    
    /** 
    * Constructor that creates the iterator with a specific 2D doubly linked list and a default horizontal direction.
    * @param image	The 2D doubly linked list that will be iterated through.
    */
    public ImageIterator(Node<T> image) {
    	this.image = image;
    	direction = Direction.HORIZONTAL;
    	sectionRoot = this.image;
    	tail.setRight(image);
    }
    
    
    /** 
    * Constructor that creates the iterator with a specific 2D doubly linked list and direction.
    * @param image	The 2D doubly linked list that will be iterated through.
    * @param dir	The direction the iterator will go in (horizontal is left to right, then top to bottom; vertical is top to bottom, then left to right).
    */
    public ImageIterator(Node<T> image, Direction dir) {
    	this.image = image;
    	direction = dir;
    	sectionRoot = this.image;
    	
    	if (dir == Direction.HORIZONTAL) {tail.setRight(image);}
    	else {tail.setDown(image);}
    }
    
    
    /** 
    * Method that checks if the iterator has anymore nodes after it.
    * @return	A boolean on whether the iterator has another node after it.
    */
    public boolean hasNext() {
    	return (tail.getRight() != null || tail.getDown() != null);
    }
    
    
    /** 
    * Method that gets the node after the iterator.
    * @return	The node that was the next item in the 2D doubly linked list
    */
    public Node<T> next() {
    	Node<T> answer;
    	
    	if (direction == Direction.HORIZONTAL) { //if the direction is horizontal
    		if (tail.getRight() != null) {
    			answer = tail.getRight();
    		}
    		else {
    			answer = sectionRoot.getDown();
    			sectionRoot = answer;
    		}
    	}
    	else { //if the direction is vertical
    		if (tail.getDown() != null) {
    			answer = tail.getDown();
    		}
    		else {
    			answer = sectionRoot.getRight();
    			sectionRoot = answer;
    		}
    	}
    	tail = answer;
    	
    	return answer;
    }
    
    
    /** Method that removes nodes from the 2D doubly linked list (NON FUNCTIONAL). */
    public void remove() {
    	throw new UnsupportedOperationException();
    }
    
    
    
}
