import java.util.ArrayList;

// Abstract class -> Product
abstract class Product {
    // Attributes
    private final String productId;
    private final String name;
    private double price;

    // Constructor
    Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    // Getters and setter methods (Encapsulation)
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price > 0) {
            this.price = price;
        } else {
            System.out.println("Invalid amount.");
        }
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateDiscount();

    // Concrete method to display details
    public void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
    }
}

// Interface -> Taxable
interface Taxable {
    // Methods to be used by classes implementing interface
    double calculateTax();
    String getTaxDetails();
}


// Subclass -> Electronics
class Electronics extends Product implements Taxable {
    // Attributes
    private static final double DISCOUNT_PERCENTAGE = 10; // 10% discount
    private static final double TAX_RATE = 18; // 18% tax

    // Constructor
    Electronics(String productId, String name, double price) {
        super(productId, name, price);
    }

    // Implement abstract method
    @Override
    public double calculateDiscount() {
        return getPrice() * DISCOUNT_PERCENTAGE / 100;
    }

    // Implement Taxable interface
    @Override
    public double calculateTax() {
        return getPrice() * TAX_RATE / 100;
    }

    @Override
    public String getTaxDetails() {
        return "Tax Rate: " + TAX_RATE + "%";
    }
}

// Subclass -> Clothing
class Clothing extends Product implements Taxable {
    // Attributes
    private static final double DISCOUNT_PERCENTAGE = 20; // 20% discount
    private static final double TAX_RATE = 5; // 5% tax

    // Constructor
    Clothing(String productId, String name, double price) {
        super(productId, name, price);
    }

    // Implement abstract method
    @Override
    public double calculateDiscount() {
        return getPrice() * DISCOUNT_PERCENTAGE / 100;
    }

    // Implement Taxable interface
    @Override
    public double calculateTax() {
        return getPrice() * TAX_RATE / 100;
    }

    @Override
    public String getTaxDetails() {
        return "Tax Rate: " + TAX_RATE + "%";
    }
}

// Subclass -> Groceries (No Tax, Minimal Discount)
class Groceries extends Product {
    // Attributes
    private static final double DISCOUNT_PERCENTAGE = 5; // 5% discount

    // Constructor
    Groceries(String productId, String name, double price) {
        super(productId, name, price);
    }

    // Implement abstract method
    @Override
    public double calculateDiscount() {
        return getPrice() * DISCOUNT_PERCENTAGE / 100;
    }
}

// Main class -> Test the concepts
public class ECommercePlatform {
    public static void main(String[] args) {
        // List of Products
        ArrayList<Product> products = new ArrayList<>();

        // Add products to list
        products.add(new Electronics("11A", "Laptop", 45000));
        products.add(new Clothing("121B", "T-Shirt", 450));
        products.add(new Groceries("23AC", "Rice", 550));

        // Calculate final price of products
        finalPrice(products);
    }

    public static void finalPrice(ArrayList<Product> products) {
        for(Product product: products) {
            double discount = product.calculateDiscount();
            double tax = (product instanceof Taxable) ? ((Taxable) product).calculateTax() : 0;
            double finalPrice = product.getPrice() - discount + tax;

            // Display product details
            product.displayDetails();
            System.out.println("Discount: " + discount);
            System.out.println("Tax: " + tax);
            System.out.println("Final Price: " + finalPrice);

            // Display tax details if applicable
            if (product instanceof Taxable) {
                System.out.println(((Taxable) product).getTaxDetails());
            }

            System.out.println();
        }
    }
}
// Sample Output ->
// Product ID: 11A
// Name: Laptop
// Price: 45000.0
// Discount: 4500.0
// Tax: 8100.0
// Final Price: 48600.0
// Tax Rate: 18.0%

// Product ID: 121B
// Name: T-Shirt
// Price: 450.0
// Discount: 90.0
// Tax: 22.5
// Final Price: 382.5
// Tax Rate: 5.0%

// Product ID: 23AC
// Name: Rice
// Price: 550.0
// Discount: 27.5
// Tax: 0.0
// Final Price: 522.5