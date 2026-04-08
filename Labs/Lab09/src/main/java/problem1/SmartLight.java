package problem1;

/**
 * Smart Light device implementation.
 */
public class SmartLight implements SmartDevice {
    private String name;
    private boolean isOn;
    private int brightness;

    public SmartLight(String name) {
        this.name = name;
        this.isOn = false;
        this.brightness = 100;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " light turned ON at " + brightness + "% brightness");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " light turned OFF");
    }

    @Override
    public String getStatus() {
        return name + " Light: " + (isOn ? "ON (" + brightness + "%)" : "OFF");
    }

    public void setBrightness(int brightness) {
        this.brightness = Math.max(0, Math.min(100, brightness));
    }
}
