import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AirlineManagementSystem {
    private static Map<String, String> users = new HashMap<>();
    private static boolean loggedIn = false;
    private static String currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Airline Management System");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Book a Flight");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
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
                    logout();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        if (loggedIn) {
            System.out.println("You are already logged in as " + currentUser);
            return;
        }
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedIn = true;
            currentUser = username;
            System.out.println("Login successful! Welcome, " + username);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void signUp(Scanner scanner) {
        if (loggedIn) {
            System.out.println("You are already logged in. Please log out to sign up a new user.");
            return;
        }
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose another one.");
            return;
        }
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        users.put(username, password);
        System.out.println("Sign up successful! You can now log in.");
    }

    private static void bookFlight(Scanner scanner) {
        if (!loggedIn) {
            System.out.println("You must be logged in to book a flight.");
            return;
        }
        // Simple booking logic
        System.out.print("Enter flight number to book: ");
        String flightNumber = scanner.nextLine();
        // Here you would typically check availability and process the booking
        System.out.println("Flight " + flightNumber + " booked successfully for " + currentUser + "!");
    }

    private static void logout() {
        if (!loggedIn) {
            System.out.println("You are not logged in.");
            return;
        }
        loggedIn = false;
        currentUser = null;
        System.out.println("Logged out successfully.");
    }
}
