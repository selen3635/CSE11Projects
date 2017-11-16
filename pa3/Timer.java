/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Oct 15, 2015
 * File:    Timer
 * Source of help: Text book
 * Description: This is a timer. User can user this class
 *              to reset time and get time.
 */

/**
 * Description: Class allowing calculations of timing between events.
 */
public class Timer {

  // time when Timer stated or reset.
  private double startTime;

  //Create timer, initiallizing startTime with current time
  public Timer() {

    startTime = System.currentTimeMillis(); 
  }

  // Return number of seconds since last reset
  public double elapsedMilliseconds() {

    return System.currentTimeMillis() - startTime;
  }

  // Return number of seconds since last reset
  public double elapsedSecond() {

    return this.elapsedMilliseconds() / 1000;
  }

  // Reset startTime
  public void reset () {

    startTime = System.currentTimeMillis() ;
  }
}
