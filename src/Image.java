import java.util.Iterator;

/**
* Class that creates and adds specific functionality to two dimensional doubly linked lists.
* @author Daniel Burnayev
* @version 1.0
* @param <T> The type of Object the nodes of this two dimensional doubly linked list will contain.
*/
public class Image<T extends Comparable<T>> implements Iterable<Node<T>>
{
	/** The top left node of the 2D doubly linked list. */
	private Node<T> head;
	
	/** The bottom right node of the 2D doubly linked list. */
	private Node<T> tail;
	
	/** The number of nodes high this 2D linked list is. */
	private int height;
	
	/** The number of nodes wide this 2D linked list is. */
	private int width;
    
    /**  
    * Constructor that creates a 2D doubly linked list, with nodes consisting of default values.
    * @param width	The number of nodes wide this new linked list will be
    * @param height	The number of nodes high this new linked list will be
    */
    public Image(int width, int height) {
    	if (width < 1 || height < 1) {throw new RuntimeException("This Image should at least be a 1x1 node!");}
    	
    	int rowNodesMade = 0;
    	int colNodesMade = 0;
    	Node<T> start = null;
    	Node<T> end = null;
    	Node<T> rowBegin = null;
    	
    	while (rowNodesMade < height) {
    		while (colNodesMade < width) {
    			Node<T> temp = new Node<T>();
    			
    			if (rowNodesMade > 0) { // if it's PAST the first row
    				if (colNodesMade > 0) { // and PAST the first column
    					temp.setUp(end.getUp().getRight());
    					temp.setLeft(end);
    					end.getUp().getRight().setDown(temp);
    					end.setRight(temp);
    				}
    				else { // but at the first column
    					temp.setUp(rowBegin);
    					rowBegin.setDown(temp);    							   	
    					rowBegin = temp;
    				}
    			}
    			else { // if it's the first row
    				if (colNodesMade > 0) { // and PAST the first column
    					temp.setLeft(end);
    					end.setRight(temp);
    				}
    				else { // and it's the first column
    					start = temp;
    					rowBegin = start;
    				}
    			}    			
    			end = temp;
    			colNodesMade++;
    			if (rowNodesMade == height - 1 && colNodesMade == width) {tail = end;}
    		}
    		rowNodesMade++;
    		colNodesMade = 0;
    	}
    	
    	head = start;
    	this.height = height;
    	this.width = width;
    }
    
    
    /**  
    * Method that returns the number of nodes the linked list has from top to bottom.
    * @return	The number of nodes the linked list has from top to bottom
    */
    public int getHeight() {return height;}
    
 /**  
 * Method that returns the number of nodes the linked list has from top to bottom.
 * @return	The number of nodes the linked list has from left to right
 */
	public int getWidth() {return width;}

	/**  
    * Method that returns the strat of the linked list.
    * @return	The top left node of the linked list
    */
	public Node<T> getHead() {return head;}

    
    /**  
    * Method that returns an iterator that allows for horizontal traversal.
    * @return	An iterator that allows for horizontal traversal
    */
    public Iterator<Node<T>> iterator() {return new ImageIterator<T>(head);}
	
	/**  
    * Method that returns an iterator that allows for either vertical or horizontal traversal.
    * @param dir	The custom direction (can be either vertical or horizontal)
    * @return	An iterator that allows for horizontal traversal
    */
	public Iterator<Node<T>> iterator(Direction dir) {return new ImageIterator<T>(head, dir);}
    
	
	/**
	* Method that puts a new row in the 2D linked list at the given index.
	* @param index	The index where the new row will be inserted (can be from 0 to height)
	* @param value	The value of every node in this new row.
	*/
	public void insertRow(int index, T value) {
		if (index < 0 || index > height) {throw new RuntimeException("Index must be between 0 and whatever height is.");}
	
		Node<T> prevNode = searchSurface(index, Direction.VERTICAL);
		Node<T> otherNode = (index == height) ? prevNode : prevNode.getUp();
		Node<T> end = null;
				
		for (int i = 0; i < width; i++) {
			Node<T> temp = new Node<T>(value);
			temp.setLeft(end);
			
			if (i == 0 && index == 0) {head = temp;}
			else if (i == width - 1 && index == height) {tail = temp;}
			
			if (end != null) {end.setRight(temp);}
			if (index > 0) {
				temp.setUp(otherNode);
				otherNode.setDown(temp);
			}
			if (index < height) {
				temp.setDown(prevNode);
				prevNode.setUp(temp);
			}
			
			end = temp;
			prevNode = prevNode.getRight();
			if (otherNode != null) {otherNode = otherNode.getRight();}
		}
		
		height++;
	}


