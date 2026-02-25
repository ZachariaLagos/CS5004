package Artists;

import java.util.Objects;

public class Musician extends Artist {
  private String recordingCompany;
  private String lastRecordedAlbum;

  public Musician(String name, int age, String[] genres, String[] awards,
      String recordingCompany, String lastRecordedAlbum) {
    super(name, age, genres, awards);
    this.recordingCompany = recordingCompany;
    this.lastRecordedAlbum = lastRecordedAlbum;
  }

  public String getRecordingCompany() { return recordingCompany; }
  public void setRecordingCompany(String recordingCompany) { this.recordingCompany = recordingCompany; }
  public String getLastRecordedAlbum() { return lastRecordedAlbum; }
  public void setLastRecordedAlbum(String lastRecordedAlbum) { this.lastRecordedAlbum = lastRecordedAlbum; }

  @Override
  public String toString() {
    return super.toString()
        + ", recordingCompany='" + recordingCompany + "'"
        + ", lastRecordedAlbum='" + lastRecordedAlbum + "'";
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Musician musician = (Musician) o;
    return Objects.equals(recordingCompany, musician.recordingCompany)
        && Objects.equals(lastRecordedAlbum, musician.lastRecordedAlbum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), recordingCompany, lastRecordedAlbum);
  }
}