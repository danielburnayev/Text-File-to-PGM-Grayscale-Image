# Text-File-to-PGM-Grayscale-Image

Runner is a Java class used to test the linked list's performance; you can run it in your terminal with <strong>java Runner \\<input filename\\> \\<output filename\\></strong>
* input filename is the name of an existing pgm file, which the resulting file will be based from
*   The input file must be in the following format (all these lines are seperated by one new line):
*     First line: P2 (indicates the text file will be in a grayscale format)
*     Second line: X Y (X and Y are positive integers representing the width and height of the file's image in pixels, respectively)
*     Thrid line: Z (Z is a positive integer representing the maximum grayscale value; 255 is normally put here)
*     Fourth line: ... (X * Y positive integers seperated by one space that represent the grayscale values of the individual pixels in the file's image)
* output filename is the name of an existing or non-existing file (if the output file doesn't exist before Runner is called, a new output file will be created).
