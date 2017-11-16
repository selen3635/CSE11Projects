/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    11/11, 2015
 * File:    Spin100Controller.java
 * Source of help: cse 11 web page, karan
 *     This is a game with two players. There are 5 five buttons on canvas,
 * which allow player to click and play with it. The goal of this game is
 * to spin the wheel and get score as close to 100 as possible. If anyone
 * go over 100 will lose game, but if both players go over 100 that will be
 * a tie. Otherwise, program will compare two scores from two players and
 * decide who is winner base on their score. Players are allowed to restart
 * game, so basically they can play with it as many time as they want,  just
 * simply click on restart. Everything will be reset to begining when user
 * click on restart.
 */

import objectdraw.*;
import Acme.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


/**
 *    This class is going to display some labels on the top of canvas,
 * and also some buttons on the bottom of canvas. There are two wheels
 * on the middle of canvas. Its a game with two players, players can
 * click on spin button to spin wheel. The goal of this game is to spin
 * the wheel and get score as close to 100 as possible. Program will 
 * come out a winner based on the score that player got. Players are 
 * allowed to restart game to play it again by simply clicking on restart
 * button.
 */
public class Spin100Controller extends WindowController implements
                               ActionListener{

  // initialize frame width and height
  private static final int FRAME_WIDTH = 840;
  private static final int FRAME_HEIGHT = 660;
  
  // constant variables
  private static final int MESSAGE_X = 25;
  private static final int HALF = 2;
  private static final int GRID_ROW = 2;
  private static final int FONT_SIZE = 24;
  private static final int ARRAYSIZE = 20;
  private static final int MAX_SCORE = 100;

  // number of total images
  private static final int NUM_OF_IMAGES = 20;

  // initialize image height and weight
  private static final double IMAGE_WIDTH = 185;
  private static final double IMAGE_HEIGHT = 99;

  // constant variables for wheel's x and y coordinate and delay time
  private static final int MIN_TICKS = 50;
  private static final int WHEEL_DELAY = 1;
  private static final int WHEEL_Y_COORD = 10;
  private static final int GAME_OVER_Y_COORD = 5;
  private static final double WHEEL_X_COORD = ((FRAME_WIDTH / 2) 
                             - IMAGE_WIDTH)/2;
  private static final double WHEEL_XRIGHT =  WHEEL_X_COORD + 
                             ( FRAME_WIDTH / 2);

  // variables for red pointer coordinate, angle, weight and height
  private static final double POINTER_WIDTH = 150;
  private static final double POINTER_XL = WHEEL_X_COORD + IMAGE_WIDTH
                              -POINTER_WIDTH / 2 - 10;
  private static final double POINTER_XR = WHEEL_XRIGHT + IMAGE_WIDTH
                              - POINTER_WIDTH / 2 - 10;
  private static final double POINTER_Y = 257.5 - POINTER_WIDTH / 2;
  private static final double POINTER_HEIGHT = 150;
  private static final double START_ANGLE = -15;
  private static final double ARC_ANGLE = 30;


  // create new variables for JLael
  private JLabel topText1;
  private JLabel topText2;
  private JLabel topText3;

  // create new variables for JPanel
  private JPanel northPanel1;
  private JPanel northPanel2;
  private JPanel northPanel3;
  private JPanel northPanel4;
  private JPanel northPanel5;
  private JPanel southPanel;

  // create new variabels for JButton
  private JButton spinP1;
  private JButton finishPlayer1;
  private JButton restart;
  private JButton spinP2;
  private JButton finishPlayer2;

  // create new variables for player's score
  private int player1Score;
  private int player2Score;

  // create new text message variables
  private Text player1Message;
  private Text player2Message;
  private Text tieMessage;

  // array to store images
  private Image [] playerArray = new Image[ARRAYSIZE];

  // create two wheel variables
  private Spin100Wheel leftWheel;
  private Spin100Wheel rightWheel;

  // create new arc variable
  private FilledArc pointer1;
  private FilledArc pointer2; 

  // boolean value to check whether user press on buttons
  private boolean spin1Press;
  private boolean spin2Press;
  private boolean finish1;
  private boolean finish2;
  private boolean isFinishEnable;

  private Location imageLocation;

  // default array with initialized numbers
  private int[] array = new int[] {50, 85, 30, 65, 10, 45, 70, 25, 90, 5,
             100, 15, 80, 35, 60, 20, 40, 75, 55, 95}; 

  /**
   *    This is main method for program, run as an application
   *  with Acme.MainFrame. It will create a new frame.
   * @param args  This represent arument for command line
   */
  public static void main(String [] args) {

    // create a new frame on screen
    new Acme.MainFrame ( new Spin100Controller(), args, 
      FRAME_WIDTH, FRAME_HEIGHT);
  }

  /**
   *   This method is going to display some labels and buttons and 
   * draw two arrows on canvas.  Also create messages on canvas but
   * hide at beginging.
   */
  public void begin () {

    // create new panels
    northPanel1 = new JPanel();
    northPanel2 = new JPanel();
    northPanel3 = new JPanel();
    northPanel4 = new JPanel();
    southPanel = new JPanel();
    northPanel5 = new JPanel();
    
    // add north panel1 and south panel to the canvas
    this.add( northPanel1, BorderLayout.NORTH);
    this.add( southPanel,  BorderLayout.SOUTH);

    // set layout for panels
    northPanel1.setLayout( new GridLayout(GRID_ROW, 1));
    //northPanel2.setLayout( new FlowLayout());
    northPanel3.setLayout( new BorderLayout());

    // add sub panels to north main panel
    northPanel1.add( northPanel2);
    northPanel1.add( northPanel3, BorderLayout.SOUTH);
    northPanel3.add(northPanel4, BorderLayout.NORTH);
    northPanel3.add(northPanel5, BorderLayout.SOUTH); 

    // get new buttons and name it
    spinP1 = new JButton( "Click to Spin P1");
    finishPlayer1 = new JButton( "Finish Player1");
    restart = new JButton( "Restart");
    spinP2 = new JButton( " Click to Spin P2");
    finishPlayer2 = new JButton( "Finish Player2");

    // create new labels
    topText1 = new JLabel ("Spin100");
    topText2 = new JLabel ( "Player 1's score is " + player1Score,
                            JLabel.CENTER);
    topText3 = new JLabel ( "Player 2's score is " + player2Score,
                            JLabel.CENTER);    

    // set font size for top text1
    topText1.setFont( new Font("Comic SansMS", Font.BOLD, FONT_SIZE));

    //  add text into north panel
    northPanel2.add(topText1);
    northPanel4.add(topText2);
    northPanel5.add(topText3);

    // add buttons to south panel
    southPanel.add(spinP1);
    southPanel.add(finishPlayer1);
    southPanel.add(restart);
    southPanel.add(spinP2);
    southPanel.add(finishPlayer2);

    // add listener to buttons
    spinP1.addActionListener(this);
    finishPlayer1.addActionListener(this);
    restart.addActionListener(this);
    spinP2.addActionListener(this);
    finishPlayer2.addActionListener(this);

    this.validate();

    // cal this method to get iamges
    getImage();

    // call this mehod to draw pointer
    drawPointer();

    // create tie message 
    tieMessage = new Text("Tie", FRAME_WIDTH / HALF, GAME_OVER_Y_COORD,
                          canvas);

    // move it to correct position
    tieMessage.moveTo( FRAME_WIDTH / HALF - tieMessage.getWidth() / HALF
                         , GAME_OVER_Y_COORD);

    // set color and bold
    tieMessage.setColor(Color.BLUE);
    tieMessage.setBold(true);

    // create player 1 winner message 
    player1Message = new Text("P1 Winner", MESSAGE_X, GAME_OVER_Y_COORD,
                           canvas);

    // move it to correct position
    player1Message.moveTo( WHEEL_X_COORD / HALF - player1Message.getWidth()
                           / HALF, GAME_OVER_Y_COORD);

    // set color and bold
    player1Message.setColor(Color.GREEN);
    player1Message.setBold(true);

    // create player 2 winner message 
    player2Message = new Text("P2 Winner", WHEEL_XRIGHT + IMAGE_WIDTH +
                           MESSAGE_X, GAME_OVER_Y_COORD, canvas);

    // move it to correct position
    player2Message.moveTo( FRAME_WIDTH - WHEEL_X_COORD / HALF -
                  player2Message.getWidth() / HALF, GAME_OVER_Y_COORD);

    // set color and bold
    player2Message.setColor(Color.GREEN);
    player2Message.setBold(true);

    // hide message
    tieMessage.hide();
    player1Message.hide();
    player2Message.hide();
   }

  /**
   *   This method is going to draw two red arrows on canvas.
   */
  public void drawPointer () {

    // draw a sector on canvas
    pointer1 = new FilledArc ( POINTER_XL, POINTER_Y, POINTER_WIDTH,
       POINTER_HEIGHT, START_ANGLE, ARC_ANGLE, canvas); 

    // set sector's color to red
    pointer1.setColor(Color.RED);

    // draw another sector on canvas
    pointer2 = new FilledArc( POINTER_XR, POINTER_Y, POINTER_WIDTH,
        POINTER_HEIGHT, START_ANGLE, ARC_ANGLE, canvas);

    // set sector's color to red
    pointer2.setColor(Color.RED);


  }

  /**
   *   This is event specific call back method definitions for the
   * event as promised by the interface. Will check whcih button that 
   * user pressed.
   * @param evt  This represents action events
   */
  public void actionPerformed ( ActionEvent evt) {

    Object source = evt.getSource();

    // check if user click on spin button
    if(source == spinP1 ) {

      // update tick and then spin wheel
      spin1Press = true;
      leftWheel.resetDelay(); 
      leftWheel.updateTick();

    }

    // check if player1 click on finish button
    else if ( source == finishPlayer1 && player1Score > 0 ) {

      // return true if player1 click on finish
      finish1 = true;  

      if ( finish1 ) {

        // disable player 1's buttons
        spin1Press = false;
        spinP1.setEnabled(false);
        finishPlayer1.setEnabled(false);
      }
    }

    // check if user click on restart button
    else if ( source == restart ) {

      // call restart game method
      restartGame();

      // reset all buttons to enable
      spinP1.setEnabled(true);
      finishPlayer1.setEnabled(true);
      spinP2.setEnabled(true);
      finishPlayer2.setEnabled(true);
     

    }

    // check if player2 click on spin button
    else if ( source == spinP2 && (finish1 || player1Score > MAX_SCORE ))
    {
      
      // update tick and spin wheel
      spin2Press = true;
      rightWheel.resetDelay();
      rightWheel.updateTick();
    }

    // check if player 2 click on finish button
    else if ( (source == finishPlayer2 && player2Score > 0) ) {

      // return true if player 2 click on finish button
      finish2 = true; 

      if ( finish2 ) {
        
        // set player2's buttons to disable
        spin2Press = false;
        spinP2.setEnabled(false);
        finishPlayer2.setEnabled(false);
      }

     // after player2 finish, then print messages
     printMessage();

    }


  }

  /**
   *   This method is going to get iamge from file and store images
   * to an array.
   */
  public void getImage () {

    // a loop to get all iamges
    for( int i = 0; i < ARRAYSIZE; i++) {

      double y =  WHEEL_Y_COORD + i * IMAGE_HEIGHT;

      // location for iamge
      imageLocation = new Location ( WHEEL_X_COORD , y);

      // name of image
      String path = "Big_Wheel-" + array[i] + ".png";
 
      // get image
      Image image = getImage(path);
   
      // store image to array
      playerArray[i] = image;   

    }

    // top image location for both wheels
    Location topLeftLoc = new Location( WHEEL_X_COORD, WHEEL_Y_COORD);
    Location topRightLoc = new Location( WHEEL_XRIGHT, WHEEL_Y_COORD);

    // create two image wheels
    leftWheel = new Spin100Wheel( playerArray, WHEEL_DELAY, topLeftLoc,
                                  this, MIN_TICKS, canvas);

    rightWheel = new Spin100Wheel( playerArray, WHEEL_DELAY, topRightLoc,
                                  this, MIN_TICKS, canvas); 
  }

  
  /**
   *   This method is going to display player's score after they spin
   * the wheel.
   * @param num  This represent the index of image after spin the wheel
   */
  public void setScore(int num) {

    // check whether player 1 press on spin button
    if ( spin1Press) {

    // calculate player 1's core
    player1Score += array[num];

    // update player 1's score 
    topText2.setText("Player 1's score is " + player1Score);

      // check whether player 1's score exceed max score
      if ( player1Score > MAX_SCORE) {

        // if true,  then disable player 1's button
        spinP1.setEnabled(false);
        spin1Press = false;
        finishPlayer1.setEnabled(false);
      }

    }

    // check whether player 2 press on spin button
    else if ( spin2Press) {

      // calculate player 2's score
      player2Score += array[num];

      // update player 2's score
      topText3.setText("Player 2's score is " + player2Score);

      // check whether player 2's score exceed max score
      if ( player2Score > MAX_SCORE ) {

        // if true, then disable player 2's button
        spinP2.setEnabled(false);
        spin2Press = false;
        finishPlayer2.setEnabled(false);

        // if player 2's score over 100 then print message
        printMessage();
      }
    }
  }

  /**
   * This method is going to reset game, so everything will be initialized
   * after user click on restart button
   */
  public void restartGame() {

    // reset images
    leftWheel.resetImage();
    rightWheel.resetImage();

    // redraw pointer
    drawPointer();
    
    // set scores to 0
    player1Score = 0;
    player2Score = 0;

    // set button's boolean value to false
    spin1Press = false;
    spin2Press = false;
    finish1 = false;
    finish2 = false;

    // reset player's score back to 0
    topText2.setText("Player 1's score is " + player1Score);
    topText3.setText("Player 2's score is " + player2Score);

    // hide message
    tieMessage.hide();
    player1Message.hide();
    player2Message.hide();

  } 

  /**
   *   This method is going to compare two players score and then
   * printer winner meesage or tie.
   */
  public void printMessage () {
 
    // check whether both player's score exceed max score
    if ( player1Score > MAX_SCORE && player2Score > MAX_SCORE ) {
 
      // print tie meesage
      tieMessage.show();

    }

    // check whether player 1's score exceed max score
    else if ( player1Score > MAX_SCORE && player2Score < MAX_SCORE ) {

      // print player 2 win message
      player2Message.show();

    }

    // check whether player 2's score exceed max score
    else if ( player1Score < MAX_SCORE && player2Score > MAX_SCORE ) {

      // print player 1 win message
      player1Message.show();

    }

    // check if player1's score greater than player 2's score
    else if ( player1Score > player2Score ) {

      // print player 1 win message
      player1Message.show();

    }

    // check if player 2's score greater than player 1's score
    else if ( player1Score < player2Score) {

      // print player 2 win meesage
      player2Message.show();
    }

    // check whether both player's score equal
    else if ( player1Score == player2Score ){

      // print tie message
      tieMessage.show(); 

    }

  } 
}
