import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A filter class that processes a list of {@link Vehicle} objects
 * and extracts information about vehicles manufactured before 1999.
 */
public class OlderVehiclesFilter {

    private List<Vehicle> vehicles = new ArrayList<>();

    /**
     * Constructs an OlderVehiclesFilter with a provided list of vehicles.
     *
     * @param vehicles a List of Vehicle objects to be filtered
     */
    public OlderVehiclesFilter(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Constructs an OlderVehiclesFilter with exactly three Vehicle objects.
     *
     * @param vehicle1 the first Vehicle
     * @param vehicle2 the second Vehicle
     * @param vehicle3 the third Vehicle
     */
    public OlderVehiclesFilter(Vehicle vehicle1, Vehicle vehicle2, Vehicle vehicle3) {
        this.vehicles.add(vehicle1);
        this.vehicles.add(vehicle2);
        this.vehicles.add(vehicle3);
    }

    /**
     * Filters vehicles manufactured before 1999 and returns a list of
     * formatted strings describing each matching vehicle.
     *
     * <p>Uses Java Streams and lambdas to filter the internal vehicle list,
     * keeping only those with a model year strictly less than 1999, then
     * maps each qualifying vehicle to a human-readable summary string
     * containing its make, model, and year.</p>
     *
     * @return a List of Strings in the format "Make: [make], Model: [model], Year: [year]"
     *         for every vehicle whose year is before 1999
     */
    public List<String> filterOlderVehicles() {
        return vehicles.stream()
                .filter(v -> v.getYear() < 1999)
                .map(v -> "Make: " + v.getMake()
                        + ", Model: " + v.getModel()
                        + ", Year: " + v.getYear())
                .collect(Collectors.toList());
    }
}
