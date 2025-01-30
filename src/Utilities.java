import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
* Class responsible for the creation and preservation of Images.
* @author Daniel Burnayev
* @version 1.0
*/
public final class Utilities
{
    /**
    * Method that creates an image from the .pgm file given.
    * @param pgmFile	The name of the .pgm file where the image will be extracted
    * @return	An image of shorts
    */
    public static Image<Short> loadImage(String pgmFile)
    {
    	try {
    		File openedFile = new File(pgmFile);
        	Scanner fileScanner = new Scanner(openedFile);
        	int width = 0;
        	int height = 0;
        	int counter = 0;
        	Short[] pixelNums = null;
        	Image<Short> image = null;
        	
        	while (fileScanner.hasNext()) {
        		String currentElem = fileScanner.next();
        		switch (counter) {
        			case 0:
        				if (!currentElem.equals("P2")) {return null;}
        				break;
        			case 1:
        				width = Integer.parseInt(currentElem);
        				break;
        			case 2:
        				height = Integer.parseInt(currentElem);
        				break;
        			case 3:
        				pixelNums = new Short[width * height];
        				image = new Image<Short>(width, height);
        				break;
        			default:
        				pixelNums[counter - 4] = Short.parseShort(currentElem);
        				break;
        		}
        		counter++;
        	}
        	counter = 0;
        	fileScanner.close();
        	
        	for (Node<Short> node : image) {
        		node.setValue(pixelNums[counter]);
        		counter++;
        	}
        	
        	return image;
    	}
    	catch (FileNotFoundException f) {
    		throw new RuntimeException();
    	}       
    }

    /**
    * Method that saves an image of shorts to a .pgm file.
    * @param image	An image of shorts
    * @param pgmFile	The name of the .pgm file where the image will be saved
    */
    public static void saveImage(Image<Short> image, String pgmFile)
    {
        
        try {
    		File openedFile = new File(pgmFile);
        	PrintWriter fileWriter = new PrintWriter(openedFile);
        	
        	fileWriter.append("P2\n\n" + image.getWidth() + " " + image.getHeight() + "\n\n255\n\n");
        	for (Node<Short> node : image) {
        		fileWriter.append(node.getValue() + " ");
        	}
        	fileWriter.close();
    	}
    	catch (FileNotFoundException f) {
    		throw new RuntimeException();
    	}  
    }
}
