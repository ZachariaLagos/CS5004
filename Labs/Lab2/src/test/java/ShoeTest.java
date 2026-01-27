import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class ShoeTest {
  private Shoe shoe1;
  private Shoe shoe2;

  @BeforeEach
  void setUp() {
    this.shoe1 = new Shoe(Kind.SNEAKER, Color.RED, Brand.NIKE, 8.5);
    this.shoe2 = new Shoe(Kind.BOOT, Color.WHITE, Brand.UGG, 7.0);
  }

  @Test
  void getKind() {
    Assertions.assertEquals(Kind.SNEAKER, this.shoe1.getKind());
    Assertions.assertEquals(Kind.BOOT, this.shoe2.getKind());
  }

  @Test
  void getColor() {
    Assertions.assertEquals(Color.RED, this.shoe1.getColor());
    Assertions.assertEquals(Color.WHITE, this.shoe2.getColor());
  }

  @Test
  void getBrand() {
    Assertions.assertEquals(Brand.NIKE, this.shoe1.getBrand());
    Assertions.assertEquals(Brand.UGG, this.shoe2.getBrand());
  }

  @Test
  void getSize() {
    Assertions.assertEquals(8.5, this.shoe1.getSize());
    Assertions.assertEquals(7.0, this.shoe2.getSize());
  }

  @Test
  void testToString() {
    Assertions.assertEquals("The sneaker is of Nike (dramatic color, size 8.5)" , this.shoe1.toString());
    Assertions.assertEquals("The boot is of UGG (white color, size 7.0)" , this.shoe2.toString());
  }

  @Test
  void throwsException() {
    IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
        () -> new Shoe(Kind.DRESS, Color.BLACK, Brand.NIKE, 9.5));
    System.out.println("Wrong argument: " + ex1.getMessage());
    assertEquals("NIKE does not sell dress shoes", ex1.getMessage());
    IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
        () -> new Shoe(Kind.BOOT, Color.BLACK, Brand.UGG, 7.5));
    System.out.println("Wrong argument: " + ex2.getMessage());
    assertEquals("UGG does not sell black shoes", ex2.getMessage());
  }
}

