/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date: October 09, 2015
 * File: DraggingMickey
 * Sources of help: piazza, cse 11 home page, Connor Smith.
 * Description: A program that produces an animation of mickey.
 *              The mickey will appear while user click the mouse, if
 *              user press on mickey, it turns color and can be drag
 *              within window. When user release the mouse, mickey will
 *              turn color back to begining one.
 */

import objectdraw.*;
import java.awt.*;
import Acme.*;

/**
 * Description: This class is going to draw a mickey on a designed window,
 *              user can simply click mouse to draw a mickey, then press
 *              mickey and drag anywhere within window.
 */ 

public class DraggingMickey extends WindowController {

  // here is the location of instructions will display.
  private static final int INSTR1_X = 50;
  private static final int INSTR1_Y = 50;
  private static final int INSTR2_X = INSTR1_X;
  private static final int INSTR2_Y = INSTR1_Y +20;

  // Radius for mickey face and ear2
  private static final int FACE_RADIUS = 50;
  private static final int EAR_RADIUS = 30;

  // Center of each ear is this
  // offset up and over (x and y) from center of face.
  private static final int EAR_OFFSET = 50; 

  // The width and height for frame.
  private static final int FRAME_WIDTH = 750;
  private static final int FRAME_HEIGHT = 750;

  // Circles for mickey face and ears.
  private FilledOval mickeyFace;
  private FilledOval mickeyEar1;
  private FilledOval mickeyEar2;

  // Colors for mickey
  private static final Color MICKEYCOLOR1 = Color.RED;
  private static final Color MICKEYCOLOR2 = Color.BLACK;

  // A flag to check if user press on mickey
  private boolean mickeyGrabbedFace;
  private boolean mickeyGrabbedEar1;
  private boolean mickeyGrabbedEar2;

  // check if there is mickey
  private boolean mickeyCreate = false;

  // new text for instructions
  private Text instructions1;
  private Text instructions2;

 // point where mouse was last seen
  private Location lastPoint;

  /**
   * Description: This is main method for program, run as an
   *              application with Acme.MainFrame.
   * @param: FRAME_WIDTH: this arguement that represent
   *         the width for screen which we will create.
   * @param: FRAME_HEIGHT: this argument that represent the width for screen
   *                       which we will create.
   * return: Void
   */

  public static void main ( String [] args ) {
    new Acme.MainFrame( new DraggingMickey(), args, FRAME_WIDTH,
                        FRAME_HEIGHT);
  }

  /**
   * Description: Place some brief instructions on the screen.
   * return: void
   */

  public void begin () {

    // create instructions
    instructions1 = new Text ("Click to display a Mickey silhouette centered"
      +" at the mouse click.", INSTR1_X, INSTR1_Y, canvas);
    instructions2 = new Text ("Mouse press in any part of the image and drag"
      +" to move image around.", INSTR2_X, INSTR2_Y, canvas);

    instructions1.hide();
    instructions2.hide();

  }

  /**
   * Description: Display the instructions on screen.
   * @param: e, this represents location within the screen.
   * return: void
   */

  public void onMouseEnter(Location e) {

    // show the instructions
    instructions1.show();
    instructions2.show();
  }

  /**
   * Description: Disappear the mickey on the screen.
   * @param: e, this represents location within the screen.
   * return: void
   */
   
  public void onMouseExit(Location e) {
    
    //check if there is mickey.
    if (mickeyCreate ==true ) {

    // hide mickey face, and ears.
    mickeyFace.hide();
    mickeyEar1.hide();
    mickeyEar2.hide();
    mickeyCreate = false;
   
    } 
  }  
  
  /**
   * Description: Display the mickey on screen and hide instructions.
   * @param: point, this represents location where mouse last on screen.
   * return: void
   */
   
  public void onMouseClick (Location point) {
     
    //hide instructions
    instructions1.hide();
    instructions2.hide();

    lastPoint = point;
    double mouse_X = point.getX();
    double mouse_Y = point.getY();
    
    //check if there is mickey.
    if (mickeyCreate == false ) {
      
      //create a new mickey face
      mickeyFace  = new FilledOval ( mouse_X - FACE_RADIUS, mouse_Y - 
        FACE_RADIUS, FACE_RADIUS + FACE_RADIUS, FACE_RADIUS + FACE_RADIUS,
        canvas); 

      //create a new mickey ear
      mickeyEar1 = new FilledOval( mouse_X - FACE_RADIUS - EAR_RADIUS, mouse_Y
        - FACE_RADIUS - EAR_RADIUS, EAR_RADIUS + EAR_RADIUS, EAR_RADIUS 
        + EAR_RADIUS, canvas);
  
      // create a new mickey ear
      mickeyEar2 = new FilledOval( mouse_X - EAR_RADIUS +
        EAR_OFFSET, mouse_Y - FACE_RADIUS - EAR_RADIUS, EAR_RADIUS +
        EAR_RADIUS, EAR_RADIUS + EAR_RADIUS, canvas);
      mickeyCreate = true;
    }
  
  }

  /**
   * Description: To check if user press on mickey.
   * @param: point, this represents location where mouse last on screen.
   * return: void
   */

  public void onMousePress (Location point) {

    lastPoint = point;

    //check if there is mickey.
    if (mickeyCreate == true ) {
    
    //check to see if mouse is inside of mickey.
    mickeyGrabbedFace = mickeyFace.contains(point);
    mickeyGrabbedEar1 = mickeyEar1.contains(point);
    mickeyGrabbedEar2 = mickeyEar2.contains(point);

    }
  }
  
  /**
   * Description: This method is to drag mickey within the screen.
   *              Also it will turn mickey's color to red.
   * @param: point, this represents location where mouse last on screen.
   * return: void
   */
   
  public void onMouseDrag (Location point) {

    //check if user click on mickey before dragging mickey.
    if (mickeyGrabbedFace || mickeyGrabbedEar1 || mickeyGrabbedEar2) {

      double xMoveDistance = point.getX() - lastPoint.getX();
      double yMoveDistance = point.getY() - lastPoint.getY();
      
      //change mickey's color to red when dragging.
      mickeyFace.setColor(MICKEYCOLOR1);
      mickeyEar1.setColor(MICKEYCOLOR1);
      mickeyEar2.setColor(MICKEYCOLOR1);
    
      // move the mickey
      mickeyFace.move(xMoveDistance, yMoveDistance);
      mickeyEar1.move(xMoveDistance, yMoveDistance);
      mickeyEar2.move(xMoveDistance, yMoveDistance);

      lastPoint = point;
 
    }
  }

  /**
   * Description: When user release the mouse, it will reset color
   *              of mickey to black.
   * @param: point, this represents location where mouse last on screen.
   * return: void
   */
  public void onMouseRelease (Location point) {
    
    //check if there is a mickey.
    if (mickeyCreate == true) {

    //change mickey's color back to black.
    mickeyFace.setColor(MICKEYCOLOR2);
    mickeyEar1.setColor(MICKEYCOLOR2);
    mickeyEar2.setColor(MICKEYCOLOR2);
    }
  }
 
}