    /**
    * Method that removes the column at the desired index from the 2D linked list.
    * @param index	The index where the new row will be inserted (can be from 0 to width-1)
    */
    public void removeColumn(int index) {
    	if (index < 0 || index > width - 1) {throw new RuntimeException("Index must be from 0 to whatever width-1 is.");}
    	
    	Node<T> prevNode = searchSurface(index, Direction.HORIZONTAL);
    	
    	for (int i = 0; i < height; i++) {
    		if (index > 0) {
    			if (i == height - 1 && index == width - 1) {tail = prevNode.getLeft();}
    			prevNode.getLeft().setRight(prevNode.getRight());
    		}
    		if (index < width - 1) {
    			if (i == 0 && index == 0) {head = prevNode.getRight();}
    			prevNode.getRight().setLeft(prevNode.getLeft());
    		}
    		
    		prevNode = prevNode.getDown();
    	}
    	
    	width--;
    }


    /**
    * Method that removes all adjacent rows and columns that all have the same values.
    * @return	An int of the amount of nodes removed.
    */
    public int compress() {
    	int nodesRemoved = 0;
    	int row = 1;
    	int col = 1;
    	Node<T> tracker = head.getDown();
    	
    	while (row < height) {
    		if (tracker.getValue() == tracker.getUp().getValue()) {
    			Node<T> node = tracker.getRight();
    			
    			while (node != null && node.getValue() == node.getUp().getValue()) {
    				node = node.getRight();
    			}
    			
    			if (node == null) {
    				removeRow(row - 1);
    				row--;	
    				nodesRemoved += width;
    			}
    		}
    		tracker = tracker.getDown();
    		row++;
    	}
    	
    	tracker = head.getRight();
    	while (col < width) {
    		if (tracker.getValue() == tracker.getLeft().getValue()) {
    			Node<T> node = tracker.getDown();
    			
    			while (node != null && node.getValue() == node.getLeft().getValue()) {
    				node = node.getDown();
    			}
    			
    			if (node == null) {
    				removeColumn(col - 1);
    				col--;
    				nodesRemoved += height;
    			}
    		}
    		tracker = tracker.getRight();
    		col++;
    	}
    	
    	return nodesRemoved; 
    }

	
    /**
    * Method that copies values from the sides of the 2D linked list and surrounds the linked list with it.
    */
    public void addBorder() {
    	Node<T> topLeft = new Node<T>(head.getValue());
    	Node<T> bottomRight = new Node<T>(tail.getValue());
    	
    	Node<T> start = head;
    	Node<T> end = tail;
    	Node<T> tracker1 = topLeft;
    	Node<T> tracker2 = bottomRight;
    	
    	for (int col = 0; col < width; col++) {
    		Node<T> tempTop = new Node<T>(start.getValue());
    		Node<T> tempBottom = new Node<T>(end.getValue());
    		
    		tracker1.setRight(tempTop);
    		tempTop.setLeft(tracker1);
    		tempTop.setDown(start);
    		start.setUp(tempTop);
    		
    		tracker1 = tempTop;
    		start = (col < width - 1) ? start.getRight() : start;
    		
    		tracker2.setLeft(tempBottom);
    		tempBottom.setRight(tracker2);
    		tempBottom.setUp(end);
    		end.setDown(tempBottom);
    		
    		tracker2 = tempBottom;
    		end = (col < width - 1) ? end.getLeft() : end;
    	}
    	
    	Node<T> topRight = new Node<T>(searchSurface(width - 1, Direction.HORIZONTAL).getValue());
    	Node<T> bottomLeft = new Node<T>(searchSurface(height - 1, Direction.VERTICAL).getValue());
    	
    	tracker1.setRight(topRight);
    	topRight.setLeft(tracker1);
    	tracker1 = topRight;
    	tracker2.setLeft(bottomLeft);
    	bottomLeft.setRight(tracker2);
    	tracker2 = bottomLeft;
    	
    	for (int row = 0; row < height; row++) {
    		Node<T> tempRight = new Node<T>(start.getValue());
    		Node<T> tempLeft = new Node<T>(end.getValue());
    		
    		tracker1.setDown(tempRight);
    		tempRight.setUp(tracker1);
    		tempRight.setLeft(start);
    		start.setRight(tempRight);
    		
    		tracker1 = tempRight;
    		start = (row < height - 1) ? start.getDown() : start;
    		
    		tracker2.setUp(tempLeft);
    		tempLeft.setDown(tracker2);
    		tempLeft.setRight(end);
    		end.setLeft(tempLeft);
    		
    		tracker2 = tempLeft;
    		end = (row < height - 1) ? end.getUp() : end;
    	}
    	
    	tracker1.setDown(bottomRight);
    	bottomRight.setUp(tracker1);
    	tracker2.setUp(topLeft);
    	topLeft.setDown(tracker2);
    	head = topLeft;
    	tail = bottomRight;
    	
    	width += 2;
    	height += 2;
    }


