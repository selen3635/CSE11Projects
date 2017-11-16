/**
 * Name:   Xiaolong Zhou
 * Login:  cs1faug
 * Date:   11/19/2015
 * File:   Snake$SnakeSegment
 * Source of help:  cse 11 web page
 *       This is a game that a snake will run and eat apples. At begining
 * There is just a snake head on the canvas. Program will place an apple
 * randomly on the canvas. If user press arrow key, snake will start 
 * moving. When snake go to the same place with apple, snake will eat apple
 * . Actually, old apple just disappear, and will generate a new apple
 * randomly on canvas, at this time snake will get one body which means
 * snake become longer. So snake is getting longer and longer after eating
 * apple until there is no more space for placing apple. There is two
 * score on canvas shows current score and highest score. If player hit
 * wall or snake itself will result in game over. Player can click on
 * new game to restart game.
 */

import Acme.*;
import objectdraw.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.util.*;
import java.util.Random;

/**
 *    This class is going to draw snake segment on canvas. When user press
 * on arrow key, snake will start to move. When snake go to the same place
 * with apple, snake will eat apple. Actually, old apple just disappear,
 * and will generate a new apple randomly on canvas, at this time snake
 * will get one body which means snake become longer. 
 */
public class Snake extends ActiveObject implements KeyListener {

  // How much the Snake grows by when it eats an apple
  private static final int GROW_BY = 1; 
  
  private static final int TWO = 2;

  // The number of cells the snake has left to grow.
  private int leftToGrow;

  // The diameter of each SnakeSegment
  private int size;

  // The delay between each pause in run
  private int delay;
  private DrawingCanvas canvas;

  // Which way the snake is going.
  private Direction currentDir = Direction.STILL;

  // Whether the game is activated or not.
  private boolean isRunning = false, paused = false;

  // The coordinate the snake needs to go to in order to grow
  private Coordinate nextApple;
  private Coordinate lastCoord;
  private Coordinate coord;

  // The snake is a collection of segments.
  ArrayList<SnakeSegment> snake;
  ArrayList<Coordinate> snakeCoord; 

  // We need to know where the head is for apple eating and crashing
  SnakeSegment head;
  SnakeSegment body;

  SnakeController controller;

  /**
   *  This is default constructor for this class.
   * @param coord  This represents a coordinate
   * @param size   This represents size of snake segment
   * @param delay  This represents delay time for this program
   * @param canvas   This represents canvas.
   * @param controller   This represents controller class
   */
  public Snake(Coordinate coord, int size, int delay, DrawingCanvas canvas,
      SnakeController controller) {

    this.coord = coord;
    this.controller = controller;
    this.canvas = canvas;
    this.delay = delay;
    this.size = size;

    // create a new head segment on canvas
    head = new SnakeSegment( coord, size, true, canvas); 

    canvas.addKeyListener(this);

    //  snake segment arraylist
    snake = new ArrayList<SnakeSegment>();

    // add head to segment arraylist
    snake.add(head);


    leftToGrow =(int) Math.pow( (canvas.getWidth() / size), TWO) - 1; 

    paused = false;

    start();

  }
  
  /**
   *    This is a inner class. It will create snake segment on canvas.
   */
  private class SnakeSegment {

    // Visible appearance of the snake
    private final Color SNAKE_COLOR = Color.GREEN;
    private final Color SNAKE_OUTLINE = Color.BLACK;
    private FilledArc segment;

    // the location of each snake segment.
    private Coordinate coord;

    // head constants
    private static final double UP_ANGLE = 90 + 22.5;
    private static final double LEFT_ANGLE = 90 + UP_ANGLE;
    private static final double DOWN_ANGLE = 90 + LEFT_ANGLE;
    private static final double RIGHT_ANGLE = 90 + DOWN_ANGLE;
    private static final double HEAD_ARC_ANGLE = 360 - 45;
    private static final double BODY_ARC_ANGLE = 360;


    /**
     * Creates a snakeSegment, by putting its parts on the canvas
     *
     * @param coord    This represents coordinate
     * @param size     This represents the size of snake
     * @param isHead   To check whether is head
     * @param canvas   This prepresent canvas
     */
    public SnakeSegment(Coordinate coord, int size, boolean isHead,
        DrawingCanvas canvas) {

      double x = coord.getX();
      double y = coord.getY();

      this.coord = coord;

      // To check if it is head
      if ( isHead ) {
   
        // draw a snake's head on canvas
        segment = new FilledArc( x, y, size, size, UP_ANGLE,
            HEAD_ARC_ANGLE, canvas);

        // set head color to green
        segment.setColor(SNAKE_COLOR);

      }

      else {

        // Otherwise draw a snake body on canvas
        segment = new FilledArc(x, y, size, size,
           0, BODY_ARC_ANGLE, canvas);

        // set body color to green
        segment.setColor(SNAKE_COLOR);
      }

    }

    /**
     *   This method is going to return a coordinate
     */
    public Coordinate getCoord() {

      // return a coordinate
      return this.coord;

    }

    /**
     *    This method is going to move segment to a coordinate
     * @param dx   This represents x coordinate that segment going to move
     * @param dy   This represents y coordinate that segment going to move
     */
    public void moveTo(int dx, int dy) {

      // move segment
      segment.moveTo(dx, dy);
      coord = new Coordinate(dx, dy);
    }

