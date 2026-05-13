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
    public String getPersonId() { return personId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }

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

   
}