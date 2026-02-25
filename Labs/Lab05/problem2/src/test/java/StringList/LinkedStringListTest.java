package StringList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedStringListTest {

  private LinkedStringList emptyList;
  private LinkedStringList list;

  @BeforeEach
  void setUp() {
    emptyList = new LinkedStringList();

    list = new LinkedStringList();
    list.add("apple");
    list.add("banana");
    list.add("cherry");
  }

  // isEmpty
  @Test
  void testIsEmptyTrue() {
    assertTrue(emptyList.isEmpty());
  }

  @Test
  void testIsEmptyFalse() {
    assertFalse(list.isEmpty());
  }

  @Test
  void testIsEmptyAfterAdd() {
    emptyList.add("hello");
    assertFalse(emptyList.isEmpty());
  }

  // size
  @Test
  void testSizeEmpty() {
    assertEquals(0, emptyList.size());
  }

  @Test
  void testSizeNonEmpty() {
    assertEquals(3, list.size());
  }

  @Test
  void testSizeAfterAdd() {
    list.add("date");
    assertEquals(4, list.size());
  }

  // contains
  @Test
  void testContainsTrue() {
    assertTrue(list.contains("apple"));
    assertTrue(list.contains("banana"));
    assertTrue(list.contains("cherry"));
  }

  @Test
  void testContainsFalse() {
    assertFalse(list.contains("grape"));
    assertFalse(emptyList.contains("apple"));
  }

  // containsAll
  @Test
  void testContainsAllTrue() {
    LinkedStringList other = new LinkedStringList();
    other.add("apple");
    other.add("banana");
    assertTrue(list.containsAll(other));
  }

  @Test
  void testContainsAllFalse() {
    LinkedStringList other = new LinkedStringList();
    other.add("apple");
    other.add("grape");
    assertFalse(list.containsAll(other));
  }

  @Test
  void testContainsAllEmpty() {
    assertTrue(list.containsAll(emptyList));
  }

  // filterLargerThan
  @Test
  void testFilterLargerThan() {
    StringList filtered = list.filterLargerThan(5);
    assertTrue(filtered.contains("apple"));
    assertFalse(filtered.contains("banana"));
    assertFalse(filtered.contains("cherry"));
  }

  @Test
  void testFilterLargerThanNoneRemoved() {
    StringList filtered = list.filterLargerThan(10);
    assertEquals(3, filtered.size());
  }

  @Test
  void testFilterLargerThanAllRemoved() {
    StringList filtered = list.filterLargerThan(0);
    assertEquals(0, filtered.size());
  }

  @Test
  void testFilterLargerThanEmpty() {
    StringList filtered = emptyList.filterLargerThan(5);
    assertTrue(filtered.isEmpty());
  }

  // hasDuplicates
  @Test
  void testHasDuplicatesTrue() {
    list.add("apple");
    assertTrue(list.hasDuplicates());
  }

  @Test
  void testHasDuplicatesFalse() {
    assertFalse(list.hasDuplicates());
  }

  @Test
  void testHasDuplicatesEmpty() {
    assertFalse(emptyList.hasDuplicates());
  }

  // removeDuplicates
  @Test
  void testRemoveDuplicates() {
    list.add("apple");
    list.add("banana");
    StringList result = list.removeDuplicates();
    assertEquals(3, result.size());
    assertFalse(result.hasDuplicates());
  }

  @Test
  void testRemoveDuplicatesNoDuplicates() {
    StringList result = list.removeDuplicates();
    assertEquals(3, result.size());
  }

  @Test
  void testRemoveDuplicatesEmpty() {
    StringList result = emptyList.removeDuplicates();
    assertTrue(result.isEmpty());
  }

  // equals and hashCode
  @Test
  void testEqualsSameObject() {
    assertEquals(list, list);
  }

  @Test
  void testEqualsIdentical() {
    LinkedStringList list2 = new LinkedStringList();
    list2.add("apple");
    list2.add("banana");
    list2.add("cherry");
    assertEquals(list, list2);
    assertEquals(list.hashCode(), list2.hashCode());
  }

  @Test
  void testEqualsDifferent() {
    LinkedStringList list2 = new LinkedStringList();
    list2.add("grape");
    assertNotEquals(list, list2);
  }

  @Test
  void testEqualsNull() {
    assertNotEquals(null, list);
  }

  // toString
  @Test
  void testToStringEmpty() {
    assertEquals("[]", emptyList.toString());
  }

  @Test
  void testToStringNonEmpty() {
    String result = list.toString();
    assertTrue(result.contains("apple"));
    assertTrue(result.contains("banana"));
    assertTrue(result.contains("cherry"));
  }
}