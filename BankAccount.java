abstract class Bankaccount {

    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private int pin;
    private String accountType;

    // Constructor
    public Bankaccount(String accountNumber, String accountHolderName, double initialDeposit, int pin, String accountType) {
        this.accountNumber     = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance           = initialDeposit;
        this.pin               = pin;
        this.accountType       = accountType;
    }

    // deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            System.out.println("New Balance: " + balance);
        } else {
            System.out.println("Invalid amount!");
        }
    }

    // withdraw
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            System.out.println("New Balance: " + balance);
        }
    }

    // display details
    public void displayDetails() {
        System.out.println("=== Account Details ===");
        System.out.println("Account No   : " + accountNumber);
        System.out.println("Holder Name  : " + accountHolderName);
        System.out.println("Account Type : " + accountType);
        System.out.println("Balance      : " + balance);
    }

    // Getters
    public String getAccountNumber(){  
        return accountNumber;
    }
    public String getAccountHolderName() {  
        return accountHolderName;
    }
    public double getBalance(){  
        return balance;
    }
    public String getAccountType(){  
        return accountType;
    }

    // protected setter (only subclasses can use this)
    protected void setBalance(double balance) { this.balance = balance; }

    // PIN checker
    public boolean checkPin(int enteredPin) { return this.pin == enteredPin; }

    // PIN changer
    public void changePin(int oldPin, int newPin, int confirmPin) {
        if (!checkPin(oldPin)) {
            System.out.println("Wrong old PIN! Access denied.");
            return;
        }
        if (newPin < 1000 || newPin > 9999) {
            System.out.println("PIN must be exactly 4 digits!");
            return;
        }
        if (newPin != confirmPin) {
            System.out.println("PINs do not match! Try again.");
            return;
        }
        this.pin = newPin;
        System.out.println("PIN changed successfully!");
    }
}