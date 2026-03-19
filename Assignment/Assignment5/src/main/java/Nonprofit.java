import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a non-profit organization that tracks donations it receives.
 * Supports adding donations of any type and querying the total donations
 * processed in a given year.
 */
public class Nonprofit {
  protected String name;
  protected Collection<Donations> donations;

  /**
   * Constructs a Nonprofit with the given name and an empty donations collection.
   *
   * @param name the name of the non-profit organization
   */
  public Nonprofit(String name) {
    this.name = name;
    donations = new ArrayList<>();
  }

  /**
   * Adds a donation to this non-profit's collection.
   *
   * @param donation the donation to add
   */
  public void addDonation(Donations donation) {
    donations.add(donation);
  }

  /**
   * Calculates and returns the total donations processed in the specified year.
   * Each donation type determines its own contribution logic for the given year.
   *
   * @param year the calendar year to sum donations for
   * @return the total donation amount processed in the given year
   */
  public double getTotalDonationsForYear(int year) {
    double total = 0; // local variable, not a field — avoids accumulation across calls
    for (Donations donation : donations) {
      total += donation.getAmountOfYear(year);
    }
    return total;
  }

  /**
   * Returns the name of the non-profit organization.
   *
   * @return the organization's name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the non-profit organization.
   *
   * @param name the new organization name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the collection of all donations made to this non-profit.
   *
   * @return the donations collection
   */
  public Collection<Donations> getDonations() {
    return donations;
  }

  /**
   * Replaces the current donations collection with the provided one.
   *
   * @param donations the new collection of donations
   */
  public void setDonations(Collection<Donations> donations) {
    this.donations = donations;
  }
}