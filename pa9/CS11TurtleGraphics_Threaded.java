/*
 * Name:   Xiaolong Zhou
 * Login:  cs11faug
 * Date:   11/24/2015
 * File:   CS11TurtleGraphics_Threaded.java
 * Source of Help: cseweb.ucsd.edu/~ricko/CSE11/
 *     This program is going to use a turtle to draw some charactors.
 * (CS11 FAUG JAVA UC SAN DIEGO). Every charactor has its own turtle to
 * draw itself in the same time. Which means all turtles will start at
 * same time and draw different charactors.
 */

import turtleClasses.*;
import java.awt.Color;

/**
 * Name:    CS11TurtleGraphics
 * Purpose: This class is going to draw some charactors using given
 *          turtle and all functions from turtle package. Every charactor
 *          has its own turtle to draw itself in the same time. Which
 *          means all turtles will start at same time and draw different
 *          charactors.
 */

public class CS11TurtleGraphics_Threaded extends Turtle 
                                     implements Runnable{

  // constant variables for charactor width and height
  private final static int CHAR_WIDTH = 40;
  private final static int CHAR_HEIGHT = 80;

  // constant variables for spacing between lines and charactors
  private final static int PADDING_BETWEEN_CHARS = 40;
  private final static int PADDING_BETWEEN_LINES = 40;

  // constant variables for half width and height
  private final static int CHAR_HALFWIDTH = 20;
  private final static int CHAR_HALFHEIGHT = 40;

  // constant variables for drawwing charactors
  private final static int CHAR_LENGTH =30;
  private final static int CHAR_VLENGTH = 82;
  private final static int DEGREE_V=76;
  private final static int DEGREE_V2=-76;
  private final static int DEGREE_N=-30;
  private final static int CHAR_NLENGTH= 91;
  private final static int DEGREE_D=56;
  private final static int CHAR_DL = 40;
  private final static int DEGREE_D2=34;
  private final static int CHAR_DL2=27;
  private final static int CHAR_DL3=24;
  private final static int DEGREE_D3=26;
  private final static int DEGREE_D4=64;
  private final static int G_WIDTH = 12;

  // constants variable for charactor space and line space
  private final static int CHAR_SPACING = CHAR_WIDTH + 
                           PADDING_BETWEEN_CHARS;
  private final static int LINE_SPACING = CHAR_HEIGHT + 
                           PADDING_BETWEEN_LINES;

  // staring x coordinate for line 1
  private final static int START_X_1 = 240;

  // starting x coordinate for line 2 
  private final static int START_X_2 = 400;

  // starting x coordinate for line 3
  private final static int START_X_3 = 120; 

  // starting y coordinate for line 1
  private final static int START_Y = 80; 

  // starting y coordinate for line 2 
  private final static int START_Y_2 = START_Y + CHAR_HEIGHT + 
                           PADDING_BETWEEN_LINES;

  // starting y coordinate for line 3
  private final static int START_Y_3 = START_Y + LINE_SPACING+ LINE_SPACING;
  
  // constant variable for pen width
  private final static int PEN_W1 =12;
   
  // constant variables for world width and height
  private final static int WORLD_WIDTH = 1080;
  private final static int WORLD_HEIGHT = 480;

  //Delay between turtle actions (turns, moves) in milliseconds.
  private final static int DELAY = 500;

  // instance variables
  private char ch;
  private int x;
  private int y;


  /**
   *     This is default constructor for this program. It will create a 
   *  new turtle at specific location with specific delay on world w.
   * @param w   This represents world 
   * @param ch  This represents the charactor that turtle going to draw
   * @param x   This represents where x coordinate turtle going to start
   * @param y   This represents where y coordinate turtle going to start
   */ 
  public CS11TurtleGraphics_Threaded(World w, char ch, int x, int y,
               int delay) {

    // on world w with delay of each turtle's action
    super(w, delay); 

    this.ch = ch;
    this.x = x;
    this.y = y;
   
    // set pen width for turtle
    setPenWidth(PEN_W1);

    // call run method
    new Thread(this).start();
  }               

  /**
   *    This method is going to draw charactor C at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */
  private void drawC(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y); 

    // face to right
    turnToFace(getXPos() + 1, getYPos()); 

    // set pen down
    penDown();

    //starting to draw charactor
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
  }

  /**
   *    This method is going to draw charactor S at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */ 
  private void drawS(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y); 

    // face to right
    turnToFace(getXPos() + 1, getYPos()); 

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_WIDTH);
    turnLeft();
    forward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_WIDTH);
  }

  /**
   *    This method is going to draw charactor 1 at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */  
  private void draw1(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face to southwest
    turnToFace(getXPos() - 1, getYPos() + 1);

    //set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_HALFWIDTH);
    backward(CHAR_HALFWIDTH);
    turnToFace(getXPos(), getYPos() + 1);
    forward(CHAR_HEIGHT);
    turnRight();
    forward(CHAR_HALFWIDTH);
    turnRight();
    turnRight();
    forward(CHAR_WIDTH);
  }

  /**
   *    This method is going to draw charactor F at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */ 
  private void drawF(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x,y);

    // face to right
    turnToFace(getXPos()+1, getYPos());

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    backward(CHAR_HALFHEIGHT);
    turnLeft();
    forward(CHAR_LENGTH);
  }

  /**
   *    This method is going to draw charactor A at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */  
  private void drawA(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face down
    turnToFace(getXPos(), getYPos()+1);

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_HEIGHT);
    backward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_WIDTH);
  }

  /**
   *    This method is going to draw charactor U at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */  
  private void drawU(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face down
    turnToFace(getXPos(), getYPos() +1);

    //set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
    turnLeft();
    forward(CHAR_HEIGHT);
  }

  /**
   *    This method is going to draw charactor G at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */   
  private void drawG(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face right
    turnToFace(getXPos() + 1, getYPos());

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
    turnLeft();
    forward(CHAR_HALFHEIGHT);
    turnLeft();
    forward(G_WIDTH);
  }

  /**
   *    This method is going to draw charactor J at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */    
  private void drawJ(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face right
    turnToFace(getXPos()+1 , getYPos());

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_WIDTH);
    backward(CHAR_HALFWIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnRight();
    forward(CHAR_HALFWIDTH);
    turnRight();
    forward(CHAR_HALFWIDTH);
  }

  /**
   *    This method is going to draw charactor V at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */     
  private void drawV(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face right
    turnToFace(getXPos() + 1, getYPos() );
    turn(DEGREE_V);

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_VLENGTH);
    turnToFace(getXPos() + 1, getYPos());
    turn(DEGREE_V2);
    forward(CHAR_VLENGTH);
  }

  /**
   *    This method is going to draw charactor N at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */      
  private void drawN(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face down
    turnToFace(getXPos(), getYPos()+1);

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_HEIGHT);
    backward(CHAR_HEIGHT);
    turn(DEGREE_N);
    forward(CHAR_NLENGTH);
    turn(DEGREE_N);
    turn(DEGREE_N);
    turnLeft();
    forward(CHAR_HEIGHT);
  }

  /**
   *    This method is going to draw charactor D at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */       
  private void drawD(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face down
    turnToFace(getXPos(), getYPos()+1);

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_HEIGHT);
    backward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_DL2);
    turn(DEGREE_D);
    forward(CHAR_DL3);
    turn(DEGREE_D2);
    forward(CHAR_DL);
    turn(DEGREE_D3);
    forward(CHAR_DL3);
    turn(DEGREE_D4);
    forward(CHAR_DL2);
  }

  /**
   *    This method is going to draw charactor I at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */        
  private void drawI(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face right
    turnToFace(getXPos() + 1, getYPos());

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_WIDTH);
    backward(CHAR_HALFWIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnRight();
    forward(CHAR_HALFWIDTH);
    turnRight();
    turnRight();
    forward(CHAR_WIDTH);
  }

  /**
   *    This method is going to draw charactor E at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */         
  private void drawE(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face right
    turnToFace(getXPos() + 1, getYPos());

    // set  pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnLeft();
    forward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_WIDTH);
  }

  /**
   *    This method is going to draw charactor O at specific location
   * @param x  This represents where x coordinate turtle going to start
   * @param y  This represents where y coordinate turtle going to start
   */          
  private void drawO(int x, int y) {

    // keep pen up before drawwing
    penUp();

    // always start in upper left corner of this char block
    moveTo(x, y);

    // face down
    turnToFace(getXPos(), getYPos() + 1);

    // set pen down
    penDown();

    // starting to draw charactor
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
    turnLeft();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
  }

  /**
   *     This is main method for this program. It will create multiple
   * turtle at same time and draw charactors
   * @param args  This represents argument for command line
   */  
  public static void main(String [] args) {

    // starting x offset for line 1
    int startX1 = START_X_1; 

    // staring x offset for line 2
    int startX2 = START_X_2; 

    // starting x offset for line 3
    int startX3 = START_X_3; 

    // starting y offset for line 1
    int startY = START_Y; 

    // starting y offset for line 2
    int startY2 = START_Y_2;

    // starting y offset for line 3
    int startY3 = START_Y_3;

    // local variable
    int x, y;

    // create a new world panel
    World w = new World(WORLD_WIDTH, WORLD_HEIGHT);
   
    // This turtle draws a C char
    new CS11TurtleGraphics_Threaded(w, 'C', x= startX1, 
      y = startY, DELAY);

    // This turtle draws a S char
    new  CS11TurtleGraphics_Threaded(w, 'S', x +=  CHAR_SPACING,
      y, DELAY);

    // This turtle draws a 1 char
    new  CS11TurtleGraphics_Threaded(w, '1', x += CHAR_SPACING +
      CHAR_HALFWIDTH, y, DELAY);

    // This turtle draws a 1 char
    new  CS11TurtleGraphics_Threaded(w, '1', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a F char
    new  CS11TurtleGraphics_Threaded(w, 'F', x += CHAR_HALFWIDTH
        + PADDING_BETWEEN_CHARS, y, DELAY);

    // This turtle draws a A char
    new  CS11TurtleGraphics_Threaded(w, 'A', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a U char
    new  CS11TurtleGraphics_Threaded(w, 'U', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a G char
    new  CS11TurtleGraphics_Threaded(w, 'G', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a J char
    new  CS11TurtleGraphics_Threaded(w, 'J', x = startX2,
        y = startY2, DELAY);

    // This turtle draws a A char
    new  CS11TurtleGraphics_Threaded(w, 'A', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a V char
    new  CS11TurtleGraphics_Threaded(w, 'V', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a A char
    new  CS11TurtleGraphics_Threaded(w, 'A', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a U char
    new  CS11TurtleGraphics_Threaded(w, 'U', x = startX3, 
        y = startY3, DELAY);

    // This turtle draws a C char
    new  CS11TurtleGraphics_Threaded(w, 'C', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a S char
    new  CS11TurtleGraphics_Threaded(w, 'S', x += CHAR_SPACING
        + CHAR_WIDTH, y, DELAY);

    // This turtle draws a A char
    new  CS11TurtleGraphics_Threaded(w, 'A', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a N char
    new  CS11TurtleGraphics_Threaded(w, 'N', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a D char
    new  CS11TurtleGraphics_Threaded(w, 'D', x += CHAR_SPACING
        + CHAR_WIDTH, y, DELAY);

    // This turtle draws a I char
    new  CS11TurtleGraphics_Threaded(w, 'I', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a E char
    new  CS11TurtleGraphics_Threaded(w, 'E', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a G char
    new  CS11TurtleGraphics_Threaded(w, 'G', x += CHAR_SPACING
        , y, DELAY);

    // This turtle draws a O char
    new  CS11TurtleGraphics_Threaded(w, 'O', x += CHAR_SPACING
        , y, DELAY);
        
  }

  /**
   *     This method is going to call appropriate method to draw 
   *  specific charactors
   */ 
  public void run() {

    // switch statement, switch parameter ch
    switch(ch) {

      // if parameter is C, then draw C
      case 'C': 
        this.drawC(x, y); break;

      // if parameter is S, then draw S
      case 'S': 
        this.drawS(x, y); break;

      // if parameter is 1, then draw 1
      case '1': 
        this.draw1(x, y); break;

      // if parameter is F, then draw F
      case 'F': 
        this.drawF(x, y); break;

      // if parameter is A, then draw A
      case 'A': 
        this.drawA(x, y); break;

      // if parameter is U, then draw U
      case 'U':
        this.drawU(x, y); break;

      // if parameter is G, then draw G
      case 'G':
        this.drawG(x, y); break;

      // if parameter is J, then draw J
      case 'J':
        this.drawJ(x, y); break;

      // if parameter is V, then draw V
      case 'V':
        this.drawV(x, y); break;

      // if parameter is N, then draw N
      case 'N':
        this.drawN(x, y); break;

      // if parameter is D, then draw D
      case 'D':
        this.drawD(x, y); break;

      // if parameter is i, then draw I
      case 'I':
        this.drawI(x, y); break;

      // if parameter is E, then draw E
      case 'E':
        this.drawE(x, y); break;

      // if parameter is O, then draw O
      case 'O':
        this.drawO(x, y); break;
    }

  }
} // End of public class CS11TurtleGraphics extends Turtle

