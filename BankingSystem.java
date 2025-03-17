import java.util.ArrayList;

// Abstract class -> BankAccount
abstract class BankAccount {
    // Attributes
    private String accountNumber;
    private String holderName;
    protected double balance;

    // Constructor
    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Getters & Setters (Encapsulation)
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Concrete methods to deposit and withdraw
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + " | Updated Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | Updated Balance: " + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    // Abstract method for interest calculation
    public abstract double calculateInterest();

    // Display Account Details
    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + balance);
    }
}

// Interface -> Loanable
interface Loanable {
    // methods to be overridden by classes implements interface
    boolean applyForLoan(double amount);
    double calculateLoanEligibility();
}

// Subclass -> SavingsAccount
class SavingsAccount extends BankAccount {
    // Attributes
    private static final double INTEREST_RATE = 4.0; // 4% per year

    // Constructor
    SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    // Implement abstract method
    @Override
    public double calculateInterest() {
        return (balance * INTEREST_RATE) / 100;
    }
}

// Subclass -> CurrentAccount
class CurrentAccount extends BankAccount implements Loanable {
    // Attributes
    private static final double INTEREST_RATE = 2.0; // 2% per year
    private static final double LOAN_MULTIPLIER = 2.5; // Loan eligibility factor

    // Constructor
    CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    // Implement abstract method
    @Override
    public double calculateInterest() {
        return (balance * INTEREST_RATE) / 100;
    }

    // Implement Loanable methods
    @Override
    public boolean applyForLoan(double amount) {
        if (amount <= calculateLoanEligibility()) {
            System.out.println("Loan Approved: " + amount);
            return true;
        } else {
            System.out.println("Loan Denied: Exceeds eligibility.");
            return false;
        }
    }

    @Override
    public double calculateLoanEligibility() {
        return balance * LOAN_MULTIPLIER;
    }
}

// Main class -> Test the concepts
public class BankingSystem {
    public static void main(String[] args) {
        // List of accounts
        ArrayList<BankAccount> accounts = new ArrayList<>();

        // add bank accounts of different types
        accounts.add(new SavingsAccount("UB-1001", "Jane", 50000));
        accounts.add(new CurrentAccount("KKBK-2002", "John", 75000));

        // Process and display account details
        accountAndDetails(accounts);

        // Test Loan application
        CurrentAccount John  = (CurrentAccount) accounts.get(1);
        John.applyForLoan(100000);
    }

    // method to process account and show details (Polymorphism)
    public static void accountAndDetails(ArrayList<BankAccount> accounts) {
        for (BankAccount account : accounts) {
            account.displayDetails();
            System.out.println("Annual Interest: " + account.calculateInterest());

            // Check for Loan Eligibility (Only CurrentAccount)
            if (account instanceof Loanable loanableAccount) {
                System.out.println("Loan Eligibility: " + loanableAccount.calculateLoanEligibility());
            }

            System.out.println();
        }
    }
}
// Sample Output ->
// Account Number: UB-1001
// Holder Name: Jane
// Balance: 50000.0
// Annual Interest: 2000.0

// Account Number: KKBK-2002
// Holder Name: John
// Balance: 75000.0
// Annual Interest: 1500.0
// Loan Eligibility: 187500.0

// Loan Approved: 100000.0