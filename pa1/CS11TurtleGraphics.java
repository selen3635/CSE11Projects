/*
 * Name:   Xiaolong Zhou
 * Login:  cs11faug
 * Date:   Sep 25, 2015
 * File:   CS11TurtleGraphics.java
 * Source of Help: cseweb.ucsd.edu/~ricko/CSE11/
 * This program is going to use a turtle to draw some charactors.
 * (CS11 FAUG JAVA UC SAN DIEGO)
 */

import turtleClasses.*;
import java.awt.Color;

/*
 * Name:    CS11TurtleGraphics
 * Purpose: This class is going to draw some charactors using given
 *          turtle and all functions from turtle package.
 */

public class CS11TurtleGraphics extends Turtle {
  private final static int CHAR_WIDTH = 40;
  private final static int CHAR_HEIGHT = 80;
  private final static int PADDING_BETWEEN_CHARS = 40;
  private final static int PADDING_BETWEEN_LINES = 40;
  private final static int CHAR_HALFWIDTH = 20;
  private final static int CHAR_HALFHEIGHT = 40;
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

  private final static int CHAR_SPACING = CHAR_WIDTH + PADDING_BETWEEN_CHARS;
  private final static int LINE_SPACING = CHAR_HEIGHT + PADDING_BETWEEN_LINES;

  private final static int START_X_1 = 40; // starting x offset for line 1
  private final static int START_X_2 = 40; // starting x offset for line 2
  private final static int START_X_3 = 40; // starting x offset for line 3
  private final static int START_Y = 40;   // starting y offset
  private final static int START_Y_2 = LINE_SPACING;
  private final static int START_Y_3 = START_Y + LINE_SPACING+ LINE_SPACING;

  private final static int PEN_WIDTH = 12;
  private final static Color PEN_COLOR = Color.RED;
  private final static int WORLD_WIDTH = 1080;
  private final static int WORLD_HEIGHT = 480;

/*
 * Delay between turtle actions (turns, moves) in milliseconds.
 * 1000 = 1 sec. so 200 = 0.2 sec.
 */
  private final static int DELAY = 200;

/*
 * Name:       CS11TurtleGraphics
 * Purpose:    This is a constructor for this programe.
 *             It will creat a screen for us, and all charactors that we draw
 *             will appear on this screen.
 * Parameters: world w , this represents the screen we will create.
 *             int delay, this represent delay between turtle actions.
 */

  public CS11TurtleGraphics(World w, int delay) {
    super(w, delay); // Invoke superclass's actor to create this turtle
  }                  // on World w with delay of each turtle's action.

/*
 * Name:       drawC
 * Purpose:    This method will draw a charactor C.
 * Parameters: int x, this represent location for X axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */

  private void drawC(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
  }

/*
 * Name:       drawS
 * Purpose:    This method will draw a charactor S.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */

  private void drawS(int x, int y) {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
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

/*
 * Name:       draw1
 * Purpose:    This method will draw a number 1.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y), is a point on coordinate axis.
 * Return:     Void.
 */

  private void draw1(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() - 1, getYPos() + 1);
    penDown();
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

/*
 * Name:       drawF
 * Purpose:    This method will draw a charactor F.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */
  private void drawF(int x, int y) {
    penUp();
    moveTo(x,y);
    turnToFace(getXPos()+1, getYPos());
    penDown();
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    backward(CHAR_HALFHEIGHT);
    turnLeft();
    forward(CHAR_LENGTH);
  }

/*
 * Name:       drawA
 * Purpose:    This method will draw a charactor A.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */
  private void drawA(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos(), getYPos()+1);
    penDown();
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

/*
 * Name:       drawU
 * Purpose:    This method will draw a charactor U.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */
  private void drawU(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos(), getYPos() +1);
    penDown();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
    turnLeft();
    forward(CHAR_HEIGHT);
  }

/*
 * Name:       drawG
 * Purpose:    This method will draw a charactor G.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */
  private void drawG(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos());
    penDown();
    forward(CHAR_WIDTH);
    backward(CHAR_WIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
    turnLeft();
    forward(CHAR_HALFHEIGHT);
    turnLeft();
    forward(PEN_WIDTH);
  }

/*
 * Name:       drawJ
 * Purpose:    This method will draw a charactor J.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */

  private void drawJ(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos()+1 , getYPos());
    penDown();
    forward(CHAR_WIDTH);
    backward(CHAR_HALFWIDTH);
    turnRight();
    forward(CHAR_HEIGHT);
    turnRight();
    forward(CHAR_HALFWIDTH);
    turnRight();
    forward(CHAR_HALFWIDTH);
  }

