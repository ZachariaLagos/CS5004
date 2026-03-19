/**
 * Represents a music item in the library collection.
 * A Music item's creator is either a RecordingArtist or a Band.
 */
public class Music extends Item {

  /**
   * Constructs a Music item with a solo RecordingArtist as its creator.
   *
   * @param artist the recording artist
   * @param title  the music title
   * @param year   the year the music was released
   */
  public Music(RecordingArtist artist, String title, int year) {
    super(artist, title, year);
  }

  /**
   * Constructs a Music item with a Band as its creator.
   *
   * @param band  the band
   * @param title the music title
   * @param year  the year the music was released
   */
  public Music(Band band, String title, int year) {
    super(band, title, year);
  }
}