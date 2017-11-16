

import Acme.*;
import objectdraw.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SnakeController extends WindowController
                             implements ActionListener, KeyListener {

  private static final int Y_PADDING = 50;
  private static final int LINUX_PADDING = 6;
  private static final int MIN_DIM = 500;
  private static final int MAX_DIM = 800;
  private static final int MAX_SPEED = 100;
  private static final int MIN_SPEED = 1000;
  private static final int MIN_SIZE = 20;
  private static final int MAX_SIZE = 400;
  private static final int NUM_TOP_COLUMNS = 2;
  private static final int NUM_TOP_ROWS = 1;
  private static final int FONT_SIZE = 40;
  private static final int SCORE = 100;

  private static final int HALF = 2;
  private static final int THREE = 3;

  private static int dimensions;

  private int side;
  private int size;
  private int delay;
  private int seed;
  private int score;
  private int highScore;

  private JLabel scoreLabel;
  private JLabel highScoreLabel;
  
  private JButton newgame;

  private Text gameOverText;
  private Text winText;
  private Text pauseText;

  private JPanel northPanel;
  private JPanel southPanel;

  private boolean gameOver;
  private boolean won;

  private boolean paused;

  private Snake snake;

  private Coordinate coord;

  private FilledRect apple;

  private Random randomIndexGenerator = new Random();

  ArrayList<Coordinate> openSpaces = new ArrayList<Coordinate>();
  ArrayList<Coordinate> nonOpenSpace = new ArrayList<Coordinate>();


  private SnakeController( String [] args) {

    if ( args.length != THREE) {

      System.out.printf( PA8Strings.USAGE);

      System.exit(1);

    }

    this.dimensions = Integer.parseInt(args[0]);
    this.size = Integer.parseInt(args[1]);
    this.delay = Integer.parseInt(args[HALF]);


    if ( dimensions < MIN_DIM || dimensions > MAX_DIM) {

      System.out.printf( PA8Strings.OUT_OF_RANGE, dimensions, MIN_DIM,
          MAX_DIM);

      System.exit(1);

    }

    if ( size < MIN_SIZE || size > MAX_SIZE ) {

      System.out.printf(PA8Strings.OUT_OF_RANGE, size, MIN_SIZE, MAX_SIZE);
      System.out.printf("");
      System.out.printf( PA8Strings.USAGE);

      System.exit(1);

    }

    if ( size >= MIN_SIZE && size <= MAX_SIZE ) {

      if ( size > (dimensions / HALF)) {

        System.out.printf(PA8Strings.SEG_TOO_LARGE, size, dimensions,
          dimensions);
        System.out.printf("");
        System.out.printf(PA8Strings.USAGE);

        System.exit(1);

      }
      
      else if ( (dimensions % size) != 0) {

        System.out.printf(PA8Strings.SEG_DOES_NOT_FIT, size, dimensions,
          dimensions);
        System.out.printf("");

        System.out.printf(PA8Strings.USAGE);

        System.exit(1);

      } 

    }

    if ( delay < MAX_SPEED || delay > MIN_SPEED) {

      System.out.printf(PA8Strings.USAGE);

      System.exit(1);

    }

    side = dimensions;

    for ( int i = 0; i < side / size; i++ ) {

      for( int j = 0; j < side / size; j++) {

        Coordinate coord = new Coordinate(i * size , j * size);

        openSpaces.add(coord);

      }

    }

  }


  public void begin() {

    coord = new Coordinate(size, size);

    // create new panels
    northPanel = new JPanel();
    southPanel = new JPanel();

    // add north and south panel to the canvas
    this.add( northPanel, BorderLayout.NORTH);
    this.add( southPanel, BorderLayout.SOUTH);

    // set up layout for panels
    northPanel.setLayout( new GridLayout(1, HALF));
    
    //create new labels
    scoreLabel = new JLabel("Score: " + score);
    highScoreLabel = new JLabel("Hight Score: " + highScore);

    // label to north panel
    northPanel.add(scoreLabel);
    northPanel.add(highScoreLabel);

    // create new button
    newgame = new JButton("New Game");

    // add button to south panel
    southPanel.add(newgame);

    // add listener to button
    newgame.addActionListener(this);

    canvas.addKeyListener(this);

    this.validate();


    snake = new Snake(coord, size, delay, canvas, this);

    canvas.addKeyListener(snake);

    gameOverText = new Text("GAMVE OVER", canvas.getWidth() / HALF,
        canvas.getHeight() / HALF, canvas); 
    
    gameOverText.setFontSize(FONT_SIZE);

    gameOverText.moveTo( canvas.getWidth() / HALF -
        gameOverText.getWidth() / HALF
        , canvas.getHeight() / HALF- 
        gameOverText.getHeight() / HALF);

    pauseText = new Text("PAUSED", canvas.getWidth() / HALF,
        canvas.getHeight() / HALF, canvas);

    pauseText.setFontSize(FONT_SIZE);

    pauseText.moveTo( canvas.getWidth() / HALF -
        pauseText.getWidth() / HALF
        , canvas.getHeight() / HALF- 
        pauseText.getHeight() / HALF);


    winText = new Text("YOU WON", canvas.getWidth() / HALF,
        canvas.getHeight() / HALF, canvas);

    winText.setFontSize(FONT_SIZE);

    winText.moveTo( canvas.getWidth() / HALF -
        winText.getWidth() / HALF
        , canvas.getHeight() / HALF- 
        winText.getHeight() / HALF);

    gameOverText.hide();
    pauseText.hide();
    winText.hide();

    canvas.requestFocusInWindow();
  }

  public static void main( String [] args) {

    new Acme.MainFrame( new SnakeController(args), args, dimensions,
        dimensions +  Y_PADDING);

  }


  public void keyPressed(KeyEvent e) {

    int keyCode = e.getKeyCode();

    if ( keyCode == KeyEvent.VK_SPACE) {

      if ( paused == false ) {
        
        paused = true;
        snake.pauseGame();

        pauseText.show();
      }

      else if ( paused == true) {

        paused = false;

        snake.pauseGame();

        pauseText.hide();
      }

    }

  }

  public void keyReleased(KeyEvent e) {


  }

  public void keyTyped(KeyEvent e) {


  }

  public void actionPerformed( ActionEvent evt) {

    Object source = evt.getSource();

    if ( source == newgame ) {

      canvas.clear();

      apple = null;
      snake = new Snake( coord, size, delay, canvas, this);
      restartGame();
      
    }

    canvas.requestFocusInWindow();
  }

  public Coordinate placeApple(DrawingCanvas canvas) {

    int maxVal = side / size;

    if ( snake!= null) {

      ArrayList<Coordinate> nonOpenSpace = snake.snakeCoordinate();
      
      for( Coordinate  snakeCoord : nonOpenSpace) {

        openSpaces.remove(snakeCoord);
      }
    }

    int appleIndex = randomIndexGenerator.nextInt(openSpaces.size());

    Coordinate appleCoord = new Coordinate(openSpaces.get(appleIndex));

   //Location appleLoc = new Location(appleCoord.getX(), appleCoord.getY());

    if ( apple != null) {

      apple.removeFromCanvas();

    }

    apple = new FilledRect( appleCoord.getX(), appleCoord.getY(), size,
        size, canvas);
    apple.setColor(Color.RED);


    return appleCoord;
  }

  public void restartGame() {
 
    if ( snake != null) {

    snake.newGame();  

    updateScore();
    paused = false;

    
    }

    canvas.addKeyListener(this);
    canvas.addKeyListener(snake);

    canvas.requestFocusInWindow();
  }

  public void updateScore() {

    if ( highScore < score) {

      highScore = score;

      highScoreLabel.setText("High Score: " + highScore);
    }

  }

  public void sumScore() {

    score = score + SCORE;

    scoreLabel.setText("Score: " + score);

  }


}