/*
 * Name:       drawV
 * Purpose:    This method will draw a charactor V.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */

  private void drawV(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos() );
    turn(DEGREE_V);
    penDown();
    forward(CHAR_VLENGTH);
    turnToFace(getXPos() + 1, getYPos());
    turn(DEGREE_V2);
    forward(CHAR_VLENGTH);
  }

/*
 * Name:       drawN
 * Purpose:    This method will draw a charactor N.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */

  private void drawN(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos(), getYPos()+1);
    penDown();
    forward(CHAR_HEIGHT);
    backward(CHAR_HEIGHT);
    turn(DEGREE_N);
    forward(CHAR_NLENGTH);
    turn(DEGREE_N);
    turn(DEGREE_N);
    turnLeft();
    forward(CHAR_HEIGHT);
  }

/*
 * Name:       drawD
 * Purpose:    This method will draw a charactor D.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */

  private void drawD(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos(), getYPos()+1);
    penDown();
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

/*
 * Name:       drawI
 * Purpose:    This method will draw a charactor I.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */

  private void drawI(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos());
    penDown();
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

/*
 * Name:       drawE
 * Purpose:    This method will draw a charactor E.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */

  private void drawE(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos());
    penDown();
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

/*
 * Name:       drawO
 * Purpose:    This method will draw a charactor O.
 * Parameters: int x, this represent location for x axis.
 *             int y, this represent location for y axis.
 *             (x, y) is a point on coordinate axis.
 * Return:     Void.
 */

  private void drawO(int x, int y) {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos(), getYPos() + 1);
    penDown();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
    turnLeft();
    forward(CHAR_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
  }

/*
 * Name:         Mian
 * Purpose:      This is a main method for program to call all functions
 *               within class.
 * Parameters:   int startX1, this represents the starting point for first
 *               row on x-axis.
 *               int startX2, this represents the starting point for second
 *               row on x-axis.
 *               int startX3, this represents the starting point for third
 *               row on x-axis.
 *               int startY, this represents the starting point for first
 *               row on y-axis.
 *               int startY2, this represents the starting point for second
 *               row on y-axis.
 *               int startY3, this represents the starting point for third
 *               row on y-axis.
 *               int x, y : this is a point where turtle will be located.
 * Return:       Void.
 */

  public static void main(String [] args) {
    int startX1 = START_X_1; // starting x offset for line 1
    int startX2 = START_X_2; // starting x offset for line 2
    int startX3 = START_X_3; // starting x offset for line 3
    int startY = START_Y; // starting y offset
    int startY2 = START_Y_2;
    int startY3 = START_Y_3;

    int x, y;
    World w = new World(WORLD_WIDTH, WORLD_HEIGHT);
    CS11TurtleGraphics myTurtle = new CS11TurtleGraphics(w, DELAY);

    myTurtle.setPenWidth(PEN_WIDTH);
    myTurtle.setPenColor(PEN_COLOR);

    myTurtle.drawC(x = startX1, y = startY);
    myTurtle.drawS(x += CHAR_SPACING, y);
    myTurtle.draw1(x +=CHAR_SPACING+ CHAR_HALFWIDTH, y);
    myTurtle.draw1(x +=CHAR_SPACING, y);
    myTurtle.drawF(x +=CHAR_HALFWIDTH+ PADDING_BETWEEN_CHARS, y);
    myTurtle.drawA(x +=CHAR_SPACING, y);
    myTurtle.drawU(x +=CHAR_SPACING, y);
    myTurtle.drawG(x +=CHAR_SPACING, y);
    myTurtle.drawJ(x = startX2, y += startY2);
    myTurtle.drawA(x += CHAR_SPACING , y);
    myTurtle.drawV(x += CHAR_SPACING, y);
    myTurtle.drawA(x += CHAR_SPACING, y);
    myTurtle.drawU(x = startX3, y = startY3);
    myTurtle.drawC(x += CHAR_SPACING, y);
    myTurtle.drawS(x += CHAR_SPACING+ CHAR_WIDTH , y);
    myTurtle.drawA(x += CHAR_SPACING , y);
    myTurtle.drawN(x += CHAR_SPACING, y);
    myTurtle.drawD(x += CHAR_SPACING + CHAR_WIDTH, y);
    myTurtle.drawI(x += CHAR_SPACING, y);
    myTurtle.drawE(x += CHAR_SPACING, y);
    myTurtle.drawG(x += CHAR_SPACING, y);
    myTurtle.drawO(x += CHAR_SPACING, y);
    
  }

} // End of public class CS11TurtleGraphics extends Turtle
