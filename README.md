# Text-File-to-PGM-Grayscale-Image


## Instructions:
Runner is a Java class used to test the linked list's performance; you can run it in your terminal with <strong>java Runner \<input filename\> \<output filename\></strong>
* input filename is the name of an existing PGM file, which the resulting file will be based from
  * The input file must be in the following format (all these lines are seperated by one new line):
    * First line: P2 (indicates the text file will be in a grayscale format)
    * Second line: X Y (X and Y are positive integers representing the width and height of the file's image in pixels, respectively)
    * Thrid line: Z (Z is a positive integer representing the maximum grayscale value; 255 is normally put here)
    * Fourth line: ... (X * Y positive integers seperated by one space that represent the grayscale values of the individual pixels in the file's image)
  * Example of correct input file text format:
      <img width="795" alt="Screenshot 2025-01-30 at 4 02 01 PM" src="https://github.com/user-attachments/assets/ede7b783-c8b9-4afa-9556-c1a9d259c964" />

* output filename is the name of an existing or non-existing PGM file
  * If the output file doesn't exist before Runner is called, a new output file will be created
