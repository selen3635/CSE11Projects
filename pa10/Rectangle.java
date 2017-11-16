/*
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    12/01/2015
 * File:    Rectangle.java
 * Source of Help:  cse11 homepage
 *      This class is going to draw rectangle based on given width and
 * height and upperleft corner point. It will inheritant move method from
 * other class in order to move itself.
 */


import java.awt.*;
import objectdraw.*;


/**
 *     This class is going to draw rectangle based on given width and
 *  height and upperleft corner point. It will inheritant move method from
 * other class in order to move itself.
 */
public class Rectangle extends ARectangle {

  // instance variables for width and height
  private int width;
  private int height;

  // create new reference for filledrect and framedrect
  private FilledRect rectangle1;
  private FramedRect rectangle2;

  /**
   *     This is default constructor for this class. It will set both width
   * and height to 0.
   */
  public Rectangle() {

    // set width and height to 0
    this.setWidth(0);
    this.setHeight(0);

  }

  /**
   *    This is overload constructor with four parameters
   * @param x      this represents x value for upperleft point
   * @param y      this represents y value for upperleft point
   * @param width  this represents width for rectangle
   * @param height this represents height for rectangle
   */
  public Rectangle(int x, int y, int width, int height) {

    // call super constructor to set class name and upperleft point
    super("Rectangle", x, y);

    // set width and height
    this.setWidth(width);
    this.setHeight(height);


  }

  /**
   *    This is overload constructor with three parameters
   * @param upperleft     this represents upperleft point
   * @param width         this represents width of rectangle
   * @param height        this represents height of rectangle
   */
  public Rectangle(Point upperLeft, int width, int height) {

    // call super constructor to set class name and upperleft point
    super("Rectangle", upperLeft);

    // set width and height
    this.setWidth(width);
    this.setHeight(height);

  }

  /**
   *    This is overload constructor with one parameter
   * @param r     this represents reference of rectangle
   */
  public Rectangle( Rectangle r) {

    // call super constructor to set upperleft point
    super(r);

    // set width and height
    this.setWidth(r.getWidth());
    this.setHeight(r.getHeight());

  }

  /**
   *    This method is going to print string value for rectangle
   * It will print class name, widht and height value.
   * @return string   this will return a string value
   */
  @Override
  public String toString() {

    //return class name and width and height
    return super.toString() + " Width: " + this.getWidth() + " Height: "
      + this.getHeight();

  }

  /**
   *    This method is going to check wheteher both object has same type
   *  and value.
   * @param o    this represents an object
   * @return boolean   return a boolean value
   */
  @Override
  public boolean equals(Object o) {

    // check for type hierarchy equivalence
    if ( super.equals(o)) {

      // check width and height
      if( ((Rectangle)o).getWidth() == this.getWidth() &&
          ((Rectangle)o).getHeight() == this.getHeight() ) {

        // return true if they are euqal
        return true;
      }
    }

    // otherwise return false
    return false;
  
  }

  /**
   *    This method is going to draw a rectangle based on givene width,
   * height and upperleft point.
   * @param canvas    this represents canvas
   * @param c         this represents color
   * @param fill      this represents whether is filled
   */
  public void draw( DrawingCanvas canvas, Color c, boolean fill) {

    // check if it is filled
    if ( fill ) {

      // draw a filled rectangle
      rectangle1 = new FilledRect( this.getUpperLeft().getX(),
          this.getUpperLeft().getY(), this.getWidth(), this.getHeight(),
          canvas);

      //check color
      if ( c == null) {

        c = Color.BLACK;

        // set color
        rectangle1.setColor(c);
      }
      else
        // set color
        rectangle1.setColor(c);
    }

    else {

      // draw a frame rectangle
      rectangle2 = new FramedRect( this.getUpperLeft().getX(),
          this.getUpperLeft().getY(), this.getWidth(), this.getHeight(),
          canvas);

      // check color
      if ( c == null) {

        c = Color.BLACK;

        // set color
        rectangle2.setColor(c);
      }
      else
        // set color
        rectangle2.setColor(c);
 
    }

  }

  /**
   *     This is a getter, and will return a width
   * @return int   this will return a widht
   */
  public int getWidth() {

    // return a width
    return this.width;

  }

  /**
   *    This is a setter, it will set width for rectangle
   * @param w    this represents width of rectangle
   */
  private void setWidth(int w) {

    // set width
    this.width = w;

  }
 
  /**
   *    This is a getter, it will return a height
   * @return int   this will return a height
   */
  public int getHeight() {

    // return a height
    return this.height;

  }

  /**
   *    This is a setter, it will set height for rectangle
   * @param h    this represents height of rectangle
   */
  private void setHeight(int h) {

    // set height
    this.height = h;

  }
}
