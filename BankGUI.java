import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BankGUI extends JFrame {
    private Bank bank;
    private Admin defaultAdmin;
    private JTextArea logConsole;

    public BankGUI() {
        // Initialize Core System Backend Details
        bank = new Bank("ABC Corporate Bank");
        
        // Seed Initial Staff Members
        bank.addStaff(new Staff("S101", "Rahim", "01711111111", "rahim@gmail.com", "Dhaka", "STF001", "Manager", 40000));
        bank.addStaff(new Staff("S102", "Karim", "01822222222", "karim@gmail.com", "Rajshahi", "STF002", "Cashier", 30000));
        
        // Seed Master System Administrator
        defaultAdmin = new Admin("A101", "System Master", "01999999999", "admin@gmail.com", "Dhaka", "Senior", "Management", "admin123");

        // Window Design Properties
        setTitle("ABC Bank Management System — Enterprise Edition");
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Create Header Banner Component
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(24, 43, 73));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("ABC BANK CO. MANAGEMENT SHELL");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // Core Layout Management Panels (Tab Container)
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 13));
        
        tabbedPane.addTab("Customer Terminal", buildCustomerPanel());
        tabbedPane.addTab("Staff Operations Panel", buildStaffPanel());
        tabbedPane.addTab("Admin Dashboard", buildAdminPanel());
        add(tabbedPane, BorderLayout.CENTER);

        // System Diagnostic Monitor Console (Bottom Area)
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createTitledBorder("System Output Monitor & Audit Trail Log"));
        
        logConsole = new JTextArea(8, 70);
        logConsole.setEditable(false);
        logConsole.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logConsole.setBackground(Color.BLACK);
        logConsole.setForeground(new Color(57, 255, 20)); // Matrix Neon Green Color
        
        JScrollPane scrollPane = new JScrollPane(logConsole);
        southPanel.add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        // Intercept standard console streams and re-route logs straight onto screen monitor UI
        interceptSystemStreams();
        System.out.println("[SYSTEM ONLINE]: ABC Software Base Engine initialization phase absolute.");
    }

    // CUSTOMER PANEL PANEL BUILDING METHOD
    private JPanel buildCustomerPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formGrid = new JPanel(new GridLayout(9, 2, 8, 8));
        formGrid.setBorder(BorderFactory.createTitledBorder("Create Direct Client Account File"));

        JTextField txtPersonId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtPhone = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtAddress = new JTextField();
        JTextField txtCustId = new JTextField();
        JComboBox<String> cmbType = new JComboBox<>(new String[]{"Savings", "Current"});
        JTextField txtDeposit = new JTextField();
        JTextField txtPin = new JTextField();

        formGrid.add(new JLabel("National/Person ID:")); formGrid.add(txtPersonId);
        formGrid.add(new JLabel("Full Name:")); formGrid.add(txtName);
        formGrid.add(new JLabel("Phone Number:")); formGrid.add(txtPhone);
        formGrid.add(new JLabel("Email Address:")); formGrid.add(txtEmail);
        formGrid.add(new JLabel("Residential Address:")); formGrid.add(txtAddress);
        formGrid.add(new JLabel("Assigned Customer ID:")); formGrid.add(txtCustId);
        formGrid.add(new JLabel("Financial Account Schema Type:")); formGrid.add(cmbType);
        formGrid.add(new JLabel("Opening Deposit Funds (tk):")); formGrid.add(txtDeposit);
        formGrid.add(new JLabel("Secure Safety PIN (4-Digits):")); formGrid.add(txtPin);

        JButton btnCreate = new JButton("Execute Account Creation Sequence");
        btnCreate.addActionListener(e -> {
            try {
                bank.createAccount(
                    txtPersonId.getText(), txtName.getText(), txtPhone.getText(),
                    txtEmail.getText(), txtAddress.getText(), txtCustId.getText(),
                    (String) cmbType.getSelectedItem(),
                    Double.parseDouble(txtDeposit.getText()),
                    Integer.parseInt(txtPin.getText())
                );
            } catch (Exception ex) {
                System.out.println("[CRITICAL SCHEMA EXCEPTION]: Master Variable verification error! Verify type mapping.");
            }
        });

        JPanel leftContainer = new JPanel(new BorderLayout());
        leftContainer.add(formGrid, BorderLayout.CENTER);
        leftContainer.add(btnCreate, BorderLayout.SOUTH);
        mainPanel.add(leftContainer, BorderLayout.WEST);

        // Transactional Operations UI Subpanel Block
        JPanel txPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        txPanel.setBorder(BorderFactory.createTitledBorder("Direct Vault Transaction Services"));

        JButton btnDeposit = new JButton("Run Cash Inflow Deposit Transaction");
        btnDeposit.addActionListener(e -> {
            String acc = JOptionPane.showInputDialog(this, "Enter Destination Account Code:");
            String amt = JOptionPane.showInputDialog(this, "Enter Absolute Deposit Inflow Volume (tk):");
            if(acc != null && amt != null) bank.deposit(acc, Double.parseDouble(amt));
        });

        JButton btnWithdraw = new JButton("Run Vault Cash Outflow Withdrawal Request");
        btnWithdraw.addActionListener(e -> {
            String acc = JOptionPane.showInputDialog(this, "Enter Source Account Code:");
            String amt = JOptionPane.showInputDialog(this, "Enter Absolute Cash Outflow Volume (tk):");
            String pin = JOptionPane.showInputDialog(this, "Authorize Transaction via Secret PIN:");
            if(acc != null && amt != null && pin != null) {
                bank.withdraw(acc, Double.parseDouble(amt), Integer.parseInt(pin));
            }
        });

        JButton btnTransfer = new JButton("Run Clearing-House Inter-Account Fund Transfer");
        btnTransfer.addActionListener(e -> {
            String from = JOptionPane.showInputDialog(this, "Enter Source Account Number Origin:");
            String to = JOptionPane.showInputDialog(this, "Enter Target Destination Account Beneficiary:");
            String amt = JOptionPane.showInputDialog(this, "Enter Wire Transfer Net Capital (tk):");
            String pin = JOptionPane.showInputDialog(this, "Authorize Ledger Transfer Security PIN:");
            if(from != null && to != null && amt != null && pin != null) {
                bank.transfer(from, to, Double.parseDouble(amt), Integer.parseInt(pin));
            }
        });

        JButton btnBalance = new JButton("Query Core Asset Book Ledger Account Balance");
        btnBalance.addActionListener(e -> {
            String acc = JOptionPane.showInputDialog(this, "Target Audit Account Code Number:");
            String pin = JOptionPane.showInputDialog(this, "Account Verification Passcode Authorization PIN:");
            if(acc != null && pin != null) bank.checkBalance(acc, Integer.parseInt(pin));
        });

        JButton btnPrintTransactions = new JButton("Simulate & Test New Ledger Transaction Record Creation");
        btnPrintTransactions.addActionListener(e -> {
            String type = JOptionPane.showInputDialog(this, "Enter System Transaction Classification Label (e.g. Online_Bill_Pay):");
            String amt = JOptionPane.showInputDialog(this, "Enter Financial Volume Amount Metric:");
            String acc = JOptionPane.showInputDialog(this, "Associated Identity Account Number Index Reference:");
            if (type != null && amt != null && acc != null) {
                try {
                    Transaction transaction = new Transaction(type, Double.parseDouble(amt), acc);
                    transaction.recordTransaction();
                } catch (Exception ex) {
                    System.out.println("[TRANSACTION FAULT]: " + ex.getMessage());
                }
            }
        });

        txPanel.add(btnDeposit);
        txPanel.add(btnWithdraw);
        txPanel.add(btnTransfer);
        txPanel.add(btnBalance);
        txPanel.add(btnPrintTransactions);

        mainPanel.add(txPanel, BorderLayout.CENTER);
        return mainPanel;
    }

    // STAFF REGISTRATION PANEL BUILDING METHOD
    private JPanel buildStaffPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Staff Identification Verification Gateway"));
        
        controlPanel.add(new JLabel("Input Staff Key Pointer ID to Login Session:"));
        JTextField txtStaffIdInput = new JTextField(12);
        controlPanel.add(txtStaffIdInput);
        panel.add(controlPanel, BorderLayout.NORTH);

        JPanel opsGrid = new JPanel(new GridLayout(2, 2, 15, 15));
        opsGrid.setBorder(BorderFactory.createTitledBorder("Secure Teller Operational Protocols (Authentication Checked)"));

        JButton btnStaffCreateCust = new JButton("Staff Operation Protocol: Authorized Client File Creation");
        JButton btnStaffAssist = new JButton("Staff Operation Protocol: Query Core Customer Dossier");
        JButton btnStaffVerify = new JButton("Staff Operation Protocol: Execute Identity Document Verification");
        JButton btnStaffList = new JButton("Global System Protocol: View All Registered Bank Officers");

        btnStaffCreateCust.addActionListener(e -> {
            Staff s = verifyAndGetStaff(txtStaffIdInput.getText());
            if (s == null) return;
            
            // Spawn account creation window authenticated through processing staff officer 
            String pId = JOptionPane.showInputDialog(this, "Client National ID Card Code:");
            String name = JOptionPane.showInputDialog(this, "Client Identity Full Legal Name:");
            String phone = JOptionPane.showInputDialog(this, "Client Primary Communications Mobile Line:");
            String mail = JOptionPane.showInputDialog(this, "Client Internet Electronic Mail Address:");
            String addr = JOptionPane.showInputDialog(this, "Client Certified Current Home Address:");
            String cId = JOptionPane.showInputDialog(this, "Assign Unique Customer Matrix Index Sequence ID:");
            String type = JOptionPane.showInputDialog(this, "Enter Ledger Asset Classification Model Schema (Savings/Current):");
            String dep = JOptionPane.showInputDialog(this, "Initial Vault Capital Deposit Investment (tk):");
            String pin = JOptionPane.showInputDialog(this, "Assign Safe Financial Security Authorization Code PIN:");

            if(pId != null && name != null && phone != null && mail != null && addr != null && cId != null && type != null && dep != null && pin != null) {
                bank.createAccountByStaff(s, pId, name, phone, mail, addr, cId, type, Double.parseDouble(dep), Integer.parseInt(pin));
            }
        });

        btnStaffAssist.addActionListener(e -> {
            Staff s = verifyAndGetStaff(txtStaffIdInput.getText());
            if (s == null) return;
            String targetAcc = JOptionPane.showInputDialog(this, "Specify Target Client Ledger File Code Account Number:");
            if(targetAcc != null) bank.assistCustomer(s, targetAcc);
        });

        btnStaffVerify.addActionListener(e -> {
            Staff s = verifyAndGetStaff(txtStaffIdInput.getText());
            if (s == null) return;
            s.verifyDocuments();
        });

        btnStaffList.addActionListener(e -> bank.listAllStaff());

        opsGrid.add(btnStaffCreateCust);
        opsGrid.add(btnStaffAssist);
        opsGrid.add(btnStaffVerify);
        opsGrid.add(btnStaffList);

        panel.add(opsGrid, BorderLayout.CENTER);
        return panel;
    }

    // ADMIN PRIVILEGED DASHBOARD BUILDING METHOD
    private JPanel buildAdminPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton btnListAll = new JButton("Admin Clearance Level 1: System-wide Ledger Audit (List Accounts)");
        JButton btnRemove = new JButton("Admin Clearance Level 2: Revoke & Clear Active Account Node");
        JButton btnInterest = new JButton("Admin Clearance Level 3: Run Global Savings Accounts Interest Accrual Batch Job");
        JButton btnViewStaff = new JButton("Admin Clearance Level 4: Audit Registry Records of All Bank Personnel");

        btnListAll.addActionListener(e -> {
            if (runAdminLoginChallenge()) bank.listAllAccounts();
        });

        btnRemove.addActionListener(e -> {
            if (runAdminLoginChallenge()) {
                String targetAcc = JOptionPane.showInputDialog(this, "Target Financial Account Code Ledger Node for Permanent Deletion:");
                if (targetAcc != null) bank.removeAccount(targetAcc);
            }
        });

        btnInterest.addActionListener(e -> {
            if (runAdminLoginChallenge()) bank.calculateInterestForAll();
        });

        btnViewStaff.addActionListener(e -> {
            if (runAdminLoginChallenge()) bank.listAllStaff();
        });

        panel.add(new JLabel("SYSTEM SUPERUSER COMMAND CONSOLE", SwingConstants.CENTER));
        panel.add(btnListAll);
        panel.add(btnRemove);
        panel.add(btnInterest);
        panel.add(btnViewStaff);

        return panel;
    }

    // HELPER CORE VALIDATION METHODS
    private Staff verifyAndGetStaff(String enteredId) {
        if (enteredId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Security Authentication Gate Failure! Staff operational session identification key field cannot remain empty.", "Authentication Fault", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        for (Staff s : bank.getStaffList()) {
            if (s.getStaffId().equalsIgnoreCase(enteredId.trim()) || s.getPersonId().equalsIgnoreCase(enteredId.trim())) {
                return s;
            }
        }
        System.out.println("[ACCESS DENIED]: Entered Identification Token Signature lookup unsuccessful inside registry.");
        return null;
    }

    private boolean runAdminLoginChallenge() {
        String passInput = JOptionPane.showInputDialog(this, "Elevated Access Required! Provide Master Secure Server Password Key:", "System Security Access Query", JOptionPane.WARNING_MESSAGE);
        if (passInput == null) return false;
        return defaultAdmin.login(passInput);
    }

    private void interceptSystemStreams() {
        PrintStream interceptor = new PrintStream(new ByteArrayOutputStream() {
            @Override
            public synchronized void write(byte[] b, int off, int len) {
                String outputStr = new String(b, off, len);
                SwingUtilities.invokeLater(() -> logConsole.append(outputStr));
            }
        }, true);
        System.setOut(interceptor);
        System.setErr(interceptor);
    }

    // SYSTEM DESKTOP ARCHITECTURE RUNTIME DRIVER RUN ENTRY POINT
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new BankGUI().setVisible(true);
        });
    }
}