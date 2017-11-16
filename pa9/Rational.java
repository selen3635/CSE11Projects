/*
 * Name:   Xiaolong Zhou
 * Login:  cs11faug
 * Date:   11/25/2015
 * File:   Rational.java
 * Source of help: cse 11 homepage
 *       This program is designed for testing and calculating fractions.
 *  If user enter an invalid rational, such as denominator equal to 0,
 *  it will out put a error message. It will also find out great common
 *  divisor and simplify fraction to get a new simplified rational.
 *  There are some more calculations which is add, subtract, mutiply, and
 *  division. These method will fulltill these calculations. Tester will
 *  test all possibilities, if program pass all possibilities that mean
 *  this program is working.
 */



/**
 *       This program is going to test all possible cases by using 40
 *  tester. If user enter an invalid rational, such as denominator equal
 *  to 0, it will out put a error message. If denominator equals to 1, 
 *  then this rational just equals to numerator. It will also find out 
 *  great common divisor and simplify fraction to get a new simplified
 *  rational. There are some more calculation which is add, subtract, 
 *  multiply, and division.
 */
public class Rational {

  // instantce variable for numerator and denominator
  private int numerator, denominator;


  /**
   *    This is default constructor for this program.
   */
  public Rational() {

    // set numerator and denominator
    this.setNumerator(0);

    this.setDenominator(1);

  }

  /**
   *     This is constructor with one parameter.
   * @param num    This represents numerator.
   */
  public Rational(int num) {

    // set numerator, and set denoinator equals to 1
    this.setNumerator(num); 
    this.setDenominator(1);     

  }

  /**
   *     This is constructor with two parameters.
   * @param num     This represents numerator.
   * @param denom   This represents denominator.
   */
  public Rational(int num, int denom) {

    // set numerator and denominator
    this.setNumerator(num);
    this.setDenominator(denom);

    // simplify rational
    reduce();

  }

  /**
   *     This is a copy constructor by passing a object.
   * @param r     This represents a reference of rational
   */
  public Rational ( Rational r) {

    // set numerator and denominator
    this.setNumerator( r.getNumerator());
    this.setDenominator( r.getDenominator());

    // simplify ratoinal
    reduce();

  }
 
  /**
   *    This medthod is going to get numerator.
   * @return  return a integer numerator
   */
  private int getNumerator() {

    // return numerator
    return this.numerator;
  }

  /**
   *   This method is going to get denominator.
   * @return  return a integer denominator
   */
  private int getDenominator() {

    // return denominator
    return this.denominator;
  }

  /**
   *   This method is going to set numerator
   * @param num   This represents numerator
   */
  private void setNumerator(int num) {

    // set numerator
    this.numerator = num;

  }

  /**
   *    This method is going to set denominator.
   * @param denom    This represents denominator
   */
  private void setDenominator(int denom) {

    // check whether denominator equals to 0
    if ( denom == 0) {

      // if yes, throw a error message
      throw new IllegalArgumentException();
    }

    else

      // otherwise, set denominator
      this.denominator = denom;
  }

  /**
   *    This medhod is going to find out great common divisor.
   * @param x   This represents numerator
   * @param y   This represents denominator
   * @return   return a integer of great common divisor.
   */
  private int gcd(int x, int y) {

    // check whether denominator equals to 0 
    if ( y == 0){

      // if yes, then return x
      return x;    

    }

    else
      
      // recursion,
      return gcd(y, x % y);

  }

  /**
   *     This medthod is going to use both numerator and denominator
   * to divide by gcd, in order to reduce rational to get a simplified
   * fraction.
   */
  private void reduce() {

    // local variable
    int num, denom;

    // assign numerator and denominator to local variable
    num = this.getNumerator();
    denom = this.getDenominator();

    // reduce numerator and denominator by dividing gcd
    this.setNumerator( num / gcd( Math.abs(num), Math.abs(denom)));
    this.setDenominator(denom / gcd(Math.abs(num), Math.abs(denom)));

    // check whether both numerator and denominator less than 0
    if ( this.getNumerator() < 0 && this.getDenominator() < 0 ) {

      // if yes, change both sign for them
      this.setNumerator(this.getNumerator() * (-1));
      this.setDenominator(this.getDenominator() * (-1));


    }
 
    // check whether denominator less than 0
    else if( getDenominator() < 0) {

      // if yes, change both sign for them
      this.setNumerator(this.getNumerator() * (-1));
      this.setDenominator(this.getDenominator() * (-1));

    }
  }

