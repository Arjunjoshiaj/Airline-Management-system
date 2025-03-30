import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AirlineManagementSystem {
    
    private static Map<String, String> users = new HashMap<>();
    private static boolean loggedIn = false;
    private static String currentUser;

    // New managers for handling flights and bookings
    private static FlightManager flightManager = new FlightManager();
    private static BookingManager bookingManager = new BookingManager();

    public static void main(String[] args) {
        // Sample flights added for demonstration
        flightManager.addFlight(new Flight("AI101", "New York"));
        flightManager.addFlight(new Flight("AI102", "London"));
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Welcome to the Airline Management System");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Book a Flight");
            System.out.println("4. View All Flights");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    signUp(scanner);
                    break;
                case 3:
                    bookFlight(scanner);
                    break;
                case 4:
                    viewFlights();
                    break;
                case 5:
                    logout();
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

   // Existing methods (login, signUp, logout) remain unchanged...

   private static void viewFlights() {
       if (!loggedIn) {
           System.out.println("You must be logged in to view flights.");
           return;
       }
       System.out.println("Available Flights:");
       for (Flight flight : flightManager.getAllFlights()) {
           System.out.println("Flight Number: " + flight.getFlightNumber() + ", Destination: " + flight.getDestination() + ", Available: " + flight.isAvailable());
       }
   }

   private static void bookFlight(Scanner scanner) {
       if (!loggedIn) {
           System.out.println("You must be logged in to book a flight.");
           return;
       }
       System.out.print("Enter flight number to book: ");
       String flightNumber = scanner.nextLine();
       Flight flight = flightManager.findFlight(flightNumber);
       
       if (flight != null && flight.isAvailable()) {
           try {
               flight.bookFlight();
               Booking booking = new Booking(currentUser, flightNumber);
               bookingManager.addBooking(booking);
               System.out.println("Flight " + flightNumber + " booked successfully for " + currentUser + "!");
           } catch (IllegalStateException e) {
               System.out.println(e.getMessage());
           }
       } else if (flight != null) {
           System.out.println("Sorry, the selected flight is already booked.");
       } else {
           System.out.println("Flight not found.");
       }
   }
}
