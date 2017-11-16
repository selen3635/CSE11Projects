/*
 * Name:   Xiaolong Zhou
 * Login:  cs11faug
 * Date:   11/24/2015
 * File:   EC_TestRational.java
 * Sources of Help: cse 11 home page
 * Description: TODO
 */


/*
 * Extra Credit Tester for class Rational
 */
public class EC_TestRational
{

  private static final int MAXTESTS = 15;
  private static in failedTests = 0;
  /*
   * Test Driver
   */
  public static void main( String[] args )
  {
    System.out.println("\nRunning Extra Credit TestRational...");

    // Extra Credit
    testReciprocal();
    testFloor();
    testCeiling();

    System.out.println("\n...Finished.");

    // results calculation
    System.out.println("RESULT: Total # of Tests Passed: " + (MAXTESTS -
          failedTests) + "/40" );

    if ( failedTest != 0) 

      System.out.println(" -- FIX ME!!!  -. -||| ");

    else
      System.out.println(" -- Yeah!!!!! ~.~ ");
  }

  /*
   * EXTRA CREDIT - Tests for reciprocal()
   */
  private static void testReciprocal()
  {
    System.out.println("\n===== Testing reciprocal() method =====");

    // Test 1
    System.out.println("  Test 1:   ");

    try
    {
      Rational r1 = new Rational(1, 0);
 
      System.out.print("FAIL");
      failedTests++;
    }
    catch(IllegalArgumentException e)
    {
      System.out.println("PASS");
    }
    catch(Exception e) 
    {
      System.out.println("EXCEPTION");
      failedTests++;
    } 

    // Test 2
    System.out.print("   Test 2:   ");
    
    try
    {
      Rational r2 = new Rational(1, 1);
      Rational r2res = r2.reciprocal();

      if( r2res.toString().equals("1"))
        
        System.out.println("PASS");
      
      else
      {
        System.out.println("FAIL");
        failedTests++;
      }

      catch(Exception e) 
      {
        System.out.println("EXCEPTION");
        failedTests++;

  }



  /*
   * EXTRA CREDIT - Tests for floor()
   */
  private static void testFloor()
  {
    System.out.println("\n===== Testing    floor()   method =====");

    //TODO: Replace line below with your own tests
    System.out.println("EXTRA CREDIT - floor() not yet implemented");
  }



  /*
   * EXTRA CREDIT - Tests for ceiling()
   */
  private static void testCeiling()
  {
    System.out.println("\n===== Testing   ceiling()  method =====");

    //TODO: Replace line below with your own tests
    System.out.println("EXTRA CREDIT - ceiling() not yet implemented");
  }
  
}

