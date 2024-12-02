import java.io.*;
import java.util.Scanner;

public class TicketingSystemCLI {
    static Configuration config;
    static boolean isSystemRunning = false;
    static boolean isConfigured = false;  // Flag to check if the system has been configured

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Main Menu
        while (!exit) {
            try {
                System.out.println("\n==========================================");
                System.out.println("        Real-Time Ticketing System");
                System.out.println("============================================");
                System.out.println("1. Start Ticketing System");
                System.out.println("2. Stop Ticketing System");
                System.out.println("3. View Real-Time Status");
                System.out.println("4. View Logs");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        startTicketingSystem(scanner); // Starts the system and collects configuration
                        break;
                    case 2:
                        stopTicketingSystem(); // Stops the system and resets configuration
                        break;
                    case 3:
                        viewRealTimeStatus(); // View system status if running
                        break;
                    case 4:
                        viewLog(); // View logs
                        break;
                    default:
                        System.out.println("Invalid choice! Please select a valid option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input! Please enter a valid menu option (1 - 4)");
                scanner.next(); // Clear the invalid input
            }
        }
        scanner.close();
    }

    // Method to start the ticketing system and collect configuration values
    public static void startTicketingSystem(Scanner scanner) {
        if (isConfigured) {
            System.out.println("\nThe system is already configured. You cannot reconfigure it until the system is stopped.");
            return;
        }

        System.out.println("\nStarting the Ticketing System...");

        // Collect system configuration parameters from user input
        int totalTickets = getValidInput(scanner, "Enter total number of tickets: ");
        int ticketReleaseRate = getValidInput(scanner, "Enter ticket release rate (tickets per minute): ");
        int customerRetrievalRate = getValidInput(scanner, "Enter customer retrieval rate (tickets per minute): ");
        int maxTicketCapacity = getValidInput(scanner, "Enter max ticket capacity: ");

        // Initialize the configuration object with new values
        config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        saveConfigurationToJson(); // Save the new configuration to a new JSON file

        // Display the collected configuration
        System.out.println("\nSystem Configuration:");
        System.out.println("Total Tickets: " + config.getTotalTickets());
        System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
        System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
        System.out.println("Max Ticket Capacity: " + config.getMaxTicketCapacity());

        isSystemRunning = true; // Mark the system as running
        isConfigured = true; // Mark the system as configured
        System.out.println("\nTicketing system has been successfully started!");
    }

    // Method to stop the ticketing system
    public static void stopTicketingSystem() {
        if (!isSystemRunning) {
            System.out.println("\nThe system is not currently running.");
            return;
        }
        System.out.println("\nStopping the Ticketing System.");
        isSystemRunning = false;
        isConfigured = false;
        config = null; // Reset configuration to ensure fresh input next time
        System.out.println("\nTicketing system has been successfully stopped.");
    }

    // Method to view real-time status
    public static void viewRealTimeStatus() {
        if (!isSystemRunning) {
            System.out.println("\nThe system is not running. Start the system first.");
            return;
        }
        System.out.println("\nViewing Real-Time Status...");
    }

    // Method to view logs
    public static void viewLog() {
        if (!isSystemRunning) {
            System.out.println("\nThe system is not running. Start the system first.");
            return;
        }
        System.out.println("\nViewing Logs...");
    }

    // Method to get valid input from the user
    private static int getValidInput(Scanner scanner, String prompt) {
        int input = -1;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();

                // Check if the input is a positive number
                if (input <= 0) {
                    System.out.println("Invalid input! Please enter a positive number greater than 0.");
                } else {
                    valid = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return input;
    }

    // Save the current configuration to a uniquely named JSON file
    public static void saveConfigurationToJson() {
        if (config == null) {
            System.out.println("No configuration to save. Please start the system first.");
            return;
        }
        // Generate a unique file name based on existing files
        String fileName = generateUniqueFileName();
        ConfigurationManager.saveConfigurationToJson(config, fileName); // Save the configuration to a unique file
        System.out.println("Configuration saved to " + fileName);
    }

    // Method to generate a unique file name based on existing files
    private static String generateUniqueFileName() {
        int fileIndex = 1;
        File dir = new File(".");  // Current directory
        String baseFileName = "ConfigurationSettings_";
        String fileExtension = ".json";

        // Loop to find the next available file name
        while (true) {
            String fileName = baseFileName + String.format("%03d", fileIndex) + fileExtension;
            File file = new File(fileName);
            if (!file.exists()) {
                return fileName;  // Return the first non-existing file name
            }
            fileIndex++;  // Increment file index to check the next file
        }
    }
}
