import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// Database class - handles all data storage and retrieval
public class Database implements Serializable {
    private static final long serialVersionUID = 5L;
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Hospital> hospitals = new ArrayList<>();
    private Admin admin;
    
    // File names for saving data
    private static final String PATIENTS_FILE = "patients.json";
    private static final String HOSPITALS_FILE = "hospitals.json";

    public Database() {
        // Create default admin account
        admin = new Admin("admin", "admin12345");

        // Load existing data
        loadPatients();
        loadHospitals();

        // If no hospitals exist, create some default ones
        if (hospitals.isEmpty()) {
            createDefaultHospitals();
        }
    }

    // Create default hospitals with hardcoded data
    private void createDefaultHospitals() {
        createFallbackHospitals();
    }

    // Parse JSON content and create hospital objects
    private void parseHospitalJson(String jsonContent) {
        try {
            // Remove whitespace and brackets
            jsonContent = jsonContent.trim().substring(1, jsonContent.length() - 1);
            
            // Split by hospital objects
            String[] hospitalObjects = jsonContent.split("\\},\\s*\\{");
            
            for (int i = 0; i < hospitalObjects.length; i++) {
                String hospitalJson = hospitalObjects[i];
                
                // Clean up the JSON object
                if (i == 0) {
                    hospitalJson = hospitalJson.substring(1); // Remove leading {
                }
                if (i == hospitalObjects.length - 1) {
                    hospitalJson = hospitalJson.substring(0, hospitalJson.length() - 1); // Remove trailing }
                }
                
                Hospital hospital = parseHospitalObject(hospitalJson);
                if (hospital != null) {
                    hospital.setVerified(true); // Make default hospitals verified
                    hospitals.add(hospital);
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing hospital JSON: " + e.getMessage());
            createFallbackHospitals();
        }
    }

    // Parse individual hospital JSON object
    private Hospital parseHospitalObject(String hospitalJson) {
        try {
            String name = extractJsonValue(hospitalJson, "name");
            String code = extractJsonValue(hospitalJson, "code");
            String district = extractJsonValue(hospitalJson, "district");
            String address = extractJsonValue(hospitalJson, "address");
            String username = extractJsonValue(hospitalJson, "username");
            String password = extractJsonValue(hospitalJson, "password");
            int totalBeds = Integer.parseInt(extractJsonValue(hospitalJson, "totalBeds"));
            int generalBeds = Integer.parseInt(extractJsonValue(hospitalJson, "generalBeds"));
            int icuBeds = Integer.parseInt(extractJsonValue(hospitalJson, "icuBeds"));
            String phone1 = extractJsonValue(hospitalJson, "phone1");
            String phone2 = extractJsonValue(hospitalJson, "phone2");
            boolean isGovernment = Boolean.parseBoolean(extractJsonValue(hospitalJson, "isGovernment"));
            boolean isActive = Boolean.parseBoolean(extractJsonValue(hospitalJson, "isActive"));

            return new Hospital(name, code, district, address, username, password,
                    totalBeds, generalBeds, icuBeds, phone1, phone2, isGovernment, isActive);
        } catch (Exception e) {
            System.err.println("Error parsing hospital object: " + e.getMessage());
            return null;
        }
    }

    // Extract value from JSON string
    private String extractJsonValue(String json, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*";
        int startIndex = json.indexOf(pattern);
        if (startIndex == -1) return "";
        
        startIndex += pattern.length();
        
        // Handle string values (enclosed in quotes)
        if (json.charAt(startIndex) == '"') {
            startIndex++; // Skip opening quote
            int endIndex = json.indexOf('"', startIndex);
            return json.substring(startIndex, endIndex);
        } else {
            // Handle numeric/boolean values
            int endIndex = json.indexOf(',', startIndex);
            if (endIndex == -1) endIndex = json.length();
            return json.substring(startIndex, endIndex).trim();
        }
    }

    // Fallback method with hardcoded data
    private void createFallbackHospitals() {
        String[][] hospitalData = {
            {"Dhaka Medical College Hospital", "HOSP-DHK-123456", "Dhaka", "Shahbag, Dhaka", "dmch", "hospital12345", "200", "50", "30", "+8801712345678", "+8801712345679", "true", "true"},
            {"Apollo Hospital Dhaka", "HOSP-DHK-123457", "Dhaka", "Bashundhara, Dhaka", "apollo", "hospital12345", "150", "80", "40", "+8801712345680", "+8801712345681", "true", "true"},
            {"Square Hospital", "HOSP-DHK-123458", "Dhaka", "Panthapath, Dhaka", "square", "hospital12345", "180", "60", "35", "+8801712345682", "+8801712345683", "true", "true"},
            {"Cumilla Medical Center", "HOSP-CML-123459", "Cumilla", "Racecourse, Cumilla", "cumillamed", "hospital12345", "100", "30", "20", "+8801712345684", "+8801712345685", "true", "true"},
            {"Moon Hospital Cumilla", "HOSP-CML-123460", "Cumilla", "Jhawtala, Cumilla", "moonhosp", "hospital12345", "120", "40", "25", "+8801712345686", "+8801712345687", "true", "true"}
        };

        for (String[] data : hospitalData) {
            Hospital hospital = new Hospital(
                data[0], data[1], data[2], data[3], data[4], data[5],
                Integer.parseInt(data[6]), Integer.parseInt(data[7]), Integer.parseInt(data[8]),
                data[9], data[10], Boolean.parseBoolean(data[11]), Boolean.parseBoolean(data[12])
            );
            hospital.setVerified(true);
            hospitals.add(hospital);
        }
        saveHospitals();
    }

    // Save patients to JSON file
    private void savePatients() {
        try {
            String json = JsonUtil.patientsToJson(patients);
            JsonUtil.saveToFile(json, PATIENTS_FILE);
        } catch (Exception e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }

    // Load patients from JSON file
    private void loadPatients() {
        try {
            String jsonContent = JsonUtil.loadFromFile(PATIENTS_FILE);
            if (!jsonContent.isEmpty()) {
                patients = JsonUtil.jsonToPatients(jsonContent);
            } else {
                patients = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Error loading patients: " + e.getMessage());
            patients = new ArrayList<>();
        }
    }

    // Save hospitals to JSON file
    private void saveHospitals() {
        try {
            String json = JsonUtil.hospitalsToJson(hospitals);
            JsonUtil.saveToFile(json, HOSPITALS_FILE);
        } catch (Exception e) {
            System.out.println("Error saving hospitals: " + e.getMessage());
        }
    }

    // Load hospitals from JSON file
    private void loadHospitals() {
        try {
            String jsonContent = JsonUtil.loadFromFile(HOSPITALS_FILE);
            if (!jsonContent.isEmpty()) {
                hospitals = JsonUtil.jsonToHospitals(jsonContent);
            } else {
                hospitals = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Error loading hospitals: " + e.getMessage());
            hospitals = new ArrayList<>();
        }
    }

    // Add a new patient
    public boolean addPatient(Patient patient) {
        if (findPatient(patient.getUsername(), null) != null) {
            return false; // Username already exists
        }
        patients.add(patient);
        savePatients();
        return true;
    }

    // Add a new hospital
    public boolean addHospital(Hospital hospital) {
        if (findHospital(hospital.getUsername(), null) != null) {
            return false; // Username already exists
        }
        hospitals.add(hospital);
        saveHospitals();
        return true;
    }

    // Update hospital bed counts
    public boolean updateHospitalBeds(Hospital hospital, int availableEconomyBeds, int availableVipBeds, int availableIcuBeds) {
        boolean success = hospital.setAvailableEconomyBeds(availableEconomyBeds) &&
                hospital.setAvailableVipBeds(availableVipBeds) &&
                hospital.setAvailableIcuBeds(availableIcuBeds);
        if (success) {
            saveHospitals();
        }
        return success;
    }

    // Update hospital total bed counts
    public boolean updateHospitalTotalBeds(Hospital hospital, int totalEconomyBeds, int totalVipBeds, int totalIcuBeds) {
        boolean success = hospital.setTotalEconomyBeds(totalEconomyBeds) &&
                hospital.setTotalVipBeds(totalVipBeds) &&
                hospital.setTotalIcuBeds(totalIcuBeds);
        if (success) {
            saveHospitals();
        }
        return success;
    }

    // Update hospital contact information
    public void updateHospitalContacts(Hospital hospital, String emergencyContact, String customerCareContact) {
        hospital.setEmergencyContact(emergencyContact);
        hospital.setCustomerCareContact(customerCareContact);
        saveHospitals();
    }

    // Approve a hospital
    public void approveHospital(Hospital hospital) {
        hospital.setVerified(true);
        saveHospitals();
    }

    // Reject a hospital
    public void rejectHospital(Hospital hospital) {
        hospitals.remove(hospital);
        saveHospitals();
    }

// REMOVE HOSPITAL METHOD - add here!
    public boolean removeHospital(Hospital hospital) {
        boolean removed = hospitals.remove(hospital);
        if (removed) saveHospitals();
        return removed;
    }

    // Find a patient by username and password
    public Patient findPatient(String username, String password) {
        for (Patient patient : patients) {
            if (patient.getUsername().equals(username) && (password == null || patient.checkPassword(password))) {
                return patient;
            }
        }
        return null;
    }

    // Find a hospital by username and password
    public Hospital findHospital(String username, String password) {
        for (Hospital hospital : hospitals) {
            if (hospital.getUsername().equals(username) && (password == null || hospital.checkPassword(password))) {
                return hospital;
            }
        }
        return null;
    }

    // Get admin account
    public Admin getAdmin(String username, String password) {
        return (admin.getUsername().equals(username) && admin.checkPassword(password)) ? admin : null;
    }

    // Get hospitals by district
    public ArrayList<Hospital> getHospitalsByDistrict(String district) {
        ArrayList<Hospital> result = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            if (hospital.getDistrict().equalsIgnoreCase(district) && hospital.isVerified()) {
                result.add(hospital);
            }
        }
        return result;
    }

    // Search hospitals by name
    public ArrayList<Hospital> searchHospitalsByName(String name) {
        ArrayList<Hospital> result = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            if (hospital.getName().toLowerCase().contains(name.toLowerCase()) && hospital.isVerified()) {
                result.add(hospital);
            }
        }
        return result;
    }

    // Get hospitals waiting for approval
    public ArrayList<Hospital> getPendingHospitals() {
        ArrayList<Hospital> result = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            if (!hospital.isVerified()) {
                result.add(hospital);
            }
        }
        return result;
    }

    // Get approved hospitals
    public ArrayList<Hospital> getVerifiedHospitals() {
        ArrayList<Hospital> result = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            if (hospital.isVerified()) {
                result.add(hospital);
            }
        }
        return result;
    }

    // Get all hospital names
    public String[] getAllHospitalNames() {
        String[] names = new String[hospitals.size()];
        for (int i = 0; i < hospitals.size(); i++) {
            names[i] = hospitals.get(i).getName();
        }
        return names;
    }

    // Find hospital by name
    public Hospital findHospitalByName(String name) {
        for (Hospital hospital : hospitals) {
            if (hospital.getName().equals(name)) {
                return hospital;
            }
        }
        return null;
    }
} 