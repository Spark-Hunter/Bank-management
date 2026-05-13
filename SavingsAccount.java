public class SavingsAccount extends Bankaccount {

    // private fields
    private double interestRate;
    private double minBalance;

    // Constructor
    public SavingsAccount(String accountNumber, String accountHolderName,
                          double initialDeposit, int pin) {
        super(accountNumber, accountHolderName, initialDeposit, pin, "Savings");
        this.interestRate = 0.05;   // 5% interest rate
        this.minBalance   = 500.0;  // minimum balance 500tk
    }

    // calculate and apply interest
    public void calculateInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
        System.out.println("Interest applied  : " + interest);
        System.out.println("New Balance       : " + getBalance());
    }

    // enforce minimum balance during withdrawal
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (getBalance() - amount < minBalance) {
            System.out.println("Cannot withdraw! Minimum balance of "
                             + minBalance + "tk must be maintained.");
        } else {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn         : " + amount);
            System.out.println("New Balance       : " + getBalance());
        }
    }

    // display details
    @Override
    public void displayDetails() {
        System.out.println("=== Savings Account Details ===");
        System.out.println("Account No        : " + getAccountNumber());
        System.out.println("Holder Name       : " + getAccountHolderName());
        System.out.println("Account Type      : " + getAccountType());
        System.out.println("Balance           : " + getBalance());
        System.out.println("Interest Rate     : " + (interestRate * 100) + "%");
        System.out.println("Minimum Balance   : " + minBalance);
    }

    // Getters
    public double getInterestRate() { return interestRate; }
    public double getMinBalance()   { return minBalance; }

    // Setters
    public void setInterestRate(double interestRate) {
        if (interestRate > 0 && interestRate < 1) {
            this.interestRate = interestRate;
            System.out.println("Interest rate updated!");
        } else {
            System.out.println("Invalid interest rate!");
        }
    }
}