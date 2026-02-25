package Artists;

public class Filmmaker extends MultimediaArtists{
  /**
   * constructor for Artists.Filmmaker
   * @param name
   * @param age
   * @param genres
   * @param awards
   * @param movies
   * @param series
   * @param otherMultimedia
   */
  public Filmmaker(String name, int age, String[] genres, String[] awards, String[] movies, String[] series, String[] otherMultimedia) {
    super(name, age, genres, awards, movies, series, otherMultimedia);
  }
}
