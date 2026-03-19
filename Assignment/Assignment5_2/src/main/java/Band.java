import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Band — a group creator consisting of one or more RecordingArtists.
 */
public class Band extends Creator {
  private final String name;
  private final List<RecordingArtist> members;

  /**
   * Constructs a Band with the given name and an empty member list.
   *
   * @param name the band's name
   */
  public Band(String name) {
    this.name = name;
    this.members = new ArrayList<>();
  }

  /**
   * Constructs a Band with the given name and initial list of members.
   *
   * @param name    the band's name
   * @param members the initial list of RecordingArtists in the band
   */
  public Band(String name, List<RecordingArtist> members) {
    this.name = name;
    this.members = new ArrayList<>(members);
  }

  /**
   * Returns the band's name.
   *
   * @return band name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns an unmodifiable view of the band's members.
   *
   * @return list of RecordingArtists
   */
  public List<RecordingArtist> getMembers() {
    return List.copyOf(members);
  }

  /**
   * Adds a RecordingArtist to the band's membership.
   *
   * @param artist the artist to add
   */
  public void addMember(RecordingArtist artist) {
    members.add(artist);
  }

  /**
   * Two Bands are equal if they have the same name and the same members.
   *
   * @param o the object to compare
   * @return true if equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Band b)) return false;
    return name.equals(b.name) && members.equals(b.members);
  }

  @Override
  public int hashCode() {
    return 31 * name.hashCode() + members.hashCode();
  }

  @Override
  public String toString() {
    return name;
  }
}