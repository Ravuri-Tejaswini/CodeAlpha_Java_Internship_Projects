
import java.util.ArrayList;
import java.util.Scanner;
class Room {
    int roomNumber;
    String type;
    boolean isBooked;

    Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isBooked = false;
    }
}
class Reservation {
    String customerName;
    Room room;

    Reservation(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
    }
}
public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeRooms();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> bookRoom(sc);
                case 3 -> cancelReservation(sc);
                case 4 -> {
                    System.out.println("Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Deluxe"));
        rooms.add(new Room(103, "Suite"));
    }

    static void viewRooms() {
        for (Room r : rooms) {
            System.out.println("Room " + r.roomNumber + " (" + r.type + ") - " +
                    (r.isBooked ? "Booked" : "Available"));
        }
    }

    static void bookRoom(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        for (Room r : rooms) {
            if (!r.isBooked) {
                r.isBooked = true;
                reservations.add(new Reservation(name, r));
                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("No rooms available");
    }

    static void cancelReservation(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        for (Reservation res : reservations) {
            if (res.customerName.equalsIgnoreCase(name)) {
                res.room.isBooked = false;
                reservations.remove(res);
                System.out.println("Reservation cancelled");
                return;
            }
        }
        System.out.println("Reservation not found");
    }
}