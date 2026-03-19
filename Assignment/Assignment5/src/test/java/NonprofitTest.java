import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Nonprofit donation tracking system.
 * Covers one-time, monthly, and pledge donation types across various year scenarios.
 */
class NonprofitTest {
  private Nonprofit org;

  private OneTime donation1;
  private Monthly donation2;
  private Monthly donation3;
  private Monthly donation7; // monthly spanning multiple years, no cancel
  private Pledge donation4;
  private Pledge donation5;
  private Pledge donation6;

  /**
   * Sets up a Nonprofit instance with a variety of donation types before each test.
   * donation1 - OneTime $200.10 in 2020
   * donation2 - Monthly $100.50 starting June 2021, no cancel (active into 2022+)
   * donation3 - Monthly $100.50 starting June 2022, cancelled July 2022
   * donation4 - Pledge $150.10 created Jan 2023, no processing date
   * donation5 - Pledge $250.50 created Aug 2024, processed Sept 2025
   * donation6 - Pledge $250.50 created Aug 2025, processed Sept 2025
   * donation7 - Monthly $50.00 starting Nov 2024, no cancel (spans 2024, 2025, 2026+)
   */
  @BeforeEach
  void setUp() {
    org = new Nonprofit("EastNorthern");

    donation1 = new OneTime(200.1, LocalDateTime.of(2020, Month.MARCH, 25, 0, 0));

    donation2 = new Monthly(100.5, LocalDateTime.of(2021, Month.JUNE, 25, 0, 0));

    donation3 = new Monthly(100.5, LocalDateTime.of(2022, Month.JUNE, 25, 0, 0));
    donation3.cancel(LocalDateTime.of(2022, 7, 26, 0, 0));

    donation4 = new Pledge(150.1, LocalDateTime.of(2023, 1, 25, 0, 0));

    donation5 = new Pledge(250.5, LocalDateTime.of(2024, 8, 25, 0, 0),
        LocalDateTime.of(2025, 9, 1, 0, 0));

    donation6 = new Pledge(250.5, LocalDateTime.of(2025, 8, 25, 0, 0),
        LocalDateTime.of(2025, 9, 1, 0, 0));

    donation7 = new Monthly(50.0, LocalDateTime.of(2024, Month.NOVEMBER, 1, 0, 0));

    org.addDonation(donation1);
    org.addDonation(donation2);
    org.addDonation(donation3);
    org.addDonation(donation4);
    org.addDonation(donation5);
    org.addDonation(donation6);
    org.addDonation(donation7);
  }


  /**
   * Tests that addDonation correctly adds donations to the collection.
   */
  @Test
  void addDonation() {
    Nonprofit testOrg = new Nonprofit("TestOrg");
    testOrg.addDonation(donation1);
    testOrg.addDonation(donation2);
    testOrg.addDonation(donation3);
    testOrg.addDonation(donation4);
    assertTrue(testOrg.getDonations().contains(donation1));
    assertTrue(testOrg.getDonations().contains(donation2));
    assertTrue(testOrg.getDonations().contains(donation3));
    assertTrue(testOrg.getDonations().contains(donation4));
  }

  /**
   * Tests that getTotalDonationsForYear returns correct totals per year.
   * 2020: donation1 ($200.10)
   * 2021: donation2 (7 months Jun-Dec = $703.50)
   * 2022: donation2 (full year $1206.00) + donation3 (Jun-Jul = $201.00)
   * 2023: donation2 (full year $1206.00) — donation4 has no processing date
   * 2024: donation2 (full year $1206.00) + donation7 (Nov-Dec = $100.00)
   * 2025: donation2 (full year $1206.00) + donation5 ($250.50) + donation6 ($250.50) + donation7 (full year $600.00)
   */
  @Test
  void getTotalDonationsForYear() {
    assertEquals(200.1,   org.getTotalDonationsForYear(2020), 0.001);
    assertEquals(703.5,   org.getTotalDonationsForYear(2021), 0.001);
    assertEquals(1407.0,  org.getTotalDonationsForYear(2022), 0.001);
    assertEquals(1206.0,  org.getTotalDonationsForYear(2023), 0.001);
    assertEquals(1306.0,  org.getTotalDonationsForYear(2024), 0.001);
    assertEquals(2307.0,  org.getTotalDonationsForYear(2025), 0.001);
  }

  /**
   * Tests that getTotalDonationsForYear returns consistent results on repeated calls
   * (i.e. does not accumulate state between calls).
   */
  @Test
  void getTotalDonationsForYearIsIdempotent() {
    double first  = org.getTotalDonationsForYear(2020);
    double second = org.getTotalDonationsForYear(2020);
    assertEquals(first, second, 0.001);
  }

  /**
   * Tests getName returns the correct organization name.
   */
  @Test
  void getName() {
    assertEquals("EastNorthern", org.getName());
  }

