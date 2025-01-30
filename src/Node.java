/**
* The class that creates a node with 4 pointers to allow for 2D doubly linked lists.
* @author Daniel Burnayev
* @version 1.0
* @param <T>	The class that will be the type of data this node holds
*/
public final class Node<T extends Comparable<T>> implements Comparable<Node<T>>
{   
 /** Holds whatever data/value is inside the node. */
	private T data;
	
   /** A reference to the node above. */
  	private Node<T> up;
  	
   /** A reference to the node below. */
  	private Node<T> down;

    /** A reference to the node to the right. */
    private Node<T> right;

    /** A reference to the node to the left. */
    private Node<T> left;
    
    
    /** Default constructor that sets all the fields to null. */
    public Node() {
    	data = null;
    	up = null;
    	down = null;
    	right = null;
    	left = null;
    }
    
    
    /**
    * Constructor that stores an object in this node. 
    * @param value	An object of type T that will be the put into data.
    */
    public Node(T value) {
    	data = value;
    	up = null;
    	down = null;
    	right = null;
    	left = null;
    } 
      
    
    /** 
    * Getter method that gets whatever is stored in a node.
    * @return	An object stored in a node.
    */
    public T getValue() {return data;}
    
    /** 
    * Getter method that gets whatever node is above it.
    * @return	The node above it (could be null if not pointing to an actual node).
    */
    public Node<T> getUp() {return up;}
    
    /** 
    * Getter method that gets whatever node is below it.
    * @return	The node below it (could be null if not pointing to an actual node).
    */
    public Node<T> getDown() {return down;}
    
    /** 
    * Getter method that gets whatever node is to the right of it.
    * @return	The node to the right it (could be null if not pointing to an actual node).
    */
    public Node<T> getRight() {return right;}
    
    /** 
    * Getter method that gets whatever node is to the left of it.
    * @return	The node to the left it (could be null if not pointing to an actual node).
    */
    public Node<T> getLeft() {return left;}
    
    /**
    * Setter method that changes whatever is stored in a node to something else.
    * @param value	The object replacing whatever is currently stored.
    */
    public void setValue(T value) {data = value;}
    
    /**
    * Setter method that changes the node above to another node.
    * @param p	The new node.
    */
    public void setUp(Node<T> p) {up = p;}

	/**
    * Setter method that changes the node below to another node.
    * @param p	The new node.
    */
	public void setDown(Node<T> p) {down = p;}

 /**
 * Setter method that changes the node to the right to another node.
 * @param p	The new node.
 */
	public void setRight(Node<T> p) {right = p;}

 /**
 * Setter method that changes the node to the left to another node.
 * @param p	The new node.
 */
	public void setLeft(Node<T> p) {left = p;}
      
      
    /**
    * Method that compares two nodes based of the objects they store.
    * @param otherNode	A node to compare the called node to
    * @return	An integer indicator of how one node compares to another (negative means less than, zero means equal, positive means greater than)
    */
    public int compareTo(Node<T> otherNode) {
    	try {
    		return data.compareTo(otherNode.data);
    	}
    	catch (NullPointerException nul) {
    		if (otherNode != null) {return data == null ? -1 : 1;}
    		throw nul;
    	}
    }
    
}
