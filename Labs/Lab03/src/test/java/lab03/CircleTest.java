package lab03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircleTest {

  Circle circle1, circle2, circle3;

  @BeforeEach
  void setUp() {
    circle1 = new Circle(3, 4, 5);
    circle2 = new Circle(10.32, 10.43, 10);
    circle3 = new Circle(20);
  }

  @Test
  void testConstructor() {
    Circle c1 = new Circle(5, 7, 3);
    assertEquals("Circle: center (5.000,7.000) radius 3.000", c1.toString());
    
    Circle c2 = new Circle(15);
    assertEquals("Circle: center (0.000,0.000) radius 15.000", c2.toString());
  }

  @Test
  void area() {
    assertEquals(Math.PI * 25, circle1.area(), 0.001);
    assertEquals(Math.PI * 100, circle2.area(), 0.001);
  }

  @Test
  void areaLargeCircle() {
    assertEquals(Math.PI * 400, circle3.area(), 0.001);
    assertEquals(Math.PI * 100, circle2.area(), 0.001);
  }

  @Test
  void perimeter() {
    assertEquals(2 * Math.PI * 5, circle1.perimeter(), 0.001);
    assertEquals(2 * Math.PI * 10, circle2.perimeter(), 0.001);
  }

  @Test
  void perimeterLargeCircle() {
    assertEquals(2 * Math.PI * 20, circle3.perimeter(), 0.001);
    assertEquals(2 * Math.PI * 5, circle1.perimeter(), 0.001);
  }

  @Test
  void resize() {
    Shape resized = circle1.resize(2.5);
    assertEquals(2.5 * circle1.area(), resized.area(), 0.001);
    
    Shape resized2 = circle2.resize(0);
    assertEquals(0, resized2.area(), 0.001);
  }

  @Test
  void testToString() {
    assertEquals("Circle: center (3.000,4.000) radius 5.000", circle1.toString());
    assertEquals("Circle: center (0.000,0.000) radius 20.000", circle3.toString());
  }

  @Test
  void distanceFromOrigin() {
    assertEquals(0.0, circle3.distanceFromOrigin(), 0.001);
    assertEquals(5.0, circle1.distanceFromOrigin(), 0.001);
  }
}