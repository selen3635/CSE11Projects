/*
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    12/01/2015
 * File:    CSE11_Line.java
 * Sources of Help:  cse11 homepage
 *    This class is going to draw line based on given points. This also
 *  has move method which allows to move the line
 */


import java.awt.*;
import objectdraw.*;


/**
 *   This class is going to draw line based on given points. This also
 *  has move method which allows to move the line
 */
public class CSE11_Line extends Shape {

  // instance variales for start and end point
  private Point start;
  private Point end;

  // create new reference for line
  private Line line1;
  private Line line2;


  /**
   *     This is default constructor for this class. It will set start
   * and end point to (0, 0).
   */
  public CSE11_Line() {

    // set start and end point
    this.setStart(new Point(0, 0));
    this.setEnd(new Point(0, 0));

  }

  /**
   *   This is overload constructor with two parameters
   * @param start    this represents start point for line
   * @param end      this represents end point for line
   */
  public CSE11_Line( Point start, Point end) {

    // set start and end point
    this.setStart(new Point(start));
    this.setEnd(new Point(end));

  }

  /**
   *    This is overload constructor with one parameter
   * @param line   this represents reference line
   */
  public CSE11_Line( CSE11_Line line) {

    // set start and end point
    this.setStart(new Point( line.getStart()));
    this.setEnd(new Point( line.getEnd()));

  }

  /**
   *   This method is going to move line with x and y distance
   * @param xDelta     this represens x distance that will move
   * @param yDelata    this represents y distance that will move
   */
  public void move( int xDelta, int yDelta) {

    // move start and end points
    this.getStart().move(xDelta, yDelta);
    this.getEnd().move(xDelta, yDelta);

  }

  /**
   *    This medthod is going to print string value for line
   * @return string   this will return a string value
   */
  @Override
  public String toString() {

    // return class name and start and end point value
    return "" + this.getClass().getName() + ": " + 
      this.getStart().toString() + " to  " + this.getEnd().toString();

  }

  /**
   *    This method is going to check whether both object has same type
   * and value.
   * @param o    this represents an object
   * @return boolean    return a boolean value
   */
  @Override 
  public boolean equals( Object o) {

    // check for type hierarchy equivalence
    if ( o instanceof CSE11_Line) {

      // check start and end points
      if( ((CSE11_Line)o).getStart().equals(this.getStart()) &&
          ((CSE11_Line)o).getEnd().equals(this.getEnd())) {

        // return true if they are equal
        return true;
      }
    }

    // otherwise return false
    return false;

  }

  /**
   *    Computer the hash code for this object
   * @return in   return an integer
   */
  @Override
  public int hashCode() {

    return this.toString().hashCode();

  }

  /**
   *   This method is going to draw a line based on given start and
   * end point.
   * @param canvas   this represents canvas
   * @param c        this represents color 
   * @param fill     this represents whether is filled
   */
  public void draw( DrawingCanvas canvas, Color c, boolean fill) {

    // check if it is filled
    if ( fill) {

      // draw a line
      line1 = new Line(this.getStart().getX(), this.getStart().getY(),
          this.getEnd().getX(), this.getEnd().getY(), canvas );

      //check color
      if ( c == null) {

        c = Color.BLACK;

        // set color
        line1.setColor(c);
      }
      else
        line1.setColor(c);
    }

    else {

      // draw a line
      line2 = new Line(this.getStart().getX(), this.getStart().getY(),
          this.getEnd().getX(), this.getEnd().getY(), canvas);

      // check color
      if ( c == null) {

        c = Color.BLACK;

        // set color
        line2.setColor(c);
      }
      else
        line2.setColor(c);
 
    }

  }

  /**
   *   This is a getter, will return a point
   * @return point    return a point
   */
  public Point getStart() {

    return this.start;

  }

  /**
   *   This is a setter, will set point 
   * @param p   this represents point
   */
  private void setStart( Point p) {

    this.start = p;

  }

  /**
   *   This is a getter, will return a point
   * @return point    return a point
   */
  public Point getEnd() {

    return this.end;

  }

  /**
   *   This is a setter, will set point 
   * @param p   this represents point
   */
  private void setEnd(Point p) {

    this.end = p;

  }


}
