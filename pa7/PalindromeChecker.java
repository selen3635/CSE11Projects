/**
 * Name:   Xiaolong ZHou
 * Login:  cs11faug
 * Date:   11/06/2015
 * File:   PalindromeChecker.java
 * Source of help: cse11 home page, karan lala , nathan
 *     This program is design to check whatever that user entered is
 * considered as palindrome or not. User is allowed to enter characters,
 * numbers, symbols or even combination of all. Program will automatically
 * eliminate all symbols to figure out whether the things that
 * user entered is palindrome or not. If it is palindrome, then will output
 * whatever user input and " is a palindrome". Otherwise will output
 * whatever user input with " is not a palindrome."
 *
 */

import java.lang.Object.*;

/**
 *   This class is going to eliminate all symbols to generate
 * a new string. Then through some algrithm from this class to figure out
 * whether this input is palindrome. Basically, user is allowed to entered
 * anything they want include symbols, numbers and punctuations. 
 */
public class PalindromeChecker {

  /**
   *    This method is going to generate a new string by eliminating all
   * symbols and punctuations. The new only has characters and numbers.
   * @param phrase  This represents things that user entered.
   * @return string   return a new string value
   */  
  public static String filter ( String phrase ) {
    
    // local string variable
    String newString = "" ;

    // a loop to check each element of phrase whether is letter or digit
    for ( int i = 0; i < phrase.length(); i++ ) {

      // check index i element whether is letter or digit
      if ( Character.isLetterOrDigit( phrase.charAt(i))) {

        // if index of element is letter or digit then store it
        newString = newString + phrase.charAt(i);
      }  
    }
    
    // convert all character of that string to upper case
    newString =  newString.toUpperCase();

    // return new string value
    return newString;
 
  }

  /**
   *     This method is going to compare the characters at the low and
   * high index values and recurse on the remaining middle characters of
   * the string until it reaches base case. This is call the recursive.
   * @param phrase  This represents the string value after user filter
   * @param low   This represents lowest index of that string value
   * @param high  This represents highest index of that string value
   * @return boolean  return true if it is palindrome otherwise false
   */
  public static boolean isPalindrome ( String phrase, int low, int high) {

    // check whether length of string less than 2   
    if ( phrase.length() == 1 || phrase.length () == 0 ) {

      return true;
    }   

    // check whether lowest index of string same to highest index of
    // string value
    else if ( phrase.charAt(low) == phrase.charAt(high) ){

      low ++;
      high --;

      //  make sure all value of parameter is within length of string
      if ( high >= 0 && low <= phrase.length() ) {

        // call method again
        isPalindrome ( phrase, low, high); 
      }

      return true;  

    }

    // it its not equal, return false
    else {
    
      return false;

    }

  }

  /**
   *    This method is going to compare first and last character of 
   * string value. If first and last are same then generate a new string
   * withou these to characters and consider first and last character of
   * new string again. Keep check until 1 or 0 string left.
   * @param phrase  This represent the string value after user filter.
   * @return boolean  Return true if it is palindrome otherwise false.
   */  
  public static boolean isPalindrome ( String phrase ) {

    // check if length of string less than 2
    if ( phrase.length() < 2) {

      return true;    
    }

    // check first and last character of string whether same
    else if ( phrase.charAt(0) == phrase.charAt(phrase.length() -1 )) {
     
      // local variables to check new string
      int low = 1;
      int high = phrase.length() - 1;

      // To check high index number of string is greater or equal to 0.
      if ( high >= 0 ) {

        // generate a new substring 
        phrase = phrase.substring ( low, high);
         
        // call this method again      
        isPalindrome ( phrase);
      
      }
      return true;
      
    } 

    // if they are not same then return false
    else {

      return false;

    }

  }

}
