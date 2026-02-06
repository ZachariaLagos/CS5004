public class Date {
  private String month;
  private int day;
  private int year;

  // Constructor
  public Date(String month, int day, int year) {
    this.month = month;
    this.day = day;
    this.year = year;
  }

  // Copy constructor
  public Date(Date other) {
    this.month = other.month;
    this.day = other.day;
    this.year = other.year;
  }

  // Getters
  public String getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }

  public int getYear() {
    return year;
  }

  // toString method
  public String toString() {
    return month + " " + day + ", " + year;
  }

  // equals method
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Date other = (Date) obj;
    return day == other.day &&
        year == other.year &&
        month.equals(other.month);
  }
}