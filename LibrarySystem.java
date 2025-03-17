import java.util.ArrayList;

// Abstract class -> LibraryItem
abstract class LibraryItem {
    // Attributes
    private String itemId;
    private String title;
    private String author;

    // Constructor
    LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }

    // Getters (Encapsulation)
    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // abstract method to get each item's loan duration
    public abstract int getLoanDuration(); // Days

    // Concrete Method to display details
    public void getItemDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
    }
}

// Interface -> Reservable
interface Reservable {
    // methods to be overridden by classes implements interface
    boolean reserveItem();
    boolean checkAvailability();
}

// Subclass -> Book
class Book extends LibraryItem implements Reservable {
    // Attributes
    private static final int LOAN_DAYS = 14; // Books can be loaned for 14 days
    private boolean isReserved;

    // Constructor
    Book(String itemId, String title, String author) {
        super(itemId, title, author);
        this.isReserved = false;
    }

    // Implement abstract method
    @Override
    public int getLoanDuration() {
        return LOAN_DAYS;
    }

    // Implement Reservable interface
    @Override
    public boolean reserveItem() {
        if (!isReserved) {
            isReserved = true;
            System.out.println("Book '" + getTitle() + "' has been reserved.");
            return true;
        } else {
            System.out.println("Book '" + getTitle() + "' is already reserved.");
            return false;
        }
    }

    @Override
    public boolean checkAvailability() {
        return !isReserved;
    }
}

// Subclass -> Magazine
class Magazine extends LibraryItem {
    // Attributes
    private static final int LOAN_DAYS = 7; // Magazines can be loaned for 7 days

    // Constructor
    Magazine(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    // Implement abstract method
    @Override
    public int getLoanDuration() {
        return LOAN_DAYS;
    }
}

// Subclass -> DVD
class DVD extends LibraryItem implements Reservable {
    // Attributes
    private static final int LOAN_DAYS = 5; // DVDs can be loaned for 5 days
    private boolean isReserved;

    // Constructor
    DVD(String itemId, String title, String author) {
        super(itemId, title, author);
        this.isReserved = false;
    }

    // Implement abstract method
    @Override
    public int getLoanDuration() {
        return LOAN_DAYS;
    }

    // Implement Reservable interface
    @Override
    public boolean reserveItem() {
        if (!isReserved) {
            isReserved = true;
            System.out.println("DVD '" + getTitle() + "' has been reserved.");
            return true;
        } else {
            System.out.println("DVD '" + getTitle() + "' is already reserved.");
            return false;
        }
    }

    @Override
    public boolean checkAvailability() {
        return !isReserved;
    }
}

// Main class -> Test the concepts
public class LibrarySystem {
    public static void main(String[] args) {
        // List of library items
        ArrayList<LibraryItem> items = new ArrayList<>();

        // add items to library
        items.add(new Book("B-101", "The Alchemist", "Paulo Coelho"));
        items.add(new Magazine("M-202", "National Geographic", "Various"));
        items.add(new DVD("D-303", "Inception", "Christopher Nolan"));

        // process items and display details
        libraryItems(items);

        // reserve an item
        Book book = (Book) items.get(0);
        book.reserveItem(); // Reserve the book
        book.reserveItem(); // Try reserve again (should fail)
    }

    // Process Library Items (Polymorphism)
    public static void libraryItems(ArrayList<LibraryItem> items) {
        for(LibraryItem item: items) {
            item.getItemDetails();
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");

            // Check if the item is reservable
            if (item instanceof Reservable reservableItem) {
                System.out.println("Availability: " + (reservableItem.checkAvailability() ? "Available" : "Reserved"));
            }

            System.out.println();
        }
    }
}
// Sample Output ->
// Item ID: B-101
// Title: The Alchemist
// Author: Paulo Coelho
// Loan Duration: 14 days
// Availability: Available

// Item ID: M-202
// Title: National Geographic
// Author: Various
// Loan Duration: 7 days

// Item ID: D-303
// Title: Inception
// Author: Christopher Nolan
// Loan Duration: 5 days
// Availability: Available

// Book 'The Alchemist' has been reserved.
// Book 'The Alchemist' is already reserved.