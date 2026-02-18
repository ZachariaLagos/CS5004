package lab04;

/**
 * Empty book node — every list must terminate with one of these.
 */
public class EmptyNode implements IListOfBooks {

  public EmptyNode() {}

  /**
   * Returns 0 since an empty node contains no books.
   * @return 0
   */
  @Override
  public int count() {
    return 0;
  }

  /**
   * Returns 0.0 since an empty node has no books to price.
   * @return 0.0f
   */
  @Override
  public float totalPrice() {
    return 0.0f;
  }

  /**
   * Base case for allBefore recursion. An empty list has no books,
   * so return a new EmptyNode rather than null to keep recursive callers safe.
   * @param year the cutoff year (unused at this base case)
   * @return a new EmptyNode
   */
  @Override
  public IListOfBooks allBefore(int year) {
    return new EmptyNode();
  }

  /**
   * Base case for addAtEnd recursion. Adding a book to the end of an empty
   * list produces a one-element list.
   * @param book the book to add
   * @return a new ElementNode containing the book followed by a new EmptyNode
   */
  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(book, new EmptyNode());
  }

  /**
   * Returns an empty string since there are no books to display.
   * @return ""
   */
  @Override
  public String toString() {
    return "";
  }
}