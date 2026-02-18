import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test suite for {@link FractionImpl}. Covers every public method,
 * including edge cases and expected exceptions.
 */
class FractionImplTest {

  private Fraction half;
  private Fraction third;
  private Fraction negHalf;
  private Fraction zero;
  private Fraction whole;

  /** Creates fresh fraction instances before each test. */
  @BeforeEach
  void setUp() {
    half    = new FractionImpl(1, 2);
    third   = new FractionImpl(1, 3);
    negHalf = new FractionImpl(-1, 2);
    zero    = new FractionImpl(0, 5);
    whole   = new FractionImpl(6, 2);
  }

  /** Positive numerator and denominator — basic happy path. */
  @Test
  void constructor_positiveValues() {
    Fraction f = new FractionImpl(3, 4);
    assertEquals(3, f.getNumerator());
    assertEquals(4, f.getDenominator());
  }

  /** Fraction is reduced to lowest terms in the constructor. */
  @Test
  void constructor_reducesToLowestTerms() {
    Fraction f = new FractionImpl(4, 2);
    assertEquals(2, f.getNumerator());
    assertEquals(1, f.getDenominator());
  }

  /** Negative numerator is kept negative; denominator stays positive. */
  @Test
  void constructor_negativeNumerator() {
    assertEquals(-1, negHalf.getNumerator());
    assertEquals(2,  negHalf.getDenominator());
  }

  /** Negative denominator: sign is moved to numerator, denominator flipped positive. */
  @Test
  void constructor_negativeDenominator() {
    Fraction f = new FractionImpl(1, -2);
    assertEquals(-1, f.getNumerator());
    assertEquals(2,  f.getDenominator());
  }

  /** Both negative — double negative yields a positive fraction. */
  @Test
  void constructor_bothNegative() {
    Fraction f = new FractionImpl(-3, -4);
    assertEquals(3, f.getNumerator());
    assertEquals(4, f.getDenominator());
  }

  /** Zero numerator should work; denominator normalizes to 1 after GCD reduction. */
  @Test
  void constructor_zeroNumerator() {
    assertEquals(0, zero.getNumerator());
    assertEquals(1, zero.getDenominator());
  }

