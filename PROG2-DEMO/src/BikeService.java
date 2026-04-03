   import java.time.LocalDateTime;
   import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;



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
    private Stack<ERyderLog> logStack = new Stack<>();

    public void addLog(String logId, String event) {
        ERyderLog log = new ERyderLog(logId, event, LocalDateTime.now());
        logStack.push(log);
    }
    public void viewSystemLogs() {
        for (ERyderLog log : logStack) {
            System.out.println(log);
        }
    }
       private Queue<BikeRequest> bikeRequestQueue = new ArrayDeque<>();

    public void reserveBike(String email, String location) {
        BikeRequest request = new BikeRequest(email, location, LocalDateTime.now());
        bikeRequestQueue.add(request);
    }

    public void removeTrip() {
        if (!bikeRequestQueue.isEmpty()) {
            bikeRequestQueue.poll();
        }
    }
    public void viewQueue() {
    for (BikeRequest req : bikeRequestQueue) {
        System.out.println(req);
    }
}




}

     
    

   


