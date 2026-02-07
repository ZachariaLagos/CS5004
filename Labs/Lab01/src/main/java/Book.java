/**
 * Represents a Book with their details--title, author and pages
 */
public class Book {
  private String title;
  private String author;
  private String pages;
  /**
   Creates a new book given the book's title, author and pages.
   * @param title the book's title
   * @param author the book's author
   * @param pages the book's pages
   */
  public Book(String title, String author, String pages) {
    this.title = title;
    this.author = author;
    this.pages = pages; }
  /**
   * @return the title
   */
  public String getTitle() { return this.title; }
  /**
   * @return the author
   */
  public String getAuthor() { return this.author; }
  /**
   * @return the pages
   */
  public String getPages() {return this.pages; }}

