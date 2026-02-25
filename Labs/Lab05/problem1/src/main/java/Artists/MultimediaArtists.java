package Artists;

import java.util.Arrays;

/**
 * Abstract class for multimedia artists including Actors, Dancers, and Filmmakers.
 */
public abstract class MultimediaArtists extends Artist {
  private String[] movies;
  private String[] series;
  private String[] otherMultimedia;

  protected MultimediaArtists(String name, int age, String[] genres, String[] awards,
      String[] movies, String[] series, String[] otherMultimedia) {
    super(name, age, genres, awards);
    this.movies = movies;
    this.series = series;
    this.otherMultimedia = otherMultimedia;
  }

  public String[] getMovies() { return movies; }
  public String[] getSeries() { return series; }
  public String[] getOtherMultimedia() { return otherMultimedia; }
  public void setMovies(String[] movies) { this.movies = movies; }
  public void setSeries(String[] series) { this.series = series; }
  public void setOtherMultimedia(String[] otherMultimedia) { this.otherMultimedia = otherMultimedia; }

  @Override
  public String toString() {
    return super.toString()
        + ", movies=" + Arrays.toString(movies)
        + ", series=" + Arrays.toString(series)
        + ", otherMultimedia=" + Arrays.toString(otherMultimedia);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    MultimediaArtists that = (MultimediaArtists) o;
    return Arrays.equals(movies, that.movies)
        && Arrays.equals(series, that.series)
        && Arrays.equals(otherMultimedia, that.otherMultimedia);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + Arrays.hashCode(movies);
    result = 31 * result + Arrays.hashCode(series);
    result = 31 * result + Arrays.hashCode(otherMultimedia);
    return result;
  }
}