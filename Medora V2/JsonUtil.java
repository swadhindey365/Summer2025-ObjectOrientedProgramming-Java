import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// Simple JSON utility class for basic serialization/deserialization
public class JsonUtil {
    
    // Convert ArrayList of Patients to JSON
    public static String patientsToJson(ArrayList<Patient> patients) {
        StringBuilder json = new StringBuilder("[\n");
        
        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            json.append("  {")
                .append("\"username\":\"").append(p.getUsername()).append("\",")
                .append("\"password\":\"").append(p.getPassword()).append("\",")
                .append("\"email\":\"").append(p.getEmail()).append("\",")
                .append("\"phoneNumber\":\"").append(p.getPhoneNumber()).append("\"")
                .append("}");
            
            if (i < patients.size() - 1) json.append(",");
            json.append("\n");
        }
        
        return json.append("]").toString();
    }
    
    // Convert ArrayList of Hospitals to JSON
    public static String hospitalsToJson(ArrayList<Hospital> hospitals) {
        StringBuilder json = new StringBuilder("[\n");
        
        for (int i = 0; i < hospitals.size(); i++) {
            Hospital h = hospitals.get(i);
            json.append("  {")
                .append("\"name\":\"").append(h.getName()).append("\",")
                .append("\"licenseNumber\":\"").append(h.getLicenseNumber()).append("\",")
                .append("\"district\":\"").append(h.getDistrict()).append("\",")
                .append("\"address\":\"").append(h.getAddress()).append("\",")
                .append("\"username\":\"").append(h.getUsername()).append("\",")
                .append("\"password\":\"").append(h.getPassword()).append("\",")
                .append("\"totalEconomyBeds\":").append(h.getTotalEconomyBeds()).append(",")
                .append("\"totalVipBeds\":").append(h.getTotalVipBeds()).append(",")
                .append("\"totalIcuBeds\":").append(h.getTotalIcuBeds()).append(",")
                .append("\"availableEconomyBeds\":").append(h.getAvailableEconomyBeds()).append(",")
                .append("\"availableVipBeds\":").append(h.getAvailableVipBeds()).append(",")
                .append("\"availableIcuBeds\":").append(h.getAvailableIcuBeds()).append(",")
                .append("\"emergencyContact\":\"").append(h.getEmergencyContact()).append("\",")
                .append("\"customerCareContact\":\"").append(h.getCustomerCareContact()).append("\",")
                .append("\"isVerified\":").append(h.isVerified())
                .append("}");
            
            if (i < hospitals.size() - 1) json.append(",");
            json.append("\n");
        }
        
        return json.append("]").toString();
    }
    
    // Parse JSON to Patients (simplified)
    public static ArrayList<Patient> jsonToPatients(String json) {
        ArrayList<Patient> patients = new ArrayList<>();
        
        // Simple parsing - split by objects
        String[] objects = json.replace("[", "").replace("]", "").split("\\},\\s*\\{");
        
        for (String obj : objects) {
            obj = obj.replace("{", "").replace("}", "");
            String[] fields = obj.split(",");
            
            String username = "", password = "", email = "", phone = "";
            
            for (String field : fields) {
                String[] keyValue = field.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();
                    
                    switch (key) {
                        case "username": username = value; break;
                        case "password": password = value; break;
                        case "email": email = value; break;
                        case "phoneNumber": phone = value; break;
                    }
                }
            }
            
            if (!username.isEmpty()) {
                patients.add(new Patient(username, password, email, phone));
            }
        }
        
        return patients;
    }
    
    // Parse JSON to Hospitals (simplified)
    public static ArrayList<Hospital> jsonToHospitals(String json) {
        ArrayList<Hospital> hospitals = new ArrayList<>();
        
        // Simple parsing - split by objects
        String[] objects = json.replace("[", "").replace("]", "").split("\\},\\s*\\{");
        
        for (String obj : objects) {
            obj = obj.replace("{", "").replace("}", "");
            String[] fields = obj.split(",");
            
            String name = "", license = "", district = "", address = "";
            String username = "", password = "", emergency = "", care = "";
            int totalEco = 0, totalVip = 0, totalIcu = 0;
            int availEco = 0, availVip = 0, availIcu = 0;
            boolean verified = false;
            
            for (String field : fields) {
                String[] keyValue = field.split(":", 2);
                if (keyValue.length == 2) {
                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();
                    
                    switch (key) {
                        case "name": name = value; break;
                        case "licenseNumber": license = value; break;
                        case "district": district = value; break;
                        case "address": address = value; break;
                        case "username": username = value; break;
                        case "password": password = value; break;
                        case "emergencyContact": emergency = value; break;
                        case "customerCareContact": care = value; break;
                        case "totalEconomyBeds": totalEco = Integer.parseInt(value); break;
                        case "totalVipBeds": totalVip = Integer.parseInt(value); break;
                        case "totalIcuBeds": totalIcu = Integer.parseInt(value); break;
                        case "availableEconomyBeds": availEco = Integer.parseInt(value); break;
                        case "availableVipBeds": availVip = Integer.parseInt(value); break;
                        case "availableIcuBeds": availIcu = Integer.parseInt(value); break;
                        case "isVerified": verified = Boolean.parseBoolean(value); break;
                    }
                }
            }
            
            if (!name.isEmpty()) {
                Hospital h = new Hospital(name, license, district, address, username, password,
                        totalEco, totalVip, totalIcu, emergency, care, true, true);
                h.setAvailableEconomyBeds(availEco);
                h.setAvailableVipBeds(availVip);
                h.setAvailableIcuBeds(availIcu);
                h.setVerified(verified);
                hospitals.add(h);
            }
        }
        
        return hospitals;
    }
    
    // Save to file
    public static void saveToFile(String content, String filename) {
        try {
            Files.write(Paths.get(filename), content.getBytes());
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
    
    // Load from file
    public static String loadFromFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
            return "[]";
        }
    }
}
