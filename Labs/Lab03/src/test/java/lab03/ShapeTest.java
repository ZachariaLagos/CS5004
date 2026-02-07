package lab03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShapeTest {

  Shape circle1, circle2, circle3, rect1, rect2, triangle1, triangle2;

  @BeforeEach
  void setUp() {
    circle1 = new Circle(3, 4, 5);
    circle2 = new Circle(10.32, 10.43, 10);
    circle3 = new Circle(20);
    rect1 = new Rectangle(5, 6, 2.5, 2);
    rect2 = new Rectangle(2, 3, 10, 10);
    triangle1 = new Triangle(0, 0, 4, 0, 0, 3);
    triangle2 = new Triangle(1, 1, 5, 1, 3, 4);
  }

  /**
   * Test compareTo with all three outcomes: negative, positive, and zero.
   * This test verifies all three return values as required by rubric.
   */
  @Test
  void testCompareTo() {
    // Test negative outcome: smaller area returns negative
    assertTrue(rect1.compareTo(circle1) < 0);

    // Test positive outcome: larger area returns positive
    assertTrue(circle1.compareTo(rect1) > 0);

    // Test zero outcome: equal areas return zero
    Shape rect3 = new Rectangle(0, 0, 5, 2);  // area = 10
    Shape rect4 = new Rectangle(1, 1, 2, 5);  // area = 10
    assertEquals(0, rect3.compareTo(rect4));
  }

  @Test
  void testCompareToSmallerArea() {
    assertTrue(rect1.compareTo(circle1) < 0);
    assertTrue(triangle1.compareTo(circle2) < 0);
  }

  @Test
  void testCompareToLargerArea() {
    assertTrue(circle1.compareTo(rect1) > 0);
    assertTrue(rect2.compareTo(triangle1) > 0);
  }

  @Test
  void testCompareToEqualArea() {
    // Create two rectangles with equal area
    Shape rect3 = new Rectangle(0, 0, 5, 2);  // area = 10
    Shape rect4 = new Rectangle(1, 1, 2, 5);  // area = 10
    assertEquals(0, rect3.compareTo(rect4));
    assertEquals(0, rect4.compareTo(rect3));
  }

  @Test
  void testCompareToTransitivity() {
    assertTrue(triangle1.compareTo(rect2) < 0);
    assertTrue(rect2.compareTo(circle2) < 0);
    assertTrue(triangle1.compareTo(circle2) < 0);
  }

  @Test
  void distanceFromOrigin() {
    assertEquals(0.0, circle3.distanceFromOrigin(), 0.001);
    assertEquals(0.0, triangle1.distanceFromOrigin(), 0.001);
  }

  @Test
  void distanceFromOriginAwayFromOrigin() {
    assertEquals(5.0, circle1.distanceFromOrigin(), 0.001);
    assertEquals(Math.sqrt(2), triangle2.distanceFromOrigin(), 0.001);
  }

  @Test
  void testResizeMaintainsType() {
    Shape resizedCircle = circle1.resize(2.0);
    assertTrue(resizedCircle instanceof Circle);

    Shape resizedRect = rect1.resize(3.0);
    assertTrue(resizedRect instanceof Rectangle);
  }

  @Test
  void testResizeMaintainsTypeTriangle() {
    Shape resizedTriangle = triangle1.resize(4.0);
    assertTrue(resizedTriangle instanceof Triangle);

    Shape resizedCircle = circle2.resize(0.5);
    assertTrue(resizedCircle instanceof Circle);
  }

  @Test
  void testAreaPositive() {
    assertTrue(circle1.area() > 0);
    assertTrue(rect1.area() > 0);
  }

  @Test
  void testPerimeterPositive() {
    assertTrue(triangle1.perimeter() > 0);
    assertTrue(rect2.perimeter() > 0);
  }

  @Test
  void testResizeIncrease() {
    double factor = 2.5;
    Shape resizedCircle = circle1.resize(factor);
    assertTrue(resizedCircle.area() > circle1.area());

    Shape resizedRect = rect1.resize(factor);
    assertTrue(resizedRect.area() > rect1.area());
  }

  @Test
  void testResizeDecrease() {
    double factor = 0.5;
    Shape resizedCircle = circle2.resize(factor);
    assertTrue(resizedCircle.area() < circle2.area());

    Shape resizedTriangle = triangle2.resize(factor);
    assertTrue(resizedTriangle.area() < triangle2.area());
  }

  @Test
  void testCrossShapeComparison() {
    assertNotEquals(0, circle1.compareTo(rect1));
    assertNotEquals(0, rect2.compareTo(triangle1));
  }
}