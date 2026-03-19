/**
 * Abstract base class representing an item in the library collection.
 * Every item has a creator, a title, and a year of release or publication.
 */
public abstract class Item {
  private final Creator creator;
  private final String title;
  private final int year;

  /**
   * Constructs an Item with the specified creator, title, and year.
   *
   * @param creator the item's creator (author, recording artist, or band)
   * @param title   the item's title
   * @param year    the year the item was released or published
   */
  public Item(Creator creator, String title, int year) {
    this.creator = creator;
    this.title = title;
    this.year = year;
  }

  /**
   * Returns the item's creator.
   *
   * @return the Creator
   */
  public Creator getCreator() {
    return creator;
  }

  /**
   * Returns the item's title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the year the item was released or published.
   *
   * @return the year
   */
  public int getYear() {
    return year;
  }

  @Override
  public String toString() {
    return String.format("\"%s\" by %s (%d)", title, creator.getName(), year);
  }
}