/*
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    12/01/2015
 * File:    ARectangle.java
 * Source of Help:    cse11 homepage
 *       This is a super class for square and rectangle. It will be extend
 *  by other classes and utilized by other classes. This is going to set
 *  upperleft point and also move the object. This class also check 
 *  whether two object have same upperleft point.
 */


import java.awt.*;
import objectdraw.*;


/**
 *     This is a super class for square and rectangle. It will be extend
 *  by other classes and utilized by other classes. This is going to set
 *  upperlefft point and also move the object. This class also check 
 *  whether two objects have same upperleft point.
 */
public abstract class ARectangle extends Shape {

  // Upper left corner - Common to all rectangular shapes
  private Point upperLeft;

  /**
   *    This is default constructor for this class. It will set upperleft
   *  point to (0, 0).
   */
  public ARectangle() {

    // set upper left point
    this.setUpperLeft(new Point(0, 0));

  }

  /**
   *     This is overload constructor with three parameters.
   * @param name    this represents name of class
   * @param x       this represents x value for upperleft point
   * @param y       this represents y value for upperleft point
   */
  public ARectangle( String name, int x, int y) {

    // call super constructor to set name
    super(name);

    // set upperleft point
    this.setUpperLeft(new Point(x ,y));

  }

  /**
   *     This is overload constructor with two parameters
   * @param name    this represents name of class
   * @param upperleft    this represents upperleft point
   */
  public ARectangle( String name, Point upperLeft) {

    // call super constructor to set name
    super(name);

    // set upperleft point
    this.setUpperLeft(new Point(upperLeft));

  }

  /**
   *     This is overload constructor with one parameter
   * @param r     this represents reference of ARectangle
   */
  public ARectangle( ARectangle r ) {

    // set upperleft point
    this.setUpperLeft(new Point( r.getUpperLeft()));

  }

  /**
   *     This method is going to move the object.
   * @param xDelta    this represents x distance that object going to move
   * @param yDelta    this represents y distance that object going to move
   */
  public void move( int xDelta, int yDelta) {

    this.getUpperLeft().move(xDelta, yDelta);

  }

  /**
   *     This method is going to print some string values
   * @return  string  this will return a string value
   */
  @Override
  public String toString() {

    // return class name and upperleft point
    return "" + this.getClass().getName() + ": Upper Left Corner: " +
      upperLeft.toString();
  
  }

  /**
   *     This method is going to check whether both object has same type
   * @param o    this represents an object
   * @return  boolean    this will return a boolean value
   */
  @Override
  public boolean equals( Object o) {

    // std checks + upperLeft
    if ( o instanceof ARectangle) {

      // check upperleft point
      if( ((ARectangle)o).getUpperLeft() == this.getUpperLeft() ) {

        // return true if they are equal
        return true;

      }
    }
    //otherwise return false
    return false;

  }

  /**
   *     Compute the hash code for this object
   * @return int  return an integer
   */
  @Override
  public int hashCode() {

    return this.toString().hashCode();

  }

  /**
   *    This is a getter, and will return a point of upper left
   * @Point   return a point
   */
  public Point getUpperLeft() {

    // return upperleft point
    return this.upperLeft;

  }

  /**
   *     This is a setter, it will set upperleft point
   * @param p     this represents upperleft point
   */
  private void setUpperLeft( Point p) {

    // set upperleft point
    this.upperLeft = p;

  }


}
