package problem1;

/**
 * Legacy thermostat with incompatible interface.
 * Uses different method names than SmartDevice interface.
 */
public class LegacyThermostat {
    private boolean isActive;
    private int currentTemp;
    private int targetTemp;

    public LegacyThermostat() {
        this.isActive = false;
        this.currentTemp = 72;
        this.targetTemp = 70;
    }

    public void activate() {
        isActive = true;
        System.out.println("Legacy Thermostat ACTIVATED - Target: " + targetTemp + "°F");
    }

    public void deactivate() {
        isActive = false;
        System.out.println("Legacy Thermostat DEACTIVATED");
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setTargetTemp(int temp) {
        this.targetTemp = temp;
    }

    public int getTargetTemp() {
        return targetTemp;
    }

    public boolean isActive() {
        return isActive;
    }
}
