public class Person {
  private String name;
  private Date birthDate;
  private Date deathDate;

  // Constructor with all parameters
  public Person(String name, Date birthDate, Date deathDate) {
    this.name = name;
    this.birthDate = birthDate;
    this.deathDate = deathDate;
  }

  // Copy constructor
  public Person(Person other) {
    this.name = other.name;
    this.birthDate = new Date(other.birthDate);
    if (other.deathDate != null) {
      this.deathDate = new Date(other.deathDate);
    } else {
      this.deathDate = null;
    }
  }

  // Getters
  public String getName() {
    return name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public Date getDeathDate() {
    return deathDate;
  }

  // toString method
  public String toString() {
    if (deathDate == null) {
      return name + ", " + birthDate + " -";
    } else {
      return name + ", " + birthDate + " - " + deathDate;
    }
  }

  // equals method
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Person other = (Person) obj;

    return name.equals(other.name) &&
        birthDate.equals(other.birthDate) &&
        ((deathDate == null && other.deathDate == null) ||
            (deathDate != null && deathDate.equals(other.deathDate)));
  }
}
