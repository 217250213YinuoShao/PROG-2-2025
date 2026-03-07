public class Main {
    
    public static void main(String[] args) {
        ERyder bike1 = new ERyder();
        bike1.printBikeDetails();

        ERyder bike2 = new ERyder(1001, 75, true, 123.5);
        bike2.ride();
        bike2.printBikeDetails();
    }
}