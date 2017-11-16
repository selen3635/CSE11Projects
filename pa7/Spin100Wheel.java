/**
 * Name:   Xiaolong Zhou
 * Login:  cs11faug
 * Date:   11/12/2015
 * File:   Spin100Wheel
 * Source of help: cse 11 web page, karan
 *     This is a game with two players. There are 5 five buttons on canvas,
 * which allow player to click and play with it. The goal of this game is 
 * to spin the wheel and get score as close to 100 as possible. If anyone
 * go over 100 will lose game, but if both players go over 100 that will be
 * a tie. Otherwise, program will compare two scores from two players and
 * decide who is winner base on their score. Players are allowed to restart
 * game, so basically they can play with it as many time as they want, just
 * simply click on restart. Everything will be reset to begining when user
 * click on restart.
 */ 


import objectdraw.*;
import Acme.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;


/**
 *    This class is going to display wheels on canvas which is all images,
 * and also generate a random number of total ticks in order to spin the
 * wheel. Once player click on spin button, it will generate a new random
 * tick from 51 to 63, we have a forever loop, it will start to spin after
 * we have a new number of tick. Tick will decrease by 1 after each spin
 * util 0 and stop spin.
 */
public class Spin100Wheel extends ActiveObject {

  // constant for counting how many ticks
  private static final int TICK_NUM1 = 12;
  private static final double TICK_NUM2 = 0.65;
  private static final double TICK_NUM3 = 0.05;

  // image index constants
  private static final int IMAGE_1 = 2;
  private static final int IMAGE_4 = 19;
  private static final int IMAGE_5 = 18;

  // constants variables
  private static final int HALF = 2;
  private static final int DELAY_1 = 20;
  private static final int DELAY_2 = 200;
  private static final int DELAY_3 = 500;
  private static final int DELAY_4 = 5;

  //  size of array
  private static final int ARRAYSIZE = 20;

  // create new image variables for 5 images
  private VisibleImage image1;
  private VisibleImage image2;
  private VisibleImage image3;
  private VisibleImage image4;
  private VisibleImage image5;

  // create a Image type of array
  private Image[] imageArray = new Image[ARRAYSIZE];

  // instance variables 
  private DrawingCanvas canvas;
  private RandomDoubleGenerator randomNum;
  private Spin100Controller controller;
  private int delay;
  private Location topNumLoc;
  private Location initialLoc;
  private int minTicks;

  // total times that image will spin
  private int totalTicks;

  private int counter;
 
  // height of image
  private static final double IMAGE_HEIGHT = 99;

  public Spin100Wheel( Image[] pics, int delay, Location topNumLoc,
                       Spin100Controller controller, int minTicks,
                       DrawingCanvas canvas) {

    this.canvas = canvas;
    this.minTicks = minTicks;
    this.imageArray = pics;
    this.delay = delay;
    this.controller = controller;
    this.topNumLoc = topNumLoc;

    // copy location 
    initialLoc = new Location(topNumLoc);
       
    // display first image on the canvas 
    image1 = new VisibleImage( pics[IMAGE_1], topNumLoc, canvas);

    // update location for second image
    topNumLoc.translate(0, IMAGE_HEIGHT);

    // display second image on the canvas
    image2 = new VisibleImage( pics[1], topNumLoc, canvas);

    // update location for third image
    topNumLoc.translate(0, IMAGE_HEIGHT);

    // display third image on canvas
    image3 = new VisibleImage( pics[0], topNumLoc, canvas);

    // update location for fourth image
    topNumLoc.translate(0, IMAGE_HEIGHT);

    // display fourth image on canvas
    image4 = new VisibleImage( pics[IMAGE_4], topNumLoc, canvas);

    // update location for fifth image
    topNumLoc.translate(0, IMAGE_HEIGHT);

    // display fifth image on canvas
    image5 = new VisibleImage( pics[IMAGE_5], topNumLoc, canvas);

    // random double generator fromm 0 to 1
    randomNum = new RandomDoubleGenerator(0, 1);
    

    start();
  }

