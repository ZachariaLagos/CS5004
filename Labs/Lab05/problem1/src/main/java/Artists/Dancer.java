package Artists;

public class Dancer extends MultimediaArtists{

  /**
   * constructor for Artists.Dancer
   * @param name
   * @param age
   * @param genres
   * @param awards
   * @param movies
   * @param series
   * @param otherMultimedia
   */
  public Dancer(String name, int age, String[] genres, String[] awards, String[] movies, String[] series, String[] otherMultimedia) {
    super(name, age, genres, awards, movies, series, otherMultimedia);
  }
}
