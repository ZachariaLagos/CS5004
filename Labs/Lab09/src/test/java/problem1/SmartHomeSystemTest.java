package problem1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * CS5004 Spring 2026 - Lab 9: Design Patterns
 * Problem 1: Smart Home Control System - Unit Tests
 */
public class SmartHomeSystemTest {

    // ========================================================================
    // SmartLight Tests
    // ========================================================================

    @Test
    public void testSmartLightCreation() {
        SmartLight light = new SmartLight("Living Room");
        assertNotNull(light);
        assertTrue(light.getStatus().contains("Living Room"));
        assertTrue(light.getStatus().contains("OFF"));
    }

    @Test
    public void testSmartLightTurnOn() {
        SmartLight light = new SmartLight("Kitchen");
        light.turnOn();
        assertTrue(light.getStatus().contains("ON"));
    }

    @Test
    public void testSmartLightTurnOff() {
        SmartLight light = new SmartLight("Kitchen");
        light.turnOn();
        light.turnOff();
        assertTrue(light.getStatus().contains("OFF"));
    }

    @Test
    public void testSmartLightBrightness() {
        SmartLight light = new SmartLight("Bedroom");
        light.setBrightness(50);
        light.turnOn();
        assertTrue(light.getStatus().contains("50%"));
    }

    @Test
    public void testSmartLightBrightnessClampingMax() {
        SmartLight light = new SmartLight("Test");
        light.setBrightness(150);
        light.turnOn();
        assertTrue(light.getStatus().contains("100%"));
    }

    @Test
    public void testSmartLightBrightnessClampingMin() {
        SmartLight light = new SmartLight("Test");
        light.setBrightness(-50);
        light.turnOn();
        assertTrue(light.getStatus().contains("0%"));
    }

    // ========================================================================
    // SmartSpeaker Tests
    // ========================================================================

    @Test
    public void testSmartSpeakerCreation() {
        SmartSpeaker speaker = new SmartSpeaker("Office");
        assertNotNull(speaker);
        assertTrue(speaker.getStatus().contains("Office"));
        assertTrue(speaker.getStatus().contains("OFF"));
    }

    @Test
    public void testSmartSpeakerTurnOn() {
        SmartSpeaker speaker = new SmartSpeaker("Office");
        speaker.turnOn();
        assertTrue(speaker.getStatus().contains("ON"));
    }

    @Test
    public void testSmartSpeakerTurnOff() {
        SmartSpeaker speaker = new SmartSpeaker("Office");
        speaker.turnOn();
        speaker.turnOff();
        assertTrue(speaker.getStatus().contains("OFF"));
    }

    @Test
    public void testSmartSpeakerVolume() {
        SmartSpeaker speaker = new SmartSpeaker("Office");
        speaker.setVolume(75);
        speaker.turnOn();
        assertTrue(speaker.getStatus().contains("75"));
    }

    @Test
    public void testSmartSpeakerVolumeClampingMax() {
        SmartSpeaker speaker = new SmartSpeaker("Test");
        speaker.setVolume(150);
        speaker.turnOn();
        assertTrue(speaker.getStatus().contains("100"));
    }

    @Test
    public void testSmartSpeakerVolumeClampingMin() {
        SmartSpeaker speaker = new SmartSpeaker("Test");
        speaker.setVolume(-50);
        speaker.turnOn();
        assertTrue(speaker.getStatus().contains("0"));
    }

    // ========================================================================
    // LegacyThermostat Tests
    // ========================================================================

    @Test
    public void testLegacyThermostatCreation() {
        LegacyThermostat thermostat = new LegacyThermostat();
        assertNotNull(thermostat);
        assertFalse(thermostat.isActive());
        assertEquals(72, thermostat.getCurrentTemp());
    }

    @Test
    public void testLegacyThermostatActivate() {
        LegacyThermostat thermostat = new LegacyThermostat();
        thermostat.activate();
        assertTrue(thermostat.isActive());
    }

    @Test
    public void testLegacyThermostatDeactivate() {
        LegacyThermostat thermostat = new LegacyThermostat();
        thermostat.activate();
        thermostat.deactivate();
        assertFalse(thermostat.isActive());
    }

