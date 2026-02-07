package lab03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TriangleTest {

  Triangle triangle1, triangle2, triangle3, degenerateTriangle;

  @BeforeEach
  void setUp() {
    triangle1 = new Triangle(0, 0, 4, 0, 0, 3);
    triangle2 = new Triangle(1, 1, 5, 1, 3, 4);
    triangle3 = new Triangle(0, 0, 6, 0, 3, 6);
    degenerateTriangle = new Triangle(0, 0, 2, 0, 4, 0);
  }

  @Test
  void testConstructorWithDuplicatePoints() {
    // Test duplicate point 1 and 2
    assertThrows(IllegalArgumentException.class, () -> {
      new Triangle(1, 2, 1, 2, 3, 4);
    });
    
    // Test duplicate point 1 and 3
    assertThrows(IllegalArgumentException.class, () -> {
      new Triangle(1, 2, 3, 4, 1, 2);
    });
  }

  @Test
  void testConstructorValid() {
    Triangle t = new Triangle(0, 0, 1, 0, 0, 1);
    assertEquals(0.5, t.area(), 0.001);
    assertEquals("Triangle: points (0.000,0.000), (1.000,0.000), (0.000,1.000)", 
                 t.toString());
  }

  @Test
  void area() {
    // Test right triangle (3-4-5 triangle)
    assertEquals(6.0, triangle1.area(), 0.001);
    // Test general triangle
    assertEquals(6.0, triangle2.area(), 0.001);
  }

  @Test
  void areaLargeTriangle() {
    // Test larger triangle
    assertEquals(18.0, triangle3.area(), 0.001);
    // Test degenerate triangle (collinear points)
    assertEquals(0.0, degenerateTriangle.area(), 0.001);
  }

  @Test
  void perimeter() {
    // Test 3-4-5 right triangle: perimeter = 3 + 4 + 5 = 12
    assertEquals(12.0, triangle1.perimeter(), 0.001);
    // Test general triangle
    double expected = 4 + 2 * Math.sqrt(13);
    assertEquals(expected, triangle2.perimeter(), 0.001);
  }

  @Test
  void perimeterDegenerate() {
    // Test degenerate triangle
    assertEquals(8.0, degenerateTriangle.perimeter(), 0.001);
    // Test larger triangle
    double expected = 6 + 2 * Math.sqrt(45);
    assertEquals(expected, triangle3.perimeter(), 0.001);
  }

  @Test
  void resize() {
    Shape resized1 = triangle1.resize(4.0);
    assertEquals(4.0 * triangle1.area(), resized1.area(), 0.001);
    
    Shape resized2 = triangle2.resize(0.25);
    assertEquals(0.25 * triangle2.area(), resized2.area(), 0.001);
  }

  @Test
  void resizePerimeterScaling() {
    double factor = 2.5;
    Shape resized = triangle1.resize(factor);
    assertEquals(Math.sqrt(factor) * triangle1.perimeter(), 
                 resized.perimeter(), 0.001);
    
    Shape resized2 = triangle3.resize(1.0);
    assertEquals(triangle3.area(), resized2.area(), 0.001);
  }

  @Test
  void testToString() {
    assertEquals("Triangle: points (0.000,0.000), (4.000,0.000), (0.000,3.000)", 
                 triangle1.toString());
    assertEquals("Triangle: points (1.000,1.000), (5.000,1.000), (3.000,4.000)", 
                 triangle2.toString());
  }

  @Test
  void distanceFromOrigin() {
    // triangle1 has reference at (0,0)
    assertEquals(0.0, triangle1.distanceFromOrigin(), 0.001);
    // triangle2 has reference at (1,1)
    assertEquals(Math.sqrt(2), triangle2.distanceFromOrigin(), 0.001);
  }
}