public class App {
    public static void main(String[] args) {
        Feedback feedback = new Feedback("John", "Doe", "john.doe@example.com");

        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this e-Bike to my friends and family.";

        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            feedback.analyseFeedback(true, sent1, sent2, sent3, sent4, sent5);
        }
        long end = System.nanoTime();
        System.out.println(feedback);
        System.out.println("Total time (String concatenation): " + (end - start) + " ns");

        
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            feedback.analyseFeedback(false, sent1, sent2, sent3, sent4, sent5);
        }
        end = System.nanoTime();
        System.out.println("Total time (StringBuilder): " + (end - start) + " ns");
    }
}
    

