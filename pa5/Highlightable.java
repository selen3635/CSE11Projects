 /**
  * Name:    Xiaolong Zhou
  * Login:   cs11faug
  * Date:    Oct 25, 2015
  * File:    Puzzle.java
  * Source of help: cse11 homepage, Vinh Doan, Karan
  * Description: This is inferface for program which contain two
  *              method prototype.
  */

import java.awt.Color;


/**
 * Description: This is inferface for program which contain two
 *              methods prototype.
 */
public interface Highlightable {

  /**
   * Description: This method is going to show border highlight.
   * @param color: This represents color for border
   * return void
   */
  public abstract void showHighlight( Color color);
  
  /**
   * Description: This method is going to hide border highlight.
   */
  public abstract void hideHighlight();

}
