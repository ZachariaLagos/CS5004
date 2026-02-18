package lab04;

/**
 * A non-empty node in the linked list of Books.
 * Holds one Book and a reference to the rest of the list.
 */
public class ElementNode implements IListOfBooks {

  private Book book;
  private IListOfBooks rest;

  /**
   * @param book the book stored at this node
   * @param rest the remainder of the list (never null — use EmptyNode)
   */
  public ElementNode(Book book, IListOfBooks rest) {
    this.book = book;
    this.rest = rest;
  }

  /**
   * Returns the number of books in this list by counting recursively.
   * @return 1 plus the count of the remaining nodes
   */
  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  /**
   * Returns the sum of prices of all books in this list.
   * @return this book's price plus the total price of the remaining nodes
   */
  @Override
  public float totalPrice() {
    return this.book.getPrice() + this.rest.totalPrice();
  }

  /**
   * Returns a sublist of books published strictly before the given year.
   * Includes this book if it qualifies, then recurses on the rest.
   * @param year the cutoff year (exclusive)
   * @return a new list containing only books published before the given year, preserving order
   */
  @Override
  public IListOfBooks allBefore(int year) {
    if (this.book.before(year)) {
      return new ElementNode(this.book, this.rest.allBefore(year));
    } else {
      return this.rest.allBefore(year);
    }
  }

  /**
   * Returns a new list with the given book appended to the end.
   * Recurses through the rest of the list until the EmptyNode base case is reached.
   * @param book the book to append
   * @return a new list with the book added at the tail
   */
  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(this.book, this.rest.addAtEnd(book));
  }

  /**
   * Returns a string representation of all books in the list, one per line.
   * @return each book's toString output separated by newlines
   */
  @Override
  public String toString() {
    String restStr = this.rest.toString();
    if (restStr.isEmpty()) {
      return this.book.toString();
    } else {
      return this.book.toString() + "\n" + restStr;
    }
  }
}