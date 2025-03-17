import java.util.ArrayList;

// Abstract class -> FoodItem
abstract class FoodItem {
    // Attributes
    private String itemName;
    private double price;
    private int quantity;

    // Constructor
    FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters (Encapsulation)
    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // abstract method to calculate total price
    public abstract double calculateTotalPrice();

    // concrete method to display item details
    public void getItemDetails() {
        System.out.println("Item: " + itemName);
        System.out.println("Price per unit: ₹" + price);
        System.out.println("Quantity: " + quantity);
    }
}

// Interface -> Discountable
interface Discountable {
    // methods to be overridden by classes implements interface
    double applyDiscount();
    String getDiscountDetails();
}

// Subclass -> VegItem
class VegItem extends FoodItem implements Discountable {
    // Attributes
    private static final double DISCOUNT_PERCENTAGE = 10.0; // 10% Discount

    // Constructor
    VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    // Implement abstract method
    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }

    // Implement Discountable interface
    @Override
    public double applyDiscount() {
        return calculateTotalPrice() * (DISCOUNT_PERCENTAGE / 100);
    }

    @Override
    public String getDiscountDetails() {
        return "Discount: " + DISCOUNT_PERCENTAGE + "%";
    }
}

// Subclass -> NonVegItems
class NonVegItem extends FoodItem implements Discountable {
    // Attributes
    private static final double ADDITIONAL_CHARGE = 20.0; // Extra charge for non-veg
    private static final double DISCOUNT_PERCENTAGE = 5.0; // 5% Discount

    // Constructor
    NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    // Implement abstract method
    @Override
    public double calculateTotalPrice() {
        return (getPrice() * getQuantity()) + ADDITIONAL_CHARGE;
    }

    // Implement Discountable interface
    @Override
    public double applyDiscount() {
        return calculateTotalPrice() * (DISCOUNT_PERCENTAGE / 100);
    }

    @Override
    public String getDiscountDetails() {
        return "Discount: " + DISCOUNT_PERCENTAGE + "% (Extra charge: ₹" + ADDITIONAL_CHARGE + ")";
    }
}

// Main class -> Test the concepts
public class FoodDeliverySystem {
    public static void main(String[] args) {
        // Create a list of food items
        ArrayList<FoodItem> order = new ArrayList<>();

        // add items to list
        order.add(new VegItem("Paneer Tikka", 150, 2));
        order.add(new NonVegItem("Chicken Biryani", 250, 1));

        // place the order
        placeOrder(order);
    }

    // Method to process order (Polymorphism)
    public static void placeOrder(ArrayList<FoodItem> items) {
        double grandTotal = 0;

        for (FoodItem item : items) {
            item.getItemDetails();
            double totalPrice = item.calculateTotalPrice();
            System.out.println("Total Price: ₹" + totalPrice);

            if (item instanceof Discountable discountItem) {
                double discount = discountItem.applyDiscount();
                System.out.println(discountItem.getDiscountDetails());
                System.out.println("Discount Amount: ₹" + discount);
                totalPrice -= discount;
            }

            System.out.println("Final Price after discount: ₹" + totalPrice);
            System.out.println();
            grandTotal += totalPrice;
        }

        System.out.println("Grand Total: ₹" + grandTotal);
    }
}
// Sample Output ->
// Item: Paneer Tikka
// Price per unit: ₹150.0
// Quantity: 2
// Total Price: ₹300.0
// Discount: 10.0%
// Discount Amount: ₹30.0
// Final Price after discount: ₹270.0

// Item: Chicken Biryani
// Price per unit: ₹250.0
// Quantity: 1
// Total Price: ₹270.0
// Discount: 5.0% (Extra charge: ₹20.0)
// Discount Amount: ₹13.5
// Final Price after discount: ₹256.5

// Grand Total: ₹526.5