package lab03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RectangleTest {

  Rectangle rect1, rect2;

  @BeforeEach
  void setUp() {
    rect1 = new Rectangle(5, 6, 2.5, 2);
    rect2 = new Rectangle(2, 3, 10, 10);
  }

  @Test
  void testConstructor() {
    Rectangle r1 = new Rectangle(1, 2, 5, 3);
    assertEquals("Rectangle: LL corner (1.000,2.000) width 5.000 height 3.000", 
                 r1.toString());
    
    Rectangle r2 = new Rectangle(0, 0, 8, 4);
    assertEquals("Rectangle: LL corner (0.000,0.000) width 8.000 height 4.000", 
                 r2.toString());
  }

  @Test
  void area() {
    assertEquals(5, rect1.area(), 0.001);
    assertEquals(100, rect2.area(), 0.001);
  }

  @Test
  void perimeter() {
    assertEquals(9, rect1.perimeter(), 0.001);
    assertEquals(40, rect2.perimeter(), 0.001);
  }

  @Test
  void resize() {
    Shape resized = rect1.resize(12.5);
    assertEquals(12.5 * rect1.area(), resized.area(), 0.001);
    
    Shape resized2 = rect2.resize(0.001);
    assertEquals(0.001 * rect2.area(), resized2.area(), 0.001);
  }

  @Test
  void testToString() {
    assertEquals("Rectangle: LL corner (5.000,6.000) width 2.500 height 2.000", 
                 rect1.toString());
    assertEquals("Rectangle: LL corner (2.000,3.000) width 10.000 height 10.000", 
                 rect2.toString());
  }

  @Test
  void distanceFromOrigin() {
    assertEquals(Math.sqrt(61), rect1.distanceFromOrigin(), 0.001);
    assertEquals(Math.sqrt(13), rect2.distanceFromOrigin(), 0.001);
  }
}