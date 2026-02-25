package StringList;

/**
 * Linked list implementation of the StringList ADT.
 */
public class LinkedStringList implements StringList {
  private Node head;

  public LinkedStringList() {
    this.head = null;
  }

  /**
   * Adds a new String to the front of the list.
   * @param s the String to add
   */
  @Override
  public void add(String s) {
    Node newNode = new Node(s);
    newNode.next = head;
    head = newNode;
  }

  /**
   * Checks whether the list is empty.
   * @return true if the list is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Gets the total number of elements in the list.
   * @return the size of the list
   */
  @Override
  public int size() {
    int count = 0;
    Node curr = head;
    while (curr != null) {
      count++;
      curr = curr.next;
    }
    return count;
  }

  /**
   * Checks if the given String is in the list.
   * @param s the String to search for
   * @return true if found, false otherwise
   */
  @Override
  public boolean contains(String s) {
    Node curr = head;
    while (curr != null) {
      if (curr.data.equals(s)) return true;
      curr = curr.next;
    }
    return false;
  }

  /**
   * Checks that all elements of the given list are in this list.
   * @param other the other StringList
   * @return true if all elements of other are in this list
   */
  @Override
  public boolean containsAll(StringList other) {
    if (!(other instanceof LinkedStringList)) return false;
    Node curr = ((LinkedStringList) other).head;
    while (curr != null) {
      if (!this.contains(curr.data)) return false;
      curr = curr.next;
    }
    return true;
  }

  /**
   * Returns a new list with all elements longer than maxLength removed.
   * @param maxLength the maximum allowed String length
   * @return filtered StringList
   */
  @Override
  public StringList filterLargerThan(int maxLength) {
    LinkedStringList result = new LinkedStringList();
    Node curr = head;
    while (curr != null) {
      if (curr.data.length() <= maxLength) result.add(curr.data);
      curr = curr.next;
    }
    return result;
  }

  /**
   * Checks if the list has at least one duplicate element.
   * @return true if there is at least one duplicate
   */
  @Override
  public boolean hasDuplicates() {
    Node curr = head;
    while (curr != null) {
      Node runner = curr.next;
      while (runner != null) {
        if (curr.data.equals(runner.data)) return true;
        runner = runner.next;
      }
      curr = curr.next;
    }
    return false;
  }

  /**
   * Returns a new list with all duplicates removed.
   * @return StringList with no duplicates
   */
  @Override
  public StringList removeDuplicates() {
    LinkedStringList result = new LinkedStringList();
    Node curr = head;
    while (curr != null) {
      if (!result.contains(curr.data)) result.add(curr.data);
      curr = curr.next;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    Node curr = head;
    while (curr != null) {
      sb.append(curr.data);
      if (curr.next != null) sb.append(", ");
      curr = curr.next;
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LinkedStringList other = (LinkedStringList) o;
    Node c1 = this.head, c2 = other.head;
    while (c1 != null && c2 != null) {
      if (!c1.data.equals(c2.data)) return false;
      c1 = c1.next;
      c2 = c2.next;
    }
    return c1 == null && c2 == null;
  }

  @Override
  public int hashCode() {
    int result = 0;
    Node curr = head;
    while (curr != null) {
      result = 31 * result + curr.data.hashCode();
      curr = curr.next;
    }
    return result;
  }
}