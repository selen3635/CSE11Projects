/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Nov 03, 2015
 * File:    ResizablePizzaSliceEC
 * Source of help: cseweb.ucsd.edu/~richo/cse11/pa4.pdf
 *    This program will draw pizzas on canvas while user click
 * their mouse. The canvas is separated to four quadrant,
 * and each quadrant will have different color. Color for each
 * quadrant has been initialized. Also, user can resize canvas,
 * and move lines arround canvas. Pizzas will automatically
 * update their color based on what quadrant they are in.
 * Moreover, there are three buttons on canvas, user can click
 * on those button to start/stop spin pizza. Clear button is
 * to clear all pizzas on canvas. User also have speed slider
 * to control how fast that pizza gonna spin. Pizza is able
 * to be dragged arround canvas.
 */

import Acme.*;
import java.awt.Color;
import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
/**
 *    A program that produce pizzas on canvas. User can create
 * as many pizza as they want just simply click on canvas.
 * Pizza's color is different between each quadrant. In 
 * addition, user can drag lines and pizza'c color will also
 * change since they have new location. User can also spin
 * pizza or stop spin pizza and clear all pizza as well.
 * Pizza is able to be dragged by user arround canvas.
 */
public class ResizablePizzaSliceEC extends ActiveObject
             implements ChangeListener, ActionListener,
                        MouseListener, MouseMotionListener
                        , ComponentListener{

  // pause time for program 
  private int pauseTime;

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

  // create lines variables
  private Line vLine;
  private Line hLine;

  // variable to check spin/ resize, and switch
  private boolean isSpin = true;
  private boolean isResize = true;
  private boolean clockSpin = true;
  private boolean cClockSpin = false;

  // speed variables
  private int speed;
  private static final int MAX_SPEED = 100;

  // constant angle that pizza going to spin
  private static final double SPIN_ANGLE = 10;

  // variable to check whether user press on pizza
  private boolean pizzaPressed;

  // keep track last location point
  private Location lastPoint;

  // canvas variable
  private DrawingCanvas canvas;

  private static final int MARGIN = 5;

  // get location of pizza proportion to canvas
  private double tipXProportion;
  private double tipYProportion;

  /**
   * This is a default constructor to create pizza.
   * @param center  this is center location of pizza
   * @param size  this represents the size of pizza
   * @param hLine  this represents horizontal line on canvas
   * @param vLine  this represents vertical line on canvas
   * @param isSpin this will check whether pizza is spining
   * @param isResize this will check whether pizza is resizing
   * @param speed  this represents speed of pizza
   * @param clockSpin  this represents pizza is spining clock wise
   * @param cClockSpin  this represents pizza is spining counter clock wise
   */ 
  public ResizablePizzaSliceEC(Location center, double size,
    DrawingCanvas canvas, Line hLine, Line vLine,
    boolean isSpin, boolean isResize, int speed, 
    boolean clockSpin, boolean cClockSpin) {

    this.vLine = vLine;
    this.hLine = hLine;    

    this.isSpin = isSpin;
    this.isResize = isResize;
    this.speed = speed;
    this.clockSpin = clockSpin;
    this.cClockSpin = cClockSpin;

    this.canvas = canvas;

    // pause time for spining pizza
    pauseTime = MAX_SPEED - speed;

    // assign center point to tip of pizza   
    this.tip = center;
    
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

    // add listener to canvas
    canvas.addMouseListener(this);
    canvas.addMouseMotionListener(this);
    canvas.addComponentListener(this);

    // get tip proportion according to canvas
    tipXProportion = tip.getX() / canvas.getWidth();
    tipYProportion = tip.getY() / canvas.getHeight();


    // To start run method on bottom
    start(); 
 
  }

  /**
   *  This method is going to check the location of pizza,
   *  and update color of pizza to appropriate color.
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
   * This method is going to increase/decrease size of pizza.
   */ 
  public void run() {

    // flag to check growth or descent
    boolean growth = true;
    boolean descent =false;

    // Infinite loop, so this always runs
    while (pizzaSlice != null) {
   
      if ( isSpin && isResize ) {
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

        if ( clockSpin == true && cClockSpin == false) { 

          // get new start angle for pizza
          newStartAngle -= SPIN_ANGLE;
        }

        else {

          newStartAngle += SPIN_ANGLE;
        }

        // set new start angle to pizza slice
        pizzaSlice.setStartAngle( newStartAngle);


        // pause time depends on speed of pizza
        pause( pauseTime);

      }
      // To call checkQuad method to update color
      checkQuad();

    }
  }

  /**
   *  This method is going to find out new pause time for
   *  spining pizza when user change the speed of spin.
   * @param evt  This represent the action event
   *  
   */
  public void stateChanged ( ChangeEvent evt) {

    // get value of speed slider
    JSlider speedSlider = (JSlider)evt.getSource();

    // get new pause time depends on the value of speed
    pauseTime = MAX_SPEED - ( speedSlider.getValue());
  }

  /**
   *  This method is going to detect which button that
   *  user click on. Return boolean values if user click
   *  on start/stop button. Remove all pizzas if user
   *  click on clear all button
   * @param evt  This represents action event
   * 
   */
  public void actionPerformed ( ActionEvent evt ) {

    //check whether user click on start button and make sure there is pizza
    if ( evt.getActionCommand() == "Start" && pizzaSlice != null) {

      // if true then return true to let pizza start to spin and resize
      isSpin = true;
      isResize = true;
    }

    //check whether user click on stop button and make sure there is pizza
    if ( evt.getActionCommand() == "Stop" && pizzaSlice != null) {

      //if true then return false to let pizza stop to spin and resize
      isSpin = false;
      isResize  = false;

    }

    // check whether user click on clear all button
    if (evt.getActionCommand() == "Clear All") {

      // check whether there is pizza  
      if ( pizzaSlice != null ) {

        // if true then remove all pizzas
        pizzaSlice.removeFromCanvas();

        // remove actoin listener from buttons
        ((JButton)evt.getSource()).removeActionListener(this);
        
      }

    }

    // check whether user click on Switch spin button
    if ( evt.getActionCommand() == "Switch Spin") {

      // check which direction that pizza is spining now
      if( clockSpin == true && cClockSpin == false ) {

        // set spin direction
        clockSpin = false;
        cClockSpin = true;
      }

      else {

        // set spin direction
        clockSpin = true;
        cClockSpin = false;
      }

    }

  }

  /**
   * This method is going to set spin speed of pizza
   * @param newSpeed  This represents spin speed of pizza
   */
  public void setSpeed( int newSpeed) {

    speed = newSpeed;
  }

  /**
   *  This method is going to check whether point is on pizza slice.
   * @param point : This represents location where mouse press
   * @return boolean  return boolean values
   */
  public boolean contains( Location point) {

    // return true is pizza contains point
    return pizzaSlice.contains(point);

  }

  /**
   *  This method is going to move all pizzas with dx distance
   *  for x coordinate and dy distance for y coordinate.
   * @param dx   distance for x coordinate
   * @param dy   distance for y coodinate
   *  
   */
  public void move( double dx, double dy) {

    // to make sure the tip of pizza doesnt go off canvas when drag
    if ( tip.getX() + dx < (canvas.getWidth()- MARGIN) &&
        tip.getX() + dx > MARGIN  &&
        tip.getY() + dy < (canvas.getHeight() - MARGIN) &&
        tip.getY() + dy > MARGIN ) {

      // move pizzas and also pizza's tip
      pizzaSlice.move(dx, dy);
      tip.translate(dx, dy);

    }

  }

  public void mouseClicked ( MouseEvent evt) {


  }

  /**
   * This method is going to check whether user press on pizza
   * @param evt  this represents event
   * 
   */
  public void mousePressed ( MouseEvent evt ) {

    Location point;

    //get location of evt
    point = new Location ( evt.getX(), evt.getY() );

    // check if there is pizza
    if ( pizzaSlice != null ) {

      // check whether user press on pizza
      if ( pizzaSlice.contains(point) ) {

        // return true if user press on pizza
        pizzaPressed = true;
      }

      else {

        // otherwise return false
        pizzaPressed = false;
      }

      // keep track last loacation point
      lastPoint = point;
    }

  }

  /**
   * This method is going to check whether user release mouse
   * @param evt  this represents mouse event
   *  
   */
  public void mouseReleased ( MouseEvent evt) {

    // set pizza pressed to false when user release mouse
    pizzaPressed = false;


    tipXProportion = tip.getX() / canvas.getWidth();
    tipYProportion = tip.getY() / canvas.getHeight();

  }

  /**
   * This method is going to move the pizza when user drag it
   * @param evt  This represents mouse event
   * 
   */
  public void mouseDragged ( MouseEvent evt) {

    Location point;

    //get location of evt
    point = new Location ( evt.getX(), evt.getY() );

    // check whether there is pizza
    if ( pizzaSlice != null ) {

      // check whether user press on pizzas
      if ( pizzaPressed) {

        // distance x and y that pizza going to move
        double xMoveDistance = point.getX() - lastPoint.getX();
        double yMoveDistance = point.getY() - lastPoint.getY();

        // move pizzas
        this.move( xMoveDistance, yMoveDistance );

        //keep track the last point
        lastPoint = point;
        
      }
    }
 
  }
  /**
   * This method is going to move all pizza.
   * @param dx  x coordinate that pizza going to move to
   * @param dy  y coordinate that pizza going to move to
   */
  public void moveTo ( double dx, double dy) {

    pizzaSlice.moveTo( dx, dy);
    
  }


  public void mouseMoved( MouseEvent evt) {

  }

  public void mouseEntered( MouseEvent evt) {

  }

  /**
   * This method is going to stop control pizza when mouse exit canvas.
   * @param evt  this represents mouse event.
   */
  public void mouseExited( MouseEvent evt) {

    pizzaPressed = false;
  }

  /**
   * This method is going to move pizzas proportionaly while user 
   * resize canvas.
   * @param e this represents event of component
   */
  public void componentResized( ComponentEvent e) {

    // x coordinate distance that pizza will move
    double dx = (( DrawingCanvas) e.getSource()).getWidth() *
      tipXProportion - tip.getX();

    // y coordinate distance that pizza will move
    double dy = (( DrawingCanvas) e.getSource()).getHeight() *
       tipYProportion - tip.getY();

    // move pizza
    this.move ( dx, dy);
      
  }

  public void componentHidden ( ComponentEvent e) {

  }

  public void componentShown ( ComponentEvent e) {

  }

  public void componentMoved( ComponentEvent e) {

  }
}
