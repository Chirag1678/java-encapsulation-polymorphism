import java.util.ArrayList;

// Abstract class -> Patient
abstract class Patient {
    // Attributes
    private String patientId;
    private String name;
    private int age;

    // Constructor
    Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    // Getters (Encapsulation)
    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // abstract method to calculate bill
    public abstract double calculateBill();

    // concrete method to display patient details
    public void getPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

// Interface -> MedicalRecord
interface MedicalRecord {
    // methods to be overridden by classes implements interface
    void addRecord(String record);
    void viewRecords();
}

// Subclass -> InPatient
class InPatient extends Patient implements MedicalRecord {
    // Attributes
    private int daysAdmitted;
    private double dailyRate;
    private ArrayList<String> medicalHistory = new ArrayList<>();

    // Constructor
    InPatient(String patientId, String name, int age, int daysAdmitted, double dailyRate) {
        super(patientId, name, age);
        this.daysAdmitted = daysAdmitted;
        this.dailyRate = dailyRate;
    }

    // Implement abstract method
    @Override
    public double calculateBill() {
        return daysAdmitted * dailyRate;
    }

    // Implement MedicalRecord interface
    @Override
    public void addRecord(String record) {
        medicalHistory.add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical Records for " + getName() + ": " + medicalHistory);
    }
}

// Subclass -> OutPatient
class OutPatient extends Patient implements MedicalRecord {
    // Attributes
    private double consultationFee;
    private ArrayList<String> medicalHistory = new ArrayList<>();

    // Constructor
    OutPatient(String patientId, String name, int age, double consultationFee) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
    }

    // Implement abstract method
    @Override
    public double calculateBill() {
        return consultationFee;
    }

    // Implement MedicalRecord interface
    @Override
    public void addRecord(String record) {
        medicalHistory.add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical Records for " + getName() + ": " + medicalHistory);
    }
}

// Main class -> Test the concepts
public class HospitalSystem {
    public static void main(String[] args) {
        // Create a list of patients
        ArrayList<Patient> patients = new ArrayList<>();

        // Add patients to list
        InPatient ip1 = new InPatient("P001", "Rahul", 45, 5, 2000);
        ip1.addRecord("Diagnosed with fever, under observation.");
        patients.add(ip1);

        OutPatient op1 = new OutPatient("P002", "Sneha", 30, 500);
        op1.addRecord("Routine check-up, prescribed vitamins.");
        patients.add(op1);

        // process patient details and display billing and details
        patientBilling(patients);
    }

    // method to handle different patient types and their bills
    public static void patientBilling(ArrayList<Patient> patients) {
        for (Patient patient : patients) {
            patient.getPatientDetails();
            double bill = patient.calculateBill();
            System.out.println("Total Bill: ₹" + bill);

            if (patient instanceof MedicalRecord record) {
                record.viewRecords();
            }

            System.out.println();
        }
    }
}
// Sample Output ->
// Patient ID: P001
// Name: Rahul
// Age: 45
// Total Bill: ₹10000.0
// Medical Records for Rahul: [Diagnosed with fever, under observation.]

// Patient ID: P002
// Name: Sneha
// Age: 30
// Total Bill: ₹500.0
// Medical Records for Sneha: [Routine check-up, prescribed vitamins.]