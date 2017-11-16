/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Oct 25, 2015
 * File:    Puzzle.java
 * Source of help: cse11 homepage, Vinh Doan, Karan
 * Description: This program will create a canvas on screen. There will be
 *              nine 100px * 100px borders on the left side of canvas, and
 *              nine 100px * 100px images on the right side of canvas in
 *              random order. This is a puzzle game. User can drag pictrue
 *              from right hand side to left hand side in order to get
 *              right position for image. Eventually, nine image will form
 *              a complete picture.
 */

import objectdraw.*;
import Acme.*;
import java.awt.*;


/**
 * Description: This is a puzlle game with nine images and border on the
 *              canvas. User can press and drag image arround canvas.
 *              The aim of this game is let user to get all images to 
 *              right position to form a complete picture. Finally, the
 *              canvas will show "YOU WIN!!!" if user get all images to 
 *              the right position.
 */
public class Puzzle extends WindowController {

  // initialize the width and height for canvas
  private static final int FRAME_WIDTH = 735;
  private static final int FRAME_HEIGHT = 380;

  // x and y coordinate for text message, and font size of text.
  private static final int TEXT_X = 375;
  private static final int TEXT_Y = 140;
  private static final int TEXT_FONT = 50;

  // Number of px between Puzzle Pieces
  private static final int PUZZLE_SPACING = 20;
 
  // offset from left side of canvas
  private static final int PUZZLE_OFFSET = 355;

  // left margin of the board
  private static final int BOARD_MARGIN_X = 25;

  // top margin of the board
  private static final int BOARD_MARGIN_Y = 40;

  // can be changed to expand the puzzle
  private static final int PIECES_PER_COL = 3;
  private static final int PIECES_PER_ROW = 3; 

  // length of each image
  private static final int SIDE_LENGTH = 100;

  // To check whether user press on board piece/ puzzle piece
  private boolean puzzlePressed = false;
  private boolean boardPressed = false;

  // To check whether user press on board piece
  private boolean boardClick = false;

  // the size of array
  private static final int ARRAYSIZE = 9; 

  // location object
  private Location imageLocation;
  private Location lastPoint;

  // arrays to store images 
  private PuzzlePiece [] puzzlePieceArray = new PuzzlePiece[ARRAYSIZE]; 
  private BoardPiece [] boardPieceArray = new BoardPiece[ARRAYSIZE];

  // To check whether user finish to posite all images
  private boolean [] checkFinish = new boolean [ARRAYSIZE];

  // this is a sample array with length 9, and store integer from 0 to 8.
  private int [] sampleArray = new int[ARRAYSIZE];

  // this is a array use to store the random number that we generate
  private int[] finalArray = new int [ARRAYSIZE];

  // variable to indicate the number that image is pressed
  private int imagePressedNum;

  // this is a text message that will show on canvas after user finish
  private Text winMessage;

  // generate a variable for BoardPiece class and PuzzlePiece class
  private BoardPiece leftSide;
  private PuzzlePiece rightSide;

  /**
   * Description: This is main method for program, run as an 
   *              application with Acme.MainFrame. It will create 
   *              a new frame with 735 width and 380 height.
   * @param args: This represents argument for command line.
   */ 
  public static void main ( String[] args) {

    // create a new frame on canvas.
    new Acme.MainFrame( new Puzzle(), args, FRAME_WIDTH, FRAME_HEIGHT);

  }


  /**
   * Description: To set up messages on canvas( font side, color, bold).
   *              It should be hide at begining. Also it will call
   *              two image methods to get image on canvas.
   */ 
  public void begin () {

    // create a new text message
    winMessage = new Text( "YOU WIN!!!", TEXT_X, TEXT_Y, canvas);

    // set color for message
    winMessage.setColor(Color.GREEN);
 
    // set font size and for message
    winMessage.setFontSize(TEXT_FONT);
    winMessage.setBold(true);

    // hide message
    winMessage.hide();

    // call methods
    boardImage();
    puzzleImage(); 
  }

