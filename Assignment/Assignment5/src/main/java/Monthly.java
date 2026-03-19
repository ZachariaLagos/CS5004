import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents a recurring monthly donation made to a non-profit organization.
 * The donation repeats once per month from the creation date until it is
 * cancelled. A cancellation date may be set after creation, provided it is
 * not prior to the creation date.
 */
public class Monthly extends Donations {
  protected LocalDateTime cancelDate;

  /**
   * Constructs a Monthly donation with no cancellation date set.
   *
   * @param amount       the amount donated each month
   * @param creationDate the date and time the monthly donation began
   */
  public Monthly(double amount, LocalDateTime creationDate) {
    super(amount, creationDate);
    this.cancelDate = null;
  }

  /**
   * Sets the cancellation date for this monthly donation.
   *
   * @param cancelDate the date and time the donation is to be cancelled
   * @throws IllegalArgumentException if the cancel date is not after the creation date
   */
  public void cancel(LocalDateTime cancelDate) {
    if (cancelDate.isAfter(creationDate)) {
      this.cancelDate = cancelDate;
    } else {
      throw new IllegalArgumentException("Cancel date must be after creation date.");
    }
  }

  /**
   * Returns the cancellation date and time of this monthly donation,
   * or {@code null} if the donation has not been cancelled.
   *
   * @return the cancellation date and time, or {@code null} if not cancelled
   */
  public LocalDateTime getCancelDate() {
    return cancelDate;
  }

  /**
   * Returns the total amount donated in the specified year.
   * Counts one payment per month for each month the donation was active
   * within the given year, accounting for donations that started in prior
   * years or were cancelled in future years.
   *
   * @param year the calendar year to calculate donations for
   * @return the total amount donated in the given year, or 0 if the donation
   *         was not active at any point during that year
   */
  @Override
  public double getAmountOfYear(int year) {
    // Donation hasn't started yet in this year
    if (creationDate.getYear() > year) return 0;

    // Donation was cancelled before this year
    if (cancelDate != null && cancelDate.getYear() < year) return 0;

    // Determine start month: Jan (1) if donation started in a prior year, else its actual start month
    int startMonth = (creationDate.getYear() < year) ? 1 : creationDate.getMonthValue();

    // Determine end month: Dec (12) if no cancel or cancel is in a future year, else cancel month
    int endMonth;
    if (cancelDate == null || cancelDate.getYear() > year) {
      endMonth = 12;
    } else {
      endMonth = cancelDate.getMonthValue();
    }

    return amount * (endMonth - startMonth + 1);
  }
}