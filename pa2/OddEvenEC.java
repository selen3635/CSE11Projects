/**
 * Name:   Xiaolong Zhou
 * Login:  CS11faug
 * Date:   Oct 02, 2015
 * File:   OddEvenEC.java
 * Source of help: Karan Lala, cseweb.ucsd.edu/~richo/cse11/PA2.pdf
 * Description: This programm will find out two distinct largest odd numbers
 *              and two distinct smallest even numbers base on the numbers
 *              that user input. 
 */

import java.util.Scanner;
import java.lang.Integer;

/**
 * Description: A programm that produces numbers based on user entered.
 *              It will out put two distinct largest odd numbers, and
 *              two distinct smallest even numbers.
 */
public class OddEvenEC {

  private final static int oddEvenDef = 2; /* use this variable to define
                                              odd or even number */

  /**
   * Description: This is main method for this program.Run as an application
   *              with user input.
   * @param args: This is argument that represent what user entered for
   *              program to excute.
   * return: Void
   */
  public static void main (String [] args) {
    
    // numbers that user input 
    int number;

    // counters for odd and even numbers
    int oddCount = 0;
    int evenCount = 0;

    int totalNum = 0;

    // initialize largest odd and second largest odd number
    int largestOddNum = Integer.MIN_VALUE;
    int secondLargestOdd = Integer.MIN_VALUE;

    // initialize smallest even and second smallest even number
    int smallestEvenNum = Integer.MAX_VALUE;
    int secondSmallestEven = Integer.MAX_VALUE;
    
    // print out instructions
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

        /* To find out whether the even number input by user is less than
           default smallest even number.*/
        if ( number < smallestEvenNum) {

          secondSmallestEven = smallestEvenNum;
          smallestEvenNum = number;
          
        }

        /* To check whether number less than second smallest even number and
           not equal to smallest even number */
        else if(number < secondSmallestEven && number != smallestEvenNum) {
          secondSmallestEven = number;
        }
        
      }
      
      // To find out whether this is a odd number.
      if ( number % oddEvenDef != 0) {
      
        // Keeping track of how many even numbeers have been encountered.
        oddCount++;
        
        /* To find out whether the odd number input by user is bigger than
           default largest odd number. */ 
        if ( number >largestOddNum ) {
          
          secondLargestOdd = largestOddNum;
          largestOddNum = number;
        }

        /* To check whether number bigger than second largest odd number
           and not equal to largest odd number.*/
        else if ( number > secondLargestOdd && number != largestOddNum) {
          secondLargestOdd = number;
        }
      }
    }

    // To make sure that user entered even number.
    if ( evenCount > 0 ) {

      System.out.println("Smallest distinct even number entered was "
          + smallestEvenNum );
    
    }

    // To check whether second smallest even equal to MAX VALUE.
    if ( secondSmallestEven != Integer.MAX_VALUE) {
      System.out.println("Second smallest distinct even number entered was "
          + secondSmallestEven);
    }
    
    // To make sure that user entered odd number.
    if ( oddCount > 0 ) {
      System.out.println("Largest distinct odd number entered was " + largestOddNum );
    }
    
    // To check whether second largest odd equal to MIN VALUE.
    if ( secondLargestOdd != Integer.MIN_VALUE ) {
      System.out.println("Second largest distinct odd number entered was "
          + secondLargestOdd);
    }

    // To find out how many numbers that user entered.
    totalNum = oddCount + evenCount;
  
    // To track if there is no numbers has been entered.
    if (totalNum == 0 ) {

      System.out.println("No numbers entered");
    }
  }
} // The end of program.

