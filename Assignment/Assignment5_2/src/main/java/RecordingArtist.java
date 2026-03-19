/**
 * Represents a recording artist — an individual person who creates music,
 * either as a solo act or as a member of a Band.
 */
public class RecordingArtist extends Person {

  /**
   * Constructs a RecordingArtist with the given first and last name.
   *
   * @param firstName the artist's first name
   * @param lastName  the artist's last name
   */
  public RecordingArtist(String firstName, String lastName) {
    super(firstName, lastName);
  }
}