  /**
   *  This method is going to get tick number by receive a random double
   * number and convert to a inteter then add it to minimum ticks.
   * @param num   This represent random double number which generate by
   *              double random generator.
   * @return int   return a inteter number ( total number of ticks).
   */
  private int getTicks(double num) {


    // check whether num less than 0.65
    if ( num < TICK_NUM2 ) {

      // if true use num divide by 0.05 and add 1 and minimum ticks
      return  ((int)(num / TICK_NUM3)) + 1 + minTicks;
     
    }

    else {

      // other wise add different number to minimum ticks
      return  TICK_NUM1- ((int)((num - TICK_NUM2)/ TICK_NUM3)) + minTicks;

    }
      
  }

  /**
   *    This method is going to update ticks by calling getTicks method.
   *  Will generate a random number from 0 to 1 and pass it to getTicks
   *  method.
   */
  public void updateTick () {

    // generate a random number between 0 and 1
    double num = randomNum.nextValue();
   
    // get ticks
    totalTicks = getTicks(num);
  }

  /**
   *  This method is going to return total ticks.
   * @return int  return total ticks
   */
  public synchronized int getTotalTicks() {

    return totalTicks;

  }

  /**
   *   This method is going to set total ticks.
   * @param num  This represent total ticks
   */
  public synchronized void setTotalTicks(int num) {

    totalTicks = num;

  }

  /**
   *   This method is going to reset delay time to 1 at begining of game
   * or everytime user restart game.
   */
  public void resetDelay () {

    // reset delay time to 1
    delay = 1; 

  }

  /**
   * This method is going to spin images when totalTick is greater than
   *  0, which means that user click on spin button.
   */
  public void run() {

    // forever loop
    while ( true) {

      // check if there still tick left 
      if ( totalTicks > 0) {
  
        // spin image down by 1 each time   
        image1.setImage( imageArray[(counter + HALF + ARRAYSIZE) % 
                         ARRAYSIZE]);
        image2.setImage( imageArray[(counter + 1 + ARRAYSIZE ) % 
                         ARRAYSIZE]);
        image3.setImage( imageArray[(counter + ARRAYSIZE) % 
                         ARRAYSIZE]);
        image4.setImage( imageArray[(counter - 1 + ARRAYSIZE) % 
                         ARRAYSIZE]);
        image5.setImage( imageArray[(counter - HALF + ARRAYSIZE) % 
                         ARRAYSIZE]);
  
        // keep track how many tick left 
        setTotalTicks(getTotalTicks() - 1);

        // check delay time
        if ( delay < DELAY_1 ) {

          // increase delay time
          delay ++;
        }
        
        // keep check delay time
        else if ( delay >= DELAY_1 && delay < DELAY_2) {

          // increse delay time
          delay = delay + DELAY_4;
        }

        // keep track delay time
        else if ( delay>= DELAY_2 && delay < DELAY_3) {

          // increase delay time
          delay = delay + DELAY_1;
        }

        //set score
        if ( totalTicks == 0) {

         controller.setScore((counter + ARRAYSIZE) % ARRAYSIZE);
        }

        // keep track counter
        counter++;

      }
    
    // pause program with delay time
    pause(delay);

    }  

  }

  public void resetImage() {

    //copy location
    topNumLoc = new Location(initialLoc);

    // reset all images when user restart game
    image1 = new VisibleImage( imageArray[IMAGE_1], topNumLoc, canvas); 
    topNumLoc.translate(0, IMAGE_HEIGHT);
    image2 = new VisibleImage( imageArray[1], topNumLoc, canvas);
    topNumLoc.translate(0, IMAGE_HEIGHT);
    image3 = new VisibleImage( imageArray[0], topNumLoc, canvas);
    topNumLoc.translate(0, IMAGE_HEIGHT);
    image4 = new VisibleImage( imageArray[IMAGE_4], topNumLoc, canvas);
    topNumLoc.translate(0, IMAGE_HEIGHT);
    image5 = new VisibleImage( imageArray[IMAGE_5], topNumLoc, canvas);

    // reset coutner to 0
    counter = 0;

    // To stop spin when restart game
    totalTicks = 0;
  }
}