    @Test
    public void testLegacyThermostatSetTargetTemp() {
        LegacyThermostat thermostat = new LegacyThermostat();
        thermostat.setTargetTemp(75);
        assertEquals(75, thermostat.getTargetTemp());
    }

    // ========================================================================
    // Adapter Pattern Tests - LegacyThermostatAdapter
    // ========================================================================

    @Test
    public void testAdapterImplementsSmartDevice() {
        LegacyThermostat legacy = new LegacyThermostat();
        LegacyThermostatAdapter adapter = new LegacyThermostatAdapter(legacy);
        assertTrue(adapter instanceof SmartDevice);
    }

    @Test
    public void testAdapterTurnOnCallsActivate() {
        LegacyThermostat legacy = new LegacyThermostat();
        LegacyThermostatAdapter adapter = new LegacyThermostatAdapter(legacy);
        adapter.turnOn();
        assertTrue(legacy.isActive());
    }

    @Test
    public void testAdapterTurnOffCallsDeactivate() {
        LegacyThermostat legacy = new LegacyThermostat();
        LegacyThermostatAdapter adapter = new LegacyThermostatAdapter(legacy);
        adapter.turnOn();
        adapter.turnOff();
        assertFalse(legacy.isActive());
    }

    @Test
    public void testAdapterGetStatus() {
        LegacyThermostat legacy = new LegacyThermostat();
        LegacyThermostatAdapter adapter = new LegacyThermostatAdapter(legacy);
        String status = adapter.getStatus();
        assertTrue(status.contains("Thermostat"));
        assertTrue(status.contains("72"));
    }

    @Test
    public void testAdapterSetTargetTemperature() {
        LegacyThermostat legacy = new LegacyThermostat();
        LegacyThermostatAdapter adapter = new LegacyThermostatAdapter(legacy);
        adapter.setTargetTemperature(68);
        assertEquals(68, legacy.getTargetTemp());
    }

    // ========================================================================
    // Factory Method Pattern Tests - DeviceFactory
    // ========================================================================

    @Test
    public void testFactoryCreateLight() {
        SmartDevice device = DeviceFactory.createDevice("light", "Test Light");
        assertNotNull(device);
        assertTrue(device instanceof SmartLight);
        assertTrue(device.getStatus().contains("Test Light"));
    }

    @Test
    public void testFactoryCreateSpeaker() {
        SmartDevice device = DeviceFactory.createDevice("speaker", "Test Speaker");
        assertNotNull(device);
        assertTrue(device instanceof SmartSpeaker);
        assertTrue(device.getStatus().contains("Test Speaker"));
    }

    @Test
    public void testFactoryCreateThermostat() {
        SmartDevice device = DeviceFactory.createDevice("thermostat", "Test Thermostat");
        assertNotNull(device);
        assertTrue(device instanceof LegacyThermostatAdapter);
    }

