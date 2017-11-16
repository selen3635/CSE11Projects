/**
 * Name:    Xiaolong Zhou
 * Login:   cs11faug
 * Date:    Oct 12, 2015
 * File:    Deadmau5
 * Source of help: cseweb.ucsd.edu/~richo/cse11/pa3.pdf
 * Description: This program will draw a smiling mickey on a canvas.
 *              User can press down mickey and drag it anywhere within the
 *              canvas. In addition, user can click on mickey and hold it
 *              for more than half second, this will flip mickey based on
 *              the location that user pressed.
 */    


import java.awt.Color;
import objectdraw.*;

/**
 * Description: A program that produce a mickey on a canvas contains some
 *              methods in this class. Such as user can move mickey, flip
 *              mickey, as well as to check if user click on mickey. In
 *              addition, user can rotation mickey with rotate methods.
 * */
public class Deadmau5 {

  // variables for creating mickey ear, face eyes and mouth.
  private FilledOval leftEar, rightEar, face, leftEye, rightEye;
  private FilledArc mouth;

  // initialize the radius for mickey's face, ear, eye, and mouth.
  private static final int FACE_RADIUS = 50;
  private static final int EAR_RADIUS = 40;
  private static final int EYE_RADIUS = 15;
  private static final int MOUTH_RADIUS = 40;

  // initialize the angle where arc method start and degrees to draw.
  private static final int startAngle = 180;
  private static final int arcAngle = 180;

  // Center of each ear is this offset up and over (x and y) 
  // from center of face.
  private static final int EAR_OFFSET = 50;  
  private static final int EYE_OFFSET = 20;

  // distance for eye to rotate.
  private static final int EYE_ROTATE = 30;

  // Flags for mickey to check which direction mickey face to. 
  private boolean faceUp = false;
  private boolean faceDown = false;
  private boolean faceLeft = false;
  private boolean faceRight = false;

  private int arcAngle1 = 90;
  private int arcAngle2 = 180;
  private int arcAngle3 = 270;
  private DrawingCanvas canvas;

  /**
   * Description: This is a default constructor for this class.
   *              It will produce a mickey on canvas where user click.
   * @param point: This represents the point that user click on canvas.
   */
  public Deadmau5 ( Location point, DrawingCanvas canvas) {

    // To get point where user click
    double mouse_X = point.getX();
    double mouse_Y = point.getY();
 
    // draw a new mickey left ear
    leftEar = new FilledOval ( mouse_X - FACE_RADIUS - EAR_RADIUS,
      mouse_Y - FACE_RADIUS - EAR_RADIUS, EAR_RADIUS + EAR_RADIUS,
      EAR_RADIUS + EAR_RADIUS, canvas);

    // draw a new mickey right ear
    rightEar = new FilledOval ( mouse_X - EAR_RADIUS + EAR_OFFSET,
      mouse_Y - FACE_RADIUS - EAR_RADIUS, EAR_RADIUS + EAR_RADIUS,
      EAR_RADIUS + EAR_RADIUS, canvas);

    // draw a new mickey face
    face = new FilledOval ( mouse_X - FACE_RADIUS, mouse_Y - FACE_RADIUS,
      FACE_RADIUS + FACE_RADIUS, FACE_RADIUS + FACE_RADIUS, canvas);

    // draw a new mickey eyes
    leftEye = new FilledOval ( mouse_X - EYE_OFFSET - EYE_RADIUS,
      mouse_Y - EYE_OFFSET - EYE_RADIUS, EYE_RADIUS + EYE_RADIUS,
      EYE_RADIUS + EYE_RADIUS, canvas);

    rightEye = new FilledOval ( mouse_X + EYE_OFFSET - EYE_RADIUS,
       mouse_Y - EYE_OFFSET - EYE_RADIUS,
      EYE_RADIUS + EYE_RADIUS, EYE_RADIUS + EYE_RADIUS, canvas);

    // draw a new mickey mouth
    mouth = new FilledArc ( mouse_X - MOUTH_RADIUS,
      mouse_Y - MOUTH_RADIUS, MOUTH_RADIUS
       + MOUTH_RADIUS, MOUTH_RADIUS + MOUTH_RADIUS, startAngle, arcAngle,
       canvas);

    // set mickey eyes and mouth color to white
    leftEye.setColor(Color.WHITE);
    rightEye.setColor(Color.WHITE);
    mouth.setColor(Color.WHITE); 
 
    faceUp = true;
   
  }

  /**
   * Description: This method is going to check whether user click on mickey.
   * @param point: This represents where user click on canvas.
   * @return true/false
   */ 
  public boolean contains ( Location point) {
  
    // To check whether user click on mickey.
    if ( leftEar.contains(point) || rightEar.contains(point)
        || face.contains(point) ) {

      return true; 
    }

    else {
      return false;
    }

  }

