/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Oct 29, 2015
 * File:    PuzzlePiece.java
 * Source of help: cse11 homepage
 * Description: This is going to create border and also has many
 *              method that can call from the other class.
 *              Also, this class will construct image and place it
 *              into begining position.
 */


import objectdraw.*;
import java.awt.*;


/**
 * Description: This is going to create border and also has many
 *              method that can call from the other class. Also, this
 *              class will construct image and place it into begining
 *              position.
 */
public class BoardPiece implements Piece {

  // initialize the length for border
  private static final double SIDE_LENGTH = 100;
  private static final double INNER_BORDER = 98;

  // a value user to be divide  
  private static final int HALF = 2;

  // variable for half of center
  private static final int HALF_CENTER = 25;

  // create a new variable for visibleimage
  private VisibleImage image;

  // variable for id
  private int ID;

  // create a new variable for location
  private Location center;

  // create new variables for frame
  private FramedRect outerBorder;
  private FramedRect innerBorder;

  private DrawingCanvas canvas;

  /**
   * Description: This is default constructor for this class. This is class
   *              is going to create new image object and place it into
   *               begining position.
   * @param img: this represents image we get
   * @param id: this represents id of image
   * @param loc: this represents location of image
   */
  public BoardPiece ( Image img, int id, Location loc,
    DrawingCanvas canvas)  {

    this.canvas =  canvas;    

    // x and y coordinate for location
    double x = loc.getX();
    double y = loc.getY(); 

    // create a new image object
    image = new VisibleImage (img, loc, canvas);
  
    // assign id to ID
    ID = id;

    // get x and y coordinate for center of image
    double center_X = x + SIDE_LENGTH / HALF;
    double center_Y = y + SIDE_LENGTH / HALF;

    // loacation point of center
    center = new Location ( center_X,  center_Y);

    // create new borders
    outerBorder = new FramedRect (x, y, SIDE_LENGTH, SIDE_LENGTH, canvas);
    innerBorder = new FramedRect (x+1, y+1, INNER_BORDER, INNER_BORDER,
      canvas);    
    
    // hide inner border
    innerBorder.hide();

  }

  /**
   * Description: This method is going to show border highlight.
   * @param color: This represents color for border
   * return void
   */
  public void showHighlight( Color color) {

    // show outer border and set color for it
    outerBorder.show();
    outerBorder.setColor(color);

    // show inner border and set color for it
    innerBorder.show();
    innerBorder.setColor(color);  

  }

  /**
   * Description: This method is going to hide border highlight.
   */
  public void hideHighlight() {

    // hide borders
    outerBorder.hide();
    innerBorder.hide();

  }

 /**
  * Description: This method is going to show images or text
  *              message on canvas.
  */ 
  public void show() {

    // show image
    image.show();

  }

  /**
   * Description: This method is going to hide images or text
   *              message on canvas.
   */
  public void hide() {

    // hide image
    image.hide();

  }

  /**
   * Description: This method is going to check whether this point
   *              is contained.
   * @param point: This represents the point where user mouse at.
   * @return boolean: return true if it contains, otherwise false.
   */
  public boolean contains ( Location point) {

    // check if point if within board piece center by  50 pixel * 
    //  50 pixel square
    if ( point.getX() >= center.getX() - HALF_CENTER &&
      point.getX() <= center.getX() + HALF_CENTER &&
      point.getY() >= center.getY() - HALF_CENTER &&
      point.getY() <= center.getY() + HALF_CENTER) {

      // retrue ture if it contains
      return true;
    }

      return false;
    

  }

  /**
   * Description: This method is going to check whether id from
   *              both side of image are match.
   * @param o: Pass object as parameter 
   * @return boolean: return true if it equals, oterwise false.
   */
  public boolean  equals ( Object o) {

    // return true if both image's id are equal
    if ( this.getId() == ((Piece)o).getId() ) {

      return true;
    }
 
    else {

      // otherwise return false
      return false;

    }
  }

  /**
   * Description: This method is going to get a center of location.
   * @return Location: Return center location point.
   */
  public Location getCenter() {

    // return center location
    return center;

  }

  /**
   * Description: This method is going to get ID from image.
   * @param int: return id number.
   */
  public int getId() {

    // return image id number
    return ID;
  }

  /**
   * Description: This method is going to move image.
   * @param dx: x coordinate for image going to move
   * @param dy: y coordinate for image going to move.
   */
  public void move ( double dx, double dy) {

    // move image
    image.move(dx, dy);

    //outerBorder.move(dx, dy);

    // move center point
    center.translate(dx, dy);

  }

  /**
   * Description: This method is going to check whether point
   *              is inside of complege entire image.
   * @param point: This represents the point that user click.
   * @return boolean: return true if it contains, otherwise false.
   */
  public boolean boardContain ( Location point) {

    
    //  return true if it contains, otherwise fase.
    return image.contains(point);

  }

  /**
   * Description: This method is going to remove images from canvas.
   */
  public void removeFromCanvas() {

    // remove image from canvas
    image.removeFromCanvas();

  }
}

