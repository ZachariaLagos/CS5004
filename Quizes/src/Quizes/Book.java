public class Book {
  private String title;
  private String author;
  private String ISBN;
  private int yearPublished;


  public Book(String title, String author) {
    this.title = title;
    this.author = author;
    this.ISBN = "";
    this.yearPublished=0;
  }

  public Book(String title, String author, String ISBN, int yearPublished) {
    this.title = title;
    this.author = author;
    this.ISBN= ISBN;
    this.yearPublished = yearPublished;}

  public Book(Book other) {
    this.title = other.title;
    this.author = other.author;
    this.ISBN= other.ISBN;
    this.yearPublished = other.yearPublished;}

  public String getTitle() { return this.title; }
  /**
   * @return the author
   */
  public String getAuthor() { return this.author; }

  public String getISBN() {return this.ISBN; }

  public int getYearPublished() {return this.yearPublished; }

  public void setTitle(String title) {
    this.title = title;}

  public void setAuthor(String author) {
    this.author = author;}

  public void setISBN(String ISBN) {
    this.ISBN = ISBN;}

  public void setYearPublished(int yearPublished) {
    this.yearPublished = yearPublished;}

  public String toString(){return String.format("The book %s was written by %s and published in year %d, its ISBN is %s ",
      this.title, this.author, this.yearPublished, this.ISBN);}

  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Book other = (Book) obj;
    return yearPublished == other.yearPublished &&
        title.equals(other.title) &&
        author.equals(other.author) &&
        ISBN.equals(other.ISBN);
  }

}