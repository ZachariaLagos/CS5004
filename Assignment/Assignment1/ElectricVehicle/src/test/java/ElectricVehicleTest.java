import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing Electric Vehicle class with three ev objects
 */
class ElectricVehicleTest {
  private ElectricVehicle ev1;
  private ElectricVehicle ev2;
  private ElectricVehicle ev3;
  private ElectricVehicle ev4;
  private ElectricVehicle ev5;

  /**
   * Test data for EV validation across boundary and edge cases.
   *
   * the first EV with all valid fields (Ford MachE, 100.0kWh battery, 50% charge 5, 4.0 mi/kWh efficiency)
   * the second EV with name of zero-length; other fields at upper bound.
   * the third EV with null name; other fields at lower bounds.
   * the fourth EV with valid name of Tesla Model Y and battery size; other fields exceed upper bounds.
   * the fifth EV with name of Kia EV6; other fields below lower bounds.
   */
  @BeforeEach
  void setUp() {
    this.ev1 = new ElectricVehicle("Ford MachE", 100.0, 0.5,  4.0);
    this.ev2 = new ElectricVehicle("", 150.0, 1.0, 4.5);
    this.ev3 = new ElectricVehicle( null, 10.0, 0.15, 0.5);
    this.ev4 = new ElectricVehicle( "Tesla Model Y", 160.0, 1.1, 5.0);
    this.ev5 = new ElectricVehicle( "Kia EV6", 5.0, 0.1, 0.2);
  }

  /**
   * Test the range with three fields: battery size, state of charge and default efficiency
   */
  @Test
  void range() {
    Assertions.assertEquals(100.0 * 0.5 * 4.0, ev1.range() );
    Assertions.assertEquals(150.0*1.0*4.5, ev2.range() );
    Assertions.assertEquals(10.0*0.15*0.5, ev3.range());
    Assertions.assertEquals(150.0*1.0*4.5, ev4.range());
    Assertions.assertEquals(10.0*0.15*0.5, ev5.range());
  }

  /**
   * Tests the updateEfficiency method across temperature ranges: below 15°F, 15–65°F, 65–75°F, and above 75°F.
   */
  @Test
  void updateEfficiency() {
    ev1.updateEfficiency(1.0);
    ev2.updateEfficiency(15.0);
    ev3.updateEfficiency(64.9);
    ev4.updateEfficiency(77.0);
    ev5.updateEfficiency(100.0);
    Assertions.assertEquals(0.5 * ev1.getDefaultEfficiency(), ev1.getEfficiency(), 0.001);
    Assertions.assertEquals(0.5 * ev2.getDefaultEfficiency(), ev2.getEfficiency(), 0.001);
    Assertions.assertEquals(0.999 * ev3.getDefaultEfficiency(), ev3.getEfficiency(), 0.001);
    Assertions.assertEquals(1.0 * ev4.getDefaultEfficiency(), ev4.getEfficiency(), 0.001);
    Assertions.assertEquals(0.85 * ev5.getDefaultEfficiency(), ev5.getEfficiency(), 0.001);
  }

  /**
   * Tests the get efficiency method, at the beginning of each initialization, current efficiency equals default efficiency
   */
  @Test
  void getEfficiency() {
    Assertions.assertEquals(ev1.getDefaultEfficiency(), ev1.getEfficiency());
    Assertions.assertEquals(ev2.getDefaultEfficiency(), ev2.getEfficiency());
    Assertions.assertEquals(ev3.getDefaultEfficiency(), ev3.getEfficiency());
    Assertions.assertEquals(ev4.getDefaultEfficiency(), ev4.getEfficiency());
    Assertions.assertEquals( ev5.getDefaultEfficiency(), ev5.getEfficiency());
  }

  /**
   * Test the get battery size method
   */
  @Test
  void getBatterySize() {
    Assertions.assertEquals(100.0, ev1.getBatterySize());
    Assertions.assertEquals(150.0, ev2.getBatterySize());
    Assertions.assertEquals(10.0, ev3.getBatterySize());
    Assertions.assertEquals(150.0, ev4.getBatterySize());
    Assertions.assertEquals(10.0, ev5.getBatterySize());
  }

  /**
   * Test the get state of charge method
   */
  @Test
  void getStateOfCharge() {
    Assertions.assertEquals(0.5, ev1.getStateOfCharge());
    Assertions.assertEquals(1.0, ev2.getStateOfCharge());
    Assertions.assertEquals(0.15, ev3.getStateOfCharge());
    Assertions.assertEquals(1.0, ev4.getStateOfCharge());
    Assertions.assertEquals(0.15, ev5.getStateOfCharge());
  }

  /**
   * Test the get name method, with unknown EVs
   */
  @Test
  void getName() {
    Assertions.assertEquals("Ford MachE", ev1.getName());
    Assertions.assertEquals("unknown EV", ev2.getName());
    Assertions.assertEquals("unknown EV", ev3.getName());
    Assertions.assertEquals("Tesla Model Y", ev4.getName());
    Assertions.assertEquals("Kia EV6", ev5.getName());
  }

  /**
   * test the set state of charge method
   */
  @Test
  void setStateOfCharge() {
    ev1.setStateOfCharge(1.1);
    ev2.setStateOfCharge(0.8);
    ev3.setStateOfCharge(0.1);
    ev4.setStateOfCharge(1.0);
    ev5.setStateOfCharge(0.15);
    Assertions.assertEquals(1.0, ev1.getStateOfCharge());
    Assertions.assertEquals(0.8, ev2.getStateOfCharge());
    Assertions.assertEquals(0.15, ev3.getStateOfCharge());
    Assertions.assertEquals(1.0, ev4.getStateOfCharge());
    Assertions.assertEquals(0.15, ev5.getStateOfCharge());
  }

  /**
   * test the toString method with details of the EVs
   */
  @Test
  void testToString() {
    String ev1ToString = String.format("Ford MachE SOC: 50.0%% Range (miles): %.1f", ev1.getBatterySize()*ev1.getStateOfCharge()*ev1.getEfficiency());
    String ev2ToString = String.format("unknown EV SOC: 100.0%% Range (miles): %.1f", ev2.getBatterySize()*ev2.getStateOfCharge()*ev2.getEfficiency());
    String ev3ToString = String.format("unknown EV SOC: 15.0%% Range (miles): %.1f", ev3.getBatterySize()*ev3.getStateOfCharge()*ev3.getEfficiency());
    String ev4ToString = String.format("Tesla Model Y SOC: 100.0%% Range (miles): %.1f", ev4.getBatterySize()*ev4.getStateOfCharge()*ev4.getEfficiency());
    String ev5ToString = String.format("Kia EV6 SOC: 15.0%% Range (miles): %.1f", ev5.getBatterySize()*ev5.getStateOfCharge()*ev5.getEfficiency());
    Assertions.assertEquals(ev1ToString, ev1.toString());
    Assertions.assertEquals(ev2ToString, ev2.toString());
    Assertions.assertEquals(ev3ToString, ev3.toString());
    Assertions.assertEquals(ev4ToString, ev4.toString());
    Assertions.assertEquals(ev5ToString, ev5.toString());
  }
}