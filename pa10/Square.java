/*
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    12/01/2015
 * File:    Square.java
 * Source of Help:   cse11 home page
 *      This class is going to draw square based on given side and 
 *  upperleft corner point. It also has move method which allows to move
 *  square.
 */


import java.awt.*;
import objectdraw.*;

/**
 *     This class is going to  draw square based on given side and 
 *  upperleft corner point. It also has move method which allows to 
 *  move square.
 */
public class Square extends ARectangle {

  // instance variable for side
  private int side;

  // create new reference for filledrect and framedrect
  private FilledRect square1;
  private FramedRect square2;

  /**
   *   This is default constructor for this class. It will set side to 0
   */
  public Square() {

    // set side to 0
    this.setSide(0);

  }

  /**
   *    This is overload constructor with three parameters
   * @param x     this represents x value for upperleft point
   * @param y     this represents y value for upperleft point
   * @param side  this represents side for square
   */
  public Square( int x, int y, int side) {

    // call super constructor to set name and upperleft point
    super("Square", x, y);

    //set side
    this.setSide(side);

  }

  /**
   *     This is overload constructor with two parameters
   * @param upperleft   this represents upperleft point for square
   * @param side        this represents side for square
   */
  public Square( Point upperLeft, int side) {

    // call super constructor to set name and upperleft point
    super("Square", upperLeft);

    // set side
    this.setSide(side);

  }

  /**
   *      This is overload constructor with one parameter
   * @param r     this represents reference of square
   */
  public Square( Square r)  {

    // call super constructor to set upperleft point
    super(r);

    // set side
    this.setSide(r.getSide());

  }

  /**
   *     This method is going to print string value for square.
   * It will print class name, side and side value.
   * @return string   this will return a string value
   */
  @Override
  public String toString() {

    // return class name and side value
    return super.toString() + " Side: " + this.getSide();

  }

  /**
   *      This method is going to check whether both object has same type
   * @param o    this represents an object
   * @return boolean   return a boolean value
   */
  @Override
  public boolean equals( Object o) {

    // check for type hierarchy equivalence
    if ( super.equals(o)) {

      // check side
      if ( ((Square)o).getSide() == this.getSide()) {

        //return true if they are equal
        return true;
      }
    }

    // otherwise return false
    return false;

  }

  /**
   *     This method is going to draw a square based on given side and
   *  upperleft point
   * @param canvas    this represents canvas
   * @param c         this represents color
   * @param fill      this represents whether is filled
   */
  public void draw( DrawingCanvas canvas, Color c, boolean fill) {

    // check if it is filled
    if (fill) {

      // draw a filled square
      square1 = new FilledRect( this.getUpperLeft().getX(),
          this.getUpperLeft().getY(), this.getSide(), this.getSide(),
          canvas);

      // check color
      if ( c == null ) {

        c = Color.BLACK;

        // set color
        square1.setColor(c);
      }
      else
        //set color
        square1.setColor(c);
    }
    else {

      // draw a frame square
      square2 = new FramedRect( this.getUpperLeft().getX(),
          this.getUpperLeft().getY(), this.getSide(), this.getSide(),
          canvas);

      //check color
      if ( c == null ) {

        c = Color.BLACK;

        // set color
        square2.setColor(c);
  
      }
      else
        //set color
        square2.setColor(c);
    }

  }

  /**
   *     This is a getter, and will return a side
   * @return int    this will return a side
   */
  public int getSide() {

    // return a side
    return this.side;

  }

  /**
   *    This is a setter, it will set side for square
   * @param side    this represents side of square
   */
  private void setSide( int side) { 

    // set side
    this.side = side;

  }

}
