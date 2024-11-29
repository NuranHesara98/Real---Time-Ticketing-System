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
                    startTicketingSystem(scanner);  // Start system and collect configurations
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