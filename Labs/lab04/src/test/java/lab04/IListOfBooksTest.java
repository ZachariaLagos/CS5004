import static org.junit.jupiter.api.Assertions.*;

import lab04.Book;
import lab04.ElementNode;
import lab04.EmptyNode;
import lab04.IListOfBooks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IListOfBooksTest {

  private Book book1, book2, book3, book4;
  private IListOfBooks emptyList;
  private IListOfBooks list1;
  private IListOfBooks list2;
  private IListOfBooks list3;

  @BeforeEach
  void setUp() {
    book1 = new Book("Gone with the Wind",    "Margaret Mitchell", 1936, 20.80f);
    book2 = new Book("Journey to the West",   "Wu Chengen",        1592, 13.90f);
    book3 = new Book("American Born Chinese", "Gene Luen Yang",    2006, 30.30f);
    book4 = new Book("Pride and Prejudice",   "Jane Austen",       1813,  9.99f);

    emptyList = new EmptyNode();
    list1 = new ElementNode(book1, emptyList);
    list2 = new ElementNode(book2, list1);
    list3 = new ElementNode(book3, new ElementNode(book4, list2));
  }

  @Test
  void count_emptyListIsZero() {
    assertEquals(0, emptyList.count());
  }

  @Test
  void count_singleElement() {
    assertEquals(1, list1.count());
  }

  @Test
  void count_multipleElements() {
    assertEquals(2, list2.count());
    assertEquals(4, list3.count());
  }

  @Test
  void totalPrice_emptyListIsZero() {
    assertEquals(0.0f, emptyList.totalPrice(), 0.01f);
  }

  @Test
  void totalPrice_singleElement() {
    assertEquals(book1.getPrice(), list1.totalPrice(), 0.01f);
  }

  @Test
  void totalPrice_multipleElements() {
    float expected2 = book1.getPrice() + book2.getPrice();
    assertEquals(expected2, list2.totalPrice(), 0.01f);

    float expected3 = book1.getPrice() + book2.getPrice()
        + book3.getPrice() + book4.getPrice();
    assertEquals(expected3, list3.totalPrice(), 0.01f);
  }

  @Test
  void allBefore_emptyListReturnsEmpty() {
    assertNotNull(emptyList.allBefore(2000));
    assertEquals(0, emptyList.allBefore(2000).count());
  }

  @Test
  void allBefore_noBooksQualify() {
    IListOfBooks result = list1.allBefore(1900);
    assertNotNull(result);
    assertEquals(0, result.count());
  }

  @Test
  void allBefore_allBooksQualify() {
    IListOfBooks result = list3.allBefore(2100);
    assertEquals(4, result.count());
  }

  @Test
  void allBefore_someBooksQualify() {
    IListOfBooks result = list3.allBefore(1990);
    assertEquals(3, result.count());
    float expected = book4.getPrice() + book2.getPrice() + book1.getPrice();
    assertEquals(expected, result.totalPrice(), 0.01f);
  }

  @Test
  void allBefore_boundaryYearIsExclusive() {
    IListOfBooks result = list1.allBefore(1936);
    assertEquals(0, result.count());
  }

  @Test
  void addAtEnd_toEmptyList() {
    IListOfBooks result = emptyList.addAtEnd(book1);
    assertNotNull(result);
    assertEquals(1, result.count());
    assertEquals(book1.getPrice(), result.totalPrice(), 0.01f);
  }

  @Test
  void addAtEnd_toSingleElementList() {
    IListOfBooks result = list1.addAtEnd(book2);
    assertEquals(2, result.count());
    assertEquals(book1.getPrice() + book2.getPrice(), result.totalPrice(), 0.01f);
  }

  @Test
  void addAtEnd_preservesOrder() {
    IListOfBooks result = new ElementNode(book1, new EmptyNode()).addAtEnd(book2);
    String s = result.toString();
    assertTrue(s.indexOf(book1.getTitle()) < s.indexOf(book2.getTitle()),
        "book1 should appear before book2 in the string");
  }

  @Test
  void addAtEnd_doesNotMutateOriginal() {
    int before = list2.count();
    list2.addAtEnd(book3);
    assertEquals(before, list2.count(), "Original list should be unchanged");
  }

  @Test
  void toString_emptyList() {
    assertEquals("", emptyList.toString());
  }

  @Test
  void toString_singleElement() {
    String s = list1.toString();
    assertTrue(s.contains(book1.getTitle()));
    assertTrue(s.contains(book1.getAuthor()));
  }

  @Test
  void toString_multipleElementsContainsAllBooks() {
    String s = list3.toString();
    assertTrue(s.contains(book1.getTitle()));
    assertTrue(s.contains(book2.getTitle()));
    assertTrue(s.contains(book3.getTitle()));
    assertTrue(s.contains(book4.getTitle()));
  }
}