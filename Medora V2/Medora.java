import java.util.ArrayList;
import java.util.Scanner;

// Main application class
public class Medora {
    private static Database database = new Database();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Medora Hospital Management System!");
        
        try {
            mainMenu();
        } finally {
            scanner.close();
        }
    }

    // Main menu - shows all options
    private static void mainMenu() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("=".repeat(50));
            System.out.println("1. Patient Login");
            System.out.println("2. Patient Register");
            System.out.println("3. Hospital Login");
            System.out.println("4. Hospital Register");
            System.out.println("5. Admin Login");
            System.out.println("6. Exit");
            System.out.println("-".repeat(50));
            System.out.print("Choose an option (1-6): ");

            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    patientLogin();
                    break;
                case "2":
                    patientRegister();
                    break;
                case "3":
                    hospitalLogin();
                    break;
                case "4":
                    hospitalRegister();
                    break;
                case "5":
                    adminLogin();
                    break;
                case "6":
                    System.out.println("Thank you for using Medora! Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please choose 1-6.");
            }
        }
    }

    // Patient registration
    private static void patientRegister() {
        System.out.println("\nPATIENT REGISTRATION");
        System.out.println("-".repeat(30));
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty!");
            return;
        }
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Enter phone number (+8801XXXXXXXXX): ");
        String phone = scanner.nextLine().trim();
        
        System.out.print("Enter password (min 8 characters): ");
        String password = scanner.nextLine();

        // Validate input
        if (!Patient.isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return;
        }
        if (!Patient.isValidPhoneNumber(phone)) {
            System.out.println("Invalid phone number format!");
            return;
        }
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters!");
            return;
        }

        Patient patient = new Patient(username, password, email, phone);
        if (database.addPatient(patient)) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Username already exists! Try a different username.");
        }
    }

    // Patient login
    private static void patientLogin() {
        System.out.println("\nPATIENT LOGIN");
        System.out.println("-".repeat(20));
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Patient patient = database.findPatient(username, password);
        if (patient == null) {
            System.out.println("Invalid username or password!");
            return;
        }

        System.out.println("Login successful! Welcome, " + username + "!");
        patientMenu();
    }

    // Patient menu after login
    private static void patientMenu() {
        while (true) {
            System.out.println("\nPATIENT PANEL");
            System.out.println("-".repeat(20));
            System.out.println("1. Find Hospitals by District");
            System.out.println("2. Search Hospitals by Name");
            System.out.println("3. Logout");
            System.out.print("Choose an option (1-3): ");

            String choice = scanner.nextLine().trim();
            
            if (choice.equals("3")) break;

            switch (choice) {
                case "1":
                    findHospitalsByDistrict();
                    break;
                case "2":
                    searchHospitalsByName();
                    break;
                default:
                    System.out.println("Invalid option! Please choose 1-3.");
            }
        }
    }

    // Find hospitals by district
    private static void findHospitalsByDistrict() {
        System.out.print("Enter district name: ");
        String district = scanner.nextLine().trim();
        if (district.isEmpty()) {
            System.out.println("District cannot be empty!");
            return;
        }
        
        ArrayList<Hospital> hospitals = database.getHospitalsByDistrict(district);
        showHospitals(hospitals);
    }

    // Search hospitals by name
    private static void searchHospitalsByName() {
        System.out.print("Enter hospital name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Hospital name cannot be empty!");
            return;
        }
        
        ArrayList<Hospital> hospitals = database.searchHospitalsByName(name);
        showHospitals(hospitals);
    }

    // Hospital registration
    private static void hospitalRegister() {
        System.out.println("\nHOSPITAL REGISTRATION");
        System.out.println("-".repeat(30));
        
        System.out.print("Hospital name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Hospital name cannot be empty!");
            return;
        }
        
        System.out.print("License number (HOSP-XXX-123456): ");
        String license = scanner.nextLine().trim();
        
        System.out.print("District: ");
        String district = scanner.nextLine().trim();
        if (district.isEmpty()) {
            System.out.println("District cannot be empty!");
            return;
        }
        
        System.out.print("Address: ");
        String address = scanner.nextLine().trim();
        if (address.isEmpty()) {
            System.out.println("Address cannot be empty!");
            return;
        }
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty!");
            return;
        }
        
        System.out.print("Password (min 8 characters): ");
        String password = scanner.nextLine();
        
        // Get bed counts
        int totalEconomyBeds = getIntInput("Total economy beds: ");
        int totalVipBeds = getIntInput("Total VIP beds: ");
        int totalIcuBeds = getIntInput("Total ICU beds: ");
        
        System.out.print("Emergency contact: ");
        String emergency = scanner.nextLine().trim();
        
        System.out.print("Customer care contact: ");
        String customerCare = scanner.nextLine().trim();
        
        System.out.print("Agree to Quality Healthcare? (y/n): ");
        boolean quality = scanner.nextLine().trim().equalsIgnoreCase("y");
        
        System.out.print("Agree to Transparency? (y/n): ");
        boolean transparency = scanner.nextLine().trim().equalsIgnoreCase("y");

        // Validate input
        if (!Hospital.isValidLicenseNumber(license)) {
            System.out.println("Invalid license number format!");
            return;
        }
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters!");
            return;
        }
        if (totalEconomyBeds < 0 || totalVipBeds < 0 || totalIcuBeds < 0) {
            System.out.println("Bed counts cannot be negative!");
            return;
        }

        Hospital hospital = new Hospital(name, license, district, address, username, password,
                totalEconomyBeds, totalVipBeds, totalIcuBeds, emergency, customerCare, quality, transparency);
        
        if (database.addHospital(hospital)) {
            System.out.println("Registration successful! Waiting for admin approval.");
        } else {
            System.out.println("Username already exists! Try a different username.");
        }
    }

    // Hospital login
    private static void hospitalLogin() {
        System.out.println("\nHOSPITAL LOGIN");
        System.out.println("-".repeat(20));
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Hospital hospital = database.findHospital(username, password);
        if (hospital == null) {
            System.out.println("Invalid username or password!");
            return;
        }

        if (!hospital.isVerified()) {
            System.out.println("Your hospital is waiting for admin approval.");
            return;
        }

        System.out.println("Login successful! Welcome, " + hospital.getName() + "!");
        hospitalMenu(hospital);
    }

    // Hospital menu after login
    private static void hospitalMenu(Hospital hospital) {
        while (true) {
            System.out.println("\nHOSPITAL PANEL");
            System.out.println("-".repeat(20));
            System.out.println("1. Update Available Beds");
            System.out.println("2. Update Total Beds");
            System.out.println("3. Update Contact Information");
            System.out.println("4. View Details");
            System.out.println("5. Logout");
            System.out.print("Choose an option (1-5): ");

            String choice = scanner.nextLine().trim();
            
            if (choice.equals("5")) break;

            switch (choice) {
                case "1":
                    updateAvailableBeds(hospital);
                    break;
                case "2":
                    updateTotalBeds(hospital);
                    break;
                case "3":
                    updateContactInfo(hospital);
                    break;
                case "4":
                    hospital.showDetails();
                    break;
                default:
                    System.out.println("Invalid option! Please choose 1-5.");
            }
        }
    }

    // Update available beds
    private static void updateAvailableBeds(Hospital hospital) {
        System.out.println("\nUPDATE AVAILABLE BEDS");
        System.out.println("-".repeat(25));
        
        int availableEconomyBeds = getIntInput("Available economy beds (max " + hospital.getTotalEconomyBeds() + "): ");
        int availableVipBeds = getIntInput("Available VIP beds (max " + hospital.getTotalVipBeds() + "): ");
        int availableIcuBeds = getIntInput("Available ICU beds (max " + hospital.getTotalIcuBeds() + "): ");
        
        if (database.updateHospitalBeds(hospital, availableEconomyBeds, availableVipBeds, availableIcuBeds)) {
            System.out.println("Available beds updated successfully!");
        } else {
            System.out.println("Invalid bed counts! Available beds cannot exceed total beds or be negative.");
        }
    }

    // Update total beds
    private static void updateTotalBeds(Hospital hospital) {
        System.out.println("\nUPDATE TOTAL BEDS");
        System.out.println("-".repeat(20));
        
        int totalEconomyBeds = getIntInput("Total economy beds (current: " + hospital.getTotalEconomyBeds() + "): ");
        int totalVipBeds = getIntInput("Total VIP beds (current: " + hospital.getTotalVipBeds() + "): ");
        int totalIcuBeds = getIntInput("Total ICU beds (current: " + hospital.getTotalIcuBeds() + "): ");
        
        if (database.updateHospitalTotalBeds(hospital, totalEconomyBeds, totalVipBeds, totalIcuBeds)) {
            System.out.println("Total beds updated successfully!");
        } else {
            System.out.println("Invalid total bed counts! Total beds cannot be less than available beds or be negative.");
        }
    }

    // Update contact information
    private static void updateContactInfo(Hospital hospital) {
        System.out.println("\nUPDATE CONTACT INFORMATION");
        System.out.println("-".repeat(30));
        
        System.out.print("Emergency contact: ");
        String emergencyContact = scanner.nextLine().trim();
        
        System.out.print("Customer care contact: ");
        String customerCareContact = scanner.nextLine().trim();
        
        database.updateHospitalContacts(hospital, emergencyContact, customerCareContact);
        System.out.println("Contact information updated successfully!");
    }

    // Admin login
    private static void adminLogin() {
        System.out.println("\nADMIN LOGIN");
        System.out.println("-".repeat(20));
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Admin admin = database.getAdmin(username, password);
        if (admin == null) {
            System.out.println("Invalid admin credentials!");
            return;
        }

        System.out.println("Admin login successful!");
        adminMenu();
    }

    // Admin menu after login
    private static void adminMenu() {
        while (true) {
            System.out.println("\nADMIN PANEL");
            System.out.println("-".repeat(20));
            System.out.println("1. View Verified Hospitals");
            System.out.println("2. View Pending Hospitals");
            System.out.println("3. Approve/Reject Hospital");
            System.out.println("4. Logout");
            System.out.print("Choose an option (1-4): ");

            String choice = scanner.nextLine().trim();
            
            if (choice.equals("4")) break;

            switch (choice) {
                case "1":
                    showHospitals(database.getVerifiedHospitals());
                    break;
                case "2":
                    showHospitals(database.getPendingHospitals());
                    break;
                case "3":
                    approveRejectHospital();
                    break;
                default:
                    System.out.println("Invalid option! Please choose 1-4.");
            }
        }
    }

    // Approve or reject a hospital
    private static void approveRejectHospital() {
        ArrayList<Hospital> pending = database.getPendingHospitals();
        if (pending.isEmpty()) {
            System.out.println("No hospitals waiting for approval!");
            return;
        }
        
        System.out.println("\nPENDING HOSPITALS:");
        System.out.println("-".repeat(25));
        for (int i = 0; i < pending.size(); i++) {
            System.out.println((i + 1) + ". " + pending.get(i).getName() + " (" + pending.get(i).getLicenseNumber() + ")");
        }
        
        int index = getIntInput("Select hospital number: ") - 1;
        if (index >= 0 && index < pending.size()) {
            Hospital hospital = pending.get(index);
            hospital.showDetails();
            
            System.out.print("Approve this hospital? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                database.approveHospital(hospital);
                System.out.println("Hospital approved successfully!");
            } else {
                database.rejectHospital(hospital);
                System.out.println("Hospital rejected and removed.");
            }
        } else {
            System.out.println("Invalid selection!");
        }
    }

    // Show list of hospitals
    private static void showHospitals(ArrayList<Hospital> hospitals) {
        if (hospitals.isEmpty()) {
            System.out.println("No hospitals found!");
            return;
        }
        
        System.out.println("\nHOSPITALS FOUND: " + hospitals.size());
        System.out.println("-".repeat(30));
        
        for (Hospital hospital : hospitals) {
            hospital.showDetails();
        }
    }

    // Helper method to get integer input
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
} 