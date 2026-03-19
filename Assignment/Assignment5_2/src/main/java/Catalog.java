import java.util.ArrayList;
import java.util.List;

/**
 * Represents the library's catalog — a collection of all items (books and music)
 * with search functionality.
 *
 * The catalog supports three overloaded search methods:
 * by keyword (case-insensitive title match), by Author (exact match, Books only),
 * and by RecordingArtist (exact match, Music only, including band members).
 */
public class Catalog {
  private final List<Item> items;

  /**
   * Constructs an empty Catalog.
   */
  public Catalog() {
    this.items = new ArrayList<>();
  }

  /**
   * Constructs a Catalog pre-populated with the given items.
   *
   * @param items the initial collection of items
   */
  public Catalog(List<Item> items) {
    this.items = new ArrayList<>(items);
  }

  /**
   * Adds an item to the catalog.
   *
   * @param item the item to add
   */
  public void addItem(Item item) {
    items.add(item);
  }

  /**
   * Removes an item from the catalog.
   *
   * @param item the item to remove
   * @return true if the item was present and removed, false otherwise
   */
  public boolean removeItem(Item item) {
    return items.remove(item);
  }

  /**
   * Returns all items currently in the catalog.
   *
   * @return an unmodifiable copy of the item list
   */
  public List<Item> getItems() {
    return List.copyOf(items);
  }

  // -------------------------------------------------------------------------
  // Search methods
  // -------------------------------------------------------------------------

  /**
   * Searches the catalog for items whose title contains the given keyword.
   * The search is case-insensitive.
   *
   * @param keyword the substring to search for in item titles
   * @return a list of all items whose title contains {@code keyword}
   *         (case-insensitive); empty list if none match
   */
  public List<Item> search(String keyword) {
    List<Item> results = new ArrayList<>();
    String lower = keyword.toLowerCase();
    for (Item item : items) {
      if (item.getTitle().toLowerCase().contains(lower)) {
        results.add(item);
      }
    }
    return results;
  }

  /**
   * Searches the catalog for Books that have an exact match for the given Author.
   *
   * @param author the author to search for
   * @return a list of Books whose author equals {@code author};
   *         empty list if none match
   */
  public List<Item> search(Author author) {
    List<Item> results = new ArrayList<>();
    for (Item item : items) {
      if (item instanceof Book book && book.getAuthor().equals(author)) {
        results.add(book);
      }
    }
    return results;
  }

  /**
   * Searches the catalog for Music items associated with the given RecordingArtist.
   * The artist may be the sole creator of the Music or a member of the Music's Band.
   *
   * @param artist the recording artist to search for
   * @return a list of Music items whose creator is {@code artist} directly,
   *         or whose Band contains {@code artist} as a member;
   *         empty list if none match
   */
  public List<Item> search(RecordingArtist artist) {
    List<Item> results = new ArrayList<>();
    for (Item item : items) {
      if (item instanceof Music music) {
        Creator creator = music.getCreator();
        if (creator instanceof RecordingArtist ra && ra.equals(artist)) {
          results.add(music);
        } else if (creator instanceof Band band
            && band.getMembers().contains(artist)) {
          results.add(music);
        }
      }
    }
    return results;
  }
}