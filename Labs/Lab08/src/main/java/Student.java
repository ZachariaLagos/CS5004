/**
 * Represents a student with a first name, last name, student ID, and email.
 */
public class Student {
  private String firstName;
  private String lastName;
  private int studentID;
  private String email;

  /**
   * Constructs a new Student with the given details.
   *
   * @param firstName the student's first name
   * @param lastName  the student's last name
   * @param studentID the student's unique ID number
   * @param email     the student's email address
   */
  public Student(String firstName, String lastName, int studentID, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.studentID = studentID;
    this.email = email;
  }

  /**
   * Returns the student's first name.
   *
   * @return first name
   */
  public String getFirstName() { return firstName; }

  /**
   * Returns the student's last name.
   *
   * @return last name
   */
  public String getLastName() { return lastName; }

  /**
   * Returns the student's ID number.
   *
   * @return student ID
   */
  public int getStudentID() { return studentID; }

  /**
   * Returns the student's email address.
   *
   * @return email
   */
  public String getEmail() { return email; }

  /**
   * Returns a space-separated string representation of the student,
   * matching the format used in students.txt.
   *
   * @return formatted student string
   */
  @Override
  public String toString() {
    return firstName + " " + lastName + " " + studentID + " " + email;
  }
}