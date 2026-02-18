/**
 * Interface specifying the protocol for a Fraction — a ratio of two integers
 * with a positive denominator. Implements {@link Comparable} to support
 * natural ordering between fractions.
 */
public interface Fraction extends Comparable<Fraction> {

  /**
   * Returns the numerator of this fraction.
   *
   * @return the numerator
   */
  int getNumerator();

  /**
   * Returns the denominator of this fraction. Always positive.
   *
   * @return the denominator (always &gt; 0)
   */
  int getDenominator();

  /**
   * Sets the numerator of this fraction. The fraction's sign is preserved via
   * the numerator; negative fractions have a negative numerator.
   *
   * @param n the new numerator
   */
  void setNumerator(int n);

  /**
   * Sets the denominator of this fraction. The denominator must always be
   * positive.
   *
   * @param d the new denominator
   * @throws IllegalArgumentException if {@code d} is zero or negative
   */
  void setDenominator(int d);

  /**
   * Returns the decimal (floating-point) value of this fraction.
   *
   * @return numerator divided by denominator as a {@code double}
   */
  double toDouble();

  /**
   * Returns the reciprocal of this fraction (i.e., numerator and denominator
   * are swapped). Sign conventions are maintained.
   *
   * @return a new {@link Fraction} representing the reciprocal
   * @throws IllegalArgumentException if the numerator of this fraction is 0,
   *     since 1/0 is undefined
   */
  Fraction reciprocal();

  /**
   * Adds this fraction to {@code other} and returns the result as a new
   * {@link Fraction} in simplest form.
   *
   * @param other the fraction to add; must not be {@code null}
   * @return a new {@link Fraction} equal to {@code this + other}
   */
  Fraction add(Fraction other);

  /**
   * Compares this fraction to {@code other} for order.
   *
   * @param other the fraction to compare against
   * @return a negative integer if {@code this < other}, zero if they are equal,
   *     or a positive integer if {@code this > other}
   */
  @Override
  int compareTo(Fraction other);
}