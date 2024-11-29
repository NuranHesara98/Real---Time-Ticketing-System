import java.util.Scanner;

public class TicketingSystemCLI {
    // Global variables to store system configurations
    static int totalTickets;
    static int ticketReleaseRate;
    static int customerRetrievalRate;
    static int maxTicketCapacity;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Main Menu
        while (!exit) {
            System.out.println("\n==========================================");
            System.out.println("        Real-Time Ticketing System");
            System.out.println("==========================================");
            System.out.println("1. Start Ticketing System");
            System.out.println("2. Stop Ticketing System");
            System.out.println("3. View Real-Time Status");
            System.out.println("4. View Logs");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    startTicketingSystem(scanner);
                    break;
                case 2:
                    stopTicketingSystem();
                    scanner.close();
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                case 3:
                    viewRealTimeStatus();
                    break;
                case 4:
                    viewLog();
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option");
            }
        }
    }

    // Method to start the ticketing system and collect configuration values
    public static void startTicketingSystem(Scanner scanner) {
        System.out.println("\nStarting the Ticketing System...");

        // Collect system configuration parameters
        System.out.print("Enter total number of tickets: ");
        totalTickets = getValidInput(scanner);

        System.out.print("Enter ticket release rate (tickets per minute): ");
        ticketReleaseRate = getValidInput(scanner);

        System.out.print("Enter customer retrieval rate (tickets per minute): ");
        customerRetrievalRate = getValidInput(scanner);

        System.out.print("Enter max ticket capacity: ");
        maxTicketCapacity = getValidInput(scanner);

        // Display the configured parameters
        System.out.println("\nSystem Configuration:");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate + " tickets/min");
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate + " tickets/min");
        System.out.println("Max Ticket Capacity: " + maxTicketCapacity);
        System.out.println("\nTicketing system has been successfully started!");
    }

    private static int getValidInput(Scanner scanner) {
        int input = -1;
        while (input <= 0) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
        return input;
    }

    // Method to stop the ticketing system
    public static void stopTicketingSystem() {
        System.out.println("\nStopping the Ticketing System...");

    }

    // Method to view real-time status (this could show ticket availability, etc.)
    public static void viewRealTimeStatus() {
        System.out.println("\nViewing Real-Time Status...");

    }

    // Method to view logs
    public static void viewLog() {
        System.out.println("\nViewing Logs...");

    }
}
