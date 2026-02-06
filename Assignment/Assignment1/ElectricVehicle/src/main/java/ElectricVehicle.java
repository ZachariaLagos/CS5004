/**
 * represent an Electric Vehicle with its details: name, state of  charge and range
 */
public class ElectricVehicle {
  private String name;
  private double batterySize;
  private double stateOfCharge;
  private double currentEfficiency;
  private double defaultEfficiency;

  /**
   * creates an Electric Vehicle given the four attributes
   * @param name, the vehicle's brand and model
   * @param batterySize, the vehicle's battery size, clamped between 10.0 and 150.0
   * @param stateOfCharge, the vehicle's state of Charge, clamped between 0.15 and 1.0
   * @param defaultEfficiency, vehicle's base efficiency, clamped between 0.5 and 4.5
   */
  public ElectricVehicle(String name, double batterySize, double stateOfCharge, double  defaultEfficiency){
    switch(name){
      case "":
      case null:
        this.name = "unknown EV";
        break;
      default:
        this.name = name;
        break;
    }

    if (batterySize < 10.0){
      this.batterySize = 10.0;
    } else if (batterySize > 150.0) {
      this.batterySize = 150.0;
    } else{
      this.batterySize = batterySize;
    }

    if (stateOfCharge<0.15){
      this.stateOfCharge = 0.15;
    } else if (stateOfCharge>1.0) {
      this.stateOfCharge = 1.0;
    }else {
      this.stateOfCharge=stateOfCharge;}


    if (defaultEfficiency < 0.5){
      this.defaultEfficiency = 0.5;
    } else if (defaultEfficiency > 4.5) {
      this.defaultEfficiency = 4.5;
    }else {
      this.defaultEfficiency = defaultEfficiency;
    }
    this.currentEfficiency = this.defaultEfficiency;


  }

  /**
   * calculate the range using current efficiency, state of charge and battery size
   * @return the calculated range
   */
  public double range(){
    double range = this.currentEfficiency * this.stateOfCharge * this.batterySize;
    return range;
  }

  /**
   * adjust current efficiency based on the current temperature
   * @param currentTemp, the temperature at the current time
   */
  public void updateEfficiency(double currentTemp){
    if (currentTemp >= 65.0 && currentTemp <= 77.0){
      this.currentEfficiency =1.0 * this.defaultEfficiency;
    } else if (currentTemp >= 77.0) {
      this.currentEfficiency = 0.85 * this.defaultEfficiency;
    } else if (currentTemp > 15.0){
        this.currentEfficiency = (100 - (65.0 - currentTemp)) / 100  * this.defaultEfficiency;
    }
    else {this.currentEfficiency= 0.5 * this.defaultEfficiency;}
  }

  /**
   * get the current efficiency
   * @return the current efficiency
   */
  public double getEfficiency(){
    return this.currentEfficiency;}

  /**
   * get the unchanged default efficiency
   * @return the default efficiency
   */
  public double getDefaultEfficiency(){
    return this.defaultEfficiency;}

  /**
   * get the battery size
   * @return the battery size
   */
  public double getBatterySize(){
    return this.batterySize;}

  /**
   * get the state of charge
   * @return the state of charge
   */
  public double getStateOfCharge() {
    return this.stateOfCharge;}

  /**
   * get the brand and model name of the car
   * @return the name
   */
  public String getName(){
    return this.name;}

  /**
   * change the state of charge
   * @param stateOfCharge, current state of charge, clamped between 0.15 and 1.0
   */
  public void setStateOfCharge(double stateOfCharge){
    if (stateOfCharge < 0.15){
      this.stateOfCharge = 0.15;
    } else if (stateOfCharge > 1.0) {
      this.stateOfCharge = 1.0;
    }else{this.stateOfCharge = stateOfCharge;}
  }

  /**
   * print out to string value, including name, state of charge(SOC) and range in miles
   * @return, name, state of charge (in percentage), range (in miles)
   */
  public String toString(){
    return String.format("%s SOC: %.1f%% Range (miles): %.1f", this.name, this.stateOfCharge * 100, range());
  }


}
