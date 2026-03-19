/**
 * Abstract class representing an individual person who is a creator.
 * All individuals have a first and last name tracked separately.
 */
public abstract class Person extends Creator {
  private final String firstName;
  private final String lastName;

  /**
   * Constructs a Person with the given first and last name.
   *
   * @param firstName the person's first name
   * @param lastName  the person's last name
   */
  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Returns the person's first name.
   *
   * @return first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the person's last name.
   *
   * @return last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns the person's full name in "FirstName LastName" format.
   *
   * @return full name
   */
  @Override
  public String getName() {
    return firstName + " " + lastName;
  }

  /**
   * Two Person objects are equal if they have the same first and last name
   * and are of the same concrete type.
   *
   * @param o the object to compare
   * @return true if equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person p = (Person) o;
    return firstName.equals(p.firstName) && lastName.equals(p.lastName);
  }

  @Override
  public int hashCode() {
    return 31 * firstName.hashCode() + lastName.hashCode();
  }

  @Override
  public String toString() {
    return getName();
  }
}