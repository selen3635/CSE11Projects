/*
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    12/01/2015
 * File:    Shape.java
 * Sources of Help:   cse11 home page
 *    This class is a super class. It will be extend by other classes
    and utilize by other classes. This class is going to get name and
 *  set name for object. It will check whether equal method override
 *  correctly. It also include two abstract method, which is move and
 *  draw method.
 */


import java.awt.*;
import objectdraw.*;

/**
 *    This class is a super class. It will be extend by other classes
 * and utilize by other classes. This class is going to get name and 
 * set name for object. It will check whether equal method been override
 * correctly. It also include two abstract method, which is move and
 * draw method.
 */
public abstract class Shape {

  // instance variable
  private String name;

  /**
   *   This is default constructor for this class. It will set default
   * name to Shape.
   */
  public Shape() {

    // set default name to Shape.
    this.setName("Shape");  

  }

  /**
   *     This is overload constructor with a string parameter.
   * @param name    This represent the name of class
   */
  public Shape(String name) {

    // set name
    this.setName(name);

  }

  /**
   *    This is a getter. This will get name.
   * @return string  This will return a string value
   */
  public String getName() {

    // return name
    return this.name;

  }

  /**
   *    This is a setter. This will set name.
   * @param name   This represents name of class.
   */
  private void setName(String name) {

    // set name
    this.name = name;

  }

  /* Copy this as is in your Shape.java */

  /**
   *    This method is going to check whether user override equal 
   * method correctly.
   * @param o   This represents an object
   * @return  boolean   This will return a boolean value
   */
  @Override
  public boolean equals( Object o) {

    String s = "\n***************************************************"
     + "*******\n" + "This should never print. If it does print, then\n"
     + "you did not override equals() properly in class "
     + this.getClass().getName() + "\n"
     + "**********************************************************\n";

    //print s
    System.out.println(s);

    // return ture if they are equal
    return this == o;

  }

  /**
   *   This is abstract method, it will be specified in subclasses.
   * @param xDelta  This represents x distance going to move
   * @param yDelta  This represents y distanec going to move
   */
  public abstract void move( int xDelta, int yDelta);

  /**
   *   This is abstract method, it will be specified in subclasses.
   * @param canvas    This represents canvas
   * @param c         This represents color
   * @param fill      This represents whether it is filled.
   */
  public abstract void draw( DrawingCanvas canvas, Color c, boolean fill);

}
