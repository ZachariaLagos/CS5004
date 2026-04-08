package problem1;

/**
 * Adapter that makes LegacyThermostat compatible with SmartDevice interface.
 * This is the Adapter Pattern - adapting an incompatible interface.
 */
public class LegacyThermostatAdapter implements SmartDevice {
    private LegacyThermostat legacyThermostat;

    public LegacyThermostatAdapter(LegacyThermostat legacyThermostat) {
        this.legacyThermostat = legacyThermostat;
    }

    @Override
    public void turnOn() {
        legacyThermostat.activate();
    }

    @Override
    public void turnOff() {
        legacyThermostat.deactivate();
    }

    @Override
    public String getStatus() {
        String status = legacyThermostat.isActive() ? "ACTIVE" : "INACTIVE";
        return "Thermostat: " + status + " (Current: " + legacyThermostat.getCurrentTemp() 
               + "°F, Target: " + legacyThermostat.getTargetTemp() + "°F)";
    }

    public void setTargetTemperature(int temp) {
        legacyThermostat.setTargetTemp(temp);
    }
}
