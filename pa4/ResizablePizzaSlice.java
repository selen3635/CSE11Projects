/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Oct 19, 2015
 * File:    ResizablePizzaSlice
 * Source of help: cseweb.ucsd.edu/~richo/cse11/pa4.pdf
 * Description: This program will draw pizzas on canvas while user click
 *              their mouse. The canvas is separated to four quadrant,
 *              and each quadrant will have different color. Color for each
 *              quadrant has been initialized. Also, user can resize canvas,
 *              and move lines arround canvas. Pizzas will automatically
 *              update their color based on what quadrant they are in.
 */


import java.awt.Color;
import objectdraw.*;

/**
 * Description: A program that produce pizzas on canvas. User can create
 *              as many pizza as they want just simply click on canvas.
 *              Pizza's color is different between each quadrant. In 
 *              addition, user can drag lines and pizza'c color will also
 *              change since they have new location.
 */
public class ResizablePizzaSlice extends ActiveObject {

  // pause program 33 millisecond
  private static final double PAUSE_TIME = 33;

  // increase / decrease size of pizza 5 pixel
  private static final double GROWTH = 5;

  // angle for pizza
  private static final double ARC_ANGLE = 45;
  private static final double ANGLE_OFFSET = ARC_ANGLE /2;

  // angles where pizza start
  private static final int FIRST_QUAD_ANGLE = 45;
  private static final int SEC_QUAD_ANGLE = FIRST_QUAD_ANGLE + 90;
  private static final int THIRD_QUAD_ANGLE = SEC_QUAD_ANGLE + 90;
  private static final int FOURTH_QUAD_ANGLE = THIRD_QUAD_ANGLE + 90;

  private static final int HALF_SIZE = 2;  

  // new start angle for pizza
  private int newStartAngle = 0;

  // create a new pizza
  private FilledArc pizzaSlice;

  // size of pizza
  private double size;
  private double DOUBLE_SIZEL = 180;
  private double HALF_SIZEL  = 45;

  // initialize colors for pizza
  private static final Color FIRST_QUAD_COL = new Color(255, 216,0);
  private static final Color SEC_QUAD_COL = new Color (219, 162, 74);
  private static final Color THIRD_QUAD_COL = new Color(225, 35, 1);
  private static final Color FOURTH_QUAD_COL = new Color(255, 250, 138);

  // tip of pizza slice (center of arc's bounding box)
  private Location tip;

  // create lines
  private Line vLine;
  private Line hLine;

  /**
   * Description: This is a default constructor to create pizza.
   * @param center: this is center location of pizza
   * @param size: this represents the size of pizza
   * @param hLine: this represents horizontal line on canvas
   * @param vLine: this represents vertical line on canvas
   */ 
  public ResizablePizzaSlice(Location center, double size,
    DrawingCanvas canvas, Line hLine, Line vLine) {

    this.vLine = vLine;
    this.hLine = hLine;    

    // assign center point to tip of pizza   
    tip = center;
    
    // To get upperLeft point of pizza
    Location upperLeft = new Location(center.getX()- size / HALF_SIZE,
      center.getY() - size / HALF_SIZE);
 
    // Check whether user click on first quadrant
    if ( center.getX() > vLine.getStart().getX() &&
      center.getY() < hLine.getStart().getY()) {
 
      // reset new start angle to first quadrant angle
      newStartAngle = FIRST_QUAD_ANGLE;

      // Create a new pizza on first quadrant
      pizzaSlice = new FilledArc ( upperLeft, size, size, newStartAngle -
        ANGLE_OFFSET, ARC_ANGLE, canvas);

      // set pizza's color
      pizzaSlice.setColor(FIRST_QUAD_COL);
    }

    //Check whether user click on second quadrant
    else if ( center.getX() < vLine.getStart().getX() &&
      center.getY() < hLine.getStart().getY() ) {

      // reset new start angle to second quadrant angle
      newStartAngle = SEC_QUAD_ANGLE;
    
      // Create a new pizza on second quadrant
      pizzaSlice = new FilledArc ( upperLeft, size, size, newStartAngle -
        ANGLE_OFFSET, ARC_ANGLE, canvas);

      // set pizza's color
      pizzaSlice.setColor(SEC_QUAD_COL);
    }

    // Check whether user click on third quadrant
    else if ( center.getX() < vLine.getStart().getX() &&
      center.getY() > hLine.getStart().getY() ) {

      // reset new start angle to third quadrant angle
      newStartAngle = THIRD_QUAD_ANGLE;

      // Create a new pizza on third quadrant
      pizzaSlice = new FilledArc ( upperLeft, size, size, newStartAngle -
        ANGLE_OFFSET, ARC_ANGLE, canvas);

      // set pizza's color
      pizzaSlice.setColor(THIRD_QUAD_COL);
    }

    // Check whether user click on fourth quadrant
    else if ( center.getX() > vLine.getStart().getX() &&
      center.getY() > hLine.getStart().getY() ) {

      //  reset new start angle to fourth quadrant angle
      newStartAngle = FOURTH_QUAD_ANGLE;

      // Create a new pizza on fourth quadrant
      pizzaSlice = new FilledArc ( upperLeft, size, size, newStartAngle -
        ANGLE_OFFSET, ARC_ANGLE, canvas);

      // reset pizza's color
      pizzaSlice.setColor(FOURTH_QUAD_COL);
    }

    // To start run method on bottom
    start(); 

  }

