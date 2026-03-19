import java.time.LocalDateTime;

/**
 * Represents a pledged donation to a non-profit organization.
 * A pledge is a promise to donate a given amount at a future date.
 * The processing date (when the pledge will be fulfilled) may or may not
 * be known at the time the pledge is created, and can be updated or removed later.
 */
public class Pledge extends Donations {
  protected LocalDateTime processingDate;

  /**
   * Constructs a Pledge with a specified processing date.
   *
   * @param amount         the pledged donation amount
   * @param creationDate   the date and time the pledge was created
   * @param processingDate the date and time the pledge will be processed
   * @throws IllegalArgumentException if the processing date is not after the creation date
   */
  public Pledge(double amount, LocalDateTime creationDate, LocalDateTime processingDate) {
    super(amount, creationDate);
    if (processingDate.isAfter(creationDate)) {
      this.processingDate = processingDate;
    } else {
      throw new IllegalArgumentException("Processing date must be after creation date");
    }
  }

  /**
   * Constructs a Pledge without a processing date.
   * The processing date can be set later via {@link #setProcessingDate(LocalDateTime)}.
   *
   * @param amount       the pledged donation amount
   * @param creationDate the date and time the pledge was created
   */
  public Pledge(double amount, LocalDateTime creationDate) {
    super(amount, creationDate);
  }

  /**
   * Returns the date and time the pledge is scheduled to be processed,
   * or {@code null} if no processing date has been set.
   *
   * @return the processing date and time, or {@code null} if not set
   */
  public LocalDateTime getProcessingDate() {
    return processingDate;
  }

  /**
   * Sets or updates the processing date for this pledge.
   * The processing date can also be set to {@code null} to remove it.
   *
   * @param processingDate the new processing date and time
   * @throws IllegalArgumentException if the processing date is not after the creation date
   */
  public void setProcessingDate(LocalDateTime processingDate) {
    if (processingDate.isAfter(creationDate)) {
      this.processingDate = processingDate;
    } else {
      throw new IllegalArgumentException("Processing date must be after creation date");
    }
  }

  /**
   * Returns the pledged amount if the processing date falls within the specified year.
   * If no processing date is set, returns 0.
   *
   * @param year the calendar year to calculate donations for
   * @return the pledged amount if processed in the given year, or 0 otherwise
   */
  @Override
  public double getAmountOfYear(int year) {
    if (processingDate != null && processingDate.getYear() == year) {
      return amount;
    } else {
      return 0;
    }
  }
}