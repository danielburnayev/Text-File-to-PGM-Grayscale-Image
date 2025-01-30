/**
* The enumeration that helps decide which direction the image iterator go in.
* @author Daniel Burnayev
* @version 1.0
*/
public enum Direction {
	/** Direction that has the iterator move from left to right, then go to the row below. */
	HORIZONTAL, 
	
	/** Direction that has the iterator move from top to bottom, then to the column to the right. */
	VERTICAL;
}

