import java.time.LocalDateTime;

public class Transaction {

    // private fields — all final (cannot change after creation)
    private final String        type;
    private final double        amount;
    private final LocalDateTime timestamp;
    private final String        accountNumber;

    // Constructor
    public Transaction(String type, double amount, String accountNumber) {

        // validation
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Transaction type cannot be empty!");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive!");
        }
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty!");
        }

        this.type          = type;
        this.amount        = amount;
        this.accountNumber = accountNumber;
        this.timestamp     = LocalDateTime.now(); // auto sets current time
    }

   
    // recordTransaction()
    public void recordTransaction() {
        System.out.println("=== Transaction Recorded ===");
        System.out.println("Type           : " + type);
        System.out.println("Amount         : " + amount + "tk");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Timestamp      : " + timestamp);
        System.out.println("============================");
    }

    
    // Getters only — no setters!
    public String        getType()          { return type; }
    public double        getAmount()        { return amount; }
    public LocalDateTime getTimestamp()     { return timestamp; }
    public String        getAccountNumber() { return accountNumber; }

    
    // toString() — useful for printing transaction in a list
    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + " | " +
                amount + "tk | Acc: " + accountNumber;
    }
}