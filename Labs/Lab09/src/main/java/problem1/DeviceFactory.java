package problem1;

/**
 * Factory for creating smart devices.
 * This is the Factory Method Pattern - encapsulating object creation.
 */
public class DeviceFactory {
    
    /**
     * Factory method to create devices by type.
     * @param type The type of device ("light", "speaker", "thermostat")
     * @param name The name for the device
     * @return A new SmartDevice instance
     * @throws IllegalArgumentException if type is unknown
     */
    public static SmartDevice createDevice(String type, String name) {
        switch (type.toLowerCase()) {
            case "light":
                return new SmartLight(name);
            case "speaker":
                return new SmartSpeaker(name);
            case "thermostat":
                return new LegacyThermostatAdapter(new LegacyThermostat());
            default:
                throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}
