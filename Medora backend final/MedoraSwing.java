import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Modern Swing Frontend for Hospital Management System
public class MedoraSwing {
    private Hospital currentHospital; // Track the logged-in hospital
    private static Database database = new Database();
    private JFrame mainFrame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    // Panel names for navigation
    private static final String MAIN_MENU = "MAIN_MENU";
    private static final String PATIENT_LOGIN = "PATIENT_LOGIN";
    private static final String PATIENT_REGISTER = "PATIENT_REGISTER";
    private static final String HOSPITAL_LOGIN = "HOSPITAL_LOGIN";
    private static final String HOSPITAL_REGISTER = "HOSPITAL_REGISTER";
    private static final String ADMIN_LOGIN = "ADMIN_LOGIN";
    private static final String PATIENT_PANEL = "PATIENT_PANEL";
    private static final String HOSPITAL_PANEL = "HOSPITAL_PANEL";
    private static final String ADMIN_PANEL = "ADMIN_PANEL";

    public MedoraSwing() {
        initializeUI();
    }

    private void initializeUI() {
        // Create main frame
        mainFrame = new JFrame(" Medora Hospital Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 600);
        mainFrame.setLocationRelativeTo(null); // Center on screen
        
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create card layout for navigation
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create all panels
        createMainMenuPanel();
        createPatientLoginPanel();
        createPatientRegisterPanel();
        createHospitalLoginPanel();
        createHospitalRegisterPanel();
        createAdminLoginPanel();
        createPatientPanel();
        createHospitalPanel();
        createAdminPanel();
        
        // Add main panel to frame
        mainFrame.add(mainPanel);
        
        // Show main menu first
        cardLayout.show(mainPanel, MAIN_MENU);
    }

    private void createMainMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        // Header
        JLabel headerLabel = new JLabel(" MEDORA", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
        headerLabel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Main buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2, 20, 20));
        buttonsPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 40, 100));
        
        // Create modern buttons with nude colors for better visibility
        JButton patientLoginBtn = createModernButton(" Patient Login", new Color(160, 82, 45)); // Saddle brown
        JButton patientRegisterBtn = createModernButton(" Patient Register", new Color(139, 69, 19)); // Saddle brown
        JButton hospitalLoginBtn = createModernButton(" Hospital Login", new Color(205, 133, 63)); // Peru
        JButton hospitalRegisterBtn = createModernButton(" Hospital Register", new Color(188, 143, 143)); // Rosy brown
        JButton adminLoginBtn = createModernButton(" Admin Login", new Color(160, 82, 45)); // Saddle brown
        JButton exitBtn = createModernButton(" Exit", new Color(105, 105, 105)); // Dim gray
        
        // Add action listeners
        patientLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, PATIENT_LOGIN));
        patientRegisterBtn.addActionListener(e -> cardLayout.show(mainPanel, PATIENT_REGISTER));
        hospitalLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, HOSPITAL_LOGIN));
        hospitalRegisterBtn.addActionListener(e -> cardLayout.show(mainPanel, HOSPITAL_REGISTER));
        adminLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, ADMIN_LOGIN));
        exitBtn.addActionListener(e -> System.exit(0));
        
        // Add buttons to panel
        buttonsPanel.add(patientLoginBtn);
        buttonsPanel.add(patientRegisterBtn);
        buttonsPanel.add(hospitalLoginBtn);
        buttonsPanel.add(hospitalRegisterBtn);
        buttonsPanel.add(adminLoginBtn);
        buttonsPanel.add(exitBtn);
        
        panel.add(buttonsPanel, BorderLayout.CENTER);
        
        // Footer
        JLabel footerLabel = new JLabel("Modern Healthcare Management Solution", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        footerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
        footerLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(footerLabel, BorderLayout.SOUTH);
        
        mainPanel.add(panel, MAIN_MENU);
    }

    private JButton createModernButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.BLACK);
        button.setBackground(color);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
        
        return button;
    }

    private void createPatientLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        // Header
        JLabel headerLabel = new JLabel("👤 PATIENT LOGIN", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
        headerLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Login form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Username field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField userField = new JTextField(20);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Password field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JPasswordField passField = new JPasswordField(20);
        
        // Buttons
        JButton loginBtn = createModernButton("Login", new Color(70, 130, 180));
        JButton backBtn = createModernButton("Back", new Color(128, 128, 128));
        
        // Add components
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(userField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(loginBtn);
        buttonPanel.add(backBtn);
        formPanel.add(buttonPanel, gbc);
        
        // Add action listeners
        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            
            Patient patient = database.findPatient(username, password);
            if (patient != null) {
                JOptionPane.showMessageDialog(panel, "Login successful! Welcome, " + username + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(mainPanel, PATIENT_PANEL);
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, MAIN_MENU));
        
        panel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(panel, PATIENT_LOGIN);
    }

    private void createPatientRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        // Header
        JLabel headerLabel = new JLabel(" PATIENT REGISTRATION", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Registration form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Form fields
        JTextField userField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JPasswordField passField = new JPasswordField(20);
        
        // Add form components
        addFormField(formPanel, "Username:", userField, gbc, 0);
        addFormField(formPanel, "Email:", emailField, gbc, 1);
        addFormField(formPanel, "Phone (+8801XXXXXXXXX):", phoneField, gbc, 2);
        addFormField(formPanel, "Password (min 8 chars):", passField, gbc, 3);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        JButton registerBtn = createModernButton("Register", new Color(160, 82, 45)); // Saddle brown
        JButton backBtn = createModernButton("Back", new Color(105, 105, 105)); // Dim gray
        
        buttonPanel.add(registerBtn);
        buttonPanel.add(backBtn);
        formPanel.add(buttonPanel, gbc);
        
        // Add action listeners
        registerBtn.addActionListener(e -> {
            String username = userField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String password = new String(passField.getPassword());
            
            if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!Patient.isValidEmail(email)) {
                JOptionPane.showMessageDialog(panel, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!Patient.isValidPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(panel, "Invalid phone number format!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (password.length() < 8) {
                JOptionPane.showMessageDialog(panel, "Password must be at least 8 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Patient patient = new Patient(username, password, email, phone);
            if (database.addPatient(patient)) {
                JOptionPane.showMessageDialog(panel, "Registration successful! You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(mainPanel, MAIN_MENU);
            } else {
                JOptionPane.showMessageDialog(panel, "Username already exists! Try a different username.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, MAIN_MENU));
        
        panel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(panel, PATIENT_REGISTER);
    }

    private void addFormField(JPanel panel, String labelText, JComponent field, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void createHospitalLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        // Header
        JLabel headerLabel = new JLabel(" HOSPITAL LOGIN", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
        headerLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Login form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Username field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField userField = new JTextField(20);
        
        // Password field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JPasswordField passField = new JPasswordField(20);
        
        // Buttons
        JButton loginBtn = createModernButton("Login", new Color(255, 140, 0));
        JButton backBtn = createModernButton("Back", new Color(128, 128, 128));
        
        // Add components
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(userField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        buttonPanel.add(loginBtn);
        buttonPanel.add(backBtn);
        formPanel.add(buttonPanel, gbc);
        
        // Add action listeners
        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            
            Hospital hospital = database.findHospital(username, password);
            if (hospital == null) {
                JOptionPane.showMessageDialog(panel, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!hospital.isVerified()) {
                JOptionPane.showMessageDialog(panel, "Your hospital is waiting for admin approval.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            currentHospital = hospital;
            JOptionPane.showMessageDialog(panel, "Login successful! Welcome, " + hospital.getName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(mainPanel, HOSPITAL_PANEL);
        });
        
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, MAIN_MENU));
        
        panel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(panel, HOSPITAL_LOGIN);
    }

    private void createHospitalRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        // Header
        JLabel headerLabel = new JLabel(" HOSPITAL REGISTRATION", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Create scrollable form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Form fields
        JTextField nameField = new JTextField(20);
        JTextField licenseField = new JTextField(20);
        JTextField districtField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField userField = new JTextField(20);
        JPasswordField passField = new JPasswordField(20);
        JTextField economyField = new JTextField(20);
        JTextField vipField = new JTextField(20);
        JTextField icuField = new JTextField(20);
        JTextField emergencyField = new JTextField(20);
        JTextField customerField = new JTextField(20);
        
        // Add form components
        addFormField(formPanel, "Hospital Name:", nameField, gbc, 0);
        addFormField(formPanel, "License Number:", licenseField, gbc, 1);
        addFormField(formPanel, "District:", districtField, gbc, 2);
        addFormField(formPanel, "Address:", addressField, gbc, 3);
        addFormField(formPanel, "Username:", userField, gbc, 4);
        addFormField(formPanel, "Password:", passField, gbc, 5);
        addFormField(formPanel, "Total Economy Beds:", economyField, gbc, 6);
        addFormField(formPanel, "Total VIP Beds:", vipField, gbc, 7);
        addFormField(formPanel, "Total ICU Beds:", icuField, gbc, 8);
        addFormField(formPanel, "Emergency Contact:", emergencyField, gbc, 9);
        addFormField(formPanel, "Customer Care Contact:", customerField, gbc, 10);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 11; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        JButton registerBtn = createModernButton("Register", new Color(188, 143, 143)); // Rosy brown
        JButton backBtn = createModernButton("Back", new Color(105, 105, 105)); // Dim gray
        
        buttonPanel.add(registerBtn);
        buttonPanel.add(backBtn);
        formPanel.add(buttonPanel, gbc);
        
        // Add action listeners
        registerBtn.addActionListener(e -> {
            // Get all field values
            String name = nameField.getText();
            String license = licenseField.getText();
            String district = districtField.getText();
            String address = addressField.getText();
            String username = userField.getText();
            String password = new String(passField.getPassword());
            
            // Validate required fields
            if (name.isEmpty() || license.isEmpty() || district.isEmpty() || address.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validate license format
            if (!Hospital.isValidLicenseNumber(license)) {
                JOptionPane.showMessageDialog(panel, "Invalid license number format! Use HOSP-XXX-123456", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validate password length
            if (password.length() < 8) {
                JOptionPane.showMessageDialog(panel, "Password must be at least 8 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Parse bed counts
            int economyBeds, vipBeds, icuBeds;
            try {
                economyBeds = Integer.parseInt(economyField.getText());
                vipBeds = Integer.parseInt(vipField.getText());
                icuBeds = Integer.parseInt(icuField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Bed counts must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (economyBeds < 0 || vipBeds < 0 || icuBeds < 0) {
                JOptionPane.showMessageDialog(panel, "Bed counts cannot be negative!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create hospital
            Hospital hospital = new Hospital(name, license, district, address, username, password,
                    economyBeds, vipBeds, icuBeds, emergencyField.getText(), customerField.getText(), true, true);
            
            if (database.addHospital(hospital)) {
                JOptionPane.showMessageDialog(panel, "Registration successful! Waiting for admin approval.", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(mainPanel, MAIN_MENU);
            } else {
                JOptionPane.showMessageDialog(panel, "Username already exists! Try a different username.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, MAIN_MENU));
        
        // Make form scrollable
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(panel, HOSPITAL_REGISTER);
    }

    private void createAdminLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        // Header
        JLabel headerLabel = new JLabel(" ADMIN LOGIN", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
        headerLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Login form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Username field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField userField = new JTextField(20);
        
        // Password field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JPasswordField passField = new JPasswordField(20);
        
        // Buttons
        JButton loginBtn = createModernButton("Login", new Color(220, 20, 60));
        JButton backBtn = createModernButton("Back", new Color(128, 128, 128));
        
        // Add components
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(userField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        buttonPanel.add(loginBtn);
        buttonPanel.add(backBtn);
        formPanel.add(buttonPanel, gbc);
        
        // Add action listeners
        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            
            Admin admin = database.getAdmin(username, password);
            if (admin != null) {
                JOptionPane.showMessageDialog(panel, "Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(mainPanel, ADMIN_PANEL);
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid admin credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, MAIN_MENU));
        
        panel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(panel, ADMIN_LOGIN);
    }

    private void createPatientPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        // Header
        JLabel headerLabel = new JLabel(" PATIENT PANEL", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonsPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        
        JButton findDistrictBtn = createModernButton(" Find Hospitals by District", new Color(70, 130, 180));
        JButton searchNameBtn = createModernButton(" Search Hospitals by Name", new Color(60, 179, 113));
        JButton logoutBtn = createModernButton(" Logout", new Color(128, 128, 128));
        
        // Add action listeners
        findDistrictBtn.addActionListener(e -> {
            String district = JOptionPane.showInputDialog(panel, "Enter district name:");
            if (district != null && !district.trim().isEmpty()) {
                ArrayList<Hospital> hospitals = database.getHospitalsByDistrict(district);
                showHospitalsDialog(hospitals, "Hospitals in " + district);
            }
        });
        
        searchNameBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(panel, "Enter hospital name:");
            if (name != null && !name.trim().isEmpty()) {
                ArrayList<Hospital> hospitals = database.searchHospitalsByName(name);
                showHospitalsDialog(hospitals, "Hospitals matching '" + name + "'");
            }
        });
        
        logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, MAIN_MENU));
        
        buttonsPanel.add(findDistrictBtn);
        buttonsPanel.add(searchNameBtn);
        buttonsPanel.add(logoutBtn);
        
        panel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(panel, PATIENT_PANEL);
    }
// ...existing code...
private void createHospitalPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBackground(new Color(245, 245, 220)); // Beige nude background

    // Header
    JLabel headerLabel = new JLabel(" HOSPITAL PANEL", SwingConstants.CENTER);
    headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
    headerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
    headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.add(headerLabel, BorderLayout.NORTH);

    // Buttons panel
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(5, 1, 20, 20));
    buttonsPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 150, 30, 150));

    JButton updateBedsBtn = createModernButton(" Update Available Beds", new Color(255, 140, 0));
    JButton updateTotalBtn = createModernButton(" Update Total Beds", new Color(138, 43, 226));
    JButton updateContactBtn = createModernButton(" Update Contact Information", new Color(60, 179, 113));
    JButton viewDetailsBtn = createModernButton(" View Details", new Color(70, 130, 180));
    JButton logoutBtn = createModernButton(" Logout", new Color(128, 128, 128));

    // Add action listeners
    updateBedsBtn.addActionListener(e -> showUpdateAvailableBedsDialog());
    updateTotalBtn.addActionListener(e -> showUpdateTotalBedsDialog());
    updateContactBtn.addActionListener(e -> showUpdateContactDialog());
    viewDetailsBtn.addActionListener(e -> showHospitalFullDetails());
    logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, MAIN_MENU));

    buttonsPanel.add(updateBedsBtn);
    buttonsPanel.add(updateTotalBtn);
    buttonsPanel.add(updateContactBtn);
    buttonsPanel.add(viewDetailsBtn);
    buttonsPanel.add(logoutBtn);

    panel.add(buttonsPanel, BorderLayout.CENTER);
    mainPanel.add(panel, HOSPITAL_PANEL);
}

// Show dialog for updating available beds
private void showUpdateAvailableBedsDialog() {
    if (currentHospital == null) {
        JOptionPane.showMessageDialog(mainFrame, "No hospital is logged in.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBackground(new Color(245, 245, 220));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;

    JLabel economyLabel = new JLabel("Available Economy Beds (max " + currentHospital.getTotalEconomyBeds() + "):");
    JTextField economyField = new JTextField(String.valueOf(currentHospital.getAvailableEconomyBeds()), 10);
    JLabel vipLabel = new JLabel("Available VIP Beds (max " + currentHospital.getTotalVipBeds() + "):");
    JTextField vipField = new JTextField(String.valueOf(currentHospital.getAvailableVipBeds()), 10);
    JLabel icuLabel = new JLabel("Available ICU Beds (max " + currentHospital.getTotalIcuBeds() + "):");
    JTextField icuField = new JTextField(String.valueOf(currentHospital.getAvailableIcuBeds()), 10);

    gbc.gridx = 0; gbc.gridy = 0; formPanel.add(economyLabel, gbc);
    gbc.gridx = 1; formPanel.add(economyField, gbc);
    gbc.gridx = 0; gbc.gridy = 1; formPanel.add(vipLabel, gbc);
    gbc.gridx = 1; formPanel.add(vipField, gbc);
    gbc.gridx = 0; gbc.gridy = 2; formPanel.add(icuLabel, gbc);
    gbc.gridx = 1; formPanel.add(icuField, gbc);

    int result = JOptionPane.showConfirmDialog(mainFrame, formPanel, "Update Available Beds", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        try {
            int economy = Integer.parseInt(economyField.getText());
            int vip = Integer.parseInt(vipField.getText());
            int icu = Integer.parseInt(icuField.getText());
            boolean ok = true;
            ok &= currentHospital.setAvailableEconomyBeds(economy);
            ok &= currentHospital.setAvailableVipBeds(vip);
            ok &= currentHospital.setAvailableIcuBeds(icu);
            if (!ok) {
                JOptionPane.showMessageDialog(mainFrame, "Please enter valid bed numbers within limits.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(mainFrame, "Available beds updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Show dialog for updating total beds
private void showUpdateTotalBedsDialog() {
    if (currentHospital == null) {
        JOptionPane.showMessageDialog(mainFrame, "No hospital is logged in.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBackground(new Color(245, 245, 220));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;

    JLabel totalLabel = new JLabel("Total Economy Beds:");
    JTextField totalField = new JTextField(String.valueOf(currentHospital.getTotalEconomyBeds()), 10);

    gbc.gridx = 0; gbc.gridy = 0; formPanel.add(totalLabel, gbc);
    gbc.gridx = 1; formPanel.add(totalField, gbc);

    int result = JOptionPane.showConfirmDialog(mainFrame, formPanel, "Update Total Beds", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        try {
            int total = Integer.parseInt(totalField.getText());
            boolean ok = currentHospital.setTotalEconomyBeds(total);
            if (!ok) {
                JOptionPane.showMessageDialog(mainFrame, "Total beds must be >= available beds and positive.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(mainFrame, "Total beds updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Show dialog for updating contact information
private void showUpdateContactDialog() {
    if (currentHospital == null) {
        JOptionPane.showMessageDialog(mainFrame, "No hospital is logged in.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBackground(new Color(245, 245, 220));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;

    JLabel emergencyLabel = new JLabel("Emergency Contact:");
    JTextField emergencyField = new JTextField(currentHospital.getEmergencyContact(), 15);
    JLabel customerLabel = new JLabel("Customer Care Contact:");
    JTextField customerField = new JTextField(currentHospital.getCustomerCareContact(), 15);

    gbc.gridx = 0; gbc.gridy = 0; formPanel.add(emergencyLabel, gbc);
    gbc.gridx = 1; formPanel.add(emergencyField, gbc);
    gbc.gridx = 0; gbc.gridy = 1; formPanel.add(customerLabel, gbc);
    gbc.gridx = 1; formPanel.add(customerField, gbc);

    int result = JOptionPane.showConfirmDialog(mainFrame, formPanel, "Update Contact Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        String emergency = emergencyField.getText().trim();
        String customer = customerField.getText().trim();
        if (emergency.isEmpty() || customer.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please fill in both contact fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        currentHospital.setEmergencyContact(emergency);
        currentHospital.setCustomerCareContact(customer);
        JOptionPane.showMessageDialog(mainFrame, "Contact information updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
// ...existing code...

//show Approve/Reject Dialog for new hospitals
private void showApproveRejectDialog() {
    ArrayList<Hospital> pendingHospitals = database.getPendingHospitals();
    if (pendingHospitals.isEmpty()) {
        JOptionPane.showMessageDialog(mainFrame, "No pending hospitals to approve/reject.", "Information", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    String[] hospitalNames = pendingHospitals.stream()
        .map(Hospital::getName)
        .toArray(String[]::new);

    String selectedHospitalName = (String) JOptionPane.showInputDialog(
        mainFrame,
        "Select a hospital to approve/reject:",
        "Approve/Reject Hospital",
        JOptionPane.QUESTION_MESSAGE,
        null,
        hospitalNames,
        hospitalNames[0]
    );

    if (selectedHospitalName != null) {
        Hospital selectedHospital = pendingHospitals.stream()
            .filter(h -> h.getName().equals(selectedHospitalName))
            .findFirst()
            .orElse(null);

        if (selectedHospital != null) {
            int choice = JOptionPane.showOptionDialog(
                mainFrame,
                "Approve or Reject " + selectedHospital.getName() + "?",
                "Approve/Reject Hospital",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Approve", "Reject", "Cancel"},
                "Approve"
            );

            if (choice == 0) { // Approve
                selectedHospital.setVerified(true);
                JOptionPane.showMessageDialog(mainFrame, "Hospital approved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (choice == 1) { // Reject
                database.removeHospital(selectedHospital); // You must have this method in your Database class
                JOptionPane.showMessageDialog(mainFrame, "Hospital rejected and removed!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

    private void createAdminPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220)); // Beige nude background
        
        // Header
        JLabel headerLabel = new JLabel(" ADMIN PANEL", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(139, 69, 19)); // Saddle brown for better visibility
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1, 20, 20));
        buttonsPanel.setBackground(new Color(245, 245, 220)); // Beige nude background
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 150, 30, 150));
        
        JButton viewVerifiedBtn = createModernButton(" View Verified Hospitals", new Color(60, 179, 113));
        JButton viewPendingBtn = createModernButton(" View Pending Hospitals", new Color(255, 140, 0));
        JButton approveRejectBtn = createModernButton(" Approve/Reject Hospital", new Color(138, 43, 226));
        JButton logoutBtn = createModernButton(" Logout", new Color(128, 128, 128));
        
        // Add action listeners
        viewVerifiedBtn.addActionListener(e -> {
            ArrayList<Hospital> hospitals = database.getVerifiedHospitals();
            showHospitalsDialog(hospitals, "Verified Hospitals");
        });
        
        viewPendingBtn.addActionListener(e -> {
            ArrayList<Hospital> hospitals = database.getPendingHospitals();
            showHospitalsDialog(hospitals, "Pending Hospitals");
        });
        
       approveRejectBtn.addActionListener(e -> showApproveRejectDialog());
        logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, MAIN_MENU));
        
        buttonsPanel.add(viewVerifiedBtn);
        buttonsPanel.add(viewPendingBtn);
        buttonsPanel.add(approveRejectBtn);
        buttonsPanel.add(logoutBtn);
        
        panel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(panel, ADMIN_PANEL);
    }

    private void showHospitalsDialog(ArrayList<Hospital> hospitals, String title) {
        if (hospitals.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "No hospitals found!", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Create table model
        String[] columnNames = {"Name", "District", "Address", "Economy Beds", "VIP Beds", "ICU Beds", "Emergency Contact", "Customer Care", "Verified"};
        Object[][] data = new Object[hospitals.size()][9];
        
        for (int i = 0; i < hospitals.size(); i++) {
            Hospital h = hospitals.get(i);
            data[i][0] = h.getName();
            data[i][1] = h.getDistrict();
            data[i][2] = h.getAddress();
            data[i][3] = h.getAvailableEconomyBeds() + "/" + h.getTotalEconomyBeds();
            data[i][4] = h.getAvailableVipBeds() + "/" + h.getTotalVipBeds();
            data[i][5] = h.getAvailableIcuBeds() + "/" + h.getTotalIcuBeds();
            data[i][6] = h.getEmergencyContact();
            data[i][7] = h.getCustomerCareContact();
            data[i][8] = h.isVerified() ? "Yes" : "No";
        }
        
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        
        // Improve table appearance and column widths
        table.setRowHeight(25); // Increase row height for better readability
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        // Set column widths to prevent truncation
        table.getColumnModel().getColumn(0).setPreferredWidth(200); // Name - wider
        table.getColumnModel().getColumn(1).setPreferredWidth(80);  // District
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Address
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Economy Beds
        table.getColumnModel().getColumn(4).setPreferredWidth(80);  // VIP Beds
        table.getColumnModel().getColumn(5).setPreferredWidth(80);  // ICU Beds
        table.getColumnModel().getColumn(6).setPreferredWidth(120); // Emergency Contact
        table.getColumnModel().getColumn(7).setPreferredWidth(120); // Customer Care
        table.getColumnModel().getColumn(8).setPreferredWidth(70);  // Verified
        
        // Enable text wrapping for long names
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setToolTipText(value != null ? value.toString() : ""); // Show full text on hover
                }
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // Increased size
        
        JOptionPane.showMessageDialog(mainFrame, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showHospitalFullDetails() {
        // Show a dialog to select hospital
        String[] hospitalNames = database.getAllHospitalNames();
        if (hospitalNames.length == 0) {
            JOptionPane.showMessageDialog(mainFrame, "No hospitals found!", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String selectedHospital = (String) JOptionPane.showInputDialog(
            mainFrame,
            "Select a hospital to view details:",
            "View Hospital Details",
            JOptionPane.QUESTION_MESSAGE,
            null,
            hospitalNames,
            hospitalNames[0]
        );
        
        if (selectedHospital != null) {
            Hospital hospital = database.findHospitalByName(selectedHospital);
            if (hospital != null) {
                showSingleHospitalDetails(hospital);
            }
        }
    }

    private void showSingleHospitalDetails(Hospital hospital) {
        // Create a formatted details panel instead of plain text
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.setBackground(new Color(248, 250, 252));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel headerLabel = new JLabel(" " + hospital.getName(), SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(new Color(25, 25, 112));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        detailsPanel.add(headerLabel, BorderLayout.NORTH);
        
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(new Color(248, 250, 252));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Basic Information Section
        addDetailRow(contentPanel, " License Number:", hospital.getLicenseNumber(), gbc, 0);
        addDetailRow(contentPanel, " District:", hospital.getDistrict(), gbc, 1);
        addDetailRow(contentPanel, " Address:", hospital.getAddress(), gbc, 2);
        addDetailRow(contentPanel, " Username:", hospital.getUsername(), gbc, 3);
        
        // Bed Information Section
        addDetailRow(contentPanel, " Economy Beds:", 
            hospital.getAvailableEconomyBeds() + "/" + hospital.getTotalEconomyBeds(), gbc, 4);
        addDetailRow(contentPanel, " VIP Beds:", 
            hospital.getAvailableVipBeds() + "/" + hospital.getTotalVipBeds(), gbc, 5);
        addDetailRow(contentPanel, " ICU Beds:", 
            hospital.getAvailableIcuBeds() + "/" + hospital.getTotalIcuBeds(), gbc, 6);
        
        // Contact Information Section
        addDetailRow(contentPanel, " Emergency Contact:", hospital.getEmergencyContact(), gbc, 7);
        addDetailRow(contentPanel, " Customer Care:", hospital.getCustomerCareContact(), gbc, 8);
        
        // Status
        addDetailRow(contentPanel, " Status:", 
            hospital.isVerified() ? "Verified" : "Pending Approval", gbc, 9);
        
        contentPanel.add(Box.createVerticalGlue(), gbc);
        detailsPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Show in a custom dialog with better sizing
        JDialog dialog = new JDialog(mainFrame, "Hospital Details", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(detailsPanel, BorderLayout.CENTER);
        
        // Add close button
        JButton closeBtn = createModernButton("Close", new Color(128, 128, 128));
        closeBtn.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(248, 250, 252));
        buttonPanel.add(closeBtn);
        detailsPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setSize(700, 700);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }
    
    private void addDetailRow(JPanel panel, String label, String value, GridBagConstraints gbc, int row) {
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelComponent.setForeground(new Color(55, 65, 81));
        
        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        valueComponent.setForeground(new Color(31, 41, 55));
        valueComponent.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        valueComponent.setPreferredSize(new Dimension(220, 22));

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(labelComponent, gbc);
        
        gbc.gridx = 1;
        panel.add(valueComponent, gbc);
    }

    public void show() {
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show the Swing application
        SwingUtilities.invokeLater(() -> {
            MedoraSwing app = new MedoraSwing();
            app.show();
        });
    }
} 