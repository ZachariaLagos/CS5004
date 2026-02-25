package Artists;

import java.util.Arrays;

/**
 * Abstract class for visual artists including Painters and Photographers.
 */
public abstract class VisualArtists extends Artist {
  private String[] exhibits;

  protected VisualArtists(String name, int age, String[] genres, String[] awards, String[] exhibits) {
    super(name, age, genres, awards);
    this.exhibits = exhibits;
  }

  public String[] getExhibits() { return exhibits; }
  public void setExhibits(String[] exhibits) { this.exhibits = exhibits; }

  @Override
  public String toString() {
    return super.toString() + ", exhibits=" + Arrays.toString(exhibits);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    VisualArtists that = (VisualArtists) o;
    return Arrays.equals(exhibits, that.exhibits);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + Arrays.hashCode(exhibits);
    return result;
  }
}