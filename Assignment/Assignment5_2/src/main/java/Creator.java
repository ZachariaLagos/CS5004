/**
 * Abstract base class representing a creator of a library item.
 * A creator can be an individual person or a group.
 */
public abstract class Creator {
  /**
   * Returns a display name for this creator.
   *
   * @return the creator's name as a String
   */
  public abstract String getName();
}