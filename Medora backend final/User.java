import java.io.Serializable;

// Simple User class - stores basic user info
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
} 