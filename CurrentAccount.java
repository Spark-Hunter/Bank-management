public class CurrentAccount extends Bankaccount {

    // private fields
    private double overdraftLimit;

    // Constructor
    public CurrentAccount(String accountNumber, String accountHolderName,
                          double initialDeposit, int pin) {
        super(accountNumber, accountHolderName, initialDeposit, pin, "Current");
        this.overdraftLimit = 5000.0;  // can go 5000tk below zero
    }

    // apply overdraft — withdraw beyond balance
    public void applyOverdraft(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (getBalance() - amount < -overdraftLimit) {
            System.out.println("Overdraft limit exceeded!");
            System.out.println("Maximum overdraft : " + overdraftLimit + "tk");
        } else {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn         : " + amount);
            System.out.println("New Balance       : " + getBalance());
            if (getBalance() < 0) {
                System.out.println("Warning! You are " + Math.abs(getBalance())
                                 + "tk in overdraft.");
            }
        }
    }

    // check if overdraft limit is reached
    public boolean checkOverdraftLimit() {
        if (getBalance() < 0) {
            System.out.println("Current overdraft : " + Math.abs(getBalance()) + "tk");
            System.out.println("Overdraft limit   : " + overdraftLimit + "tk");
            System.out.println("Remaining overdraft: "
                             + (overdraftLimit - Math.abs(getBalance())) + "tk");
            return true;
        }
        return false;
    }

    // override withdraw to support overdraft
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (getBalance() - amount < -overdraftLimit) {
            System.out.println("Cannot withdraw! Overdraft limit exceeded.");
        } else {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn         : " + amount);
            System.out.println("New Balance       : " + getBalance());
            if (getBalance() < 0) {
                System.out.println("Warning! You are "
                                 + Math.abs(getBalance()) + "tk in overdraft.");
            }
        }
    }

    // display details
    @Override
    public void displayDetails() {
        System.out.println("=== Current Account Details ===");
        System.out.println("Account No        : " + getAccountNumber());
        System.out.println("Holder Name       : " + getAccountHolderName());
        System.out.println("Account Type      : " + getAccountType());
        System.out.println("Balance           : " + getBalance());
        System.out.println("Overdraft Limit   : " + overdraftLimit);
    }

    // Getter
    public double getOverdraftLimit(){  
       return overdraftLimit;
    }

    // Setter
    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit > 0) {
            this.overdraftLimit = overdraftLimit;
            System.out.println("Overdraft limit updated!");
        } else {
            System.out.println("Invalid overdraft limit!");
        }
    }
}