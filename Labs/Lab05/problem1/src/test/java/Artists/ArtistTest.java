package Artists;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArtistTest {

  private Musician musician;
  private Musician musicianCopy;
  private Poet poet;
  private Actor actor;
  private Dancer dancer;
  private Filmmaker filmmaker;
  private Painter painter;
  private Photographer photographer;

  @BeforeEach
  void setUp() {
    musician = new Musician("John Doe", 30,
        new String[]{"Rock", "Pop"},
        new String[]{"Grammy"},
        "Sony Music", "Greatest Hits");

    musicianCopy = new Musician("John Doe", 30,
        new String[]{"Rock", "Pop"},
        new String[]{"Grammy"},
        "Sony Music", "Greatest Hits");

    poet = new Poet("Jane Doe", 45,
        new String[]{"Lyric", "Epic"},
        new String[]{"Pulitzer"},
        "Penguin Books", "Whispers of Time");

    actor = new Actor("Tom Smith", 35,
        new String[]{"Drama"},
        new String[]{"Oscar"},
        new String[]{"Movie A", "Movie B"},
        new String[]{"Series A"},
        new String[]{"Short Film A"});

    dancer = new Dancer("Anna Lee", 28,
        new String[]{"Ballet"},
        new String[]{"Dance Award"},
        new String[]{"Movie C"},
        new String[]{"Series B"},
        new String[]{});

    filmmaker = new Filmmaker("Chris Park", 50,
        new String[]{"Documentary"},
        new String[]{"Cannes Award"},
        new String[]{"Film X"},
        new String[]{},
        new String[]{"Ad Campaign Y"});

    painter = new Painter("Leo Brown", 60,
        new String[]{"Abstract"},
        new String[]{"Art Prize"},
        new String[]{"Exhibit A", "Exhibit B"});

    photographer = new Photographer("Maya White", 40,
        new String[]{"Portrait"},
        new String[]{"Photo Award"},
        new String[]{"Gallery A"});
  }

  // getName
  @Test
  void getName() {
    assertEquals("John Doe", musician.getName());
    assertEquals("Jane Doe", poet.getName());
    assertEquals("Tom Smith", actor.getName());
    assertEquals("Leo Brown", painter.getName());
  }

  // getAge
  @Test
  void getAge() {
    assertEquals(30, musician.getAge());
    assertEquals(45, poet.getAge());
    assertEquals(28, dancer.getAge());
  }

  // getGenre
  @Test
  void getGenre() {
    assertArrayEquals(new String[]{"Rock", "Pop"}, musician.getGenre());
    assertArrayEquals(new String[]{"Ballet"}, dancer.getGenre());
    assertArrayEquals(new String[]{"Abstract"}, painter.getGenre());
  }

  // getAwards
  @Test
  void getAwards() {
    assertArrayEquals(new String[]{"Grammy"}, musician.getAwards());
    assertArrayEquals(new String[]{"Oscar"}, actor.getAwards());
    assertArrayEquals(new String[]{"Art Prize"}, painter.getAwards());
  }

  // setName
  @Test
  void setName() {
    musician.setName("New Name");
    assertEquals("New Name", musician.getName());
    poet.setName("New Poet");
    assertEquals("New Poet", poet.getName());
  }

  // setAge
  @Test
  void setAge() {
    musician.setAge(25);
    assertEquals(25, musician.getAge());
    actor.setAge(40);
    assertEquals(40, actor.getAge());
  }

  // setGenre
  @Test
  void setGenre() {
    musician.setGenre(new String[]{"Jazz"});
    assertArrayEquals(new String[]{"Jazz"}, musician.getGenre());
    painter.setGenre(new String[]{"Realism", "Impressionism"});
    assertArrayEquals(new String[]{"Realism", "Impressionism"}, painter.getGenre());
  }

  // setAwards
  @Test
  void setAwards() {
    musician.setAwards(new String[]{"Oscar", "Grammy"});
    assertArrayEquals(new String[]{"Oscar", "Grammy"}, musician.getAwards());
    poet.setAwards(new String[]{"Nobel Prize"});
    assertArrayEquals(new String[]{"Nobel Prize"}, poet.getAwards());
  }

  // receiveAward
  @Test
  void receiveAward() {
    musician.receiveAward("MTV Award");
    assertArrayEquals(new String[]{"Grammy", "MTV Award"}, musician.getAwards());

    poet.receiveAward("Nobel Prize");
    poet.receiveAward("Booker Prize");
    assertArrayEquals(new String[]{"Pulitzer", "Nobel Prize", "Booker Prize"}, poet.getAwards());

    actor.receiveAward("Golden Globe");
    assertArrayEquals(new String[]{"Oscar", "Golden Globe"}, actor.getAwards());

    painter.receiveAward("Turner Prize");
    assertArrayEquals(new String[]{"Art Prize", "Turner Prize"}, painter.getAwards());
  }

  // toString
  @Test
  void testToString() {
    String musicianStr = musician.toString();
    assertTrue(musicianStr.contains("John Doe"));
    assertTrue(musicianStr.contains("30"));
    assertTrue(musicianStr.contains("Sony Music"));
    assertTrue(musicianStr.contains("Greatest Hits"));

    String poetStr = poet.toString();
    assertTrue(poetStr.contains("Jane Doe"));
    assertTrue(poetStr.contains("Penguin Books"));
    assertTrue(poetStr.contains("Whispers of Time"));

    String actorStr = actor.toString();
    assertTrue(actorStr.contains("Tom Smith"));
    assertTrue(actorStr.contains("Movie A"));
    assertTrue(actorStr.contains("Series A"));

    String painterStr = painter.toString();
    assertTrue(painterStr.contains("Leo Brown"));
    assertTrue(painterStr.contains("Exhibit A"));

    String photographerStr = photographer.toString();
    assertTrue(photographerStr.contains("Maya White"));
    assertTrue(photographerStr.contains("Gallery A"));

    String dancerStr = dancer.toString();
    assertTrue(dancerStr.contains("Anna Lee"));
    assertTrue(dancerStr.contains("Movie C"));

    String filmmakkerStr = filmmaker.toString();
    assertTrue(filmmakkerStr.contains("Chris Park"));
    assertTrue(filmmakkerStr.contains("Film X"));
  }

  // equals
  @Test
  void testEquals() {
    assertEquals(musician, musician);
    assertEquals(musician, musicianCopy);
    assertNotEquals(musician, poet);
    assertNotEquals(musician, null);
    assertNotEquals(actor, dancer);
    assertNotEquals(painter, photographer);

    Musician differentMusician = new Musician("Jane Smith", 25,
        new String[]{"Jazz"}, new String[]{"Grammy"}, "Universal", "Blue Note");
    assertNotEquals(musician, differentMusician);

    Painter painterCopy = new Painter("Leo Brown", 60,
        new String[]{"Abstract"}, new String[]{"Art Prize"},
        new String[]{"Exhibit A", "Exhibit B"});
    assertEquals(painter, painterCopy);

    Actor actorCopy = new Actor("Tom Smith", 35,
        new String[]{"Drama"}, new String[]{"Oscar"},
        new String[]{"Movie A", "Movie B"},
        new String[]{"Series A"},
        new String[]{"Short Film A"});
    assertEquals(actor, actorCopy);
  }

  // hashCode
  @Test
  void testHashCode() {
    assertEquals(musician.hashCode(), musicianCopy.hashCode());

    Painter painterCopy = new Painter("Leo Brown", 60,
        new String[]{"Abstract"}, new String[]{"Art Prize"},
        new String[]{"Exhibit A", "Exhibit B"});
    assertEquals(painter.hashCode(), painterCopy.hashCode());

    Actor actorCopy = new Actor("Tom Smith", 35,
        new String[]{"Drama"}, new String[]{"Oscar"},
        new String[]{"Movie A", "Movie B"},
        new String[]{"Series A"},
        new String[]{"Short Film A"});
    assertEquals(actor.hashCode(), actorCopy.hashCode());

    assertNotEquals(musician.hashCode(), poet.hashCode());
    assertNotEquals(actor.hashCode(), dancer.hashCode());
    assertNotEquals(painter.hashCode(), photographer.hashCode());
  }

  // invalid age
  @Test
  void testInvalidAgeTooLow() {
    assertThrows(IllegalArgumentException.class, () ->
        new Musician("Bad", -1, new String[]{}, new String[]{}, "Label", "Album"));
  }

  @Test
  void testInvalidAgeTooHigh() {
    assertThrows(IllegalArgumentException.class, () ->
        new Musician("Bad", 130, new String[]{}, new String[]{}, "Label", "Album"));
  }

  @Test
  void testInvalidAgeZero() {
    assertThrows(IllegalArgumentException.class, () ->
        new Musician("Bad", 0, new String[]{}, new String[]{}, "Label", "Album"));
  }

  @Test
  void testValidAgeBoundaries() {
    assertDoesNotThrow(() ->
        new Musician("Min Age", 1, new String[]{}, new String[]{}, "Label", "Album"));
    assertDoesNotThrow(() ->
        new Musician("Max Age", 127, new String[]{}, new String[]{}, "Label", "Album"));
  }

  // Musician specific
  @Test
  void testMusicianGettersSetters() {
    assertEquals("Sony Music", musician.getRecordingCompany());
    assertEquals("Greatest Hits", musician.getLastRecordedAlbum());
    musician.setRecordingCompany("Universal");
    musician.setLastRecordedAlbum("New Album");
    assertEquals("Universal", musician.getRecordingCompany());
    assertEquals("New Album", musician.getLastRecordedAlbum());
  }

  // Poet specific
  @Test
  void testPoetGettersSetters() {
    assertEquals("Penguin Books", poet.getPublishedCompany());
    assertEquals("Whispers of Time", poet.getLastPublishedCollection());
    poet.setPublishedCompany("HarperCollins");
    poet.setLastPublishedCollection("New Collection");
    assertEquals("HarperCollins", poet.getPublishedCompany());
    assertEquals("New Collection", poet.getLastPublishedCollection());
  }

  // MultimediaArtists specific
  @Test
  void testMultimediaGettersSetters() {
    assertArrayEquals(new String[]{"Movie A", "Movie B"}, actor.getMovies());
    assertArrayEquals(new String[]{"Series A"}, actor.getSeries());
    assertArrayEquals(new String[]{"Short Film A"}, actor.getOtherMultimedia());
    actor.setMovies(new String[]{"New Movie"});
    actor.setSeries(new String[]{"New Series"});
    actor.setOtherMultimedia(new String[]{"New Other"});
    assertArrayEquals(new String[]{"New Movie"}, actor.getMovies());
    assertArrayEquals(new String[]{"New Series"}, actor.getSeries());
    assertArrayEquals(new String[]{"New Other"}, actor.getOtherMultimedia());
  }

  @Test
  void testDancerGettersSetters() {
    assertArrayEquals(new String[]{"Movie C"}, dancer.getMovies());
    dancer.setMovies(new String[]{"New Dance Movie"});
    assertArrayEquals(new String[]{"New Dance Movie"}, dancer.getMovies());
  }

  @Test
  void testFilmmakerGettersSetters() {
    assertArrayEquals(new String[]{"Film X"}, filmmaker.getMovies());
    filmmaker.setMovies(new String[]{"New Film"});
    assertArrayEquals(new String[]{"New Film"}, filmmaker.getMovies());
  }

  // VisualArtists specific
  @Test
  void testVisualArtistGettersSetters() {
    assertArrayEquals(new String[]{"Exhibit A", "Exhibit B"}, painter.getExhibits());
    painter.setExhibits(new String[]{"New Exhibit"});
    assertArrayEquals(new String[]{"New Exhibit"}, painter.getExhibits());

    assertArrayEquals(new String[]{"Gallery A"}, photographer.getExhibits());
    photographer.setExhibits(new String[]{"New Gallery"});
    assertArrayEquals(new String[]{"New Gallery"}, photographer.getExhibits());
  }
}