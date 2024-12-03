import java.util.concurrent.locks.Lock;

public class TicketConsumer implements Runnable {
    private int customerId;
    private int ticketsRequested;
    private final Object ticketLock;
    private TicketingSystemCLI ticketingSystemCLI;

    public TicketConsumer(int customerId, int ticketsRequested, Object ticketLock, TicketingSystemCLI ticketingSystemCLI) {
        this.customerId = customerId;
        this.ticketsRequested = ticketsRequested;
        this.ticketLock = ticketLock;
        this.ticketingSystemCLI = ticketingSystemCLI;
    }

    @Override
    public void run() {
        // This method now has access to ticketingSystemCLI, so you can use it for synchronization and other actions
        synchronized (ticketLock) {
            if (ticketingSystemCLI.availableTickets >= ticketsRequested) {
                ticketingSystemCLI.availableTickets -= ticketsRequested;
                System.out.println("Customer " + customerId + " successfully booked " + ticketsRequested + " tickets.");
            } else {
                System.out.println("Customer " + customerId + " failed to book " + ticketsRequested + " tickets. Not enough tickets available.");
            }
        }
    }
}
