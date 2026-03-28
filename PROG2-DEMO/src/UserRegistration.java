import java.time.LocalDate;
import java.util.Scanner;
import java.time.Period;

public class UserRegistration{
    public static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    public static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    public static final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid=false;
    private boolean minorAndBirthday=false;
    private boolean minor=false;
    private boolean ageValid=false;
    private boolean cardNumberValid=false;
    private boolean validCVV=false;
    private boolean cardStillValid;
    private LocalDate dob; 
    private LocalDate currentDate;
    private String expiry;

    public void registration(){
        Scanner sc =new Scanner(System.in);
        System.out.println("Welcome to ERyder Registration");
        System.out.println("Here are your two options:");
        System.out.println("1.Register as a regular User");
        System.out.println("2.Register as a VIP User");
        System.out.println("Please enter your choice(1 or 2)");
        int choice = sc.nextInt();
        sc.nextLine(); 
        if (choice==1){
            userType="Regular User";
        }
        else if (choice ==2){
            userType="VIP User";
        }else{
            System.out.println("Invalid choice.Please restart the registration process.");
            return;
        }
        System.out.println("Let's proceed with the registration.\n ");
        System.out.println("Please enter your Full Name:");
        fullName = sc.nextLine();

        System.out.println("Please enter your Email Address:");
        emailAddress=sc.nextLine();
        System.out.println("Checking your email address's validity...");
        emailValid=analyseEmail(emailAddress);

        System.out.println("Please enter your date of birth as (yyyy-mm-dd):");
        dateOfBirth=sc.nextLine();
        System.out.println("Checking your age validity...");
        dob=LocalDate.parse(dateOfBirth);
        ageValid=analyseAge(dob);
        System.out.println("Please enter your card number:");
        cardNumber=sc.nextLong();
        sc.nextLine();
        System.out.println("Checking your card number's validity...");
        cardNumberValid=analyseCardNumber(cardNumber);
        System.out.println("Please enter the card expiry date (MM/YY):");
        expiry=sc.nextLine();
        cardExpiryDate = expiry;
        System.out.println("Checking if your card is still valid...");
        cardStillValid=analyseCardExpiryDate(expiry);

        System.out.println("Please enter your CVV:");
        cvv = sc.nextInt();
        sc.nextLine();
        System.out.println("Checking your CVV's validity...");
        validCVV=analyseCVV(cvv);


        int age = Period.between(dob, LocalDate.now()).getYears();
        boolean isBirthday = (dob.getMonthValue() == LocalDate.now().getMonthValue()) && (dob.getDayOfMonth() == LocalDate.now().getDayOfMonth());

        if (age <= 12 || age > 120) {
            System.out.println("Age invalid, exiting...");
            System.exit(0);
        }

        if ("VIP User".equals(userType)) {
            if (age <= 18 && age > 12 && isBirthday) {
                minorAndBirthday = true;
            } else if (age <= 18 && age > 12 && !isBirthday) {
                minor = true;
            }
        }

        
        String cardNumberStr = String.valueOf(cardNumber);
        int firstTwo = Integer.parseInt(cardNumberStr.substring(0, 2));
        int firstFour = Integer.parseInt(cardNumberStr.substring(0, 4));

        if (cardNumberStr.length() == 13 && cardNumberStr.startsWith("4")) {
            cardProvider = "VISA";
        } else if (cardNumberStr.length() == 15 && (cardNumberStr.startsWith("34") || cardNumberStr.startsWith("37"))) {
            cardProvider = "American Express";
        } else if (cardNumberStr.length() == 16 && firstTwo >= 51 && firstTwo <= 55) {
            cardProvider = "MasterCard";
        } else if (cardNumberStr.length() == 16 && firstFour >= 2221 && firstFour <= 2720) {
            cardProvider = "MasterCard";
        } else {
            System.out.println("Invalid card number.");
            return;
        }

        

        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();
        String cvvStr = String.valueOf(cvv);

        int month = Integer.parseInt(expiry.substring(0, 2));
        int year = Integer.parseInt(expiry.substring(3, 5)) + 2000;
        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            if ((cardProvider.equals("American Express") && cvvStr.length() == 4) ||
                (cardProvider.equals("VISA") && cvvStr.length() == 3) ||
                (cardProvider.equals("MasterCard") && cvvStr.length() == 3)) {
            if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
                    System.out.println("All checks passed!");
                }
            }
        }

        if (!emailValid) {
            System.out.println("Invalid email address");
        }

        if ("VIP User".equals(userType)) {
            if (minorAndBirthday) {
                feeToCharge = VIP_BASE_FEE * (1 - 25/100);
            } else if (minor) {
                feeToCharge = VIP_BASE_FEE * (1 - 20/100);
            } else {
                feeToCharge = VIP_BASE_FEE;
            }
        }

        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        System.out.println("Card number: " + censoredPart + lastFourDigits);

        finalCheckpoint();
    }

    public boolean analyseAge(LocalDate dob) {
        return LocalDate.now().isAfter(dob.plusYears(18));
    }

       public boolean analyseCardNumber(long cardNumber) {
        return String.valueOf(cardNumber).length() == 16;
    }
    public boolean analyseCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);
        if (cardProvider == null) return false;
        if (cardProvider.equals("American Express")) {
            return cvvStr.length() == 4;
        } else {
            return cvvStr.length() == 3;
        }
    }

    public boolean analyseCardExpiryDate(String expiryDate) {
        try {
            int month = Integer.parseInt(expiryDate.substring(0, 2));
            int year = Integer.parseInt(expiryDate.substring(3, 5)) + 2000;
            LocalDate expDate = LocalDate.of(year, month, 1);
            return !expDate.isBefore(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }

    

    public boolean finalCheckpoint() {
        return ageValid && cardNumberValid && cardStillValid && validCVV;
    }

    private boolean analyseEmail(String email){
        if (email.contains("@")&&email.contains(".")){
            System.out.println("Email address is valid.");
            return true;
        }else{
            System.out.println("Invalid email address.Please try again.");
            System.out.println("Going back to the start of the registration process...");
            return false;
        }
    }
     public static void main(String[] args) {
        new UserRegistration().registration();
    }
}