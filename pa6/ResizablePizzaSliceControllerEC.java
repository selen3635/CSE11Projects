/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Oct 19, 2015
 * File:    ResizablePizzaSliceControllerEC
 * Source of help: cseweb.ucsd.edu/~riho/cse11/pa4.pdf
 * Description: This program will draw pizzas on canvas while user click
 *              their mouse. The canvas is separated to four quadrant,
 *              and each quadrant will have different color. Color for
 *              each quadrant has been initialized. User also can resize
 *              canvas, and move line arround canvas. Pizzas will
 *              automatically update their color based on what quadrant
 *              they are in. Moverover, there are three buttons on canvas,
 *              user can click on those buttons to start/stop spin pizza.
 *              Clear button is to clear all pizzas on canvas. User also
 *              have speed slider to control how fast pizza gonna spin.
 *              Pizza is able to be dragged arround canvas.
 */
 

import java.awt.Color;
import objectdraw.*;
import Acme.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;

/**
 *    A program that create a canvas on screen, that allows
 * user to draw pizzas on it. Also it has some methods 
 * that allow user to draw, press drag pizzas on canvas.
 * Further more, There are three button and on slider
 * on canvas, which can control spin speed of pizza, and
 * start/ stop spin pizza as well.
 */
public class ResizablePizzaSliceControllerEC extends WindowController 
               implements MouseListener, MouseMotionListener,
                          ActionListener, ChangeListener {

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
  private ResizablePizzaSliceEC pizza;

  private Location lastPoint;

  // initial size of pizza
  private static final double PIZZASIZE = 90;

  // create new variables for JLabel 
  private JLabel topText;
  private JLabel speedText;

  // create new variables for JPanel
  private JPanel northPanel1;
  private JPanel northPanel2;
  private JPanel northPanel3;
  private JPanel southPanel;

  //create new variables for JBotton
  private JButton startButton;
  private JButton stopButton;
  private JButton clearButton;
  private JButton switchButton;

  //create new variable for JSlider
  private JSlider speedSlider;

  private static final int MIN_SPEED = 1;
  private static final int MAX_SPEED = 100;
  private static final int INITIAL_SPEED = 50;

  // instance variable for speed
  private int speed;

  private boolean isSpin = true;
  private boolean isResize = true;
  private boolean clockSpin = true;
  private boolean cClockSpin = false;
  /**
   *  This is main method for program, run as an  application with 
   *   Acme.MainFrame. It will create a new frame.
   * @param args This represent argument for command line.
   */
  public static void main (String [] args) {

    // create a new frame on canvas.
    new Acme.MainFrame( new ResizablePizzaSliceControllerEC(), args,
      FRAME_WIDTH, FRAME_HEIGHT);

  }
 
  /**
   *  To draw new lines on canvas. Separate canvas into four
   *  quadrant. Each quadrant has different color for pizza.
   */ 
  public void begin () {

    // create new panels
    northPanel1 = new JPanel();
    northPanel2 = new JPanel();
    northPanel3 = new JPanel();
    southPanel = new JPanel();

    // add north panel 1 and south panel to the north of canvas
    this.add( northPanel1, BorderLayout.NORTH);
    this.add( southPanel, BorderLayout.SOUTH);

    // set layout for panels
    northPanel1.setLayout ( new BorderLayout());
    northPanel2.setLayout ( new FlowLayout());
    northPanel3.setLayout ( new FlowLayout());

    // and sub panels to main panel
    northPanel1.add(northPanel2, BorderLayout.NORTH);
    northPanel1.add(northPanel3, BorderLayout.CENTER);

    //  get new buttons and name it
    startButton = new JButton( "Start" );
    stopButton = new JButton( "Stop" );
    clearButton = new JButton( "Clear All" );
    switchButton = new JButton( "Switch Spin");

    // add buttons to panel
    northPanel3.add ( startButton );
    northPanel3.add ( stopButton );
    northPanel3.add ( clearButton );
    northPanel3.add ( switchButton);

    // create a new label
    topText = new JLabel ( "Resizable PizzaSlice Controls" );
   
    // create a new slider on canvas
    speedSlider = new JSlider( JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED,
        INITIAL_SPEED);

    // get speed value
    speed = speedSlider.getValue();

    // create new label for speed
    speedText = new JLabel ( "The speed is " + speed );
 
    // add top text into north panel 2
    northPanel2.add(topText);

    // add speed text and speed slider into south panel
    southPanel.add(speedText);    
    southPanel.add(speedSlider);

    // set this class to respond to mouse event
    canvas.addMouseListener(this);
    canvas.addMouseMotionListener(this);

    this.validate();    

    // set this class to respond to button clicks
    startButton.addActionListener(this);
    stopButton.addActionListener(this);
    clearButton.addActionListener(this);
    switchButton.addActionListener(this);

    // Add listener only for slider
    speedSlider.addChangeListener(this);

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
   * To draw pizzas when user click on vanvas.
   * @param point  This represents where user click on canvas
   */
  public void mouseClicked ( MouseEvent evt) {

    // create a new location variable
    Location point;

    //get location of event
    point = new Location ( evt.getX(), evt.getY() );

     // To check user whether click on lines
    if(evt.getX() != vLine.getStart().getX() &&

       evt.getY() != hLine.getStart().getY()){

      // draw a new pizza on canvas
      pizza = new ResizablePizzaSliceEC( point, PIZZASIZE, canvas,
        hLine, vLine, isSpin, isResize, speed, clockSpin, cClockSpin);

      //set pizza to respond to button click
      startButton.addActionListener(pizza);
      stopButton.addActionListener(pizza);
      clearButton.addActionListener(pizza);
      switchButton.addActionListener(pizza);

      // add listener only for slider
      speedSlider.addChangeListener(pizza);
      }
   
  }

  /**
   * Description: This method is going to check whether user click on lines
   * @param evt  This represents event of mouse.
   */ 
  public void mousePressed ( MouseEvent evt) {

    // create a new location variable
    Location point;

    // get location of event
    point = new Location ( evt.getX(), evt.getY() );

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
   * This method is going to drag lines arround canvas.
   * @param point  This represent where user click on line
   */ 
  public void mouseDragged ( MouseEvent evt) {

    Location point;

    // get location of evt
    point = new Location ( evt.getX(), evt.getY() );

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
   *  This method is going to detect that user release their 
   *  mouse and set vertical and horizontal line press to false,
   *  which means user is not pressing on lines now.
   * @param point  This represent the point where user release their mouse.
   *  no return
   */  
  public void mouseReleased ( MouseEvent evt ) {

    // set both lines press to false   
    vLine_Press = false;
    hLine_Press = false; 

    // get lines proportion according to canvas
    VLINE_PROPORTION = vLine.getStart().getX() / canvas.getWidth();
    HLINE_PROPORTION = hLine.getStart().getY() / canvas.getHeight();


  }

  public void mouseMoved ( MouseEvent evt ) {

  }

  public void mouseEntered ( MouseEvent evt ) {

  }

  public void mouseExited ( MouseEvent evt ) {

  }
  
  /**
   *  This is event specific call back method definitions for
   *  the event as promised by the interface. Will check which
   *  button that user pressed.
   * @param evt  This represents action event
   *  
   */
  public void actionPerformed( ActionEvent evt) {

    Object source = evt.getSource();

    // check whether user press on start button
    if (source == startButton) {

      // return true if user press on start button
      isSpin = true;
      isResize = true;

      

    }

    // check whether user press on stop button
    if (source == stopButton) {

      // retrun false if user press on stop button
      isSpin = false;
      isResize = false;

     

    }

    // check whether user press on switch button
    if ( pizza != null && source == switchButton) {

      if ( clockSpin == true && cClockSpin == false ) {

        // set to spin counter clock wise
        clockSpin = false;
        cClockSpin = true;
       
      }

      else {

        // set to spin clock wise 
        clockSpin = true;
        cClockSpin = false;
        
      }

    }

   /* else if (source == clearButton && pizza != null) {

      pizza.removeFromCanvas(); 


    }*/
  
  }

  /**
   *  This method is going to invoke when target of listener has 
   *  changed state.
   * @param evt  this represents event of target
   *  
   */
  public void stateChanged ( ChangeEvent evt) {

    // get speed value
    speed = speedSlider.getValue();

    // update speed text
    speedText.setText("This speed is " + speed);

    if ( pizza != null ) {

      // set pizza speed
      pizza.setSpeed( speed);

    }

  }


  /**
   *  This method is going to resize the lines proportionally
   *  while user resize the frame.
   */ 
  public void paint ( java.awt.Graphics g) {

    super.paint(g);

    if ( vLine != null && hLine != null ) {
    // resize the vertical line proportionally
    vLine.setEndPoints( canvas.getWidth() * VLINE_PROPORTION, 0 ,
      canvas.getWidth() * VLINE_PROPORTION, canvas.getHeight());

    // resize the horizontal linei proportionally
    hLine.setEndPoints( 0, canvas.getHeight() * HLINE_PROPORTION,
      canvas.getWidth(), canvas.getHeight() * HLINE_PROPORTION);
 
    }
    
  }
   
}
