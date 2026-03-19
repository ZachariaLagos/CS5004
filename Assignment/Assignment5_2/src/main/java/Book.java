/**
 * Represents a book in the library collection.
 * A Book's creator is always an Author.
 */
public class Book extends Item {

  /**
   * Constructs a Book with the specified author, title, and publication year.
   *
   * @param author the book's author
   * @param title  the book's title
   * @param year   the year the book was published
   */
  public Book(Author author, String title, int year) {
    super(author, title, year);
  }

  /**
   * Returns the book's author (convenience cast of {@link #getCreator()}).
   *
   * @return the Author
   */
  public Author getAuthor() {
    return (Author) getCreator();
  }
}