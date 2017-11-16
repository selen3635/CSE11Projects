/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Oct 19, 2015
 * File:    ResizablePizzaSliceController
 * Source of help: cseweb.ucsd.edu/~riho/cse11/pa4.pdf
 * Description: This program will draw pizzas on canvas while user click
 *              their mouse. The canvas is separated to four quadrant,
 *              and each quadrant will have different color. Color for
 *              each quadrant has been initialized. User also can resize
 *              canvas, and move line arround canvas. Pizzas will
 *              automatically update their color based on what quadrant
 *              they are in.
 */
 

import java.awt.Color;
import objectdraw.*;
import Acme.*;

/**
 * Description: A program that create a canvas on screen, that allows
 *              user to draw pizzas on it. Also it has some methods 
 *              that allow user to draw, press drag pizzas on canvas.
 */
public class ResizablePizzaSliceController extends WindowController {

  // Initialize the width and height of frame
  private static final int FRAME_WIDTH = 600;
  private static final int FRAME_HEIGHT = 600;

  // declare lines
  private Line vLine;
  private Line hLine;
 
  private static final int HALF = 2;
 
  // margin of frame
  private static final int MARGIN = 5;
 
  // flag to check whether user click on lines
  private boolean vLine_Press = false;
  private boolean hLine_Press = false;
 
  // proportion of lines
  private double VLINE_PROPORTION;
  private double HLINE_PROPORTION;

  // create a new pizza object
  private ResizablePizzaSlice pizza;

  private Location lastPoint;

  // initial size of pizza
  private static final double PIZZASIZE = 90;
 
  /**
   * Description: This is main method for program, run as an
   *              application with Acme.MainFrame. It will create
   *               a new frame.
   * @param args: This represent argument for command line.
   */
  public static void main (String [] args) {

    // create a new frame on canvas.
    new Acme.MainFrame( new ResizablePizzaSliceController(), args,
      FRAME_WIDTH, FRAME_HEIGHT);

  }
 
  /**
   * Description: To draw new lines on canvas. Separate canvas into four
   *              quadrant. Each quadrant has different color for pizza.
   */ 
  public void begin () {

    // draw a vertical line on canvas
    vLine = new Line (canvas.getWidth() / HALF, 0, canvas.getWidth() / HALF,
          canvas.getHeight(),  canvas );

    // draw a horizontal line on canvas
    hLine = new Line (0, canvas.getHeight() / HALF, canvas.getWidth(), 
     canvas.getHeight() / HALF,  canvas );

   // get proportion of each line 
   VLINE_PROPORTION = vLine.getStart().getX() / canvas.getWidth();
   HLINE_PROPORTION = hLine.getStart().getY() / canvas.getHeight();


  }
  
  /**
   * Description: To draw pizzas when user click on vanvas.
   * @param point: This represents where user click on canvas
   */
  public void onMouseClick ( Location point) {

     // To check user whether click on lines
    if(point.getX() != vLine.getStart().getX() &&

       point.getY() != hLine.getStart().getY()){

      // draw a new pizza on canvas
      pizza = new ResizablePizzaSlice( point, PIZZASIZE, canvas,
        hLine, vLine);
    }
   
  }

  /**
   * Description: This method is going to check whether user click on lines
   */ 
  public void onMousePress ( Location point) {

    // check there is vertical line 
    if ( vLine != null ) { 
      
      // check whether user click on vertical line
      if (vLine.contains(point)) {

        // user is pressing on vertical line
        vLine_Press = true;
      }

      else {

        // user is not pressing on vertical line
        vLine_Press = false;
      }
    }
   
    // check there is horizontal line
    if ( hLine != null ) {

      // check whether user click on horizontal line
      if ( hLine.contains(point)) {

        // user is pressing on horizontal line
        hLine_Press = true;
      }
      else {

        // user is not pressing on horizontal line
        hLine_Press =false;
      }
    }

  }

  /**
   * Description: This method is going to drag lines arround canvas.
   * @param point: This represent where user click on line
   */ 
  public void onMouseDrag ( Location point) {

    // assign point to last point
    lastPoint = point;

    // check whether user press on vertical line
    if ( vLine_Press == true ) {
     
      // check whether point is within 5 to 595 pixels
      if (lastPoint.getX() >MARGIN && lastPoint.getX() < canvas.getWidth()
            -MARGIN ){
     
      // move vertical line to the point
      vLine.setEndPoints ( lastPoint.getX(), 0, lastPoint.getX(),
           canvas.getHeight());  
      
      }
    }

    // check whether user press on horizontal line
    if ( hLine_Press == true ) {

      // check whether point is within 5 to 595 pixels
      if (lastPoint.getY() >MARGIN && lastPoint.getY() < canvas.getHeight()
             -MARGIN){

      // move horizontal line to the point
      hLine.setEndPoints( 0, lastPoint.getY(), canvas.getWidth(), 
             lastPoint.getY() );

      }
    } 
   
  }
 
  /**
   * Description: This method is going to detect that user release their 
   *              mouse and set vertical and horizontal line press to false,
   *              which means user is not pressing on lines now.
   * @param point: This represent the point where user release their mouse.
   */  
  public void onMouseRelease ( Location point) {

    // set both lines press to false   
    vLine_Press = false;
    hLine_Press = false; 

    VLINE_PROPORTION = vLine.getStart().getX() / canvas.getWidth();
    HLINE_PROPORTION = hLine.getStart().getY() / canvas.getHeight();


  }
 
  /**
   * Description: This method is going to resize the lines proportionally
   *              while user resize the frame.
   */ 
  public void paint ( java.awt.Graphics g) {

    super.paint(g);

    // resize the vertical line proportionally
    vLine.setEndPoints( canvas.getWidth() * VLINE_PROPORTION, 0 ,
      canvas.getWidth() * VLINE_PROPORTION, canvas.getHeight());

    // resize the horizontal linei proportionally
    hLine.setEndPoints( 0, canvas.getHeight() * HLINE_PROPORTION,
      canvas.getWidth(), canvas.getHeight() * HLINE_PROPORTION);

  
  }
   
}
