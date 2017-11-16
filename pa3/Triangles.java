/**
 * Name:   Xiaolong Zhou
 * Login:  cs11faug
 * Date:   Oct 12, 2015
 * File:   Triangles.java
 * Source of help: Karan Lala, cseweb.ucsd.edu/~richo/cse11/pa3.pdf
 * Description: This program will draw triangles pattern using "*".
 *              The number of "*" for each side will equal and is based
 *              on the number that user entered. User can only number
 *              between 2 to 19. (2 and 19 are included)
 */

import java.util.Scanner;

/**
 * Description: A program that produces four triangles. The range of number
 *              that user can enter is between 2 to 19. It will draw 
 *              triangles with "*", and each side of triangle has same
 *              number of "*". The number is based on user enterd.
 */
public class Triangles {

  // initialize the range of number that user can enter
  private final static int MIN_VALUE = 2;
  private final static int MAX_VALUE = 19;
 
  /**
   * Description: This method uses for checking the number whether in the
   *              range which entered by user.
   * @param value: This represents the number entered by user.
   * @param min  : This represents the minimum number that user can enter.
   * @param min  : This represents the maximum number that user can enter.
   * @return     : true or false
   */
  private static boolean checkRange(int value, int min, int max) {
    
    // check whether number is within range.
    if (value > MAX_VALUE || value < MIN_VALUE) {

      return false;

    }
    else
    {
      return true;
    }
  }

  /**
   * Description: This method users for drawwing lines for each triangle,
   *              every time we call this method, it will draw a for 1
   *              triangle. Technically we need to call this method 4 time
   *              to draw one row.
   * @param first: This represents the first charactor will print out.
   * @param second: This represents the second charactor will print out.
   * @param firstCount: This represents how many first charactors will print.
   * @param totalCount: This represents total number of charactor will 
   *                    print our for one line.
   * return: void
   */
  private static void drawLine(char first, char second, int firstCount,
                               int totalCount) {
    // counter
    int i =0;
  
    // Keep check how many firstCount we have
    while ( firstCount > i ) {
    
      // print out first charactor
      System.out.print(first);
      i++; 
    }
    while ( i < totalCount) {

      // pint out second charator.
      System.out.print(second);
      i++;
    }
  }
  
  /**
   * Description: This is main method for program, when user enter a 
   *              integer it will draw triangles with same numbers of
   *              stars for each side based on the number that user enter.
   *              User can only enter number from 2 to 19.
   * @param args: This represents arguments for command line.
   * return void. 
   */

  public static void main (String [] args) {
    
    boolean checkRun = true;
    //number that user entered
    int number;
    
    // To tell user input a number a draw triangle.
    System.out.print("Enter the size of the triangles to display: ");

    //Construct a scanner that produces values scanned from standard input.
    Scanner input = new Scanner(System.in);

    
    // ask user to enter number
    if( input.hasNext()) {
          
      // read next integer
      number = input.nextInt();

      // check whether numbeer is within range  
      while (checkRange(number, MIN_VALUE, MAX_VALUE) == false) {

        // To tell user the range is from 2 to 19.
        System.out.println("Triangle size must be >=2 and <= 19; Try again.");
      
        System.out.println(""); 
        System.out.print("Enter the size of the triangles to display: ");
    
        number = input.nextInt();
      }
    
      System.out.println("");  
    
        // row for trianges
        int row = 1;
        
        // check whether row less than or equal to number 
      while ( row <=  number ){
        
        // draw line for first triangle
        drawLine(' ', '*', number-row, number);

        // space between first and second triangle
        System.out.print(" ");

        // draw line for second triangle 
        drawLine('*', ' ', row, number);

        // space between second and third triangle
        System.out.print(" ");

        // draw line for third triangle
        drawLine(' ', '*', row-1, number);

        // space between third and forth triangle
        System.out.print(" ");

        // draw line for forth triangle
        drawLine('*', ' ', number-row+1, number);
     
        // tell system go to next row
        System.out.println("");    
      
        // keep track rows of triangle 
        row++;
              
        }                       
    } 
  }
}