  /**
   * Description: This method is going to check whether user click on mickey
   *              face.
   * @param point: This represents where user click on canvas.
   * @return true/fase
   */
  public boolean inFace ( Location point) {
    
    // To check whether user click on mickey face.
    if ( face.contains(point)) {

      return true;
    }

    else {
      return false;
    }

  }

  /**
   * Description: This method is going to check whether user click on 
   *              mickey's left ear.
   * @param point: This represents where user click on canvas.
   * @return true/fasle
   */ 
  public boolean inLeftEar ( Location point) {

    // To check whether user click on mickey's left ear.
    if ( leftEar.contains(point)) {

      return true;
    }

    else {
      return false;
    }

  }

  /**
   * Description: This method is going to check whether user click on
   *              mickey's right ear.
   * @param point: This represents where user click on canvas. 
   * @return true/fase
   */
  public boolean inRightEar ( Location point) {

    // To check whether user click on mickey's right ear.
    if ( rightEar.contains(point)) {

      return true;
    }
 
    else {
      return false;
    } 
  }

  /**
   * Description: This method can move mickey with distance (dx, dy).
   * @param dx: This represents x-axis distance for mickey to move.
   * @param dy: This represents y-axis distance for mickey to move.
   *  return void
   */ 
  public void move ( double dx, double dy) {

    // To move all parts of mickey with distance  (dx, dy).
    face.move(dx, dy);
    leftEar.move(dx, dy);
    rightEar.move(dx, dy);
    leftEye.move(dx, dy);
    rightEye.move(dx, dy);
    mouth.move(dx, dy);      
  }

  /**
   * Description: This method will remove every part of mickey from canvas.
   *  return void
   */ 
  public void removeFromCanvas() {

    // To remove all parts of mickey from canvas.
    face.removeFromCanvas();
    leftEar.removeFromCanvas();
    rightEar.removeFromCanvas();
    leftEye.removeFromCanvas();
    rightEye.removeFromCanvas();
    mouth.removeFromCanvas();
  }

  /**
   * Description: This method is going to flip mickey to left.
   *  return void
   */
  public void flipLeft() {

    // To move all parts of mickey to left.
    face.move( - EAR_OFFSET - EAR_OFFSET, 0);
    leftEar.move(- EAR_OFFSET - EAR_OFFSET, 0);
    rightEar.move(- EAR_OFFSET - EAR_OFFSET, 0);
    leftEye.move(- EAR_OFFSET - EAR_OFFSET, 0);
    rightEye.move(- EAR_OFFSET - EAR_OFFSET, 0);
    mouth.move( - EAR_OFFSET - EAR_OFFSET, 0);

    // To check whether mouth visualable on canvas.
    if ( mouth.isHidden()) {

      // show eyes and mouth
      leftEye.show();
      rightEye.show();
      mouth.show();
    }

    else {
      // hide eye and mouth
      leftEye.hide();
      rightEye.hide();
      mouth.hide();
    }

    
  }
  
  /**
   * Description: This method is goint to flip mickey to right.
   *  return void
   */ 
  public void flipRight() {

    // Move all parts of mickey to right
    face.move( EAR_OFFSET + EAR_OFFSET, 0);
    leftEar.move( EAR_OFFSET + EAR_OFFSET, 0);
    rightEar.move( EAR_OFFSET + EAR_OFFSET, 0);
    leftEye.move( EAR_OFFSET + EAR_OFFSET, 0);
    rightEye.move( EAR_OFFSET + EAR_OFFSET, 0);
    mouth.move ( EAR_OFFSET + EAR_OFFSET, 0);

    // To check whether mouth is visualable on canvas
    if ( mouth.isHidden()) {

      // show eyes and mouth
      leftEye.show();
      rightEye.show();
      mouth.show();
    }

    else {
      // hide eyes and mouth
      leftEye.hide();
      rightEye.hide();
      mouth.hide();
    }

  }

  /**
   * Description: This method is going to flip mickey's face to front/back.
   *  return void
   */
  public void flip() {

    // To check whether mouth is visualable on canvas.
    if ( mouth.isHidden()) {

      // show eyes and mouth
      leftEye.show();
      rightEye.show();
      mouth.show();
    }

    else {
      // hide eyes and mouth
      leftEye.hide();
      rightEye.hide();
      mouth.hide();
    }  
  }

 
  /******************* Methods for EC Part ****************/
 