    /**
    * Method that removes the surface nodes from the 2D linked list.
    */
    public void removeBorder() {
    	if (width < 3 || height < 3) {throw new RuntimeException("The image must be a 3x3 linked list to perform this operation.");}
    	
    	removeColumn(0);
    	removeColumn(width - 1);
    	removeRow(0);
    	removeRow(height - 1);
    }


	/**
	* Method that creates a new image by setting its nodes to the largest value node in its 3x3 node neighborhood.
	* @return	The new image 
	*/
	public Image<T> maxFilter() {
		Image<T> newImage = new Image<T>(width, height);
		Node<T> newStart = newImage.getHead();
		Node<T> newRowTracker = newStart;
		
		for (Node<T> node : this) {
			Node<T> biggestNode = node;
			
			if (node.getUp() != null) {
				if (biggestNode.compareTo(node.getUp()) < 0) {biggestNode = node.getUp();}
			}
			if (node.getDown() != null) {
				if (biggestNode.compareTo(node.getDown()) < 0) {biggestNode = node.getDown();}
			}
			if (node.getLeft() != null) {
				Node<T> left = node.getLeft();
				if (biggestNode.compareTo(left) < 0) {biggestNode = left;}
				if (left.getUp() != null && biggestNode.compareTo(left.getUp()) < 0) {biggestNode = left.getUp();}
				if (left.getDown() != null && biggestNode.compareTo(left.getDown()) < 0) {biggestNode = left.getDown();}
			}
			if (node.getRight() != null) {
				Node<T> right = node.getRight();
				if (biggestNode.compareTo(right) < 0) {biggestNode = right;}
				if (right.getUp() != null && biggestNode.compareTo(right.getUp()) < 0) {biggestNode = right.getUp();}
				if (right.getDown() != null && biggestNode.compareTo(right.getDown()) < 0) {biggestNode = right.getDown();}
			}
			
			newStart.setValue(biggestNode.getValue());
			
			newStart = newStart.getRight();
			if (newStart == null) {
				newRowTracker = newRowTracker.getDown();
				newStart = newRowTracker;
			}
			
		}
		
		return newImage;
	}
	
	/**
	* Method that returns a string representation of the 2D doubly linked list.
	* @return	A string representation of the Image object 
	*/
	
	public String toString() {
 		String answer = "";
 		int colNum = 0;

		for (Node<T> node : this) {
			answer += node.getValue() + " ";
 			colNum++;
 			
 			if (colNum == getWidth()) {
 				answer += "\n";
 				colNum = 0;
 			}
		}

		return answer;
	}
	
	
	/**
	* Method that searches the surface for nodes at a specific index, changing on what direction the iterator decides to go in.
	* @param index	The index of the node that's being looked for (must be from 0 to height-1 or width-1)
	* @param dir	The custom direction the iterator will go in (can be either vertical or horizontal)
	* @return	The node (and those connected to it) at the selected index
	*/
	private Node<T> searchSurface(int index, Direction dir) {
		Node<T> answer = head;
		Iterator<Node<T>> navigator = iterator(dir);
		int indexChecker = 0;
		int dimension = (dir == Direction.HORIZONTAL) ? width : height;
		
		while (indexChecker <= index && indexChecker < dimension) {
			answer = navigator.next();
			indexChecker++;
		}
		
		return answer;
	}
	
	/**
	* Method that removes a row of the 2D linked list from the selected index.
	* @param index	The row index that will be removed (must be from 0 to height-1)
	*/
	private void removeRow(int index) {
    	Node<T> prevNode = searchSurface(index, Direction.VERTICAL);
    	
    	for (int i = 0; i < width; i++) {
    		if (index > 0) {
    			if (i == height - 1 && index == width - 1) {tail = prevNode.getUp();}
    			prevNode.getUp().setDown(prevNode.getDown());
    		}
    		if (index < width - 1) {
    			if (i == 0 && index == 0) {head = prevNode.getDown();}
    			prevNode.getDown().setUp(prevNode.getUp());
    		}
    		
    		prevNode = prevNode.getRight();
    	}
    	
    	height--;
	}

}
