/**
 * Name:   Xiaolong Zhou
 * Login:  cs11faug
 * Date:   Oct 14, 2015
 * File:   RotatingDeadmau5
 * Source of help: cseweb.ucsd.edu/~richo/cse11/pa3.pdf
 * Description: This program will draw a smiling mickey on canvas.
 *              User can press on mickey and drag it around. In addition,
 *              user can press and hold on mickey's ear for more than half
 *              second to rotate mickey. Also, user can press and hold for
 *              more than half second on mickey's face to center mickey on
 *              canvas.
 */


import objectdraw.*;
import Acme.*;

/**
 * Description: A program that produce a mickey on a canvas. User can
 *              drag mickey within canvas. Also uer can rotae mickey by
 *              press and hold mickey's ears for more than half second.
 *              While rotating, the color of mickey is also changing.
 *              Moreover, user can center mickey to window by press on 
 *              mickey's face for more than half second.
 */
public class RotatingDeadmau5 extends WindowController {

  // variables for instructions
  private Text instr1, instr2, instr3, instr4;

  // initialize the location for instructions
  private static final int INSTR1_X = 50;
  private static final int INSTR1_Y = 50;
  private static final int INSTR2_X = INSTR1_X;
  private static final int INSTR2_Y = INSTR1_Y + 20;
  private static final int INSTR3_X = INSTR1_X;
  private static final int INSTR3_Y = INSTR2_Y +20;
  private static final int INSTR4_X = INSTR1_X;
  private static final int INSTR4_Y = INSTR3_Y + 20;

  // initialize the width and height for canvas
  private static final int CANVAS_WIDTH = 750;
  private static final int CANVAS_HEIGHT = 750;


  // 0.5 second, compare this with time that user press and hold
  private static final int FLIP_PRESS_THRESHOLD = 500;

  private Location lastPoint;  

  // create a mickey
  private Deadmau5 mickey;
  private boolean mickeyCreate = false;
  
  private boolean mickeyGrabbedFace;
  private boolean mickeyGrabbedLeftEar;
  private boolean mickeyGrabbedRightEar;

  private static final int divide = 2;
  // create a timer
  private Timer setTimer;

  // variable represents how long that user press on mickey.
  private double getTime;

  /**
   * Description: This is main method for program, run as an
   *              application with Acme.MainFrame. It will
   *              create a new frame.
   * @param args: This represents arguments for command line.
   *  return void
   */ 
  public static void main (String [] args ) {

    new Acme.MainFrame(new RotatingDeadmau5(), args, CANVAS_WIDTH,
      CANVAS_HEIGHT);
  }

  /**
   * Description: To get a timer, and also get instructions.
   *  return void
   */ 
  public void begin() {

    // Get instructions
    instr1 = new Text ("Click to display a Deadmau5 silhouette centered at" +
            " the mouse click.", INSTR1_X, INSTR1_Y, canvas);
    instr2 = new Text ("Mouse press in any part of the image and drag to" + 
            " move image around.", INSTR2_X, INSTR2_Y, canvas);
    instr3 = new Text ("Mouse click in ear parts of the image with a mouse"
           + " press for more than 0.5 seconds to rotate the image.",
             INSTR3_X, INSTR3_Y, canvas);
    instr4 = new Text ("Mouse click in face part of the image with a mouse"
        + " press for more than 0.5 seconds to center the image.", 
             INSTR4_X, INSTR4_Y, canvas);

    setTimer = new Timer();   
  }

  /**
   * Description: This method is going to check whether there is a
   *              mickey, if not, it will draw a new mickey on canvas.
   * @param point: This represents where user click on canvas.
   *  return void
   */
  public void onMouseClick ( Location point) {

    // To check if there is a mickey or not.
    if ( mickeyCreate == false ) {

      // draw a new mickey
      mickey = new Deadmau5( point,  canvas); 
 
      // hide instructions
      instr1.hide();
      instr2.hide();
      instr3.hide(); 
      instr4.hide();
      mickeyCreate = true;
    }

    // Check if user press on mickey more than 0.5 second.
    if ( getTime >= FLIP_PRESS_THRESHOLD ) {
     

      // Check whether user click on left ear 
      if ( mickey.inLeftEar(point) == true ) {
     
        // rotate mickey counter clock wise 90 degree.
        mickey.leftRotate();
      }

      // Check whether user click on right ear
      else if ( mickey.inRightEar(point) == true ) {
    
        // rotate mickey clock wise 90 degree.
        mickey.rightRotate();
      } 
  
      // check whether user click on face
      if ( mickey.inFace(point) == true ) {

        double center_X = canvas.getWidth()/divide;
        double center_Y = canvas.getHeight()/divide;
        
        mickey.centerMickey(center_X, center_Y);
      }    
    }
  }

  /**
   * Description: This method is going to check whether user click
   *              on mickey, and also it will reset the timer.
   * @param point: This represents where user click on canvas.
   *  return void
   */
  public void onMousePress( Location point) { 
    lastPoint = point;

    // To check if there is mickey
    if ( mickeyCreate == true) {
    
      // To make sure whether user click on mickey
      mickeyGrabbedFace = mickey.inFace(point);
      mickeyGrabbedLeftEar = mickey.inLeftEar (point);
      mickeyGrabbedRightEar = mickey.inRightEar(point);
    }
    // reset timer
    setTimer.reset();
  }

  /**
   * Description: This method is going to drag mickey within canvas.
   * @param point: This represents where user click on mickey
   *  return void
   */
  public void onMouseDrag ( Location point) {
   
    // To check whether user press on mickey 
    if (mickey.contains(point)) {    
      double xMoveDistance = point.getX() - lastPoint.getX();
      double yMoveDistance = point.getY() - lastPoint.getY();
    
      // Distance for mickey going to move  
      mickey.move (xMoveDistance, yMoveDistance);
      
      // get last point
      lastPoint = point;    
    }
  }

  /**
   * Description: This method is going to get time when user
   *              release their mouse. To mesure how long that
   *              user press on mickey.
   * @param point: This represents where user click on canvas.
   *  return void
   */
  public void onMouseRelease( Location point) {

    getTime = setTimer.elapsedMilliseconds();  
  }

  /**
   * Description: This method is going to show instructions when
   *              user's mouse enter the canvas.
   * @param e : This represents location within the canvas.
   *  return void
   */
  public void onMouseEnter( Location e) {
  
    // show instructions
    instr1.show();
    instr2.show();
    instr3.show();
    instr4.show();
  }

  /**
   * Description: This method is going to disappear mickey on the 
   *              canvas when user's mouse exit the canvas.
   * @param e : This represents the location on the screen.
   *  return void
   */
  public void onMouseExit ( Location e) {
  
    // check if there is mickey
    if (mickeyCreate == true) {

      // remove mickey
      mickey.removeFromCanvas(); 

      mickeyCreate = false;
    }
  }
}
