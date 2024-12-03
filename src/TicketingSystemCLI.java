import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketingSystemCLI {
    // Global variables to store system configurations and shared resources
    static Configuration config;
    static boolean isSystemRunning = false;
    static boolean isConfigured = false;  // Flag to check if the system has been configured
    static int availableTickets;
    static Lock ticketLock = new ReentrantLock();  // Lock for synchronizing access to available tickets

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
        saveConfigurationToJson(); // Save the new configuration to JSON

        // Initialize available tickets based on the configuration
        availableTickets = totalTickets;

        // Display the collected configuration
        System.out.println("\nSystem Configuration:");
        System.out.println("Total Tickets: " + config.getTotalTickets());
        System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
        System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
        System.out.println("Max Ticket Capacity: " + config.getMaxTicketCapacity());

        isSystemRunning = true; // Mark the system as running
        isConfigured = true; // Mark the system as configured
        System.out.println("\nTicketing system has been successfully started!");

        // Start a few customer threads (for simulation)
        for (int i = 1; i <= 5; i++) { // Simulating 5 customers for example
            new Thread(new TicketConsumer(i, 3, ticketLock, new TicketingSystemCLI())).start(); // Each customer requests 3 tickets
        }
    }


    // Method to stop the ticketing system
    public static void stopTicketingSystem() {
        if (!isSystemRunning) {
            System.out.println("\nThe system is not currently running.");
            return;
        }
        System.out.println("\nStopping the Ticketing System.");
        isSystemRunning = false;
        isConfigured = false; // Reset the configuration status
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

    // Save the current configuration to a JSON file
    public static void saveConfigurationToJson() {
        if (config == null) {
            System.out.println("No configuration to save. Please start the system first.");
            return;
        }
        String configFileName = "config.json";
        ConfigurationManager.saveConfigurationToJson(config, configFileName); // Save the configuration
    }

    // Getter for available tickets (used by TicketConsumer)
    public int getAvailableTickets() {
        return availableTickets;
    }

    // Method to reduce available tickets (used by TicketConsumer)
    public void reduceAvailableTickets(int tickets) {
        availableTickets -= tickets;
    }
}
