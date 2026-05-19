public class Admin extends Person {

    // Admin-specific private fields
    private String adminLevel;
    private String department;
    private String password;

    // Constructor
    public Admin(String personId, String name, String email,
                 String phone, String adminLevel, String department, String password, String address) {
        super(personId, name, email, phone, address); // calls Person constructor
        this.adminLevel  = adminLevel;
        this.department  = department;
        this.password    = password;
    }

    // Getters
    public String getAdminLevel()  { return adminLevel; }
    public String getDepartment()  { return department; }

    // Setters
    public void setAdminLevel(String adminLevel)   { this.adminLevel = adminLevel; }
    public void setDepartment(String department)   { this.department = department; }

    // @Override - implementing abstract method from Person
    @Override
    public void getpersonInfo() {
        System.out.println("=== Admin Details ===");
        System.out.println("ID         : " + getPersonId());
        System.out.println("Name       : " + getName());
        System.out.println("Email      : " + getEmail());
        System.out.println("Phone      : " + getPhone());
        System.out.println("Level      : " + adminLevel);
        System.out.println("Department : " + department);
    }

    // Admin-only method
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            System.out.println("Login successful! Welcome, " + getName());
            return true;
        } else {
            System.out.println("Incorrect password! Access denied.");
            return false;
        }
    }
}
