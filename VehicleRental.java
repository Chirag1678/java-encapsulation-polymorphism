import java.util.ArrayList;

// Abstract class -> Vehicle
abstract class Vehicle {
    // Attributes
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    // Constructor
    Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber =vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    // Getters and Setters (Encapsulation)
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    // Abstract method for rental cost calculation
    public abstract double calculateRentalCost(int days);

    // Method to display vehicle details
    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Type: " + type);
        System.out.println("Rental Rate per Day: " + rentalRate);
    }
}

// Interface -> Insurable
interface Insurable {
    // methods to be overridden by classes implements interface
    double calculateInsurance();
    String getInsuranceDetails();
}

// Subclass -> Car
class Car extends Vehicle implements Insurable {
    // Attributes
    private static final double INSURANCE_RATE = 2.5; // 2.5% of rental cost

    // Constructor
    Car(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Car", rentalRate);
    }

    // Implement abstract method
    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    // Implement Insurable interface
    @Override
    public double calculateInsurance() {
        return getRentalRate() * INSURANCE_RATE / 100;
    }

    @Override
    public String getInsuranceDetails() {
        return "Insurance Rate: " + INSURANCE_RATE + "%";
    }
}

// Subclass -> Bike
class Bike extends Vehicle {
    // Attributes
    private static final double DISCOUNT = 0.1; // 10% discount for long rentals

    // Constructor
    Bike(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Bike", rentalRate);
    }

    // Implement abstract method
    @Override
    public double calculateRentalCost(int days) {
        double cost = getRentalRate() * days;
        return (days > 5) ? cost - (cost * DISCOUNT) : cost;
    }
}

// Subclass -> Truck
class Truck extends Vehicle implements Insurable {
    // Attributes
    private static final double INSURANCE_RATE = 5.0; // 5% of rental cost

    // Constructor
    Truck(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Truck", rentalRate);
    }

    // Implement abstract method
    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days + 500; // Additional fixed charge
    }

    // Implement Insurable interface
    @Override
    public double calculateInsurance() {
        return getRentalRate() * INSURANCE_RATE / 100;
    }

    @Override
    public String getInsuranceDetails() {
        return "Insurance Rate: " + INSURANCE_RATE + "%";
    }
}

// Main class -> Test the concepts
public class VehicleRental {
    public static void main(String[] args) {
        // List of vehicles
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        // Add vehicles to list
        vehicles.add(new Car("HR-01-1234", 1000));
        vehicles.add(new Bike("PB-02-5678", 500));
        vehicles.add(new Truck("CG-03-9101", 3000));

        // Process Rentals for 7 days
        rentalAndInsurance(vehicles, 7);
    }

    // Method to process rentals and insurance (Polymorphism)
    public static void rentalAndInsurance(ArrayList<Vehicle> vehicles, int days) {
        for(Vehicle vehicle: vehicles) {
            double rentalCost = vehicle.calculateRentalCost(days);
            double insuranceCost = (vehicle instanceof Insurable) ? ((Insurable) vehicle).calculateInsurance() : 0;

            // Display Vehicle Details
            vehicle.displayDetails();
            System.out.println("Rental Cost for " + days + " days: " + rentalCost);
            System.out.println("Insurance Cost: " + insuranceCost);

            // Display insurance details if applicable
            if (vehicle instanceof Insurable) {
                System.out.println(((Insurable) vehicle).getInsuranceDetails());
            }

            System.out.println();
        }
    }
}
// Sample Output ->
// Vehicle Number: HR-01-1234
// Type: Car
// Rental Rate per Day: 1000.0
// Rental Cost for 7 days: 7000.0
// Insurance Cost: 25.0
// Insurance Rate: 2.5%

// Vehicle Number: PB-02-5678
// Type: Bike
// Rental Rate per Day: 500.0
// Rental Cost for 7 days: 3150.0
// Insurance Cost: 0.0

// Vehicle Number: CG-03-9101
// Type: Truck
// Rental Rate per Day: 3000.0
// Rental Cost for 7 days: 21500.0
// Insurance Cost: 150.0
// Insurance Rate: 5.0%