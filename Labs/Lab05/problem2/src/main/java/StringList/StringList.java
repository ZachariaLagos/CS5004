package StringList;

/**
 * Interface for a List of Strings ADT.
 */
public interface StringList {

  /**
   * Checks whether the list is empty.
   * @return true if the list is empty, false otherwise
   */
  boolean isEmpty();

  /**
   * Gets the total number of elements in the list.
   * @return the size of the list
   */
  int size();

  /**
   * Checks if the given String is in the list.
   * @param s the String to search for
   * @return true if the String is found, false otherwise
   */
  boolean contains(String s);

  /**
   * Checks that all elements of the given list are in this list.
   * @param other the other StringList to check
   * @return true if all elements of other are in this list, false otherwise
   */
  boolean containsAll(StringList other);

  /**
   * Returns a new list with all elements whose length is greater than maxLength removed.
   * @param maxLength the maximum allowed String length
   * @return a filtered StringList
   */
  StringList filterLargerThan(int maxLength);

  /**
   * Checks if the list has at least one duplicate element.
   * @return true if there is at least one duplicate, false otherwise
   */
  boolean hasDuplicates();

  /**
   * Returns a new list with all duplicates removed.
   * @return a StringList with no duplicate elements
   */
  StringList removeDuplicates();

  /**
   * Adds a new String to the front of the list.
   * @param s the String to add
   */
  void add(String s);
}