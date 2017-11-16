/*
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    12/01/2015
 * File:    Circle.java
 * Sources of Help:  cse11 home page
 *      This class is going to draw circle based on given center and
 *  radius. It also has move method which allows to move circle.
 */


import java.awt.*;
import objectdraw.*;

/**
 *    This class is going to draw circle based on given center and
 * radius. It also has move method which allows to move circle.
 */
public class Circle extends Shape {

  // instance variable for center and radius
  private Point center;
  private int radius;

  // create new reference for oval
  private FilledOval circle;
  private FramedOval circle2;

  /**
   *   This is default constructor for this class. It will set center
   * point to (0, 0) and radius to 0.
   */
  public Circle() {

    // set center and radius
    this.setCenter(new Point(0 , 0));
    this.setRadius(0);

  }

  /**
   *   This is overload constructor with two parameter( center, radius)
   * @param center   this represents center of circle
   * @param radius   this represents radius of circle
   */
  public Circle(Point center, int radius) {

    // set center and radius , deep copy
    this.setCenter(new Point(center));
    this.setRadius(radius);

  }

  /**
   *   This is overload constructor with one parameter
   * @param c  this represents an object
   */
  public Circle( Circle c) {

    // set center and radius , deep copy
    this.setCenter( new Point(c.getCenter()));
    this.setRadius(c.getRadius());

  }

  /**
   *     This method is going to move circle with given x and y distance
   * @param xDelta   this represents x distance going to move
   * @param yDelta   this represents y distance going to move
   */
  public void move( int xDelta, int yDelta) {

    // move center of circle 
    this.getCenter().move(xDelta, yDelta);
   
  }

  /**
   *     This method is going to print string value for circle.
   * It will print class name and center value and radius of circle.
   * @return string   this will return a string value
   */
  @Override
  public String toString() {

    // return class name and center and radius value
    return "" + this.getClass().getName() + ": Center: "
      + center.toString() + "; Radius: " + this.getRadius();

  }

  /**
   *      This method is going to check whether both object has same type,
   * @param o   this represents an object
   * @return boolean  return a boolean value
   */
  @Override
  public boolean equals( Object o) {

    // check for type hierarchy equivalence
    if ( o instanceof Circle ) {

      // check x and y and radius value
      if( ((Circle)o).getCenter().getX() == this.getCenter().getX() &&
          ((Circle)o).getCenter().getY() == this.getCenter().getY() &&
          (((Circle)o).getRadius()) == this.getRadius()) {

        //return true if they are equal
        return true;

      }

    }
 
    // otherwise return false
    return false;

  }

  /**
   *    Compute the hash code for this object
   * @return int   return an integer
   */
  @Override
  public int hashCode() {

    return this.toString().hashCode();

  }

  /**
   *   This method is going to draw a circle based on the value passed in
   * @param canvas   this represents canvas
   * @param c        this represents color
   * @param fill     this represents whether is filled
   */
  public void draw( DrawingCanvas canvas, Color c, boolean fill) {

    // check if it is filled
    if ( fill ) {

      // draw a filled circle
      circle = new FilledOval( center.getX() - radius, 
          center.getY() - radius, 
          radius + radius, radius + radius, canvas);

      // set color
      circle.setColor(c);     
    }

    else {

    // draw a frame circle
      circle2 = new FramedOval( center.getX() - radius, 
          center.getY() - radius,  
          radius + radius, radius + radius, canvas);

      // set color
      circle2.setColor(c);
    }  

  }

  /**
   *    This is a getter, it will get center point
   * @return point   this will return a point
   */
  public Point getCenter() {

    // return center point
    return this.center;

  }

  /**
   *     This is a setter, it will set center value
   * @param center   this represents center points
   */
  private void setCenter( Point center) {

    // set center
    this.center = center;

  }

  /**
   *     This is a getter, it will get radius
   * @return int   this will return radius
   */
  public int getRadius() {

    // return radius
    return this.radius;

  }

  /**
   *    This is a setter, it will set radius 
   * @param radius  this represent radius 
   */
  private void setRadius( int radius) {

    // set radius
    this.radius = radius;

  }

}
