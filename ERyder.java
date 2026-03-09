public class ERyder {
    int bikeID;
    int batteryLevel;
    boolean isAvailable;
    double kmDriven;

    public ERyder() {}

    public ERyder(int id, int bat, boolean ava, double km) {
        bikeID = id;
        setBatteryLevel(bat); 
        isAvailable = ava;
        kmDriven = km;
    }

    
    public void ride() {
        if (batteryLevel > 0 && isAvailable) {
            System.out.println("Can ride.");
        } else {
            System.out.println("Can't ride.");
        }
    }

    
    public void printBikeDetails() {
    System.out.println("Bike ID: " + bikeID);
    System.out.println("Battery Level: " + batteryLevel + "%");
    System.out.println("Is Available: " + isAvailable);
    System.out.println("KM Driven: " + kmDriven + "km\n");
    }

    public void setBatteryLevel(int bat) {
        if (bat <0)
            batteryLevel=0;
        else if (bat >100)
            batteryLevel=100;
        else
            batteryLevel=bat;
    }
}