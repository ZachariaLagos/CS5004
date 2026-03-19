import java.time.LocalDateTime;

/**
 * Abstract base class representing a donation made to a non-profit organization.
 * All donations have an amount and a creation date/time. Subclasses define
 * how the donation amount is calculated for a given year.
 */
public abstract class Donations {
  protected double amount;
  protected LocalDateTime creationDate;

  /**
   * Constructs a Donations object with the specified amount and creation date.
   *
   * @param amount       the monetary value of the donation
   * @param creationDate the date and time the donation was made
   */
  public Donations(double amount, LocalDateTime creationDate) {
    this.amount = amount;
    this.creationDate = creationDate;
  }

  /**
   * Returns the date and time the donation was created.
   *
   * @return the creation date and time
   */
  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  /**
   * Sets the creation date and time of the donation.
   *
   * @param creationDate the new creation date and time
   */
  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * Returns the monetary amount of the donation.
   *
   * @return the donation amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Sets the monetary amount of the donation.
   *
   * @param amount the new donation amount
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * Returns the total amount contributed by this donation in the specified year.
   * The calculation logic varies by donation type and is defined in each subclass.
   *
   * @param year the calendar year to calculate donations for
   * @return the total donation amount for the given year
   */
  public abstract double getAmountOfYear(int year);
}