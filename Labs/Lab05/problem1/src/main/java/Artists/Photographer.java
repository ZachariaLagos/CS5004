package Artists;

public class Photographer extends VisualArtists{
  /**
   * super constructor for Artists.Painter
   * @param name
   * @param age
   * @param genres
   * @param awards
   * @param exhibits
   */
  public Photographer (String name, int age, String[] genres, String[] awards, String[] exhibits){
    super(name, age, genres, awards, exhibits);
  }

}
