package lab03;

/**
 * This class represents a triangle. It defines all the operations mandated by
 * the Shape interface.
 */
public class Triangle extends AbstractShape {
  private Point2D point2;
  private Point2D point3;

  /**
   * Constructs a triangle object with three points.
   * The first point is the reference point for distance calculations.
   *
   * @param x1 x coordinate of the first point (reference point)
   * @param y1 y coordinate of the first point (reference point)
   * @param x2 x coordinate of the second point
   * @param y2 y coordinate of the second point
   * @param x3 x coordinate of the third point
   * @param y3 y coordinate of the third point
   * @throws IllegalArgumentException if two or more points are identical
   */
  public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
    super(new Point2D(x1, y1));
    this.point2 = new Point2D(x2, y2);
    this.point3 = new Point2D(x3, y3);

    // Validate that no two points are identical
    if (pointsEqual(reference, point2) || pointsEqual(reference, point3) ||
        pointsEqual(point2, point3)) {
      throw new IllegalArgumentException("Triangle cannot have two or more identical points");
    }
  }

  /**
   * Helper method to check if two points are identical.
   *
   * @param p1 first point
   * @param p2 second point
   * @return true if points have the same coordinates, false otherwise
   */
  private boolean pointsEqual(Point2D p1, Point2D p2) {
    return p1.getX() == p2.getX() && p1.getY() == p2.getY();
  }

  /**
   * Helper method to calculate distance between two points.
   *
   * @param p1 first point
   * @param p2 second point
   * @return the Euclidean distance between the two points
   */
  private double distance(Point2D p1, Point2D p2) {
    double dx = p2.getX() - p1.getX();
    double dy = p2.getY() - p1.getY();
    return Math.sqrt(dx * dx + dy * dy);
  }

  @Override
  public double area() {
    // Calculate the three side lengths
    double a = distance(reference, point2);
    double b = distance(point2, point3);
    double c = distance(point3, reference);

    // Use Heron's formula: Area = sqrt(s(s-a)(s-b)(s-c))
    // where s is the semi-perimeter
    double s = (a + b + c) / 2.0;
    double areaSquared = s * (s - a) * (s - b) * (s - c);

    // Handle collinear points (area would be negative or very small due to floating point)
    if (areaSquared <= 0) {
      return 0;
    }

    return Math.sqrt(areaSquared);
  }

  @Override
  public double perimeter() {
    // Sum of all three side lengths
    double side1 = distance(reference, point2);
    double side2 = distance(point2, point3);
    double side3 = distance(point3, reference);

    return side1 + side2 + side3;
  }

  @Override
  public Shape resize(double factor) {
    // To resize by area factor, we need to scale distances by sqrt(factor)
    // We'll move all points relative to the reference point
    double scaleFactor = Math.sqrt(factor);

    // Calculate vectors from reference to other points
    double dx2 = point2.getX() - reference.getX();
    double dy2 = point2.getY() - reference.getY();
    double dx3 = point3.getX() - reference.getX();
    double dy3 = point3.getY() - reference.getY();

    // Scale the vectors
    double newX2 = reference.getX() + dx2 * scaleFactor;
    double newY2 = reference.getY() + dy2 * scaleFactor;
    double newX3 = reference.getX() + dx3 * scaleFactor;
    double newY3 = reference.getY() + dy3 * scaleFactor;

    return new Triangle(reference.getX(), reference.getY(), newX2, newY2, newX3, newY3);
  }

  @Override
  public String toString() {
    return String.format("Triangle: points (%.3f,%.3f), (%.3f,%.3f), (%.3f,%.3f)",
        reference.getX(), reference.getY(),
        point2.getX(), point2.getY(),
        point3.getX(), point3.getY());
  }
}