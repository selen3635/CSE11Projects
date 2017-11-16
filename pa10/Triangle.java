/*
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    12/01/2015
 * File:    Triangle.java
 * Sources of Help:  cse11 home page
 *       This class is going to draw triangle based on given points.
 * It also has move method which allows to move triangle.
 */


import java.awt.*;
import objectdraw.*;


/**
 *       This class is going to draw triangle based on given points.
 * It also has move method which allows to move triangle.
 */
public class Triangle extends Shape  {

  // instance variables for points
  private Point p1;
  private Point p2;
  private Point p3;

  // create new reference for line
  private Line line1;
  private Line line2;
  private Line line3;

  /**
   *    This is default constructor, It will set all point to (0 , 0).
   */
  public Triangle() {

    //set points to (0, 0).
    this.setP1(new Point(0, 0));
    this.setP2(new Point(0 ,0));
    this.setP3(new Point(0, 0));


  }

  /**
   *    This is overload constructor with three parameters
   * @param p1    this represents point 1
   * @param p2    this represents point 2
   * @param p3    this represents point 3
   */
  public Triangle( Point p1, Point p2, Point p3) {

    // set points
    this.setP1(new Point(p1));
    this.setP2(new Point(p2));
    this.setP3(new Point(p3));

  }

  /**
   *    This is overload constructor with one parameter
   * @param tri   this represents reference triangle
   */
  public Triangle( Triangle tri) {

    // set points
    this.setP1(new Point(tri.getP1()));
    this.setP2(new Point(tri.getP2()));
    this.setP3(new Point(tri.getP3()));

  }

  /**
   *   This method is going to move triangle with given x and y distance
   * @param xDelta    this represents x distance will move
   * @param yDelta    this represents y distance will move
   */
  public void move( int xDelta, int yDelta) {

    // move point
    this.getP3().move(xDelta, yDelta);

  }

  /**
   *   This method is going to print string value for triangle
   * @return string   this will return a string value
   */
  @Override
  public String toString() {

    // return class name and points value
    return "" + this.getClass().getName() + ": " + 
      this.getP1().toString()+ ", " + this.getP2().toString() + ", " + 
      this.getP3().toString();

  }

  /**
   *   This method is going to check whether both object has same type
   * and value.
   * @param o    this represents an object
   * @return boolean  this will return a boolean value
   */
  @Override
  public boolean equals( Object o ) {

    //check for type hierarchy equivalence
    if ( o instanceof Triangle) {

      // check points
      if( ((Triangle)o).getP1().equals(this.getP1()) &&
          ((Triangle)o).getP2().equals(this.getP2()) &&
          ((Triangle)o).getP3().equals(this.getP3())) {

        return true;
      }
    }

    return false;

  }

  /**
   *   compute the hash code for this object
   * @return in    return an integer
   */
  @Override
  public int hashCode() { 

    return this.toString().hashCode();

  }

  /**
   *   This method is going to draw triangle based on given points
   * @param canvas   this represents canvas
   * @param c        this represents color
   * @param fill     this represent whether is filled
   */
  public void draw( DrawingCanvas canvas, Color c, boolean fill) { 

    // check if it is filled
    if ( fill) {

      //draw lines
      line1 = new Line(this.getP1().getX(), this.getP1().getY(),
          this.getP2().getX(), this.getP2().getY(), canvas);

      line2 = new Line(this.getP1().getX(), this.getP1().getY(),
          this.getP3().getX(), this.getP3().getY(), canvas);

      line3 = new Line(this.getP2().getX(), this.getP2().getY(),
          this.getP3().getX(), this.getP3().getY(), canvas);

      // check color
      if( c == null ) {

        c = Color.BLACK;

        // set color
        line1.setColor(c);
        line2.setColor(c);
        line3.setColor(c);
      }
      else {

        // set color
        line1.setColor(c);
        line2.setColor(c);
        line3.setColor(c);
      }

    }

  }

  /**
   *   This is a getter will return a point
   * @return point   return a point
   */
  public Point getP1() {

    return this.p1;

  }

  /**
   *   This is a setter will set point
   * @param p1   this represents point
   */
  private void setP1( Point p1) {

    this.p1 = p1;

  }

  /**
   *   This is a getter will return a point
   * @return point   return a point
   */
  public Point getP2() {

    return this.p2;

  }

   /**
    *   This is a setter will set point
    * @param p1   this represents point
    */
   private void setP2(Point p2) {

    this.p2 = p2;

  }

   /**
    *   This is a getter will return a point
    * @return point   return a point
    */
   public Point getP3() {

    return this.p3;

  }

  /**
   *   This is a setter will set point
   * @param p1   this represents point
   */
  private void setP3(Point p3) {

    this.p3 = p3;

  }
}

