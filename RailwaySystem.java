import java.util.*;

class Passenger {
    String name;
    int age;
    String id;

    public Passenger(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "Name='" + name + '\'' +
                ", Age=" + age +
                ", ID='" + id + '\'' +
                '}';
    }
}

class Train {
    int trainId;
    String route;
    String departureTime;
    int totalSeats;
    int availableSeats;

    public Train(int trainId, String route, String departureTime, int totalSeats) {
        this.trainId = trainId;
        this.route = route;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "Train{" +
                "TrainID=" + trainId +
                ", Route='" + route + '\'' +
                ", DepartureTime='" + departureTime + '\'' +
                ", AvailableSeats=" + availableSeats +
                '}';
    }
}

class Booking {
    String bookingId;
    Passenger passenger;
    Train train;

    public Booking(String bookingId, Passenger passenger, Train train) {
        this.bookingId = bookingId;
        this.passenger = passenger;
        this.train = train;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "BookingID='" + bookingId + '\'' +
                ", " + passenger +
                ", " + train +
                '}';
    }
}

public class RailwaySystem {
    static Map<Integer, Train> trains = new HashMap<>();
    static Map<String, Booking> bookings = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void addTrain() {
        System.out.print("Enter Train ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Route: ");
        String route = scanner.nextLine();
        System.out.print("Enter Departure Time: ");
        String time = scanner.nextLine();
        System.out.print("Enter Total Seats: ");
        int seats = scanner.nextInt();
        Train train = new Train(id, route, time, seats);
        trains.put(id, train);
        System.out.println("Train added successfully.");
    }

    public static void bookTicket() {
        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();
        Train train = trains.get(trainId);
        if (train == null || train.availableSeats == 0) {
            System.out.println("Train not available or fully booked.");
            return;
        }
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        String passengerId = UUID.randomUUID().toString();
        Passenger passenger = new Passenger(name, age, passengerId);

        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, passenger, train);

        bookings.put(bookingId, booking);
        train.availableSeats--;

        System.out.println("Booking successful. Booking ID: " + bookingId);
    }

    public static void showBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            bookings.values().forEach(System.out::println);
        }
    }

    public static void showTrains() {
        if (trains.isEmpty()) {
            System.out.println("No trains found.");
        } else {
            trains.values().forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Railway System Menu ---");
            System.out.println("1. Add Train");
            System.out.println("2. Book Ticket");
            System.out.println("3. Show All Bookings");
            System.out.println("4. Show All Trains");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addTrain();
                case 2 -> bookTicket();
                case 3 -> showBookings();
                case 4 -> showTrains();
                case 5 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }

        }
    }
}