    @Test
    public void testFactoryCaseInsensitive() {
        SmartDevice light1 = DeviceFactory.createDevice("LIGHT", "Test");
        SmartDevice light2 = DeviceFactory.createDevice("Light", "Test");
        SmartDevice light3 = DeviceFactory.createDevice("light", "Test");
        assertTrue(light1 instanceof SmartLight);
        assertTrue(light2 instanceof SmartLight);
        assertTrue(light3 instanceof SmartLight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryInvalidType() {
        DeviceFactory.createDevice("invalid", "Test");
    }

    // ========================================================================
    // Singleton Pattern Tests - SmartHomeController
    // ========================================================================

    @Test
    public void testSingletonReturnsInstance() {
        SmartHomeController controller = SmartHomeController.getInstance();
        assertNotNull(controller);
    }

    @Test
    public void testSingletonReturnsSameInstance() {
        SmartHomeController controller1 = SmartHomeController.getInstance();
        SmartHomeController controller2 = SmartHomeController.getInstance();
        assertSame(controller1, controller2);
    }

    @Test
    public void testSingletonPrivateConstructor() throws Exception {
        Constructor<SmartHomeController> constructor = SmartHomeController.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    public void testControllerAddDevice() {
        SmartHomeController controller = SmartHomeController.getInstance();
        int initialSize = controller.getDevices().size();
        SmartDevice light = new SmartLight("Test");
        controller.addDevice(light);
        assertEquals(initialSize + 1, controller.getDevices().size());
        controller.removeDevice(light);
    }

    @Test
    public void testControllerRemoveDevice() {
        SmartHomeController controller = SmartHomeController.getInstance();
        SmartDevice light = new SmartLight("Test");
        controller.addDevice(light);
        int sizeAfterAdd = controller.getDevices().size();
        controller.removeDevice(light);
        assertEquals(sizeAfterAdd - 1, controller.getDevices().size());
    }

    @Test
    public void testControllerGetDevicesReturnsDefensiveCopy() {
        SmartHomeController controller = SmartHomeController.getInstance();
        int originalSize = controller.getDevices().size();
        controller.getDevices().add(new SmartLight("Hacker Light"));
        assertEquals(originalSize, controller.getDevices().size());
    }

    // ========================================================================
    // Facade Pattern Tests - SmartHomeFacade
    // ========================================================================

    @Test
    public void testFacadeCreation() {
        SmartHomeFacade facade = new SmartHomeFacade();
        assertNotNull(facade);
    }

    @Test
    public void testFacadeActivateNightMode() {
        SmartHomeController controller = SmartHomeController.getInstance();
        SmartLight light = new SmartLight("Test Light");
        SmartSpeaker speaker = new SmartSpeaker("Test Speaker");
        controller.addDevice(light);
        controller.addDevice(speaker);

        SmartHomeFacade facade = new SmartHomeFacade();
        facade.activateNightMode();

        assertTrue(light.getStatus().contains("ON"));
        assertTrue(speaker.getStatus().contains("OFF"));

        controller.removeDevice(light);
        controller.removeDevice(speaker);
    }

    @Test
    public void testFacadeLeaveHome() {
        SmartHomeController controller = SmartHomeController.getInstance();
        SmartLight light = new SmartLight("Test Light");
        SmartSpeaker speaker = new SmartSpeaker("Test Speaker");
        controller.addDevice(light);
        controller.addDevice(speaker);

        SmartHomeFacade facade = new SmartHomeFacade();
        facade.leaveHome();

        assertTrue(light.getStatus().contains("OFF"));
        assertTrue(speaker.getStatus().contains("OFF"));

        controller.removeDevice(light);
        controller.removeDevice(speaker);
    }

    @Test
    public void testFacadeWelcomeHome() {
        SmartHomeController controller = SmartHomeController.getInstance();
        SmartLight light = new SmartLight("Test Light");
        SmartSpeaker speaker = new SmartSpeaker("Test Speaker");
        controller.addDevice(light);
        controller.addDevice(speaker);

        SmartHomeFacade facade = new SmartHomeFacade();
        facade.welcomeHome();

        assertTrue(light.getStatus().contains("ON"));
        assertTrue(speaker.getStatus().contains("ON"));

        controller.removeDevice(light);
        controller.removeDevice(speaker);
    }

    // ========================================================================
    // Integration Tests
    // ========================================================================

    @Test
    public void testFullIntegration() {
        SmartDevice light = DeviceFactory.createDevice("light", "Living Room");
        SmartDevice speaker = DeviceFactory.createDevice("speaker", "Kitchen");
        SmartDevice thermostat = DeviceFactory.createDevice("thermostat", "Main");

        SmartHomeController controller = SmartHomeController.getInstance();
        int initialSize = controller.getDevices().size();
        controller.addDevice(light);
        controller.addDevice(speaker);
        controller.addDevice(thermostat);

        assertEquals(initialSize + 3, controller.getDevices().size());

        SmartHomeFacade facade = new SmartHomeFacade();
        facade.welcomeHome();

        assertTrue(light.getStatus().contains("ON"));
        assertTrue(speaker.getStatus().contains("ON"));

        controller.removeDevice(light);
        controller.removeDevice(speaker);
        controller.removeDevice(thermostat);
    }
}
