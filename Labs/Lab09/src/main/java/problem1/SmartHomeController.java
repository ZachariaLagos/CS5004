package problem1;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton controller that manages all smart devices.
 * This is the Singleton Pattern - ensuring only one instance exists.
 */
public class SmartHomeController {
    private static volatile SmartHomeController instance;
    private List<SmartDevice> devices;

    private SmartHomeController() {
        devices = new ArrayList<>();
        System.out.println("SmartHomeController initialized (Singleton)");
    }

    /**
     * Get the singleton instance using double-checked locking.
     * Thread-safe lazy initialization.
     */
    public static SmartHomeController getInstance() {
        if (instance == null) {
            synchronized (SmartHomeController.class) {
                if (instance == null) {
                    instance = new SmartHomeController();
                }
            }
        }
        return instance;
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
        System.out.println("Device added to controller: " + device.getStatus());
    }

    public void removeDevice(SmartDevice device) {
        devices.remove(device);
    }

    public List<SmartDevice> getDevices() {
        return new ArrayList<>(devices);
    }

    public void turnAllOn() {
        System.out.println("\n--- Turning ALL devices ON ---");
        for (SmartDevice device : devices) {
            device.turnOn();
        }
    }

    public void turnAllOff() {
        System.out.println("\n--- Turning ALL devices OFF ---");
        for (SmartDevice device : devices) {
            device.turnOff();
        }
    }

    public void showAllStatus() {
        System.out.println("\n--- Device Status ---");
        for (SmartDevice device : devices) {
            System.out.println("  " + device.getStatus());
        }
    }
}
