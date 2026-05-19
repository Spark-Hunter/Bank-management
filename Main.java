import java.util.Scanner;

public class Main {

    static Bank bank = new Bank("ABC Bank");
    static Scanner input = new Scanner(System.in);

    // MAIN METHOD
    public static void main(String[] args) {

        // Default Staff
        Staff staff1 = new Staff(
                "S101",
                "Rahim",
                "01711111111",
                "rahim@gmail.com",
                "Dhaka",
                "STF001",
                "Manager",
                40000
        );

        Staff staff2 = new Staff(
                "S102",
                "Karim",
                "01822222222",
                "karim@gmail.com",
                "Rajshahi",
                "STF002",
                "Cashier",
                30000
        );

        // Add staff to bank
        bank.addStaff(staff1);
        bank.addStaff(staff2);

        // Default Admin
        Admin admin = new Admin(
                "A101",
                "Admin",
                "admin@gmail.com",
                "01999999999",
                "Senior",
                "Management",
                "admin123",
                "Dhaka"
        );

        System.out.println("==============================");
        System.out.println("  Welcome to ABC Bank System  ");
        System.out.println("==============================");

        boolean running = true;

        while (running) {

            showMenu();

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {

                case 1 -> createAccount();

                case 2 -> deposit();

                case 3 -> withdraw();

                case 4 -> transfer();

                case 5 -> checkBalance();

                case 6 -> listAllAccounts();

                case 7 -> adminMenu(admin);

                case 8 -> staffMenu();

                case 9 -> transactionMenu();

                case 10 -> {
                    System.out.println("\nThank you for using ABC Bank!");
                    System.out.println("Goodbye!");
                    running = false;
                }

                default -> System.out.println("Invalid choice! Please try again.");
            }
        }

        input.close();
    }

