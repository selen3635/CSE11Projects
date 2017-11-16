/**
 * Name:   Xiaolong Zhou
 * Login:  CS11faug
 * Date:   Oct 08, 2015
 * File:   OddEven.java
 * Source of help: Karan Lala, cseweb.ucsd.edu/~richo/cse11/PA2.pdf
 * Description: This programm will find out the largest odd number
 *              and smallest even number base on the numbers that
 *              user input. 
 */

import java.util.Scanner;
import java.lang.Integer;

/**
 * Description: A program that produces largest odd and smallest even
 *              numbers based on the numbers entered by user.
 */
public class OddEven {

  private final static int oddEvenDef = 2; /* use this variable to define
                                              odd or even number */

  /**
   * Description: This is main method for this program to find out
   *              odd or even numbers. Run as an application with main
   *              method.
   * @param args: This is argument that represent what user entered for
   *              program to excute.
   * @return: void. 
   */
  public static void main (String [] args) {
    
    // numbers that user input
    int number;
    
    // counters for odd and even number
    int oddCount = 0;
    int evenCount = 0;

    int totalNum = 0;

    // initialize largest odd and smallest even number
    int largestOddNum = Integer.MIN_VALUE ;
    int smallestEvenNum = Integer.MAX_VALUE;
     
    // print out the instructions
    System.out.println("Enter a series of integers; EOF to Quit.");

    // Construct a Scanner that produces values scanned from standard input.
    Scanner input = new Scanner(System.in);
    
    //while there is more input(user has not hit EOF)
    while (input.hasNext()) {

      // read next integer
      number = input.nextInt();       

      // To find out whether this is a even number.
      if ( number % oddEvenDef == 0 ) { 
        
        // Keeping track of how many even numbers have been encountered.
        evenCount++;

        // To find out whether the even number input by user is less than
        // default even number.
        if ( number < smallestEvenNum ) {

          smallestEvenNum = number ;
        }
      }
      
      // To find out whether this is a odd number.
      if ( number % oddEvenDef != 0) {
      
        // Keeping track of how many even numbeers have been encountered.
        oddCount++;
        
        // To find out whether the odd number input by user is bigger than
        // default odd number. 
        if ( number >largestOddNum ) {
          
          largestOddNum = number;
        }
      }
    }
    
    // To make sure that user entered even number.
    if ( evenCount > 0 ) {

      System.out.println("Smallest even number entered was "
          + smallestEvenNum );
    }
    
    // To make sure that user entered odd number.
    if ( oddCount > 0 ) {
      System.out.println("Largest odd number entered was " + largestOddNum );
    }

    // To find out how many numbers that user entered.
    totalNum = oddCount + evenCount;
  
    // To track if there is no numbers has been entered.
    if (totalNum == 0 ) {

      System.out.println("No numbers entered");
    }
  }
} // The end of program.