  /**
   *    This method is going to add two rationals to get a new fraction
   * @param r    This represents a rational
   * @return   return a new rational
   */
  public Rational add( Rational r) {

    // local variables
    int cd, num;

    // find out common denominator
    cd = this.getDenominator() * r.getDenominator();

    // find out numerator
    num =  this.getNumerator()* r.getDenominator() +
      r.getNumerator() * this.getDenominator();

    // return a new rational
    return new Rational( num, cd);
  }

  /**
   *    This method is going to subtract two rational to get a new rational
   * @param r   This represents a rational
   * @return   return a new rational
   */
  public Rational subtract( Rational r) {

    // local variables
    int cd, num;

    // find out common denominator
    cd = this.getDenominator() * r.getDenominator();

    // find out numerator
    num = this.getNumerator() * r.getDenominator() -
      r.getNumerator() * this.getDenominator();    

    // return a new rational
    return new Rational( num, cd);
  }


  /**
   *    This method is going to multiply two rational to get a new rationl
   * @param r   This represents a rational
   * @return    return a new rational
   */
  public Rational multiply( Rational r) {

    // local variables
    int num, denom;

    // find out numerator
    num = this.getNumerator() * r.getNumerator();

    // find out denominator
    denom = this.getDenominator() * r.getDenominator();

    // return a new rational
    return new Rational( num,  denom);
  }

  /**
   *   This method is going to divide a rational to get a new rational
   * @param  r    This represents a ratoinal
   * @return    return a new rational
   */
  public Rational divide(Rational r) {

    // local variables
    int num, denom;

    // find out numerator
    num = this.getNumerator() * r.getDenominator();

    // find out denominator
    denom = this.getDenominator() * r.getNumerator();

    // return a new rational
    return new Rational( num, denom);

  }

  /**
   *    This method is going to convert a rational to string value.
   * @return   return a string value.
   */
  @Override
  public String toString() {

    // check whether denominator equals to 1
    if ( this.getDenominator() == 1) {

      // if yes, just return numerator to a string
      return "" + getNumerator();

    }

    else {

      // otherwise return numerator and denominator to string value
      return "" + getNumerator() + "/" + getDenominator();

    }

  }

  /**
   *   This medthod is going to convert a rational to a float value.
   * @return   return a float value
   */
  public float toFloat() {

    // use numerator divide by denominator
    return (float)((float)getNumerator() / getDenominator());

  }

  /**
   *    This method is going to check whether both rationals equal to 
   * each other.
   * @param obj   This represents a type of object
   * @return   return true if they are equal, otherwise false
   */
  @Override
  public boolean equals(Object obj) {

    // check whether object equals to null, and they are in same class
    if ( obj != null && obj.getClass() == this.getClass()) {

      // check whether their numerator and denominator are equal to each
      // other
      if (( (Rational)obj).getNumerator() == this.getNumerator() &&
      ((Rational)obj).getDenominator() == this.getDenominator() ) {

      return true;

      }

    }
    return false;

  }

  /**
   *    Computes a has code for this object.
   * @return   return a integer.
   */
  @Override
  public int hashCode() {


    return this.toString().hashCode();
  }

  /**
   *    This method is going to switch numerator and denominator to get
   * a new rational.
   * @return   return a new rational
   */
  public Rational reciprocal() {

    // check whether numerator equals to 0
    if (this.getNumerator() == 0) {

      // if yes, throw an exception
      throw new UnsupportedOperationException();
    }

    else {
    
    // local variables
    int temp1;
    int temp2;

    // assign numerator and denominator to local variables
    temp1 = this.getNumerator();
    temp2 = this.getDenominator();

    // switch numerator and denominator
    this.setNumerator(this.getDenominator());
    this.setDenominator(temp1);   

    // return a new rational
    return new Rational(temp2, temp1);

    }
  }
  
  /**
   *    This medthod is going to change a rational into a whole number
   * by rounding down.
   * @return return a new rational
   */
  public Rational floor() {

    // local variables
    int num, denom, newNum;

    // assign numerator and denominator to local variables
    num = this.getNumerator();
    denom = this.getDenominator();

    // get a new whole number
    newNum = num / denom;

    // return a new rational with whole number
    return new Rational(newNum);

  }

  /**
   *   This method is going to change a rational into a whole number by
   * roundingi up.
   * @return   return a new rational
   */
  public Rational ceiling() {

    // local variables
    int num, denom, newNum;

    // assign numerator and denominator to local variables
    num = this.getNumerator();
    denom = this.getDenominator();

    // get a new whole number by rounding up
    newNum = num / denom + 1;
   
    // return a new rational with whole number
    return new Rational(newNum);

  }
}
