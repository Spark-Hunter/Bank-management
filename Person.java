import java.util.Scanner;

class Person {
    // Attributes
    private String personId;
    private String name;
    private String phone;
    private String email;
    private String address;

    // Constructor
    public Person(String personId, String name, String phone, String email, String address) {
        this.personId = personId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Method to display person information
    public void getpersonInfo() {
        System.out.println("\nPerson Information:");
        System.out.println("Person ID: " + personId);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
    }

    // Method to update contact information
    public void updateContact(String phone, String email, String address) {
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Main method
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Taking input from user
        System.out.print("Enter Person ID: ");
        String id = input.nextLine();

        System.out.print("Enter Name: ");
        String name = input.nextLine();

        System.out.print("Enter Phone: ");
        String phone = input.nextLine();

        System.out.print("Enter Email: ");
        String email = input.nextLine();

        System.out.print("Enter Address: ");
        String address = input.nextLine();

        // Creating object
        Person p1 = new Person(id, name, phone, email, address);

        // Display information
        p1.getpersonInfo();

        // Updating contact info
        System.out.println("\nUpdate Contact Information");

        System.out.print("Enter New Phone: ");
        String newPhone = input.nextLine();

        System.out.print("Enter New Email: ");
        String newEmail = input.nextLine();

        System.out.print("Enter New Address: ");
        String newAddress = input.nextLine();

        // Update values
        p1.updateContact(newPhone, newEmail, newAddress);

        // Display updated information
        p1.getpersonInfo();

    }
}