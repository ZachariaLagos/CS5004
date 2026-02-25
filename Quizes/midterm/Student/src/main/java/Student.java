import java.util.Arrays;

public class Student implements Comparable {
  private String lastName;
  private int idNumber;

  public Student(String lastName, int idNumber) {
    this.lastName = lastName;
    this.idNumber = idNumber;
  }

  // Accessors
  public String getLastName() { return lastName; }
  public int getIdNumber() { return idNumber; }

  // Mutators
  public void setLastName(String lastName) { this.lastName = lastName; }
  public void setIdNumber(int idNumber) { this.idNumber = idNumber; }

  @Override
  public int compareTo(Object o) {
    Student other = (Student) o;
    // Sort by last name (lexicographic)
    return this.lastName.compareTo(other.lastName);
    // To sort by ID instead, use:
    // return this.idNumber - other.idNumber;
  }

  public static void main(String[] args) {
    Student[] students = new Student[5];
    students[0] = new Student("Bobby", 110);
    students[1] = new Student("Tom", 1);
    students[2] = new Student("Alice", 300);
    students[3] = new Student("Dan", 20);
    students[4] = new Student("Elf", 5);

    Arrays.sort(students);

    for (Student s : students) {
      System.out.println(s.getLastName() + " " + s.getIdNumber());
    }
  }
}