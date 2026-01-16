import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {
  private Book goneWithTheWind;
  private Book journeyToTheWest;
  private Book americanBornChinese;



  @BeforeEach
  void setUp() {
    this.goneWithTheWind = new Book("Gone with the wind", "Margaret Mitchell",
        "1000");
    this.journeyToTheWest = new Book("Journey to the west", "Wu Chengen",
        "600");
    this.americanBornChinese = new Book("American born Chinese", "Gene Luen Yang",
        "250");
  }

  @Test
  void getTitle() {
    Assertions.assertEquals("Gone with the wind", this.goneWithTheWind.getTitle());
    Assertions.assertEquals("Journey to the west", this.journeyToTheWest.getTitle());
    Assertions.assertEquals("American born Chinese", this.americanBornChinese.getTitle());
  }

  @Test
  void getAuthor() {
    Assertions.assertEquals("Margaret Mitchell", this.goneWithTheWind.getAuthor());
    Assertions.assertEquals("Wu Chengen", this.journeyToTheWest.getAuthor());
    Assertions.assertEquals("Gene Luen Yang", this.americanBornChinese.getAuthor());
  }

  @Test
  void getPages() {
    Assertions.assertEquals("1000", this.goneWithTheWind.getPages());
    Assertions.assertEquals("600", this.journeyToTheWest.getPages());
    Assertions.assertEquals("250", this.americanBornChinese.getPages());
  }
}