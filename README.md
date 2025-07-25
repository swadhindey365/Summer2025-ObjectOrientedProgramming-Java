# Medora Java OOP Project

Medora is a hospital information and management system demonstrating Java OOP principles.

A JavaFX-based hospital information and management system with three user roles: User, Hospital, and Admin.

## 📋 Features

### 👤 User Panel
- User login and registration
- SIM provider selection
- Contact information management

### 🏥 Hospital Panel (Multi-step Registration)
- **Step 1**: Basic hospital information (name, license, district, address)
- **Step 2**: Account credentials (username, password)
- **Step 3**: Bed information, contact numbers, and service agreements
- Dynamic emergency and customer care number management
- License number validation (DGHS-PHI-YYYY-XXXX format)

### 🛡️ Admin Panel
- Admin authentication
- System management interface

## 🗂️ Project Structure

```
hospiMate/
├── Main.java                          # Application entry point
├── controllers/                       # FXML controllers
│   ├── HomeController.java
│   ├── UserController.java
│   ├── HospitalRegisterStep1Controller.java
│   ├── HospitalRegisterStep2Controller.java
│   ├── HospitalRegisterStep3Controller.java
│   └── AdminController.java
├── models/                           # Data models
│   └── HospitalRegistrationData.java
├── views/                           # FXML UI files
│   ├── Home.fxml
│   ├── User.fxml
│   ├── HospitalRegisterStep1.fxml
│   ├── HospitalRegisterStep2.fxml
│   ├── HospitalRegisterStep3.fxml
│   └── Admin.fxml
└── data/                           # Data storage
    ├── users.txt
    ├── hospitals.txt
    ├── admin.txt
    └── logs.txt
```

## 🚀 Setup Instructions

### Prerequisites
- Java 11 or higher
- JavaFX SDK (included with Java 11+)

### Running the Application

1. **Compile the project:**
   ```bash
   javac -cp "path/to/javafx-sdk/lib/*" *.java controllers/*.java models/*.java
   ```

2. **Run the application:**
   ```bash
   java -cp ".;path/to/javafx-sdk/lib/*" Main
   ```

   **For Linux/Mac:**
   ```bash
   java -cp ".:path/to/javafx-sdk/lib/*" Main
   ```

### Using an IDE (Recommended)

1. **IntelliJ IDEA:**
   - Open the project
   - Add JavaFX SDK to project libraries
   - Run `Main.java`

2. **Eclipse:**
   - Import as Java project
   - Add JavaFX to build path
   - Run `Main.java`

3. **VS Code:**
   - Install Java Extension Pack
   - Add JavaFX to classpath
   - Run `Main.java`

## 🎯 Usage Guide

### Starting the Application
1. Run `Main.java`
2. The home screen will appear with three role options

### User Registration/Login
1. Click "User" button
2. Fill in registration form or login credentials
3. Select SIM provider from dropdown
4. Submit registration or login

### Hospital Registration
1. Click "Hospital" button
2. **Step 1**: Enter hospital details
   - Hospital name
   - License number (format: DGHS-PHI-YYYY-XXXX)
   - Select district from dropdown
   - Enter address
3. **Step 2**: Create account credentials
   - Username and password
   - Password confirmation
4. **Step 3**: Complete registration
   - Set bed counts (Economy, VIP, ICU)
   - Add emergency numbers (use + and - buttons)
   - Add customer care numbers
   - Agree to service terms
   - Submit registration

### Admin Access
1. Click "Admin" button
2. Use demo credentials:
   - Username: `admin`
   - Password: `admin123`

## 🎨 UI Features

- **Modern Design**: Clean, professional interface with gradient backgrounds
- **Responsive Layout**: Adapts to different screen sizes
- **Organized Sections**: TitledPane components for better organization
- **Dynamic Forms**: Add/remove contact numbers dynamically
- **Validation**: Input validation with user-friendly error messages
- **Navigation**: Seamless screen transitions with proper back navigation

## 🔧 Technical Details

### FXML Structure
- Uses VBox, HBox, and GridPane for responsive layouts
- TitledPane for organized sections
- Proper fx:id attributes for controller binding
- CSS styling for modern appearance

### Controller Logic
- Proper separation of concerns
- Data validation and error handling
- Scene navigation with data passing
- Dynamic UI component management

### Data Model
- `HospitalRegistrationData` class for multi-step form data
- Proper encapsulation with getters/setters
- Utility methods for data management

## 🐛 Known Issues

- Linter errors due to missing JavaFX dependencies in IDE
- These are expected and will resolve when JavaFX is properly configured

## 🔮 Future Enhancements

- Database integration
- User authentication system
- Hospital dashboard
- Admin management interface
- Data persistence
- Report generation
- Email notifications

## 📝 License

This project is created for educational purposes.

## 🤝 Contributing

Feel free to submit issues and enhancement requests!

---

**Note**: This is a frontend prototype. Backend integration and data persistence need to be implemented for production use. 