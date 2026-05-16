import java.util.Scanner;

public class Main {

    static Bank bank = new Bank("ABC Bank");
    static Scanner input = new Scanner(System.in);

    // MAIN METHOD
    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println("  Welcome to ABC Bank System  ");
        System.out.println("==============================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1  -> createAccount();
                case 2  -> deposit();
                case 3  -> withdraw();
                case 4  -> transfer();
                case 5  -> checkBalance();
                case 6  -> listAllAccounts();
                case 7  -> adminMenu();
                case 8  -> staffMenu();
                case 9  -> {
                    System.out.println("Thank you for using ABC Bank!");
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
        input.close();
    }

    // SHOW MENU
    public static void showMenu() {
        System.out.println("\n==============================");
        System.out.println("        ABC Bank Menu         ");
        System.out.println("==============================");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Check Balance");
        System.out.println("6. List All Accounts");
        System.out.println("7. Admin Panel");
        System.out.println("8. Staff Panel");
        System.out.println("9. Exit");
        System.out.println("==============================");
    }

    
    // HANDLE INPUT — CREATE ACCOUNT
    public static void createAccount() {
        System.out.println("\n--- Create New Account ---");
        System.out.print("Enter Person ID   : "); String personId   = input.nextLine();
        System.out.print("Enter Name        : "); String name       = input.nextLine();
        System.out.print("Enter Phone       : "); String phone      = input.nextLine();
        System.out.print("Enter Email       : "); String email      = input.nextLine();
        System.out.print("Enter Address     : "); String address    = input.nextLine();
        System.out.print("Enter Customer ID : "); String customerId = input.nextLine();
        System.out.print("Account Type (Savings / Current): ");
        String type = input.nextLine();
        double deposit = getDoubleInput("Enter Initial Deposit : ");
        int    pin     = getIntInput("Set 4-digit PIN       : ");

        bank.createAccount(personId, name, phone, email, address,
                           customerId, type, deposit, pin);
    }

    // HANDLE INPUT — DEPOSIT
    public static void deposit() {
        System.out.println("\n--- Deposit ---");
        System.out.print("Enter Account Number : ");
        String accountNumber = input.nextLine();
        double amount = getDoubleInput("Enter Amount         : ");

        bank.deposit(accountNumber, amount);
    }

    // HANDLE INPUT — WITHDRAW
    public static void withdraw() {
        System.out.println("\n--- Withdraw ---");
        System.out.print("Enter Account Number : ");
        String accountNumber = input.nextLine();
        double amount = getDoubleInput("Enter Amount : ");
        int    pin    = getIntInput("Enter PIN    : ");

        bank.withdraw(accountNumber, amount, pin);
    }

    
    // HANDLE INPUT — TRANSFER
    public static void transfer() {
        System.out.println("\n--- Transfer ---");
        System.out.print("Enter Your Account Number      : ");
        String fromAccount = input.nextLine();
        System.out.print("Enter Receiver Account Number  : ");
        String toAccount = input.nextLine();
        double amount = getDoubleInput("Enter Amount : ");
        int    pin    = getIntInput("Enter PIN    : ");

        bank.transfer(fromAccount, toAccount, amount, pin);
    }

    // HANDLE INPUT — CHECK BALANCE
    public static void checkBalance() {
        System.out.println("\n--- Check Balance ---");
        System.out.print("Enter Account Number : ");
        String accountNumber = input.nextLine();
        int pin = getIntInput("Enter PIN            : ");

        bank.checkBalance(accountNumber, pin);
    }

    
    // HANDLE INPUT — LIST ALL ACCOUNTS
    public static void listAllAccounts() {
        System.out.println("\n--- All Accounts ---");
        bank.listAllAccounts();
    }

    // ADMIN PANEL
    public static void adminMenu() {
        System.out.println("\n--- Admin Panel ---");
        System.out.print("Enter Admin Password : ");
        String password = input.nextLine();

        Admin admin = new Admin("AD001", "Admin", "admin@abc.com",
                                "01700000000", "ABC Bank HQ",
                                "ADMIN001", password);

        if (!admin.login(password)) {
            return;
        }

        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Accounts");
            System.out.println("2. Delete Account");
            System.out.println("3. Generate Report");
            System.out.println("4. Change Password");
            System.out.println("5. Back to Main Menu");

            int choice = getIntInput("Enter choice: ");
            switch (choice) {
                case 1 -> admin.viewAllAccounts(bank);
                case 2 -> {
                    System.out.print("Enter Account Number to delete: ");
                    String accNo = input.nextLine();
                    admin.deleteAccount(bank, accNo);
                }
                case 3 -> admin.generateReport(bank);
                case 4 -> {
                    System.out.print("Enter Old Password : ");
                    String oldPass = input.nextLine();
                    System.out.print("Enter New Password : ");
                    String newPass = input.nextLine();
                    admin.changePassword(oldPass, newPass);
                }
                case 5 -> adminRunning = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }


    // STAFF PANEL    
    public static void staffMenu() {
        System.out.println("\n--- Staff Panel ---");
        System.out.print("Enter Staff Name     : "); String sName    = input.nextLine();
        System.out.print("Enter Staff PersonId : "); String sId      = input.nextLine();
        System.out.print("Enter Staff Phone    : "); String sPhone   = input.nextLine();
        System.out.print("Enter Staff Email    : "); String sEmail   = input.nextLine();
        System.out.print("Enter Staff Address  : "); String sAddress = input.nextLine();
        double sSalary = getDoubleInput("Enter Staff Salary   : ");

        Staff staff = new Staff(sId, sName, sPhone, sEmail,
                                sAddress, sId, "Bank Staff", sSalary);
        bank.addStaff(staff);

        boolean staffRunning = true;
        while (staffRunning) {
            System.out.println("\n--- Staff Menu ---");
            System.out.println("1. Create Customer Account");
            System.out.println("2. Assist Customer");
            System.out.println("3. Verify Documents");
            System.out.println("4. View All Staff");
            System.out.println("5. Back to Main Menu");

            int choice = getIntInput("Enter choice: ");
            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- Create Account (by Staff) ---");
                    System.out.print("Enter Person ID   : "); String personId   = input.nextLine();
                    System.out.print("Enter Name        : "); String name       = input.nextLine();
                    System.out.print("Enter Phone       : "); String phone      = input.nextLine();
                    System.out.print("Enter Email       : "); String email      = input.nextLine();
                    System.out.print("Enter Address     : "); String address    = input.nextLine();
                    System.out.print("Enter Customer ID : "); String customerId = input.nextLine();
                    System.out.print("Account Type (Savings / Current): ");
                    String type    = input.nextLine();
                    double deposit = getDoubleInput("Enter Initial Deposit : ");
                    int    pin     = getIntInput("Set 4-digit PIN       : ");

                    bank.createAccountByStaff(staff, personId, name, phone,
                                              email, address, customerId,
                                              type, deposit, pin);
                }
                case 2 -> {
                    System.out.print("Enter Customer Account Number: ");
                    String accNo = input.nextLine();
                    bank.assistCustomer(staff, accNo);
                }
                case 3 -> staff.verifyDocuments();
                case 4 -> bank.listAllStaff();
                case 5 -> staffRunning = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }


    // HELPER METHODS
    // safely reads int input
    public static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(input.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    // safely reads double input
    public static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(input.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid amount.");
            }
        }
    }
}