  /**
   * Tests setName correctly updates the organization name.
   */
  @Test
  void setName() {
    org.setName("SouthWestern");
    assertEquals("SouthWestern", org.getName());
  }

  // -------------------------------------------------------------------------
  // OneTime tests
  // -------------------------------------------------------------------------

  /**
   * Tests that a OneTime donation only contributes to its creation year.
   */
  @Test
  void oneTimeOnlyCountsInCreationYear() {
    assertEquals(200.1, donation1.getAmountOfYear(2020), 0.001);
    assertEquals(0.0,   donation1.getAmountOfYear(2019), 0.001);
    assertEquals(0.0,   donation1.getAmountOfYear(2021), 0.001);
  }

  // -------------------------------------------------------------------------
  // Monthly tests
  // -------------------------------------------------------------------------

  /**
   * Tests a monthly donation with no cancel date spanning multiple years.
   * donation2 starts June 2021 — should count 7 months in 2021, 12 in 2022+.
   */
  @Test
  void monthlyNoCancelSpansYears() {
    assertEquals(703.5,  donation2.getAmountOfYear(2021), 0.001); // Jun-Dec = 7
    assertEquals(1206.0, donation2.getAmountOfYear(2022), 0.001); // full year = 12
    assertEquals(1206.0, donation2.getAmountOfYear(2023), 0.001); // full year = 12
  }

  /**
   * Tests a monthly donation cancelled within the same year it was created.
   * donation3 starts June 2022, cancelled July 2022 — should count 2 months.
   */
  @Test
  void monthlyCancelledSameYear() {
    assertEquals(201.0, donation3.getAmountOfYear(2022), 0.001); // Jun-Jul = 2
    assertEquals(0.0,   donation3.getAmountOfYear(2023), 0.001); // cancelled before 2023
  }

  /**
   * Tests a monthly donation with no cancel spanning from 2024 into future years.
   * donation7 starts Nov 2024 — should count 2 months in 2024, 12 in 2025.
   */
  @Test
  void monthlyNoCancelStartMidYear() {
    assertEquals(100.0,  donation7.getAmountOfYear(2024), 0.001); // Nov-Dec = 2
    assertEquals(600.0,  donation7.getAmountOfYear(2025), 0.001); // full year = 12
    assertEquals(0.0,    donation7.getAmountOfYear(2023), 0.001); // not started yet
  }

  /**
   * Tests that cancel() throws when cancel date is before creation date.
   */
  @Test
  void monthlyCancelBeforeCreationThrows() {
    Monthly m = new Monthly(50.0, LocalDateTime.of(2023, 6, 1, 0, 0));
    assertThrows(IllegalArgumentException.class,
        () -> m.cancel(LocalDateTime.of(2023, 5, 1, 0, 0)));
  }

  // -------------------------------------------------------------------------
  // Pledge tests
  // -------------------------------------------------------------------------

  /**
   * Tests that a Pledge with no processing date contributes $0 to any year.
   */
  @Test
  void pledgeNoProcessingDateReturnsZero() {
    assertEquals(0.0, donation4.getAmountOfYear(2023), 0.001);
    assertEquals(0.0, donation4.getAmountOfYear(2024), 0.001);
  }

  /**
   * Tests that a Pledge contributes only in its processing year.
   */
  @Test
  void pledgeCountsInProcessingYear() {
    assertEquals(250.5, donation5.getAmountOfYear(2025), 0.001);
    assertEquals(0.0,   donation5.getAmountOfYear(2024), 0.001);
    assertEquals(0.0,   donation5.getAmountOfYear(2026), 0.001);
  }

  /**
   * Tests that the Pledge constructor throws when processing date is before creation date.
   */
  @Test
  void pledgeConstructorInvalidProcessingDateThrows() {
    assertThrows(IllegalArgumentException.class, () ->
        new Pledge(250.5,
            LocalDateTime.of(2024, 8, 25, 0, 0),
            LocalDateTime.of(2023, 9, 1, 0, 0)));
  }

  /**
   * Tests that setProcessingDate throws when the new date is before creation date.
   */
  @Test
  void pledgeSetProcessingDateBeforeCreationThrows() {
    assertThrows(IllegalArgumentException.class,
        () -> donation4.setProcessingDate(LocalDateTime.of(2022, 1, 1, 0, 0)));
  }

  /**
   * Tests that setProcessingDate correctly updates the processing year.
   */
  @Test
  void pledgeSetProcessingDateUpdatesYear() {
    donation4.setProcessingDate(LocalDateTime.of(2024, 6, 1, 0, 0));
    assertEquals(150.1, donation4.getAmountOfYear(2024), 0.001);
    assertEquals(0.0,   donation4.getAmountOfYear(2023), 0.001);
  }
}