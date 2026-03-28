import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;

public class BikeRental {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private UserRegistration userReg = new UserRegistration();
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    private Scanner sc = new Scanner(System.in);

    public void simulateApplicationInput() {
        System.out.println("This is the simulation of the e-bike rental process.");

        System.out.print("Is registered user (true/false): ");
        isRegisteredUser = sc.nextBoolean();
        sc.nextLine();
        System.out.print("Enter user email address: ");
        emailAddress = sc.nextLine();
        System.out.print("Enter rental location: ");
        location = sc.nextLine();

        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);

        if (!locationValid) {
            return;
        }

        System.out.println("Simulating e-bike reservation…");
        reserveBike(bikeID);

        System.out.println("Displaying the active rentals…");
        viewActiveRentals();

        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);

        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();
    }

   
    private String validateLocation(String loc) {
        for (Bike b : BikeDatabase.bikes) {
            if (b.getLocation().equals(loc) && b.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return b.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        locationValid = false;
        return null;
    } 
    private String analyseRequest(boolean isReg, String email, String loc) {
        if (isReg) {
            System.out.println("Welcome back, " + email + "!");
        } else {
            System.out.println("You're not our registered user. Please consider registering.");
            userReg.registration();
        }
        return validateLocation(loc);
    }

      private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
        } else {
            for (ActiveRental ar : activeRentalsList) {
                System.out.println(ar);
            }
        }
    }
    private void reserveBike(String bID) {
        if (bID != null) {
            for (Bike b : BikeDatabase.bikes) {
                if (b.getBikeID().equals(bID)) {
                    tripStartTime = LocalDateTime.now();
                    b.setAvailable(false);
                    b.setLastUsedTime(tripStartTime);
                    System.out.println("Reserving the bike with the " + bID + ". Please following the on-screen instructions to locate the bike and start your pleasant journey.");
                    activeRental = new ActiveRental(bID, emailAddress, tripStartTime);
                    activeRentalsList.add(activeRental);
                    break;
                }
            }
        } else {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
        }
    }

    

    private void removeTrip(String bID) {
        Iterator<ActiveRental> it = activeRentalsList.iterator();
        while (it.hasNext()) {
            ActiveRental ar = it.next();
            if (ar.getBikeID().equals(bID)) {
                it.remove();
                break;
            }
        }

        for (Bike b : BikeDatabase.bikes) {
            if (b.getBikeID().equals(bID)) {
                b.setAvailable(true);
                b.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for riding with us.");
                break;
            }
        }
    }
}

