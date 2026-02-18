/**
 * A concrete implementation of {@link Fraction} representing a ratio of two
 * integers. The denominator is always stored as a positive integer; if the
 * fraction is negative, the numerator carries the negative sign.
 */
public class FractionImpl implements Fraction {

  private int numerator;
  private int denominator;

  /**
   * Constructs a {@code FractionImpl} with the given numerator and denominator.
   * The fraction is immediately normalized: the denominator is forced positive
   * (sign moved to numerator), and both terms are reduced to lowest form via
   * the GCD.
   *
   * @param numerator   any integer
   * @param denominator any non-zero integer; if negative the sign is absorbed
   *     into the numerator
   * @throws IllegalArgumentException if {@code denominator} is zero
   */
  public FractionImpl(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominator cannot be zero.");
    }
    // Normalize: move negative sign to numerator so denominator is always > 0
    if (denominator < 0) {
      numerator = -numerator;
      denominator = -denominator;
    }
    // Reduce to lowest terms
    int g = gcd(Math.abs(numerator), denominator);
    this.numerator = numerator / g;
    this.denominator = denominator / g;
  }

  /** {@inheritDoc} */
  @Override
  public int getNumerator() {
    return numerator;
  }

  /** {@inheritDoc} */
  @Override
  public int getDenominator() {
    return denominator;
  }

  /** {@inheritDoc} */
  @Override
  public void setNumerator(int n) {
    this.numerator = n;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Delegates to a private helper so validation logic is not duplicated.
   */
  @Override
  public void setDenominator(int d) {
    this.denominator = validatedDenominator(d);
  }

  /**
   * Validates that {@code d} is a legal (positive) denominator value.
   *
   * @param d the candidate denominator
   * @return {@code d} if valid
   * @throws IllegalArgumentException if {@code d} is zero or negative
   */
  private int validatedDenominator(int d) {
    if (d <= 0) {
      throw new IllegalArgumentException("Denominator must be positive, got: " + d);
    }
    return d;
  }

  /** {@inheritDoc} */
  @Override
  public double toDouble() {
    // Cast numerator to double before dividing to avoid integer division
    return (double) numerator / denominator;
  }

  /**
   * Returns a string representation of this fraction in simplest form, e.g.
   * {@code "-3 / 4"} or {@code "2 / 1"}. The fraction is reduced via the GCD
   * before formatting.
   *
   * @return a human-readable fraction string
   */
  @Override
  public String toString() {
    // Fraction is always kept in reduced form, so simply format fields
    return numerator + " / " + denominator;
  }

  /** {@inheritDoc} */
  @Override
  public Fraction reciprocal() {
    if (numerator == 0) {
      throw new IllegalArgumentException("Cannot take reciprocal of zero.");
    }
    // Swap numerator and denominator; FractionImpl constructor handles sign normalization
    return new FractionImpl(denominator, numerator);
  }

  /** {@inheritDoc} */
  @Override
  public Fraction add(Fraction other) {
    // Use cross-multiplication to find a common denominator:
    //   a/b + c/d  =  (a*d + c*b) / (b*d)
    int newNumerator = this.numerator * other.getDenominator()
        + other.getNumerator() * this.denominator;
    int newDenominator = this.denominator * other.getDenominator();
    // The constructor will reduce the result to lowest terms
    return new FractionImpl(newNumerator, newDenominator);
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(Fraction other) {
    // Cross-multiply to compare without floating-point rounding:
    //   a/b vs c/d  =>  a*d vs c*b  (denominators are always positive, so sign is preserved)
    return Integer.compare(
        this.numerator * other.getDenominator(),
        other.getNumerator() * this.denominator);
  }

  /**
   * Returns the greatest common divisor of {@code a} and {@code b} using
   * Euclid's recursive algorithm. Both arguments should be non-negative.
   *
   * @param a first non-negative integer
   * @param b second non-negative integer
   * @return GCD of {@code a} and {@code b}; returns {@code a} when {@code b == 0}
   */
  private static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
}