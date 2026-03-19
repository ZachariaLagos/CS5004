import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Catalog} and supporting classes.
 */
class CatalogTest {

  // ── shared fixtures ──────────────────────────────────────────────────────
  private Author tolkien;
  private Author orwell;
  private RecordingArtist freddie;
  private RecordingArtist brian;
  private RecordingArtist beyonce;
  private Band queen;

  private Book lotr;
  private Book nineteenEightyFour;
  private Book animalFarm;
  private Music bohemianRhapsody;
  private Music lemonade;
  private Music renaissanceAlbum;

  private Catalog catalog;

  @BeforeEach
  void setUp() {
    tolkien  = new Author("J.R.R.", "Tolkien");
    orwell   = new Author("George", "Orwell");

    freddie  = new RecordingArtist("Freddie", "Mercury");
    brian    = new RecordingArtist("Brian", "May");
    beyonce  = new RecordingArtist("Beyonce", "Knowles");

    queen = new Band("Queen", List.of(freddie, brian));

    lotr              = new Book(tolkien, "The Lord of the Rings", 1954);
    nineteenEightyFour = new Book(orwell,  "Nineteen Eighty-Four", 1949);
    animalFarm        = new Book(orwell,  "Animal Farm", 1945);

    bohemianRhapsody  = new Music(queen,   "Bohemian Rhapsody", 1975);
    lemonade          = new Music(beyonce, "Lemonade", 2016);
    renaissanceAlbum  = new Music(beyonce, "Renaissance", 2022);

    catalog = new Catalog(List.of(
        lotr, nineteenEightyFour, animalFarm,
        bohemianRhapsody, lemonade, renaissanceAlbum
    ));
  }

  // ── Catalog construction ─────────────────────────────────────────────────

  @Test
  void emptyCatalogHasNoItems() {
    Catalog empty = new Catalog();
    assertTrue(empty.getItems().isEmpty());
  }

  @Test
  void catalogConstructedWithItemsContainsThem() {
    assertEquals(6, catalog.getItems().size());
  }

  // ── addItem / removeItem ─────────────────────────────────────────────────

  @Test
  void addItemIncreasesSize() {
    Book newBook = new Book(tolkien, "The Hobbit", 1937);
    catalog.addItem(newBook);
    assertEquals(7, catalog.getItems().size());
    assertTrue(catalog.getItems().contains(newBook));
  }

  @Test
  void removeItemDecreasesSize() {
    boolean removed = catalog.removeItem(lemonade);
    assertTrue(removed);
    assertEquals(5, catalog.getItems().size());
    assertFalse(catalog.getItems().contains(lemonade));
  }

  @Test
  void removeItemReturnsFalseWhenNotPresent() {
    Music absent = new Music(beyonce, "Dangerously In Love", 2003);
    assertFalse(catalog.removeItem(absent));
  }

  // ── search(String keyword) ───────────────────────────────────────────────

  @Test
  void keywordSearchReturnsMatchingItems() {
    List<Item> results = catalog.search("the");
    // "The Lord of the Rings" contains "the" twice; no other titles contain it
    assertEquals(1, results.size());
    assertTrue(results.contains(lotr));
  }

  @Test
  void keywordSearchIsCaseInsensitive() {
    List<Item> lower = catalog.search("animal");
    List<Item> upper = catalog.search("ANIMAL");
    List<Item> mixed = catalog.search("AnImAl");
    assertEquals(lower, upper);
    assertEquals(lower, mixed);
    assertEquals(1, lower.size());
    assertTrue(lower.contains(animalFarm));
  }

  @Test
  void keywordSearchReturnsEmptyListWhenNoMatch() {
    assertTrue(catalog.search("zzznomatch").isEmpty());
  }

  @Test
  void keywordSearchMatchesBothBooksAndMusic() {
    // "a" appears in "Animal Farm", "Bohemian Rhapsody", "Lemonade", "Renaissance"
    List<Item> results = catalog.search("a");
    assertTrue(results.contains(animalFarm));
    assertTrue(results.contains(bohemianRhapsody));
    assertTrue(results.contains(lemonade));
    assertTrue(results.contains(renaissanceAlbum));
  }

  // ── search(Author author) ────────────────────────────────────────────────

  @Test
  void authorSearchReturnsOnlyThatAuthorSBooks() {
    List<Item> results = catalog.search(orwell);
    assertEquals(2, results.size());
    assertTrue(results.contains(nineteenEightyFour));
    assertTrue(results.contains(animalFarm));
  }

  @Test
  void authorSearchDoesNotReturnMusic() {
    List<Item> results = catalog.search(orwell);
    results.forEach(item -> assertInstanceOf(Book.class, item));
  }

  @Test
  void authorSearchReturnsEmptyListForUnknownAuthor() {
    Author unknown = new Author("Jane", "Austen");
    assertTrue(catalog.search(unknown).isEmpty());
  }

  @Test
  void authorSearchRequiresExactMatch() {
    // Same last name, different first name — should not match
    Author wrongFirst = new Author("Eric", "Orwell");
    assertTrue(catalog.search(wrongFirst).isEmpty());
  }

  // ── search(RecordingArtist artist) ───────────────────────────────────────

  @Test
  void artistSearchReturnsSoloArtistMusic() {
    List<Item> results = catalog.search(beyonce);
    assertEquals(2, results.size());
    assertTrue(results.contains(lemonade));
    assertTrue(results.contains(renaissanceAlbum));
  }

  @Test
  void artistSearchReturnsMusicWhereArtistIsBandMember() {
    // Freddie is a member of Queen; bohemianRhapsody is by Queen
    List<Item> results = catalog.search(freddie);
    assertEquals(1, results.size());
    assertTrue(results.contains(bohemianRhapsody));
  }

  @Test
  void artistSearchReturnsAllBandMembersResults() {
    // Brian is also a member of Queen
    List<Item> brianResults  = catalog.search(brian);
    List<Item> freddieResults = catalog.search(freddie);
    assertEquals(brianResults, freddieResults);
  }

  @Test
  void artistSearchDoesNotReturnBooks() {
    List<Item> results = catalog.search(beyonce);
    results.forEach(item -> assertInstanceOf(Music.class, item));
  }

  @Test
  void artistSearchReturnsEmptyListForUnknownArtist() {
    RecordingArtist unknown = new RecordingArtist("John", "Lennon");
    assertTrue(catalog.search(unknown).isEmpty());
  }

  // ── Person equality ──────────────────────────────────────────────────────

  @Test
  void twoAuthorsWithSameNameAreEqual() {
    Author a1 = new Author("George", "Orwell");
    Author a2 = new Author("George", "Orwell");
    assertEquals(a1, a2);
  }

  @Test
  void authorAndRecordingArtistWithSameNameAreNotEqual() {
    Author author         = new Author("George", "Orwell");
    RecordingArtist artist = new RecordingArtist("George", "Orwell");
    assertNotEquals(author, artist);
  }

  // ── Band ─────────────────────────────────────────────────────────────────

  @Test
  void bandContainsMembersAfterConstruction() {
    assertTrue(queen.getMembers().contains(freddie));
    assertTrue(queen.getMembers().contains(brian));
  }

  @Test
  void addMemberIncreasesBandSize() {
    RecordingArtist roger = new RecordingArtist("Roger", "Taylor");
    queen.addMember(roger);
    assertTrue(queen.getMembers().contains(roger));
  }
}