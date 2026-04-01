import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Iterator;

public class RentalService {
    private LocalDateTime tripStartTime;
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

    public void reserveBike(String bID, String emailAddress) {
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

    public void removeTrip(String bID) {
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

    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
        } else {
            for (ActiveRental ar : activeRentalsList) {
                System.out.println(ar);
            }
        }
    }
}