  /**
   * Description: This method is going to get nine images and place them
   *              into right position on the left hand side canvas.
   *              It will hide images at begining.
   */ 
  public void boardImage() {

    // a loop to get and place image for board piece
    for (int i = 0; i < ARRAYSIZE; i++) {

      // x and y coordinates for image
      double x = BOARD_MARGIN_X + SIDE_LENGTH * (i % PIECES_PER_COL);
      double y = BOARD_MARGIN_Y + SIDE_LENGTH * (i / PIECES_PER_ROW);

      // location for image
      imageLocation = new Location(x, y);

      // get name of image
      String path = "p" + i + ".jpg";

      //Image image = getImage(getDocumentBase(), path);

      // get a new image
      Image image = getImage(path);

      // place image into right location
      leftSide = new BoardPiece ( image, i,
        imageLocation, canvas);
 
      // assign images into array
      boardPieceArray[i] = leftSide;
   
      //  hide images
      leftSide.hide();
   
    } 
  }

  /**
   * Description: This method is going to get nine images and place them
   *              into right position on the right hand side canvas.
   *              It will hide images at beinging.
   */ 
  public void puzzleImage() { 

    // call method and get eight random numbers
    getRandomArray();

    // a loop to get and place image for puzzle piece
    for ( int i = 0; i < ARRAYSIZE; i++ ) {

      // x and y coordinate for images
      double x = PUZZLE_OFFSET + PUZZLE_SPACING* (i % PIECES_PER_COL + 1)
        + SIDE_LENGTH * ( i % PIECES_PER_COL);
      double y = PUZZLE_SPACING * (i / PIECES_PER_ROW + 1) +
        SIDE_LENGTH * ( i / PIECES_PER_ROW);
     
      // location for image 
      imageLocation = new Location ( x, y);  
 
      // get name of image
      String path = "p" + finalArray[i] +".jpg";

     // Image image = getImage(getDocumentBase(), path);

      // get a new image     
      Image image = getImage(path);

      // place image into right position
      rightSide = new PuzzlePiece ( image, finalArray[i], 
              imageLocation, canvas);
 
      // hide images
      puzzlePieceArray[i] = rightSide;
      
    }
  }    

  /**
   * Description: This method is going to check whether user press
   *              on images. if yes, then keep which one user pressed.
   * @param point: This represents the point where user press
   *  return:  void
   */  
  public void onMousePress( Location point) {

    // a loop to check whether user press on puzzle piece image
    for (int i = 0; i < ARRAYSIZE; i++) {

      // check whether point on any image
      if (puzzlePieceArray[i].contains(point) ) {

        // if contain then it is pressed
        puzzlePressed = true;

        // keep track which image is pressed
        imagePressedNum = i;
      }
      // keep track point 
      lastPoint = point;
    }

    // a loop to check all board piece image
    for ( int i = 0; i < ARRAYSIZE; i++ ) {

      // check whether point on any image 
      if ( boardPieceArray[i].boardContain(point)) {

        // if it contains then it is pressed
        boardPressed = true;
      }

      // keep track point
      lastPoint = point;
    }
 
  }

  /**
   * Description: This method is going to drag image on the right hand
   *              side of canvas if user press on it.
   * @param point: This represents the destination of image
   *  return: void 
   */
  public void onMouseDrag( Location point) {

    // check if user press on puzzle image
    if ( puzzlePressed ) {

      // distance of x and y coordinate that image going to move
      double xMoveDistance = point.getX() - lastPoint.getX();
      double yMoveDistance = point.getY() - lastPoint.getY();

      // move the puzzle image 
      puzzlePieceArray[imagePressedNum].move ( xMoveDistance,
           yMoveDistance);
      //keep track the last point
      lastPoint = point;
    }

    // check if user press on board image and win the game
    if ( boardPressed && gameOver() ) {

      // distance of x and y coordinate that entire image going to move
      double xDistance = point.getX() - lastPoint.getX();
      double yDistance = point.getY() - lastPoint.getY();

      // a loop that going to move every image
      for ( int i = 0; i < ARRAYSIZE; i++) {

        // move the board image
        boardPieceArray[i].move ( xDistance, yDistance);

        // keep track the last point
        lastPoint = point;
      }

    }

  }

