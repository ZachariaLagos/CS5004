import java.io.*;
import java.util.*;

/**
 * Main program for managing a student database stored in students.txt.
 * Supports loading, printing, adding, removing, and searching students
 * via a console menu.
 */
public class Main {

  /** In-memory list of all students loaded from file. */
  static ArrayList<Student> students = new ArrayList<>();

  /** Path to the persistent student data file. */
  static final String FILE_NAME = "students.txt";

  /**
   * Entry point. Loads students from file, prints the initial list,
   * then runs the interactive menu loop until the user exits.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    loadStudentsFromFile();
    printStudents();

    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      System.out.println("\n--- Menu ---");
      System.out.println("1. Add a student");
      System.out.println("2. Remove a student by ID");
      System.out.println("3. Search for a student by ID");
      System.out.println("4. Exit");
      System.out.print("Choose an option: ");

      int choice = -1;
      if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
        scanner.nextLine();
      } else {
        scanner.nextLine();
        System.out.println("Invalid input. Please enter a number.");
        continue;
      }

      switch (choice) {
        case 1 -> addStudent(scanner);
        case 2 -> removeStudent(scanner);
        case 3 -> searchStudent(scanner);
        case 4 -> running = false;
        default -> System.out.println("Invalid option. Try again.");
      }
    }

    scanner.close();
    System.out.println("Goodbye!");
  }

  /**
   * Reads students.txt line by line and populates the {@code students} list.
   * Clears any previously loaded data before reading.
   * Skips blank lines and lines with fewer than 4 fields.
   */
  static void loadStudentsFromFile() {
    students.clear();
    try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine().trim();
        if (line.isEmpty()) continue;

        String[] parts = line.split("\\s+");
        if (parts.length < 4) {
          System.out.println("Skipping malformed line: " + line);
          continue;
        }

        String firstName = parts[0];
        String lastName  = parts[1];
        int    studentID = Integer.parseInt(parts[2]);
        String email     = parts[3];

        students.add(new Student(firstName, lastName, studentID, email));
      }
    } catch (FileNotFoundException e) {
      System.out.println("students.txt not found. Starting with empty list.");
    }
  }

  /**
   * Writes the current {@code students} list to students.txt,
   * overwriting its previous contents.
   * Each student is written on its own line using {@link Student#toString()}.
   */
  static void saveStudentsToFile() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
      for (Student s : students) {
        writer.println(s.toString());
      }
    } catch (IOException e) {
      System.out.println("Error saving to file: " + e.getMessage());
    }
  }

  /**
   * Prints all students in the {@code students} list to the console,
   * each prefixed with their 1-based line number.
   * Displays a message if the list is empty.
   */
  static void printStudents() {
    if (students.isEmpty()) {
      System.out.println("No students on record.");
      return;
    }
    System.out.println("\n--- Student List ---");
    for (int i = 0; i < students.size(); i++) {
      Student s = students.get(i);
      System.out.printf("%-3d | %-10s %-12s | ID: %-6d | %s%n",
          i + 1,
          s.getFirstName(),
          s.getLastName(),
          s.getStudentID(),
          s.getEmail());
    }
  }

  /**
   * Prompts the user to enter details for a new student, validates the input,
   * checks for duplicate IDs, adds the student to the list, saves the file,
   * and prints the updated list.
   *
   * @param scanner the Scanner used to read user input
   */
  static void addStudent(Scanner scanner) {
    System.out.print("Enter first name: ");
    String firstName = scanner.nextLine().trim();

    System.out.print("Enter last name: ");
    String lastName = scanner.nextLine().trim();

    System.out.print("Enter student ID: ");
    int studentID;
    try {
      studentID = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Invalid ID. Student not added.");
      return;
    }

    for (Student s : students) {
      if (s.getStudentID() == studentID) {
        System.out.println("A student with ID " + studentID + " already exists.");
        return;
      }
    }

    System.out.print("Enter email: ");
    String email = scanner.nextLine().trim();

    students.add(new Student(firstName, lastName, studentID, email));
    saveStudentsToFile();
    System.out.println("Student added successfully.");
    printStudents();
  }

  /**
   * Prompts the user for a student ID, removes the matching student
   * from the list and from students.txt, then prints the updated list.
   * Displays an error message if no match is found.
   *
   * @param scanner the Scanner used to read user input
   */
  static void removeStudent(Scanner scanner) {
    System.out.print("Enter student ID to remove: ");
    int id;
    try {
      id = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Invalid ID.");
      return;
    }

    Iterator<Student> iter = students.iterator();
    boolean found = false;
    while (iter.hasNext()) {
      if (iter.next().getStudentID() == id) {
        iter.remove();
        found = true;
        break;
      }
    }

    if (found) {
      saveStudentsToFile();
      System.out.println("Student with ID " + id + " removed.");
      printStudents();
    } else {
      System.out.println("Error: No student found with ID " + id + ".");
    }
  }

  /**
   * Prompts the user for a student ID and displays the matching student's
   * details if found. Displays an error message if no match is found.
   *
   * @param scanner the Scanner used to read user input
   */
  static void searchStudent(Scanner scanner) {
    System.out.print("Enter student ID to search: ");
    int id;
    try {
      id = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Invalid ID.");
      return;
    }

    for (Student s : students) {
      if (s.getStudentID() == id) {
        System.out.println("\nStudent found:");
        System.out.printf("Name: %s %s | ID: %d | Email: %s%n",
            s.getFirstName(), s.getLastName(),
            s.getStudentID(), s.getEmail());
        return;
      }
    }

    System.out.println("Error: No student found with ID " + id + ".");
  }
}