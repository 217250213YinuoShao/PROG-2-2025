    public class BikeService {
    private boolean locationValid;

    public String validateLocation(String loc) {
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

   
}