  /**
   * Description: This method is going to check whether image match.
   *              Which means when user drag image from right to left
   *              side of border, this method is going to check whether
   *              image match that border since each iamge has a unique
   *              ID.
   * @param point: This represents where user release their mouse.
   *  return: void
   */ 
  public void onMouseRelease( Location point) {

    // when user release their mouse so they are not pressing on anything 
    puzzlePressed = false;
    boardPressed = false;
    boardClick = false;

    // the id of image which user pressed
    int imageId = puzzlePieceArray[imagePressedNum].getId();

    // get board image by id from pullze image id, and check if puzzle
    // image center is within 50*50 pixel in board image
    if ( boardPieceArray[imageId].contains
        ( puzzlePieceArray[imagePressedNum].getCenter())) {

      // hide specific image on right side canvas
      puzzlePieceArray[imagePressedNum].hide();
      
      // show specific image on left side canvas
      boardPieceArray[imageId].show();

      // show highlight of border for the board piece border 
      boardPieceArray[imageId].showHighlight(Color.GREEN); 

      // keep track the image that is already been moved
      checkFinish[imagePressedNum] = true;
    }

    
    // To check if user win the game     
    if ( gameOver() ) {
    
      //a loop to hide all highlight for boarder piece
      for ( int i = 0; i < ARRAYSIZE; i++ ) {
     
        // hide board piece highlight
        boardPieceArray[i].hideHighlight();
      }

      // show win message
      winMessage.show();
    }
  }

  /*public void onMouseExit ( Location e ) {

    puzzlePressed = false;
    boardPressed = false;

  } */

  /**
   * Description: This method is going to check whether user have matched
   *              all the images.
   * @return boolean: return true if user complege matching, false is
   *                   user didnt finish.
   */
  public boolean gameOver() {

    // a loop to track whether every image has been matched
    for ( int i = 0; i < ARRAYSIZE; i++ ) {

      // To see whether this image is already been matched
      if ( checkFinish[i] == false) {

        return false;
      }
  
    }

    // return true if all images have been matched.
    return true;
  }

  /**
   * Description: This method is going to get a series of random number
   *              from 0 to 8 and store it into an array.
   * @return int][]: return an integer array which store all random number
   */
  public int[] getRandomArray() {

    // to get a sample array which has number from 0 to 8
    for ( int i = 0; i < ARRAYSIZE; i++) {
  
      sampleArray[i] = i;
    }

    // create a new varible for random number with range 0 to 8 
    RandomIntGenerator randomNum = new RandomIntGenerator 
        ( 0, ARRAYSIZE - 1) ;

    // local variable
    int j = 0;
  
    boolean jumpOut = true;
    
    // a loop to check whether done with generagte a series of random number
    while ( jumpOut== true) {
     
      // a loop to generate a series of number
      for (int i = 0; i < ARRAYSIZE; i++) {

        // generate a random number in range 0 to 8
        int num = randomNum.nextValue();
  
        // to check whether number equal the number on sample array
        if ( num == sampleArray[i] ) {

          // assign that position of number to -1
          sampleArray[i] = -1;

          // assign that number to array which we will return
          finalArray[j] = num;

          // keep track position of array
          j ++;
        }
      }

      // see if that array return true value
      if ( checkArray() == true ) {

        // if yes, then jump out while loop which mean we got numbers already
        jumpOut = false;
      }

    }

    // return array which store all random numbers
    return finalArray;
  }

  /**
   * Description: This method is going to check whether sample array is
   *              contain all -1 value.
   * @param boolean: return true is sample array contain all -1 value,
   *                  otherwise is false.
   */
  public boolean checkArray() {

    // a loop to check all position of sample array
    for( int i = 0; i < ARRAYSIZE; i++) {

      // to see this specific position of array whether equal to -1
      if ( sampleArray[i] != -1) {

        // return false if not
        return false;
      }
    }

    //return true if all equal to -1
    return true;
  }

  /**
   * Descriptoin: This method is going to restart the game while user
   *              click on the complete entire image.
   * @param point: This represent the location where user click on canvas.
   */
  public void onMouseClick ( Location point) {

    // a loop to track all image whether contain point
    for (int i = 0; i < ARRAYSIZE; i++) {

      // to check whether that image contain the point where user click
      if ( boardPieceArray[i].boardContain(point)) {

        // click on board piece if contains
        boardClick = true;
     
      }
    }

    // see if user click on board piece and user win the game
    if ( boardClick && gameOver() ) {

      // reset this boolean array to initialize which is false
      for( int i = 0; i < ARRAYSIZE; i++) {

        checkFinish[i] = false;
      }     

      // hide win message
      winMessage.hide();

      // a loop to remove both puzzle image and board image from canvas
      for ( int i = 0; i < ARRAYSIZE; i++) {

        // remove all images from canvas
        boardPieceArray[i].removeFromCanvas();
        puzzlePieceArray[i].removeFromCanvas();

      }
      
      // reset game , call method to create new images.
      boardImage();
      puzzleImage();   
    }
  }

}
