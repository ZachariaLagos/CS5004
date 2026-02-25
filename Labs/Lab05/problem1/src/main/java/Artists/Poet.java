package Artists;

import java.util.Objects;

public class Poet extends Artist {
  private String publishedCompany;
  private String lastPublishedCollection;

  public Poet(String name, int age, String[] genres, String[] awards,
      String publishedCompany, String lastPublishedCollection) {
    super(name, age, genres, awards);
    this.publishedCompany = publishedCompany;
    this.lastPublishedCollection = lastPublishedCollection;
  }

  public String getPublishedCompany() { return publishedCompany; }
  public void setPublishedCompany(String publishedCompany) { this.publishedCompany = publishedCompany; }
  public String getLastPublishedCollection() { return lastPublishedCollection; }
  public void setLastPublishedCollection(String lastPublishedCollection) {
    this.lastPublishedCollection = lastPublishedCollection;
  }

  @Override
  public String toString() {
    return super.toString()
        + ", publishedCompany='" + publishedCompany + "'"
        + ", lastPublishedCollection='" + lastPublishedCollection + "'";
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Poet poet = (Poet) o;
    return Objects.equals(publishedCompany, poet.publishedCompany)
        && Objects.equals(lastPublishedCollection, poet.lastPublishedCollection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), publishedCompany, lastPublishedCollection);
  }
}