  /**
   * Description: This method is going to rotate mickey counter clock
   *              wise 90 degree while user press and hold on mickey's
   *              left ear.
   */ 
  public void leftRotate() {
    
    // first case rotation if mickey face up. 
    if( faceUp) {    
         
      // move mickey
      rightEar.move( -EAR_OFFSET-EAR_OFFSET, -EAR_OFFSET-EAR_OFFSET);
      face.move(0, -FACE_RADIUS - FACE_RADIUS); 
      leftEye.move(0, -EYE_ROTATE-EYE_ROTATE);
      rightEye.move(-EYE_OFFSET - EYE_OFFSET, -EYE_ROTATE - EYE_ROTATE
                  - EYE_OFFSET - EYE_OFFSET);
      mouth.move(0, - FACE_RADIUS - FACE_RADIUS);
 
      //reset start angle for mouth
      mouth.setStartAngle(arcAngle3);
     
      // set corlor for eyes and mouth
      leftEye.setColor(Color.WHITE);
      rightEye.setColor(Color.WHITE);
      mouth.setColor(Color.WHITE);
  
      faceUp= false;
      faceLeft = true;
       
      // after this turn, mickey face to left, so color will be blue.
      face.setColor(Color.BLUE);
      leftEar.setColor(Color.BLUE);
      rightEar.setColor(Color.BLUE);
    }

    // second case, if mickey face left
    else if ( faceLeft ) {
    
      // move mickey
      rightEar.move( -EAR_OFFSET - EAR_OFFSET, EAR_OFFSET + EAR_OFFSET);
      face.move(- FACE_RADIUS - FACE_RADIUS, 0);
      leftEye.move(- EYE_ROTATE - EYE_ROTATE, 0);
      rightEye.move( -FACE_RADIUS - FACE_RADIUS, EYE_OFFSET + EYE_OFFSET);
      mouth.move( - FACE_RADIUS - FACE_RADIUS, 0);

      // reset start angle for mouth
      mouth.setStartAngle(0);  

      // set color for eyes and mouth 
      leftEye.setColor(Color.WHITE);
      rightEye.setColor(Color.WHITE);
      mouth.setColor(Color.WHITE);

      faceLeft = false;
      faceDown = true;
    
      // after this turn, mickey face down, so color will be green
      face.setColor(Color.GREEN);
      leftEar.setColor(Color.GREEN);
      rightEar.setColor(Color.GREEN);

     }

    // third case, if mickey face down
    else if ( faceDown ) {
      
      // move mickey
      rightEar.move( EAR_OFFSET + EAR_OFFSET, EAR_OFFSET + EAR_OFFSET);
      face.move( 0, FACE_RADIUS + FACE_RADIUS);
      leftEye.move( 0, EYE_ROTATE + EYE_ROTATE);
      rightEye.move( EYE_OFFSET + EYE_OFFSET, EYE_ROTATE + EYE_ROTATE
                 + EYE_OFFSET + EYE_OFFSET);
      mouth.move(0, FACE_RADIUS + FACE_RADIUS);

      // reset start angle for mouth
      mouth.setStartAngle(arcAngle1);

      // set color for eyes and mouth
      leftEye.setColor(Color.WHITE);
      rightEye.setColor(Color.WHITE);
      mouth.setColor(Color.WHITE);
     
      faceDown = false;
      faceRight = true;

      // after this turn, mickey face right, so color will be red
      face.setColor(Color.RED);
      leftEar.setColor(Color.RED);
      rightEar.setColor(Color.RED);

     }

    // forth case, if mickey face right
    else if ( faceRight) {              
      
      // move mickey
      rightEar.move( EAR_OFFSET + EAR_OFFSET, -EAR_OFFSET - EAR_OFFSET);
      face.move( FACE_RADIUS + FACE_RADIUS, 0);
      leftEye.move( EYE_ROTATE + EYE_ROTATE, 0);
      rightEye.move( FACE_RADIUS + FACE_RADIUS, - EYE_OFFSET - EYE_OFFSET);
      mouth.move( FACE_RADIUS + FACE_RADIUS, 0);

      //reset start angle for mouth
      mouth.setStartAngle(arcAngle2);
      
      // set color for eyes and mouth
      leftEye.setColor(Color.WHITE);
      rightEye.setColor(Color.WHITE);
      mouth.setColor(Color.WHITE);
 
      faceRight = false;
      faceUp = true;

      // after this turn, mickey face up, so color will be black
      face.setColor(Color.BLACK);
      leftEar.setColor(Color.BLACK);
      rightEar.setColor(Color.BLACK);

    }
  }

