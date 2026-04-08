package problem1;

/**
 * CS5004 Spring 2026 - Lab 9: Design Patterns
 * Problem 1: Smart Home Control System
 * 
 * Main class demonstrating all design patterns:
 * - Singleton Pattern: SmartHomeController
 * - Factory Method Pattern: DeviceFactory
 * - Adapter Pattern: LegacyThermostatAdapter
 * - Facade Pattern: SmartHomeFacade
 */
public class SmartHomeSystem {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║     CS5004 Lab 9 - Problem 1: Smart Home Control System      ║");
        System.out.println("║                    Design Patterns Demo                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ====================================================================
        // DEMONSTRATE SINGLETON PATTERN
        // ====================================================================
        System.out.println("=== SINGLETON PATTERN: SmartHomeController ===");
        SmartHomeController controller1 = SmartHomeController.getInstance();
        SmartHomeController controller2 = SmartHomeController.getInstance();
        System.out.println("controller1 == controller2: " + (controller1 == controller2));
        System.out.println("(Same instance confirms Singleton pattern)\n");

        // ====================================================================
        // DEMONSTRATE FACTORY METHOD PATTERN
        // ====================================================================
        System.out.println("=== FACTORY METHOD PATTERN: DeviceFactory ===");
        System.out.println("Creating devices through factory...\n");
        
        SmartDevice livingRoomLight = DeviceFactory.createDevice("light", "Living Room");
        SmartDevice bedroomLight = DeviceFactory.createDevice("light", "Bedroom");
        SmartDevice kitchenSpeaker = DeviceFactory.createDevice("speaker", "Kitchen");
        SmartDevice officeSpeaker = DeviceFactory.createDevice("speaker", "Office");
        
        System.out.println("\nDevices created via Factory:");
        System.out.println("  - " + livingRoomLight.getStatus());
        System.out.println("  - " + bedroomLight.getStatus());
        System.out.println("  - " + kitchenSpeaker.getStatus());
        System.out.println("  - " + officeSpeaker.getStatus());

        // ====================================================================
        // DEMONSTRATE ADAPTER PATTERN
        // ====================================================================
        System.out.println("\n=== ADAPTER PATTERN: LegacyThermostatAdapter ===");
        System.out.println("Integrating legacy thermostat with incompatible interface...\n");
        
        LegacyThermostat legacyThermostat = new LegacyThermostat();
        SmartDevice thermostatAdapter = new LegacyThermostatAdapter(legacyThermostat);
        
        System.out.println("Legacy thermostat now works as SmartDevice:");
        System.out.println("  - " + thermostatAdapter.getStatus());
        
        System.out.println("\nUsing SmartDevice interface on legacy thermostat:");
        thermostatAdapter.turnOn();
        System.out.println("  Status: " + thermostatAdapter.getStatus());
        thermostatAdapter.turnOff();
        System.out.println("  Status: " + thermostatAdapter.getStatus());

        // ====================================================================
        // ADD DEVICES TO SINGLETON CONTROLLER
        // ====================================================================
        System.out.println("\n=== Adding Devices to Singleton Controller ===");
        controller1.addDevice(livingRoomLight);
        controller1.addDevice(bedroomLight);
        controller1.addDevice(kitchenSpeaker);
        controller1.addDevice(officeSpeaker);
        controller1.addDevice(thermostatAdapter);

        controller1.showAllStatus();

        // ====================================================================
        // DEMONSTRATE FACADE PATTERN
        // ====================================================================
        System.out.println("\n=== FACADE PATTERN: SmartHomeFacade ===");
        System.out.println("Using facade for complex multi-device operations...");
        
        SmartHomeFacade facade = new SmartHomeFacade();

        facade.welcomeHome();
        controller1.showAllStatus();

        facade.activateMovieMode();
        controller1.showAllStatus();

        facade.activateNightMode();
        controller1.showAllStatus();

        facade.leaveHome();
        controller1.showAllStatus();

        // ====================================================================
        // SUMMARY
        // ====================================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    PATTERNS DEMONSTRATED                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. SINGLETON: SmartHomeController - single instance          ║");
        System.out.println("║  2. FACTORY METHOD: DeviceFactory - creates devices by type   ║");
        System.out.println("║  3. ADAPTER: LegacyThermostatAdapter - interface conversion   ║");
        System.out.println("║  4. FACADE: SmartHomeFacade - simplified complex operations   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
