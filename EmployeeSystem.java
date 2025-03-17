import java.util.ArrayList;

// Abstract class -> Employee
abstract class Employee {
    // Attributes
    private final int employeeId;
    private final String name;
    private final double baseSalary;

    // Constructor
    Employee(int employeeId, String name, double baseSalary) {
        this.employeeId =employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Getter methods for private attributes (Encapsulation)
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateSalary();

    // Concrete method to display details
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Final Salary: " + calculateSalary());
    }
}

// Interface -> Department
interface Department {
    // Methods to be used by classes implementing interface
    void assignDepartment(String deptName);
    String getDepartmentDetails();
}

// Subclass -> FullTimeEmployee
class FullTimeEmployee extends Employee implements Department {
    // Attributes
    private final double bonus;
    private String department;

    // Constructor
    FullTimeEmployee(int employeeId, String name, double baseSalary, double bonus) {
        super(employeeId, name, baseSalary);
        this.bonus = bonus;
    }

    // Implement calculateSalary() from parent class
    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus;
    }

    // Implement Department Interface
    @Override
    public void assignDepartment(String deptName) {
        this.department = deptName;
    }

    @Override
    public String getDepartmentDetails() {
        return "Department: " + department;
    }
}

// Subclass -> PartTimeEmployee
class PartTimeEmployee extends Employee implements Department {
    // Attributes
    private final int workingHours;
    private final double hourlyRate;
    private String department;

    // Constructor
    PartTimeEmployee(int employeeId, String name, double baseSalary, int workingHours, double hourlyRate) {
        super(employeeId, name, baseSalary);
        this.workingHours = workingHours;
        this.hourlyRate = hourlyRate;
    }

    // Implement calculateSalary() from Parent class
    @Override
    public double calculateSalary() {
        return workingHours * hourlyRate; // Salary based on hours worked
    }

    // Implement Department interface
    @Override
    public void assignDepartment(String deptName) {
        this.department = deptName;
    }

    @Override
    public String getDepartmentDetails() {
        return "Department: " + department;
    }
}

// Main class -> Test the concepts
public class EmployeeSystem {
    public static void main(String[] args) {
        // List to store employees (Polymorphism)
        ArrayList<Employee> employees = new ArrayList<>();

        // Create Objects of subclasses
        FullTimeEmployee emp1 = new FullTimeEmployee(101, "Jane", 50000, 10000);
        emp1.assignDepartment("Development");

        PartTimeEmployee emp2 = new PartTimeEmployee(102, "John", 0, 20, 500);
        emp2.assignDepartment("IT");

        // Add employees to list
        employees.add(emp1);
        employees.add(emp2);

        // Display employee details
        for(Employee employee: employees) {
            employee.displayDetails();
            if(employee instanceof Department) {
                System.out.println(((Department) employee).getDepartmentDetails());
            }
            System.out.println();
        }
    }
}
// Sample Output ->
// Employee ID: 101
// Name: Jane
// Base Salary: 50000.0
// Final Salary: 60000.0
// Department: Development

// Employee ID: 102
// Name: John
// Base Salary: 0.0
// Final Salary: 10000.0
// Department: IT