  /**
   * Description: This method is going to rotate mickey  clock
   *              wise 90 degree while user press and hold on mickey's
   *              right ear.
   */
  public void rightRotate() {

    // first case, if mickey face up
    if ( faceUp) {
      
      // move mickey
      leftEar.move( EAR_OFFSET + EAR_OFFSET, -EAR_OFFSET - EAR_OFFSET);
      face.move(0,  -FACE_RADIUS - FACE_RADIUS);
      leftEye.move( EYE_OFFSET + EYE_OFFSET, -EYE_ROTATE -EYE_ROTATE
                - EYE_OFFSET - EYE_OFFSET);
      rightEye.move( 0, -EYE_ROTATE - EYE_ROTATE);
      mouth.move ( 0, -FACE_RADIUS - FACE_RADIUS);
 
      // reset start angle for mouth
      mouth.setStartAngle(arcAngle1);
      
      // set color for eyes and mouth
      leftEye.setColor(Color.WHITE);
      rightEye.setColor(Color.WHITE);
      mouth.setColor(Color.WHITE);

      faceUp = false;
      faceRight = true;

      // after this turn, mickey face right, so color will be red
      face.setColor(Color.RED);
      leftEar.setColor(Color.RED);
      rightEar.setColor(Color.RED);

    }

    // second case, if mickey face right
    else if ( faceRight ) {    
      
      // move mickey
      leftEar.move( EAR_OFFSET + EAR_OFFSET, EAR_OFFSET + EAR_OFFSET);
      face.move( FACE_RADIUS + FACE_RADIUS, 0);
      leftEye.move( EAR_OFFSET + EAR_OFFSET,  EYE_OFFSET + EYE_OFFSET);
      rightEye.move( EYE_ROTATE + EYE_ROTATE, 0);
      mouth.move ( FACE_RADIUS + FACE_RADIUS, 0);
   
      // reset start angle for mouth
      mouth.setStartAngle(0);

      // set color for eyes and mouth
      leftEye.setColor(Color.WHITE);
      rightEye.setColor(Color.WHITE);
      mouth.setColor(Color.WHITE);

      faceRight = false;
      faceDown = true;    

      // after this turn, mickey face down, so color will be green
      face.setColor(Color.GREEN);
      leftEar.setColor(Color.GREEN);
      rightEar.setColor(Color.GREEN);


    }

    // third case, if mickey face down
    else if ( faceDown ) {
   
      // move mickey
      leftEar.move( - EAR_OFFSET - EAR_OFFSET, EAR_OFFSET + EAR_OFFSET);
      face.move( 0, FACE_RADIUS + FACE_RADIUS);
      leftEye.move( -EYE_OFFSET - EYE_OFFSET, EYE_ROTATE + EYE_ROTATE
               + EYE_OFFSET + EYE_OFFSET);
      rightEye.move(0, EYE_ROTATE + EYE_ROTATE);
      mouth.move ( 0, FACE_RADIUS + FACE_RADIUS);
 
      // reset start angle for mouth
      mouth.setStartAngle(arcAngle3);

      // set color for eyes and mouth
      leftEye.setColor(Color.WHITE);
      rightEye.setColor(Color.WHITE);
      mouth.setColor(Color.WHITE);

      faceDown = false;
      faceLeft = true;

      // after this turn, mickey face left, so color will be blue
      face.setColor(Color.BLUE);
      leftEar.setColor(Color.BLUE);
      rightEar.setColor(Color.BLUE);

   } 

    // forth case, if mickey face left
    else if ( faceLeft) {
   
      // move mickey
      leftEar.move ( -EAR_OFFSET - EAR_OFFSET, -EAR_OFFSET - EAR_OFFSET);
      face.move ( -FACE_RADIUS - FACE_RADIUS, 0);
      leftEye.move( -EAR_OFFSET - EAR_OFFSET, -EYE_OFFSET - EYE_OFFSET);
      rightEye.move( -EYE_ROTATE - EYE_ROTATE , 0);
      mouth.move( -FACE_RADIUS - FACE_RADIUS, 0);
    
      // reset angle for mouth 
      mouth.setStartAngle(arcAngle2);

      // set color for eyes and mouth
      leftEye.setColor(Color.WHITE);
      rightEye.setColor(Color.WHITE);
      mouth.setColor(Color.WHITE);

      faceLeft = false;
      faceUp = true;

      face.setColor(Color.BLACK);
      leftEar.setColor(Color.BLACK);
      rightEar.setColor(Color.BLACK);

    }
  } 

  /**
   * Description: This method is going to center mickey to canvas.
   * @param x: center x axis for frame.
   * @param y: center y axis for frame.
   *  return void
   */ 
  public void centerMickey ( double x, double y) {

    // distance that mickey will move
    double center_X_Dis;
    double center_Y_Dis;
   
    // get distance that mickey going to move
    center_X_Dis = x - ( face.getX() + FACE_RADIUS);
    center_Y_Dis = y - ( face.getY() + FACE_RADIUS);
     
    // center mickey
    this.move(center_X_Dis, center_Y_Dis); 
  }
  
}
