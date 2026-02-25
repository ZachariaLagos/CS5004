package Artists;

import java.util.Arrays;
import java.util.Objects;

/**
 * Abstract base class for all artists.
 * Implements the Common interface to include all fields that artists share.
 */
public abstract class Artist implements Common {
  protected String name;
  protected int age;
  protected String[] genres;
  protected String[] awards;

  protected Artist(String name, int age, String[] genres, String[] awards) {
    if (age <= 0 || age >= 128) {
      throw new IllegalArgumentException("The age must be between 0 and 128");
    }
    this.name = name;
    this.age = age;
    this.genres = genres;
    this.awards = awards;
  }

  public String getName() { return name; }
  public int getAge() { return age; }
  public String[] getGenre() { return genres; }
  public String[] getAwards() { return awards; }

  public void setName(String name) { this.name = name; }
  public void setAge(int age) { this.age = age; }
  public void setGenre(String[] genres) { this.genres = genres; }
  public void setAwards(String[] awards) { this.awards = awards; }

  /**
   * Appends a new award to the existing awards array.
   * @param award the new award to add
   */
  @Override
  public void receiveAward(String award) {
    String[] newAwards = new String[this.awards.length + 1];
    for (int i = 0; i < this.awards.length; i++) {
      newAwards[i] = awards[i];
    }
    newAwards[newAwards.length - 1] = award;
    this.awards = newAwards;
  }

  @Override
  public String toString() {
    return String.format("The artist's name is %s, %d years old. Genres: %s, Awards: %s",
        name, age, Arrays.toString(genres), Arrays.toString(awards));
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Artist artist = (Artist) o;
    return age == artist.age
        && Objects.equals(name, artist.name)
        && Arrays.equals(genres, artist.genres)
        && Arrays.equals(awards, artist.awards);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(name, age);
    result = 31 * result + Arrays.hashCode(genres);
    result = 31 * result + Arrays.hashCode(awards);
    return result;
  }
}