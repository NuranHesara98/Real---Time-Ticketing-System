import java.util.Scanner;

public class TicketingSystemCLI {
    // Global variables to store system configurations
    static int totalTickets;
    static int ticketReleaseRate;
    static int customerRetrievalRate;
    static int maxTicketCapacity;
    static configurations config;
    static boolean isSystemRunning = false;

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
                       startTicketingSystem(scanner);
                       break;
                   case 2:
                       stopTicketingSystem();
                   case 3:
                       viewRealTimeStatus();
                       break;
                   case 4:
                       viewLog();
                       break;
                   default:
                       System.out.println("Invalid choice! Please select a valid option");
               }
           }catch (Exception e){
               System.out.println("Invalid Input! Please enter a valid menu option (1 - 4)");
               scanner.next();
           }
        }
        scanner.close();
    }

    // Method to start the ticketing system and collect configuration values
    public static void startTicketingSystem(Scanner scanner) {
        System.out.println("\nStarting the Ticketing System...");

        // Collect system configuration parameters
        int totalTickets = getValidInput(scanner, "Enter total number of tickets: ");
        int ticketReleaseRate = getValidInput(scanner, "Enter ticket release rate (tickets per minute): ");
        int customerRetrievalRate = getValidInput(scanner, "Enter customer retrieval rate (tickets per minute): ");
        int maxTicketCapacity = getValidInput(scanner, "Enter max ticket capacity: ");

        //Initialize the configuration object
        config = new configurations(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        // Display the configured parameters
        System.out.println("\nSystem Configuration:");
        System.out.println("Total Tickets: " + config.getTotalTickets());
        System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
        System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
        System.out.println("Max Ticket Capacity: " + config.getMaxTicketCapacity());

        isSystemRunning = true;
        System.out.println("\nTicketing system has been successfully started!");
    }

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
                scanner.next();
            }
        }
        return input;
    }


    // Method to stop the ticketing system
    public static void stopTicketingSystem() {
        if (!isSystemRunning) {
            System.out.println("\nThe system is not currently running.");
            return;

        }
            System.out.println("\nStopping the Ticketing System.");
            isSystemRunning = false;
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

        public static void viewLog() {
            if (!isSystemRunning) {
                System.out.println("\nThe system is not running. Start the system first.");
                return;
            }

            System.out.println("\nViewing Logs...");

        }
    }