    /**
     *    This method is going to set new start angle for segment
     */
    public void newStartAngle() {

      // Check if direction is pointing up
      if ( currentDir == Direction.UP) {
    
        if ( segment != null ) {

          // set to up start angle
          segment.setStartAngle(UP_ANGLE);
        }
      }

      // check if direction is pointing down
      if( currentDir == Direction.DOWN) {

        if (segment != null ) {
 
          // set to down start angle
          segment.setStartAngle(DOWN_ANGLE);
        }

      }

      // check if direction is pointing left
      if( currentDir == Direction.LEFT) {

        if ( segment != null ) {

          // set to left start angle
          segment.setStartAngle(LEFT_ANGLE);

        }
      }

      // check if direction is pointing right
      if( currentDir == Direction.RIGHT) {

        if ( segment != null ) {

          //set to right start angle
          segment.setStartAngle(RIGHT_ANGLE);
        }
      }
    }
    
    /**
     *  This method is going to remove segment from canvas.
     */
    public void clearSnake() {

      if ( segment != null ) {

        // remove segment 
        segment.removeFromCanvas();

        segment = null;
      }

    }


  }

  /**
   *   This method is going to let snake keep moving
   */
  public void run() {

    nextApple = controller.placeApple(canvas);
    // forever loop
    while ( true ) {

      if( !paused) {

        // move snake
        this.move();

      }      

      // pause program
      pause(delay);
    }

  }

  /**
   *   This method is going to detect which key that user pressed
   * @param e   This represents a key event
   */
  public void keyPressed( KeyEvent e) {

    int keyCode = e.getKeyCode();

    // check if user press up arrow key
    if ( keyCode == KeyEvent.VK_UP) {

      isRunning = true;
      // change direction to up
      currentDir = Direction.UP;

      // set start angle
      head.newStartAngle();
    }

    // check if user press down arrow key
    if ( keyCode == KeyEvent.VK_DOWN) {

      isRunning = true;
      // change direction to down
      currentDir = Direction.DOWN;

      // set start angle
      head.newStartAngle();

    }

    // check if user press left key
    if ( keyCode == KeyEvent.VK_LEFT) {

      isRunning = true;
      // change direction to left
      currentDir = Direction.LEFT;

      // set start angle
      head.newStartAngle();


    }

    // check if user press right key
    if ( keyCode == KeyEvent.VK_RIGHT) {

      isRunning = true;
      // change direction to right
      currentDir = Direction.RIGHT;

      // set start angle
      head.newStartAngle();

    }

  }

  public void keyReleased(KeyEvent e) {


  }

  public void keyTyped(KeyEvent e) {


  }

  public void actionPerformed( ActionEvent e) {


  }

  /**
   *  This method is going to return a coordinate arraylist
   * @return Arraylist  return a array list
   */
  public ArrayList<Coordinate> snakeCoordinate() {

    // create a snake coordinate arraylist
    snakeCoord = new ArrayList<Coordinate>();

    for ( int i = 0; i < snake.size(); i++ ) {

      // store each segment coordinate to this arraylist
      snakeCoord.add(snake.get(i).getCoord());

    }   

    // return arraylist
    return snakeCoord;
  }  

  /**
   *    This method is going to move snake segment
   */
  public boolean move() {

    // get coordinate of head
    Coordinate hcoord = head.getCoord();

    //  coordinate of direction that snake going to move
    Coordinate moveDir = new Coordinate ( hcoord.getX() + currentDir.getX()
       * size, hcoord.getY() + currentDir.getY() * size);

    // check game is not paused and snake is runing
    if( !paused && isRunning == true) {

     // if (0 <= coord.getX() && coord.getX() + size < canvas.getWidth()) {

        // coordinate of last segment
        Coordinate lastCoord = (snake.get(snake.size() -1)).getCoord();

        // a loop to move snake
        for ( int i = snake.size() - 1; i >= 1; i--) {

          // get last two segment
          SnakeSegment tail = snake.get(i);
          SnakeSegment secondTail = snake.get(i -1);

          // move last one to second last
          tail.moveTo(secondTail.getCoord().getX(), 
              secondTail.getCoord().getY());
      
        }  
  
        // move head
        head.moveTo( moveDir.getX(), moveDir.getY());
    
        // check if snake go to same place with apple
        if ( moveDir.equals(nextApple)) {

          // remove old apple and generate a new apple
          nextApple = controller.placeApple(canvas);

          // draw a new segment
          body = new SnakeSegment( lastCoord, size, false, canvas);
       
          controller.sumScore();

          // add segment to snake
          snake.add(body);

          leftToGrow--;
          // add segment coordinate to snake coordinate arraylist
          snakeCoord.add(body.getCoord());

        }  
    //  }
    return true;
    }

    else 
     return false; 
      

  }

  /**
   *  This method is going to change pause boolean value in order to 
   * pause game.
   */
  public void pauseGame() {

    // check if game is not paused
    if ( paused == false ) {

      // paused game
      paused = true;
    }

    else
      // otherwise dont pause game
      paused = false;

  }

  /**
   *   This method is going to reset game to beginging state
   */
  public void newGame() {

    // set boolean value to false
    isRunning = false;
    paused = false;

    // call method to clear all segment
    clearSegment();

    // clear arraylist
    snake.clear();

    if ( snakeCoord != null ) {

    // clear arraylist
    snakeCoord.clear();

   // head = new SnakeSegment( coord, size, true, canvas);

    }

  }

  /**
   *   This method is going to clear all snake segment
   */
  public void clearSegment() {

    for ( int i = 0; i < snake.size(); i++) {

      // clear segment
      snake.get(i).clearSnake();


    }
   
  }
}
