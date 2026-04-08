package problem1;

/**
 * Smart Speaker device implementation.
 */
public class SmartSpeaker implements SmartDevice {
    private String name;
    private boolean isOn;
    private int volume;

    public SmartSpeaker(String name) {
        this.name = name;
        this.isOn = false;
        this.volume = 50;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " speaker turned ON at volume " + volume);
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " speaker turned OFF");
    }

    @Override
    public String getStatus() {
        return name + " Speaker: " + (isOn ? "ON (Volume: " + volume + ")" : "OFF");
    }

    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }
}
