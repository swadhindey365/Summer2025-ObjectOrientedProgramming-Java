import java.util.regex.Pattern;

// Patient class - extends User with patient-specific info
public class Patient extends User {
    private static final long serialVersionUID = 2L;
    private String phoneNumber;
    private String email;

    public Patient(String username, String password, String email, String phoneNumber) {
        super(username, password);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Check if phone number is valid (Bangladesh format)
    public static boolean isValidPhoneNumber(String phone) {
        return Pattern.matches("^\\+8801[3-9]\\d{8}$", phone);
    }

    // Check if email is valid
    public static boolean isValidEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }
} 