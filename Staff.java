public class Staff extends Person {
    // Specific attributes from Screenshot 2026-05-13 210827_2.png
    private String staffid;
    private String role;
    private double salary;

    // Constructor
    public Staff(String personId, String name, String phone, String email, String address, 
                 String staffid, String role, double salary) {
        // Calling the parent (Person) constructor
        super(personId, name, phone, email, address);
        this.staffid = staffid;
        this.role = role;
        this.salary = salary;
    }

    // Methods defined in Screenshot 2026-05-13 210827_2.png
    public void assistCustomer() {
        System.out.println("Staff " + getName() + " is assisting a customer.");
    }

    public void createCustomerAccount() {
        System.out.println("Creating a new customer account. Authorized by: " + staffid);
    }

    public void verifyDocuments() {
        System.out.println("Verifying identity documents for the bank system.");
    }

    public void displayStaffinfo() {
        // Use the inherited method to show personal details
        getpersonInfo(); 
        // Show staff-specific details
        System.out.println("Staff ID: " + staffid);
        System.out.println("Role: " + role);
        System.out.println("Salary: " + salary);
    }
}