import java.util.*;
public class OnlineReservationSystem {
	 private static Map<String, User> users = new HashMap<>();
	    private static Map<String, Reservation> reservations = new HashMap<>();

	    public static void main(String[] args) {
	        users.put("admin", new User("admin", "admin"));

	        Scanner scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("1. Login");
	            System.out.println("2. Exit");
	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            if (choice == 1) {
	                login(scanner);
	            } else {
	                break;
	            }
	        }
	        scanner.close();
	    }

	    private static void login(Scanner scanner) {
	        System.out.print("Enter login ID: ");
	        String loginId = scanner.nextLine();
	        System.out.println();
	        System.out.print("Enter password: ");
	        String password = scanner.nextLine();

	        User user = users.get(loginId);
	        if (user != null && user.getPassword().equals(password)) {
	        	System.out.println();
	            System.out.println("Login successful!");
	            System.out.println();
	            showMainMenu(scanner);
	        } else {
	            System.out.println("Invalid login ID or password Try Again.");
	        }
	    }

	    private static void showMainMenu(Scanner scanner) {
	        while (true) {
	            System.out.println("1. Reserve a Ticket");
	            System.out.println("2. Cancel a Ticket");
	            System.out.println("3. Logout");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            if (choice == 1) {
	                reserveTicket(scanner);
	            } else if (choice == 2) {
	                cancelTicket(scanner);
	            } else {
	                break;
	            }
	        }
	    }

	    private static void reserveTicket(Scanner scanner) {
	        System.out.print("Enter your name: ");
	        String name = scanner.nextLine();
	        System.out.print("Enter train number: ");
	        String trainNumber = scanner.nextLine();
	        System.out.print("Enter train name: ");
	        String trainName = scanner.nextLine();
	        System.out.print("Enter class type: ");
	        String classType = scanner.nextLine();
	        System.out.print("Enter date of journey: ");
	        String dateOfJourney = scanner.nextLine();
	        System.out.print("Enter from (place): ");
	        String from = scanner.nextLine();
	        System.out.print("Enter destination: ");
	        String to = scanner.nextLine();

	        String pnr = generatePNR();
	        Reservation reservation = new Reservation(pnr, name, trainNumber, trainName, classType, dateOfJourney, from, to);
	        reservations.put(pnr, reservation);
	        System.out.println();
	        System.out.println("Reservation successful! Your PNR is " + pnr);
	        System.out.println();
	        System.out.println("THANK YOU ");
	        System.out.println();
	    }

	    private static void cancelTicket(Scanner scanner) {
	        System.out.print("Enter PNR number: ");
	        String pnr = scanner.nextLine();

	        Reservation reservation = reservations.get(pnr);
	        if (reservation != null) {
	        	System.out.println();
	            System.out.println("YOUR Reservation details: " + reservation);
	            System.out.println();
	            System.out.print("Do you want to cancel this reservation? (yes/no): ");
	            String confirm = scanner.nextLine();
	            if (confirm.equalsIgnoreCase("yes")) {
	                reservations.remove(pnr);
	                System.out.println();
	                System.out.println("Reservation successfully cancelled.");
	                System.out.println();
	            } else {
	                System.out.println("Your Cancellation is aborted.");
	            }
	        } else {
	            System.out.println("No reservation found for PNR " + pnr);
	        }
	    }

	    private static String generatePNR() {
	        return "PNR" + (reservations.size() + 1);
	    }
}
