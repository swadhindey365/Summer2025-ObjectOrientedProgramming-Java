import java.io.Serializable;
import java.util.regex.Pattern;

// Hospital class - stores hospital information
public class Hospital implements Serializable {
    private static final long serialVersionUID = 3L;
    private String name;
    private String licenseNumber;
    private String district;
    private String address;
    private String username;
    private String password;
    
    // Bed information
    private int totalEconomyBeds;
    private int totalVipBeds;
    private int totalIcuBeds;
    private int availableEconomyBeds;
    private int availableVipBeds;
    private int availableIcuBeds;
    
    // Contact information
    private String emergencyContact;
    private String customerCareContact;
    
    // Agreements and verification
    private boolean qualityAgreement;
    private boolean transparencyAgreement;
    private boolean isVerified;

    public Hospital(String name, String licenseNumber, String district, String address,
                    String username, String password, int totalEconomyBeds, int totalVipBeds,
                    int totalIcuBeds, String emergencyContact, String customerCareContact,
                    boolean qualityAgreement, boolean transparencyAgreement) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.district = district;
        this.address = address;
        this.username = username;
        this.password = password;
        this.totalEconomyBeds = totalEconomyBeds;
        this.totalVipBeds = totalVipBeds;
        this.totalIcuBeds = totalIcuBeds;
        
        // Initially all beds are available
        this.availableEconomyBeds = totalEconomyBeds;
        this.availableVipBeds = totalVipBeds;
        this.availableIcuBeds = totalIcuBeds;
        
        this.emergencyContact = emergencyContact;
        this.customerCareContact = customerCareContact;
        this.qualityAgreement = qualityAgreement;
        this.transparencyAgreement = transparencyAgreement;
        this.isVerified = false; // New hospitals need approval
    }

    // Simple getter methods
    public String getName() { return name; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getDistrict() { return district; }
    public String getAddress() { return address; }
    public String getUsername() { return username; }
    public int getTotalEconomyBeds() { return totalEconomyBeds; }
    public int getTotalVipBeds() { return totalVipBeds; }
    public int getTotalIcuBeds() { return totalIcuBeds; }
    public int getAvailableEconomyBeds() { return availableEconomyBeds; }
    public int getAvailableVipBeds() { return availableVipBeds; }
    public int getAvailableIcuBeds() { return availableIcuBeds; }
    public String getEmergencyContact() { return emergencyContact; }
    public String getCustomerCareContact() { return customerCareContact; }
    public boolean isVerified() { return isVerified; }
    public boolean checkPassword(String password) { return this.password.equals(password); }

    // Setter methods with simple validation
    public void setVerified(boolean verified) { 
        this.isVerified = verified; 
    }

    public boolean setTotalEconomyBeds(int beds) {
        if (beds >= availableEconomyBeds && beds >= 0 && beds <= 10000) {
            this.totalEconomyBeds = beds;
            return true;
        }
        return false;
    }

    public boolean setTotalVipBeds(int beds) {
        if (beds >= availableVipBeds && beds >= 0 && beds <= 10000) {
            this.totalVipBeds = beds;
            return true;
        }
        return false;
    }

    public boolean setTotalIcuBeds(int beds) {
        if (beds >= availableIcuBeds && beds >= 0 && beds <= 10000) {
            this.totalIcuBeds = beds;
            return true;
        }
        return false;
    }

    public boolean setAvailableEconomyBeds(int beds) {
        if (beds >= 0 && beds <= totalEconomyBeds) {
            this.availableEconomyBeds = beds;
            return true;
        }
        return false;
    }

    public boolean setAvailableVipBeds(int beds) {
        if (beds >= 0 && beds <= totalVipBeds) {
            this.availableVipBeds = beds;
            return true;
        }
        return false;
    }

    public boolean setAvailableIcuBeds(int beds) {
        if (beds >= 0 && beds <= totalIcuBeds) {
            this.availableIcuBeds = beds;
            return true;
        }
        return false;
    }

    public void setEmergencyContact(String contact) { 
        this.emergencyContact = contact; 
    }
    
    public void setCustomerCareContact(String contact) { 
        this.customerCareContact = contact; 
    }

    // Check if license number format is correct
    public static boolean isValidLicenseNumber(String license) {
        return Pattern.matches("^HOSP-[A-Z]{3}-\\d{6}$", license);
    }

    // Display hospital information in a nice format
    public void showDetails() {
        System.out.println("\nHospital Details:");
        System.out.println("Name: " + name);
        System.out.println("Address: " + address + ", " + district);
        System.out.println("Total Beds:");
        System.out.println("  - Economy: " + totalEconomyBeds);
        System.out.println("  - VIP: " + totalVipBeds);
        System.out.println("  - ICU: " + totalIcuBeds);
        System.out.println("Available Beds:");
        System.out.println("  - Economy: " + availableEconomyBeds);
        System.out.println("  - VIP: " + availableVipBeds);
        System.out.println("  - ICU: " + availableIcuBeds);
        System.out.println("Emergency Contact: " + emergencyContact);
        System.out.println("Customer Care: " + customerCareContact);
        System.out.println("Verified: " + (isVerified ? "Yes" : "No"));
    }
} 