import java.util.ArrayList;

public class Bank {

    // private fields
    private String bankName;
    private ArrayList<Customer> customers;
    private ArrayList<Staff>    staffList;

    // Constructor
    public Bank(String bankName) {
        this.bankName  = bankName;
        this.customers = new ArrayList<>();
        this.staffList = new ArrayList<>();
    }

    // CREATE ACCOUNT
    public void createAccount(String personId, String name, String phone,
                              String email, String address, String customerId,
                              String type, double initialDeposit, int pin) {

        String accountNumber = "ACC" + (1000 + customers.size() + 1);

        Bankaccount account;
        if (type.equalsIgnoreCase("Savings")) {
            account = new SavingsAccount(accountNumber, name, initialDeposit, pin);
        } else if (type.equalsIgnoreCase("Current")) {
            account = new CurrentAccount(accountNumber, name, initialDeposit, pin);
        } else {
            System.out.println("Invalid account type! Choose Savings or Current.");
            return;
        }

        Customer customer = new Customer(personId, name, phone,
                                         email, address, customerId, account);
        customers.add(customer);

        System.out.println("==============================");
        System.out.println("Account created successfully!");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Type   : " + type);
        System.out.println("Opening Balance: " + initialDeposit + "tk");
        System.out.println("==============================");
    }

    // STAFF CREATES ACCOUNT
    public void createAccountByStaff(Staff staff, String personId, String name,
                                      String phone, String email, String address,
                                      String customerId, String type,
                                      double initialDeposit, int pin) {

        if (!staffList.contains(staff)) {
            System.out.println("Unauthorized! Staff not registered in system.");
            return;
        }

        System.out.println("Account creation authorized by: " + staff.getName());
        staff.createCustomerAccount();

        createAccount(personId, name, phone, email, address,
                      customerId, type, initialDeposit, pin);
    }

    // FIND ACCOUNT
    public Customer findAccount(String accountNumber) {
        for (Customer c : customers) {
            if (c.getAccount().getAccountNumber().equals(accountNumber)) {
                return c;
            }
        }
        System.out.println("Account " + accountNumber + " not found!");
        return null;
    }

    // DEPOSIT
    public void deposit(String accountNumber, double amount) {
        Customer customer = findAccount(accountNumber);
        if (customer != null) {
            customer.getAccount().deposit(amount);
        }
    }

    // WITHDRAW
    public void withdraw(String accountNumber, double amount, int pin) {
        Customer customer = findAccount(accountNumber);
        if (customer != null) {
            if (customer.getAccount().checkPin(pin)) {
                customer.getAccount().withdraw(amount);
            } else {
                System.out.println("Wrong PIN! Access denied.");
            }
        }
    }

    // TRANSFER
    public void transfer(String fromAccount, String toAccount,
                         double amount, int pin) {

        Customer sender   = findAccount(fromAccount);
        Customer receiver = findAccount(toAccount);

        if (sender == null || receiver == null) {
            System.out.println("Transfer failed! Invalid account number.");
            return;
        }
        if (!sender.getAccount().checkPin(pin)) {
            System.out.println("Wrong PIN! Transfer denied.");
            return;
        }
        if (sender.getAccount().getBalance() < amount) {
            System.out.println("Transfer failed! Insufficient balance.");
            return;
        }

        sender.getAccount().withdraw(amount);
        receiver.getAccount().deposit(amount);

        System.out.println("==============================");
        System.out.println("Transfer successful!");
        System.out.println("From   : " + fromAccount);
        System.out.println("To     : " + toAccount);
        System.out.println("Amount : " + amount + "tk");
        System.out.println("==============================");
    }

    // CHECK BALANCE
    public void checkBalance(String accountNumber, int pin) {
        Customer customer = findAccount(accountNumber);
        if (customer != null) {
            if (customer.getAccount().checkPin(pin)) {
                System.out.println("Account Number : " + accountNumber);
                System.out.println("Balance        : " +
                                   customer.getAccount().getBalance() + "tk");
            } else {
                System.out.println("Wrong PIN! Access denied.");
            }
        }
    }

    // LIST ALL ACCOUNTS
    public void listAllAccounts() {
        if (customers.isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }
        System.out.println("==============================");
        System.out.println("Bank  : " + bankName);
        System.out.println("Total : " + customers.size() + " accounts");
        System.out.println("==============================");
        for (Customer c : customers) {
            c.getAccount().displayDetails();
            System.out.println("------------------------------");
        }
    }

    // REMOVE ACCOUNT
    public void removeAccount(String accountNumber) {
        Customer customer = findAccount(accountNumber);
        if (customer != null) {
            customers.remove(customer);
            System.out.println("Account " + accountNumber +
                               " removed successfully!");
        }
    }

    // CALCULATE INTEREST FOR ALL SAVINGS ACCOUNTS
    public void calculateInterestForAll() {
        System.out.println("Applying interest to all Savings accounts...");
        for (Customer c : customers) {
            if (c.getAccount() instanceof SavingsAccount) {
                SavingsAccount sa = (SavingsAccount) c.getAccount();
                sa.calculateInterest();
                System.out.println("Applied for : " +
                                   c.getAccount().getAccountHolderName());
            }
        }
        System.out.println("Done!");
    }

    // ADD STAFF
    public void addStaff(Staff staff) {
        if (staff == null) {
            System.out.println("Invalid staff!");
            return;
        }
        staffList.add(staff);
        System.out.println("Staff added : " + staff.getName());
    }

    // REMOVE STAFF
    public void removeStaff(Staff staff) {
        if (staffList.contains(staff)) {
            staffList.remove(staff);
            System.out.println("Staff " + staff.getName() + " removed!");
        } else {
            System.out.println("Staff not found!");
        }
    }

    // LIST ALL STAFF
    public void listAllStaff() {
        if (staffList.isEmpty()) {
            System.out.println("No staff found!");
            return;
        }
        System.out.println("==============================");
        System.out.println("Total Staff : " + staffList.size());
        System.out.println("==============================");
        for (Staff s : staffList) {
            s.displayStaffInfo();
            System.out.println("------------------------------");
        }
    }

    // ASSIST CUSTOMER (by Staff)
    public void assistCustomer(Staff staff, String accountNumber) {
        if (!staffList.contains(staff)) {
            System.out.println("Unauthorized! Staff not registered.");
            return;
        }
        Customer customer = findAccount(accountNumber);
        if (customer != null) {
            staff.assistCustomer();
            customer.getCustomerInfo();
        }
    }

    // GETTERS
    public String getBankName()               { return bankName; }
    public ArrayList<Customer> getCustomers() { return customers; }
    public ArrayList<Staff> getStaffList()    { return staffList; }
}
