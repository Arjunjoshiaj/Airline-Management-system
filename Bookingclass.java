// Booking.java
public class Booking {
    private String username;
    private String flightNumber;

    public Booking(String username, String flightNumber) {
        this.username = username;
        this.flightNumber = flightNumber;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "username='" + username + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }
}
