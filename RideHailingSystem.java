import java.util.ArrayList;

// Abstract class -> VehicleHailing
abstract class VehicleHailing {
    // Attributes
    private String vehicleId;
    private String driverName;
    private double ratePerKm;

    // Constructor
    VehicleHailing(String vehicleId, String driverName, double ratePerKm) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
    }

    // Getters (Encapsulation)
    public String getVehicleId() {
        return vehicleId;
    }

    public String getDriverName() {
        return driverName;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    // abstract method to calculate fare
    public abstract double calculateFare(double distance);

    // concrete method to get vehicle details
    public void getVehicleDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Driver: " + driverName);
        System.out.println("Rate per Km: ₹" + ratePerKm);
    }
}

// Interface -> GPS
interface GPS {
    // methods to be overridden by classes implements interface
    String getCurrentLocation();
    void updateLocation(String newLocation);
}

// Subclass -> CarHailing
class CarHailing extends VehicleHailing implements GPS {
    // Attributes
    private String currentLocation;

    // Constructor
    CarHailing(String vehicleId, String driverName, double ratePerKm, String currentLocation) {
        super(vehicleId, driverName, ratePerKm);
        this.currentLocation = currentLocation;
    }

    // Implement abstract method
    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm();
    }

    // Implement GPS interface
    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }
}

// Subclass -> BikeHailing
class BikeHailing extends VehicleHailing implements GPS {
    // Attributes
    private String currentLocation;

    // Constructor
    BikeHailing(String vehicleId, String driverName, double ratePerKm, String currentLocation) {
        super(vehicleId, driverName, ratePerKm);
        this.currentLocation = currentLocation;
    }

    // Implement abstract method
    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm();
    }

    // Implement GPS interface
    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }
}

// Subclass -> Auto
class Auto extends VehicleHailing implements GPS {
    // Attributes
    private String currentLocation;

    // Constructor
    Auto(String vehicleId, String driverName, double ratePerKm, String currentLocation) {
        super(vehicleId, driverName, ratePerKm);
        this.currentLocation = currentLocation;
    }

    // Implement abstract method
    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm();
    }

    // Implement GPS interface
    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }
}

// Main class -> Demonstrate Ride-Hailing System
public class RideHailingSystem {
    public static void main(String[] args) {
        // Create list of vehicles
        ArrayList<VehicleHailing> vehicles = new ArrayList<>();

        // Add vehicles to list
        CarHailing car = new CarHailing("C001", "Amit", 15.0, "Connaught Place");
        BikeHailing bike = new BikeHailing("B001", "Raj", 8.0, "India Gate");
        Auto auto = new Auto("A001", "Sunil", 10.0, "Karol Bagh");

        vehicles.add(car);
        vehicles.add(bike);
        vehicles.add(auto);

        // place rides for 10km
        rideAndFare(vehicles, 10.0);
    }

    // method to place rides and calculate fare
    public static void rideAndFare(ArrayList<VehicleHailing> vehicles, double distance) {
        for (VehicleHailing vehicle : vehicles) {
            vehicle.getVehicleDetails();
            double fare = vehicle.calculateFare(distance);
            System.out.println("Total Fare for " + distance + " km: ₹" + fare);

            if (vehicle instanceof GPS gps) {
                System.out.println("Current Location: " + gps.getCurrentLocation());
            }

            System.out.println();
        }
    }
}
// Sample Output ->
// Vehicle ID: C001
// Driver: Amit
// Rate per Km: ₹15.0
// Total Fare for 10.0 km: ₹150.0
// Current Location: Connaught Place

// Vehicle ID: B001
// Driver: Raj
// Rate per Km: ₹8.0
// Total Fare for 10.0 km: ₹80.0
// Current Location: India Gate

// Vehicle ID: A001
// Driver: Sunil
// Rate per Km: ₹10.0
// Total Fare for 10.0 km: ₹100.0
// Current Location: Karol Bagh