  /**
   * Description: This method is going to check the location of pizza,
   *              and update color of pizza to appropriate color.
   */ 
  public void checkQuad() {

    // Check whether pizza on first quadrant
    if ( tip.getX() > vLine.getStart().getX() &&
      tip.getY() < hLine.getStart().getY() ) {

      // reset pizza's to its appropriate color
      pizzaSlice.setColor(FIRST_QUAD_COL);
    
    }

    // Check whether pizza on second quadrant
    else if( tip.getX() < vLine.getStart().getX() &&
      tip.getY() < hLine.getStart().getY() ) {

      // reset pizza's color to its appropriate color
      pizzaSlice.setColor(SEC_QUAD_COL); 

    }

    // Check whether pizza on third quadrant
    else if( tip.getX() < vLine.getStart().getX() &&
      tip.getY() > hLine.getStart().getY() ){

      // reset pizza's color to its appropriate color
      pizzaSlice.setColor(THIRD_QUAD_COL);

    } 

    // Check whether pizza on fourth quadrant
    else if( tip.getX() > vLine.getStart().getX() &&
      tip.getY() > hLine.getStart().getY() ) {

      // reset pizza's color to its appropriate color
      pizzaSlice.setColor(FOURTH_QUAD_COL);

    }
  }

  /**
   * Description: This method is going to increase/decrease size
   *              of pizza.
   */ 
  public void run() {

    // flag to check growth or descent
    boolean growth = true;
    boolean descent =false;

    // Infinite loop, so this always runs
    while (true) {
   
      // Check if pizza size less than max size, then growth
      if ( pizzaSlice.getWidth() <= DOUBLE_SIZEL && growth) {
        
        // increase the size of pizza
        pizzaSlice.setSize( pizzaSlice.getWidth() + GROWTH,
          pizzaSlice.getHeight() + GROWTH );

        // To keep tip of pizza stable
        pizzaSlice.moveTo( pizzaSlice.getX() - GROWTH / HALF_SIZE,
          pizzaSlice.getY() - GROWTH / HALF_SIZE);
      
      }  
    
      // Check if pizza size bigger than min size, then descent
      else if ( pizzaSlice.getWidth() >= HALF_SIZEL && descent) {

        // decrease the size of pizza
        pizzaSlice.setSize( pizzaSlice.getWidth() - GROWTH,
          pizzaSlice.getHeight() - GROWTH);
 
        // To keep tip of pizza stable
        pizzaSlice.moveTo( pizzaSlice.getX() + GROWTH / HALF_SIZE,
          pizzaSlice.getY() + GROWTH / HALF_SIZE);
 
      }
   
      // check if pizza reach max size
      if ( pizzaSlice.getWidth() >= DOUBLE_SIZEL) {
 
        growth = false;
        descent = true;
      }

      // check if pizza reach min size
      if ( pizzaSlice.getWidth() <= HALF_SIZEL ) {

        growth = true;
        descent = false;
      }

      // To call checkQuad method to update color
      checkQuad();
      
      // pause for 33 millisecond
      pause( PAUSE_TIME);
   
     
    }

  }

}
