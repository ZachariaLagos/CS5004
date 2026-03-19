import java.time.LocalDateTime;

/**
 * Represents a one-time donation made to a non-profit organization.
 * The full donation amount is counted only in the year the donation was created.
 */
public class OneTime extends Donations {

  /**
   * Constructs a OneTime donation with the specified amount and creation date.
   *
   * @param amount       the monetary value of the donation
   * @param creationDate the date and time the donation was made
   */
  public OneTime(double amount, LocalDateTime creationDate) {
    super(amount, creationDate);
  }

  /**
   * Returns the donation amount if the creation year matches the specified year,
   * otherwise returns 0.
   *
   * @param year the calendar year to calculate donations for
   * @return the donation amount if it occurred in the given year, or 0 otherwise
   */
  public double getAmountOfYear(int year) {
    if (creationDate.getYear() == year) {
      return amount;
    } else {
      return 0;
    }
  }
}