  /** Zero denominator must throw {@link IllegalArgumentException}. */
  @Test
  void constructor_zeroDenominatorThrows() {
    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(1, 0));
  }

  /** Zero numerator with valid denominator is legal — must not throw. */
  @Test
  void constructor_zeroNumeratorDoesNotThrow() {
    assertDoesNotThrow(() -> new FractionImpl(0, 1));
  }

  /** Negative numerator with positive denominator is legal — must not throw. */
  @Test
  void constructor_negativeNumeratorDoesNotThrow() {
    assertDoesNotThrow(() -> new FractionImpl(-3, 4));
  }

  /** Negative denominator is legal (sign is absorbed) — must not throw. */
  @Test
  void constructor_negativeDenominatorDoesNotThrow() {
    assertDoesNotThrow(() -> new FractionImpl(3, -4));
  }

  /** setNumerator correctly updates the stored value. */
  @Test
  void setNumerator_updatesValue() {
    half.setNumerator(3);
    assertEquals(3, half.getNumerator());
  }

  /** setDenominator correctly updates the stored value. */
  @Test
  void setDenominator_updatesValue() {
    half.setDenominator(7);
    assertEquals(7, half.getDenominator());
  }

  /** setDenominator with zero must throw. */
  @Test
  void setDenominator_zeroThrows() {
    assertThrows(IllegalArgumentException.class, () -> half.setDenominator(0));
  }

  /** setDenominator with a negative value must throw. */
  @Test
  void setDenominator_negativeThrows() {
    assertThrows(IllegalArgumentException.class, () -> half.setDenominator(-3));
  }

  /** After a failed setDenominator, the original denominator is unchanged. */
  @Test
  void setDenominator_invariantPreservedAfterException() {
    assertThrows(IllegalArgumentException.class, () -> half.setDenominator(-1));
    assertEquals(2, half.getDenominator());
  }

  /** setDenominator with a positive value must not throw. */
  @Test
  void setDenominator_validValueDoesNotThrow() {
    assertDoesNotThrow(() -> half.setDenominator(5));
  }

  @Test
  void toDouble_half() {
    assertEquals(0.5, half.toDouble(), 1e-9);
  }

  @Test
  void toDouble_negativeHalf() {
    assertEquals(-0.5, negHalf.toDouble(), 1e-9);
  }

  @Test
  void toDouble_zero() {
    assertEquals(0.0, zero.toDouble(), 1e-9);
  }

  @Test
  void toDouble_wholeNumber() {
    assertEquals(3.0, whole.toDouble(), 1e-9);
  }

  /** Basic simplification: 4/2 → "2 / 1". */
  @Test
  void toString_reducedForm() {
    Fraction f = new FractionImpl(4, 2);
    assertEquals("2 / 1", f.toString());
  }

  /** Negative fraction shows negative numerator. */
  @Test
  void toString_negativeFraction() {
    assertEquals("-1 / 2", negHalf.toString());
  }

  /** Zero numerator. */
  @Test
  void toString_zero() {
    assertEquals("0 / 1", zero.toString());
  }

  /** Fraction already in lowest terms. */
  @Test
  void toString_alreadySimplest() {
    assertEquals("1 / 3", third.toString());
  }

  /** gcd works correctly: 12/6 reduces to 2/1. */
  @Test
  void gcd_basicCase() {
    assertEquals("2 / 1", new FractionImpl(12, 6).toString());
  }

  /** gcd of coprimes: 7/13 stays 7/13. */
  @Test
  void gcd_coprimes() {
    assertEquals("7 / 13", new FractionImpl(7, 13).toString());
  }

  /** gcd when numerator is 0: 0/5 reduces to 0/1. */
  @Test
  void gcd_zeroNumerator() {
    assertEquals("0 / 1", new FractionImpl(0, 5).toString());
  }

  /** gcd when denominator is 1: fraction is already in lowest terms. */
  @Test
  void gcd_denominatorOne() {
    assertEquals("7 / 1", new FractionImpl(7, 1).toString());
  }

  @Test
  void reciprocal_positiveHalf() {
    Fraction r = half.reciprocal();
    assertEquals(2, r.getNumerator());
    assertEquals(1, r.getDenominator());
  }

  @Test
  void reciprocal_negativeHalf() {
    Fraction r = negHalf.reciprocal();
    assertEquals(-2, r.getNumerator());
    assertEquals(1,  r.getDenominator());
  }

  @Test
  void reciprocal_wholeNumber() {
    Fraction r = whole.reciprocal();
    assertEquals(1, r.getNumerator());
    assertEquals(3, r.getDenominator());
  }

  /** Reciprocal of zero must throw {@link IllegalArgumentException}. */
  @Test
  void reciprocal_zeroThrows() {
    assertThrows(IllegalArgumentException.class, () -> zero.reciprocal());
  }

  /** 1/2 + 1/2 = 1/1 */
  @Test
  void add_sameDenominators() {
    Fraction result = half.add(half);
    assertEquals("1 / 1", result.toString());
  }

  /** 1/2 + 1/3 = 5/6 */
  @Test
  void add_differentDenominators() {
    Fraction result = half.add(third);
    assertEquals(5, result.getNumerator());
    assertEquals(6, result.getDenominator());
  }

  /** 1/2 + (-1/2) = 0/1 */
  @Test
  void add_positiveAndNegative() {
    Fraction result = half.add(negHalf);
    assertEquals(0, result.getNumerator());
    assertEquals(1, result.getDenominator());
  }

  /** Adding zero leaves fraction unchanged. */
  @Test
  void add_addingZero() {
    Fraction result = half.add(zero);
    assertEquals("1 / 2", result.toString());
  }

  /** (-1/2) + (-1/3) = -5/6 */
  @Test
  void add_bothNegative() {
    Fraction result = negHalf.add(new FractionImpl(-1, 3));
    assertEquals(-5, result.getNumerator());
    assertEquals(6,  result.getDenominator());
  }

  /** Result is reduced: 1/4 + 1/4 = 1/2, not 2/4. */
  @Test
  void add_resultIsReduced() {
    Fraction quarter = new FractionImpl(1, 4);
    Fraction result  = quarter.add(quarter);
    assertEquals("1 / 2", result.toString());
  }

  /** 1/2 < 3/1 → negative result */
  @Test
  void compareTo_lessThan() {
    assertTrue(half.compareTo(whole) < 0);
  }

  /** 3/1 > 1/2 → positive result */
  @Test
  void compareTo_greaterThan() {
    assertTrue(whole.compareTo(half) > 0);
  }

  /** 1/2 == 2/4 → zero */
  @Test
  void compareTo_equal() {
    Fraction twoFourths = new FractionImpl(2, 4);
    assertEquals(0, half.compareTo(twoFourths));
  }

  /** Negative vs positive: -1/2 < 1/2 */
  @Test
  void compareTo_negativeVsPositive() {
    assertTrue(negHalf.compareTo(half) < 0);
  }

  /** Both negative: -1/3 > -1/2 (closer to zero) */
  @Test
  void compareTo_bothNegative() {
    Fraction negThird = new FractionImpl(-1, 3);
    assertTrue(negThird.compareTo(negHalf) > 0);
  }

  /** Comparing with zero: 0 < 1/2 */
  @Test
  void compareTo_withZero() {
    assertTrue(zero.compareTo(half) < 0);
  }

  /**
   * Large-value compareTo: verifies correctness when cross-multiplication
   * approaches integer limits. Result is cross-checked against toDouble()
   * to confirm the sign is correct.
   */
  @Test
  void compareTo_largeValues() {
    Fraction a = new FractionImpl(999_999, 1_000_000);
    Fraction b = new FractionImpl(1_000_000, 1_000_001);
    int result = a.compareTo(b);
    // Cross-check sign against floating-point comparison
    int expected = Double.compare(a.toDouble(), b.toDouble());
    assertEquals(expected < 0 ? -1 : (expected > 0 ? 1 : 0),
        result < 0 ? -1 : (result > 0 ? 1 : 0));
  }
}