import com.google.gson.Gson;
import java.io.*;

public class ConfigurationManager {

    // Save Configuration to a JSON file
    public static void saveConfigurationToJson(Configuration config, String filename) {
        try {
            // Create the file if it doesn't exist
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();  // Create the file if it doesn't exist
            }

            // Write to the file using Gson
            try (FileWriter writer = new FileWriter(filename)) {
                Gson gson = new Gson();
                gson.toJson(config, writer);  // Save configuration to JSON
                System.out.println("Configuration saved to JSON: " + filename);
            }

        } catch (IOException e) {
            System.out.println("Error saving configuration to JSON: " + e.getMessage());
        }
    }

    // Load Configuration from a JSON file
    public static Configuration loadConfigurationFromJson(String filename) {
        try {
            // Check if the file exists before trying to read
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("Configuration file not found.");
                return null;  // Return null if the file doesn't exist
            }

            // Read from the file using Gson
            try (Reader reader = new FileReader(filename)) {
                Gson gson = new Gson();
                return gson.fromJson(reader, Configuration.class);
            }

        } catch (IOException e) {
            System.out.println("Error loading configuration from JSON: " + e.getMessage());
            return null;
        }
    }
}
