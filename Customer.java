public class Customer extends Person {

    // private fields
    private String customerId;
    private Bankaccount account;

    // Constructor
    public Customer(String personId, String name, String phone, String email, String address, String customerId, Bankaccount account) {
    super(personId, name, phone, email, address); // Must match the 5-parameter constructor
    this.customerId = customerId;
    this.account = account;



    }

    // getCustomerInfo()
    public void getCustomerInfo() {
        System.out.println("=== Customer Information ===");
        System.out.println("Person ID   : " + getPersonId());
        System.out.println("Customer ID : " + customerId);
        System.out.println("Name        : " + getName());
        System.out.println("Email       : " + getEmail());
        System.out.println("Phone       : " + getPhone());
        System.out.println("----------------------------");
        account.displayDetails();  // shows linked account details
    }


    // updateContact()
    public void updateContact(String newPhone, String newEmail) {
        if (newPhone == null || newPhone.isEmpty()) {
            System.out.println("Invalid phone number!");
            return;
        }
        if (newEmail == null || newEmail.isEmpty()) {
            System.out.println("Invalid email address!");
            return;
        }
        if (!newEmail.contains("@")) {
            System.out.println("Invalid email format! Must contain @");
            return;
        }
        setPhone(newPhone);    // uses setter from Person
        setEmail(newEmail);    // uses setter from Person
        System.out.println("Contact updated successfully!");
        System.out.println("New Phone : " + newPhone);
        System.out.println("New Email : " + newEmail);
    }

   
    // @Override displayDetails()
    @Override
    public void getpersonInfo() {
        System.out.println("=== Customer Details ===");
        System.out.println("Person ID   : " + getPersonId());
        System.out.println("Customer ID : " + customerId);
        System.out.println("Name        : " + getName());
        System.out.println("Email       : " + getEmail());
        System.out.println("Phone       : " + getPhone());
        System.out.println("Account No  : " + account.getAccountNumber());
        System.out.println("Balance     : " + account.getBalance() + "tk");
        System.out.println("Account Type: " + account.getAccountType());
    }

    
    // Getters
    public String getCustomerId()    { return customerId; }
    public Bankaccount getAccount()  { return account; }

    // Setter for account
    // (in case account needs to be updated)
    public void setAccount(Bankaccount account) {
        if (account != null) {
            this.account = account;
            System.out.println("Account updated successfully!");
        } else {
            System.out.println("Invalid account!");
        }
    }
}