    // SHOW MAIN MENU
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
        System.out.println("9. Transaction Record");
        System.out.println("10. Exit");
        System.out.println("==============================");
    }

    // CREATE ACCOUNT
    public static void createAccount() {

        System.out.println("\n--- Create New Account ---");

        System.out.print("Enter Person ID   : ");
        String personId = input.nextLine();

        System.out.print("Enter Name        : ");
        String name = input.nextLine();

        System.out.print("Enter Phone       : ");
        String phone = input.nextLine();

        System.out.print("Enter Email       : ");
        String email = input.nextLine();

        System.out.print("Enter Address     : ");
        String address = input.nextLine();

        System.out.print("Enter Customer ID : ");
        String customerId = input.nextLine();

        System.out.print("Account Type (Savings / Current): ");
        String type = input.nextLine();

        double initialDeposit =
                getDoubleInput("Enter Initial Deposit : ");

        int pin =
                getIntInput("Set 4-digit PIN       : ");

        bank.createAccount(
                personId,
                name,
                phone,
                email,
                address,
                customerId,
                type,
                initialDeposit,
                pin
        );
    }

    // DEPOSIT
    public static void deposit() {

        System.out.println("\n--- Deposit ---");

        System.out.print("Enter Account Number : ");
        String accountNumber = input.nextLine();

        double amount =
                getDoubleInput("Enter Amount         : ");

        bank.deposit(accountNumber, amount);
    }

    // WITHDRAW
    public static void withdraw() {

        System.out.println("\n--- Withdraw ---");

        System.out.print("Enter Account Number : ");
        String accountNumber = input.nextLine();

        double amount =
                getDoubleInput("Enter Amount : ");

        int pin =
                getIntInput("Enter PIN    : ");

        bank.withdraw(accountNumber, amount, pin);
    }

    // TRANSFER
    public static void transfer() {

        System.out.println("\n--- Transfer ---");

        System.out.print("Enter Your Account Number     : ");
        String fromAccount = input.nextLine();

        System.out.print("Enter Receiver Account Number : ");
        String toAccount = input.nextLine();

        double amount =
                getDoubleInput("Enter Amount : ");

        int pin =
                getIntInput("Enter PIN    : ");

        bank.transfer(fromAccount, toAccount, amount, pin);
    }

    // CHECK BALANCE
    public static void checkBalance() {

        System.out.println("\n--- Check Balance ---");

        System.out.print("Enter Account Number : ");
        String accountNumber = input.nextLine();

        int pin =
                getIntInput("Enter PIN            : ");

        bank.checkBalance(accountNumber, pin);
    }

    // LIST ALL ACCOUNTS
    public static void listAllAccounts() {

        System.out.println("\n--- All Accounts ---");

        bank.listAllAccounts();
    }

    // ADMIN PANEL
    public static void adminMenu(Admin admin) {

        System.out.println("\n--- Admin Panel ---");

        System.out.print("Enter Admin Password : ");
        String password = input.nextLine();

        if (!admin.login(password)) {
            return;
        }

        boolean adminRunning = true;

        while (adminRunning) {

            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Accounts");
            System.out.println("2. Remove Account");
            System.out.println("3. Apply Interest");
            System.out.println("4. View All Staff");
            System.out.println("5. Back to Main Menu");

            int choice = getIntInput("Enter choice: ");

            switch (choice) {

                case 1 -> bank.listAllAccounts();

                case 2 -> {

                    System.out.print("Enter Account Number : ");
                    String accNo = input.nextLine();

                    bank.removeAccount(accNo);
                }

                case 3 -> bank.calculateInterestForAll();

                case 4 -> bank.listAllStaff();

                case 5 -> adminRunning = false;

                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // STAFF PANEL
    public static void staffMenu() {

        System.out.println("\n--- Staff Panel ---");

        System.out.print("Enter Staff ID : ");
        String staffId = input.nextLine();

        Staff foundStaff = null;

        for (Staff s : bank.getStaffList()) {

            if (s.getPersonId().equals(staffId)) {
                foundStaff = s;
                break;
            }
        }

        if (foundStaff == null) {
            System.out.println("Staff not found!");
            return;
        }

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

                    System.out.print("Enter Person ID   : ");
                    String personId = input.nextLine();

                    System.out.print("Enter Name        : ");
                    String name = input.nextLine();

                    System.out.print("Enter Phone       : ");
                    String phone = input.nextLine();

                    System.out.print("Enter Email       : ");
                    String email = input.nextLine();

                    System.out.print("Enter Address     : ");
                    String address = input.nextLine();

                    System.out.print("Enter Customer ID : ");
                    String customerId = input.nextLine();

                    System.out.print("Account Type (Savings / Current): ");
                    String type = input.nextLine();

                    double deposit =
                            getDoubleInput("Enter Initial Deposit : ");

                    int pin =
                            getIntInput("Set 4-digit PIN       : ");

                    bank.createAccountByStaff(
                            foundStaff,
                            personId,
                            name,
                            phone,
                            email,
                            address,
                            customerId,
                            type,
                            deposit,
                            pin
                    );
                }

                case 2 -> {

                    System.out.print("Enter Customer Account Number : ");
                    String accNo = input.nextLine();

                    bank.assistCustomer(foundStaff, accNo);
                }

                case 3 -> foundStaff.verifyDocuments();

                case 4 -> bank.listAllStaff();

                case 5 -> staffRunning = false;

                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // TRANSACTION MENU
    public static void transactionMenu() {

        System.out.println("\n--- Transaction Record ---");

        System.out.print("Enter Transaction Type : ");
        String type = input.nextLine();

        double amount =
                getDoubleInput("Enter Amount           : ");

        System.out.print("Enter Account Number   : ");
        String accountNumber = input.nextLine();

        Transaction transaction =
                new Transaction(type, amount, accountNumber);

        transaction.recordTransaction();
    }

    // HELPER METHOD FOR INTEGER INPUT
    public static int getIntInput(String prompt) {

        while (true) {

            System.out.print(prompt);

            try {

                return Integer.parseInt(input.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    // HELPER METHOD FOR DOUBLE INPUT
    public static double getDoubleInput(String prompt) {

        while (true) {

            System.out.print(prompt);

            try {

                return Double.parseDouble(input.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Invalid input! Please enter a valid amount.");
            }
        }
    }
}