/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Oct 12, 2015
 * File:    FlippingDeadmau5.java
 * Source of help: cseweb.ucsd.edu/~richo/cse11/pa3.pdf
 * Description: This program will draw a smiling mickey on a canvas.
 *              User can press down mickey and drag it anywhere within the
 *              canvas. In addition, user can click on mickey and hold it
 *              for more than half second, this will flip mickey based on
 *              the location that user pressed.
 */


import objectdraw.*;
import Acme.*;


/**
 *Description: A program that produce a mickey on a canvas. User can drag
 *             mickey and flip mickey within the canvas. If user click on 
 *             left ear and hold for half second, mickey will flip to left.
 *             If user click on right ear and hold for more than half second
 *             , mickey will flip to right. If user click on face and hold on
 *             for more than half second, mickey's face will flip.
 */
public class FlippingDeadmau5 extends WindowController {

  // variables for instructions
  private Text instr1, instr2, instr3;

  // initialize the location for instructions
  private static final int INSTR1_X = 50;
  private static final int INSTR1_Y = 50;
  private static final int INSTR2_X = INSTR1_X;
  private static final int INSTR2_Y = INSTR1_Y + 20;
  private static final int INSTR3_X = INSTR1_X;
  private static final int INSTR3_Y = INSTR2_Y +20;

  // initialize the width and height for canvas
  private static final int CANVAS_WIDTH = 750;
  private static final int CANVAS_HEIGHT = 750;

  // 0.5 second, compare this with time that user press and hold
  private static final int FLIP_PRESS_THRESHOLD = 500;

  private Location lastPoint;  

  // create a mickey
  private Deadmau5 Mickey;

  // check if there is mickey
  private boolean mickeyCreate = false;
  
  // check if user click on mickey
  private boolean mickeyGrabbedFace;
  private boolean mickeyGrabbedLeftEar;
  private boolean mickeyGrabbedRightEar;

  // create a timer
  private Timer setTimer;

  // variable represents how long that user press on mickey.
  private double getTime;


 /**
  * Description: This is main method for program, run as an 
  *              application with Acme.MainFrame. It will show
  *              instructions and draw mickey on canvas.
  * @param args: This represents arguments for command line.
  * return : void
  */
  public static void main (String [] args ) {

    new Acme.MainFrame(new FlippingDeadmau5(), args, CANVAS_WIDTH,
      CANVAS_HEIGHT);
  }

  /**
   * Description: To get a timer. Also to get instructions.
   *
   * return: void.
   */
  public void begin() {
 
    // Get instructions
    instr1 = new Text ("Click to display a Deadmau5 silhouette centered at" +
            " the mouse click.", INSTR1_X, INSTR1_Y, canvas);
    instr2 = new Text ("Mouse press in any part of the image and drag to" + 
            " move image around.", INSTR2_X, INSTR2_Y, canvas);
    instr3 = new Text ("Mouse click in ear parts of the image with a mouse" +
            " press for more than 0.5 seconds to flip the image.",
             INSTR3_X, INSTR3_Y, canvas);

    // get a timer
    setTimer = new Timer();   
  }

  /**
   * Descriptoin: This method is going to check whether there is
   *              a mickey, if not, it will draw a new mickey on 
   *              canvas.
   * @param point: This represents where user click on canvas.
   *  return void
   */
  public void onMouseClick ( Location point) {

    // To check if there is a mickey or not.
    if ( mickeyCreate == false ) {

      // draw a new mickey
      Mickey = new Deadmau5( point,  canvas); 
      
      // hide instructions
      instr1.hide();
      instr2.hide();
      instr3.hide(); 
      
      mickeyCreate = true;
    }
    
    // Check if user press on mickey more than 0.5 second
    if ( getTime >= FLIP_PRESS_THRESHOLD ) {

      // Check whether user press on face
      if(mickeyGrabbedFace == true ) {

        // flip mickey's face
        Mickey.flip();
      }
     
      // Check whether user press on left ear
      else if ( mickeyGrabbedLeftEar == true) {

        // flip mickey to left
        Mickey.flipLeft();
      }
 
      // Check whether user press on right ear
      else if ( mickeyGrabbedRightEar == true) {

        // flip mickey to right
        Mickey.flipRight();
      }
    }     
  }

  /**
   * Description: This method is going to check whether user click
   *              on mickey. Also it will reset the timer.
   * @param point: This represents where user click on canvas.
   *  return void
   */  
  public void onMousePress( Location point) { 
    lastPoint = point;

    // To check if there is mickey.
    if ( mickeyCreate == true) {
    
      // To make sure whether user click on mickey
      mickeyGrabbedFace = Mickey.inFace(point);
      mickeyGrabbedLeftEar = Mickey.inLeftEar (point);
      mickeyGrabbedRightEar = Mickey.inRightEar(point);
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
    if (Mickey.contains(point)) { 

      // Distance for mickey going to move   
      double xMoveDistance = point.getX() - lastPoint.getX();
      double yMoveDistance = point.getY() - lastPoint.getY();
      
      // move mickey
      Mickey.move (xMoveDistance, yMoveDistance);
      
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
      Mickey.removeFromCanvas(); 

      mickeyCreate = false;
    }
  }
}
