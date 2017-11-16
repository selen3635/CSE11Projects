/*
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    12/01/2015
 * File:    Point.java
 * Sources of Help:  cse11 home page
 *    This class is going to set and get point, utilized by other
 * classes in order to draw stuffs. It has move method to move points
 * so that we can move object if we need.
 */


/**
 *    This class is going to set and get point, and utilized by other
 * classes in order to draw stuffs. It has move method to move points
 * so that we can move object if we need.
 */ 
public class Point {

  // instance variables for point
  private int x;
  private int y;

  /**
   *    This is default constructor for this class. It will set default
   * point to (0, 0);
   */
  public Point() { 

    // set point
    this.setX(0);
    this.setY(0);

  }

  /**
   *    This is overload constructor with two integer parameters
   * @param x    This represents x axis for point
   * @param y    This represents y axis for point
   */
  public Point( int x, int y) { 

    // set point
    this.setX(x);
    this.setY(y);

  }

  /**
   *    This is overload constructor with one Point parameter
   * @param point   This represents point
   */
  public Point( Point point ) { 

    // set point ,  deep copy
    this.setX( point.getX());
    this.setY( point.getY());

  }

  /**
   *   This is getter, it will get x value for point
   * @return int    it will return an integer value
   */
  public int getX() { 

    // return x value
    return this.x;

  }

  /**
   *   This is a getter, it will get y value for point
   * @return int    it will return an integer value
   */
  public int getY() { 

    //return y value
    return this.y;

  }

  /**
   *    This is a setter, it will set x value for point
   * @param x   this represents x value for point
   */
  private void setX( int x ) { 

    // set x
    this.x = x; 

  }

  /**
   *    This is setter, it will set y value for point
   * @param y   this represents x value for point
   */
  private void setY( int y) {

    //set y
    this.y = y;

  }

  /**
   *   This method is going to move point with given x and y distance.
   * @param xDelta   this represents x distance going to move
   * @param yDelta   this represents y distance going to move
   */
  public void move ( int xDelta, int yDelta) {

    // local variables
    int x, y;

    // find out distance that point going to move
    x = this.getX() + xDelta;
    y = this.getY() + yDelta;

    // set new x and new y
    this.setX(x);
    this.setY(y);

  }

  /**
   *    This method is going to print string value for point.
   * It will print class name, and the value of point.
   * @return string   it will return a string value
   */
  @Override
  public String toString() {

    // return class name and value of point
    return "" + this.getClass().getName() + ": (" + this.getX() 
      + "," + this.getY() + ")";

  }

  /**
   *    This method is going to check whether both object has same type,
   * and whether their value same.
   * @param o  this represent an object
   * @return boolean    this will return a boolean value
   */
  @Override
  public boolean equals( Object o) {

    // check for type hierarchy equivalence
    if( o instanceof Point ) {

      // check x and y value 
      if (((Point)o).getX() == this.getX() &&
          ((Point)o).getY() == this.getY() ) {

        // return true if they are equal
        return true;
      }

    }

    // otherwise return false
    return false;

  }

  /**
   *   Computer the hash code for this object
   * @return int   return an integer
   */
  @Override 
  public int hashCode() { 

    return this.toString().hashCode();

  }


}


