public class Staff extends Person {

    private String staffId;
    private String role;
    private double salary;

    // Constructor
    public Staff(String personId, String name, String phone, String email,
                 String address, String staffId, String role, double salary) {

        super(personId, name, phone, email, address);

        this.staffId = staffId;
        this.role = role;
        this.salary = salary;
    }

    // Getters
    public String getStaffId() {
        return staffId;
    }

    public String getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    // Staff operations
    public void assistCustomer() {
        System.out.println("Staff " + getName() + " is assisting a customer.");
    }

    public void createCustomerAccount() {
        System.out.println("Creating a new customer account. Authorized by: " + staffId);
    }

    public void verifyDocuments() {
        System.out.println("Verifying identity documents for the bank system.");
    }

    // Display information
    public void displayStaffInfo() {
        getpersonInfo(); // inherited from Person

        System.out.println("Staff ID: " + staffId);
        System.out.println("Role: " + role);
        System.out.println("Salary: " + salary);
    }
}