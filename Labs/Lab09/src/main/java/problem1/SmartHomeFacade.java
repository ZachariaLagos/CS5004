package problem1;

/**
 * Facade that provides simple interface for complex operations.
 * This is the Facade Pattern - simplifying complex subsystem interactions.
 */
public class SmartHomeFacade {
    private SmartHomeController controller;

    public SmartHomeFacade() {
        this.controller = SmartHomeController.getInstance();
    }

    /**
     * Activates night mode: dims lights, turns off speakers, sets thermostat low.
     */
    public void activateNightMode() {
        System.out.println("\n========================================");
        System.out.println("ACTIVATING NIGHT MODE");
        System.out.println("========================================");
        
        for (SmartDevice device : controller.getDevices()) {
            if (device instanceof SmartLight) {
                SmartLight light = (SmartLight) device;
                light.setBrightness(10);
                light.turnOn();
                System.out.println("  -> Light dimmed to 10%");
            } else if (device instanceof SmartSpeaker) {
                device.turnOff();
                System.out.println("  -> Speaker turned off");
            } else if (device instanceof LegacyThermostatAdapter) {
                LegacyThermostatAdapter thermostat = (LegacyThermostatAdapter) device;
                thermostat.setTargetTemperature(68);
                thermostat.turnOn();
                System.out.println("  -> Thermostat set to 68°F for sleeping");
            }
        }
        System.out.println("Night mode activated!");
    }

    /**
     * Leave home mode: turns off all devices for security and energy saving.
     */
    public void leaveHome() {
        System.out.println("\n========================================");
        System.out.println("ACTIVATING LEAVE HOME MODE");
        System.out.println("========================================");
        
        for (SmartDevice device : controller.getDevices()) {
            if (device instanceof SmartLight) {
                device.turnOff();
                System.out.println("  -> Light turned off");
            } else if (device instanceof SmartSpeaker) {
                device.turnOff();
                System.out.println("  -> Speaker turned off");
            } else if (device instanceof LegacyThermostatAdapter) {
                LegacyThermostatAdapter thermostat = (LegacyThermostatAdapter) device;
                thermostat.setTargetTemperature(62);
                thermostat.turnOn();
                System.out.println("  -> Thermostat set to energy-saving 62°F");
            }
        }
        System.out.println("Home secured for departure!");
    }

    /**
     * Welcome home mode: turns on lights and sets comfortable temperature.
     */
    public void welcomeHome() {
        System.out.println("\n========================================");
        System.out.println("ACTIVATING WELCOME HOME MODE");
        System.out.println("========================================");
        
        for (SmartDevice device : controller.getDevices()) {
            if (device instanceof SmartLight) {
                SmartLight light = (SmartLight) device;
                light.setBrightness(80);
                light.turnOn();
                System.out.println("  -> Light set to welcoming 80%");
            } else if (device instanceof SmartSpeaker) {
                SmartSpeaker speaker = (SmartSpeaker) device;
                speaker.setVolume(30);
                speaker.turnOn();
                System.out.println("  -> Speaker playing ambient music");
            } else if (device instanceof LegacyThermostatAdapter) {
                LegacyThermostatAdapter thermostat = (LegacyThermostatAdapter) device;
                thermostat.setTargetTemperature(72);
                thermostat.turnOn();
                System.out.println("  -> Thermostat set to comfortable 72°F");
            }
        }
        System.out.println("Welcome home!");
    }

    /**
     * Movie mode: dims lights and optimizes speaker.
     */
    public void activateMovieMode() {
        System.out.println("\n========================================");
        System.out.println("ACTIVATING MOVIE MODE");
        System.out.println("========================================");
        
        for (SmartDevice device : controller.getDevices()) {
            if (device instanceof SmartLight) {
                SmartLight light = (SmartLight) device;
                light.setBrightness(5);
                light.turnOn();
                System.out.println("  -> Light dimmed for movie atmosphere");
            } else if (device instanceof SmartSpeaker) {
                SmartSpeaker speaker = (SmartSpeaker) device;
                speaker.setVolume(70);
                speaker.turnOn();
                System.out.println("  -> Speaker set to cinema volume");
            }
        }
        System.out.println("Enjoy your movie!");
    }
}
