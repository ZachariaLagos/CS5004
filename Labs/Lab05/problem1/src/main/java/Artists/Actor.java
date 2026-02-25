package Artists;

public class Actor extends MultimediaArtists{
  /**
   * constructor for Artists.Actor
   * @param name
   * @param age
   * @param genres
   * @param awards
   * @param movies
   * @param series
   * @param otherMultimedia
   */
  public Actor(String name, int age, String[] genres, String[] awards, String[] movies, String[] series, String[] otherMultimedia) {
    super(name, age, genres, awards, movies, series, otherMultimedia);
  }


}

