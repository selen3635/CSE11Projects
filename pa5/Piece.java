/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Oct 29, 2015
 * File:    Piece.java
 * Source of help: cse11 homepage
 * Description: This is an inferface for program, which contain
 *              method that the other class can define it and use
 *              it after implement this inferface.
 */



import objectdraw.*;

/**
 * Description: This is an inferface for program, which contain
 *               methods prototype.
 */
public interface Piece extends Highlightable, Hideable {

  /**
   * Description: This method is going to check whether this point
   *              is contained.
   * @param point: This represents the point where user mouse at.
   * @return boolean: return true if it contains, otherwise false.
   */
  public abstract boolean contains( Location point);

  /**
   * Description: This method is going to check whether id from
   *              both side of image are match.
   * @param o: Pass object as parameter 
   * @return boolean: return true if it equals, oterwise false.
   */
  public abstract boolean equals( Object o);

  /**
   * Description: This method is going to get a center of location.
   * @return Location: Return center location point.
   */
  public abstract Location getCenter();

  /**
   * Description: This method is going to get ID from image.
   * @param int: return id number.
   */
  public abstract int getId();

  /**
   * Description: This method is going to move image.
   * @param dx: x coordinate for image going to move
   * @param dy: y coordinate for image going to move.
   */
  public abstract void move( double dx, double dy);

}
