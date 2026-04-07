import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserService {

    List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public RegisteredUsers addNewUsers(String userType, String fullName, String emailAddress,String dateOfBirth,String cardNumber,String cardExpiryDate,String cardProvider,String cvv,String[]lastThreeTrips) {
    RegisteredUsers newUser;
    if (userType.equalsIgnoreCase("VIP")) {
        newUser = new VIPUser(fullName, emailAddress);
    } else {
        newUser = new RegularUser(fullName, emailAddress);
    }
    registeredUsersList.add(newUser);
    return newUser;
}

    public void addNewUsers() {
        System.out.println("\n--- Add New Users ---");
        System.out.print("How many users would you like to add? ");
        int userCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < userCount; i++) {
            System.out.println("\n--- User " + (i + 1) + " Details ---");
            System.out.print("Full Name: ");
            String fullName = sc.nextLine();
            System.out.print("Email Address: ");
            String email = sc.nextLine();
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = sc.nextLine();
            System.out.print("Card Number: ");
            String cardNum = sc.nextLine();
            System.out.print("Card Expiry Date: ");
            String cardExp = sc.nextLine();
            System.out.print("Card Provider: ");
            String cardPro = sc.nextLine();
            System.out.print("CVV: ");
            String cvv = sc.nextLine();
            System.out.print("User Type: ");
            String userType = sc.nextLine();

            String[] lastThreeTrips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("\n Trip " + (j + 1) + " Details");
                System.out.print("Trip Date (YYYY-MM-DD): ");
                String tripDate = sc.nextLine();
                System.out.print("Source: ");
                String source = sc.nextLine();
                System.out.print("Destination: ");
                String dest = sc.nextLine();
                System.out.print("Fare (€): ");
                double fare = sc.nextDouble();
                sc.nextLine();
                System.out.print("Feedback (NULL if none): ");
                String feedback = sc.nextLine();
                if (feedback.isEmpty()) feedback = "NULL";

                lastThreeTrips[j] = "Date: " + tripDate +
                        ", Source: " + source +
                        ", Destination: " + dest +
                        ", Fare (€): " + fare +
                        ", Feedback: " + feedback;
            }

            RegisteredUsers newUser = new RegisteredUsers(fullName, email);
            registeredUsersList.add(newUser);
            System.out.println("User " + (i + 1) + " added successfully!");
        }
    }

    public void viewRegisteredUsers() {
        System.out.println("\n View Registered Users");
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }
        int index = 1;
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println("\nUser " + index + ": " + user);
            index++;
        }
    }

    public void removeRegisteredUsers() {
        System.out.println("\nRemove Registered Users ");
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }
        System.out.print("Enter email address of user to remove: ");
        String deleteEmail = sc.nextLine();
        Iterator<RegisteredUsers> it = registeredUsersList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            RegisteredUsers user = it.next();
            if (user.getEmailAddress().equals(deleteEmail)) {
                it.remove();
                found = true;
                break;
            }
        }
        if (!found) System.out.println("No user found with this email address");
        else System.out.println("User removed successfully!");
    }

    public void updateRegisteredUsers() {
        System.out.println("\nUpdate Registered Users");
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }
        System.out.print("Enter email address of user to update: ");
        String updateEmail = sc.nextLine();
        RegisteredUsers target = null;

        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equals(updateEmail)) {
                target = user;
                break;
            }
        }
        if (target == null) {
            System.out.println("No user found with this email address");
            return;
        }

        System.out.println("\n--- Enter New Details (Press ENTER for no change) ---");
        System.out.print("New Full Name: ");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) target.setFullName(newName);

        System.out.print("New Date of Birth (YYYY-MM-DD): ");
        String newDob = sc.nextLine();
        if (!newDob.isEmpty()) target.setDateOfBirth(newDob);

        System.out.print("New Card Provider: ");
        String newCardPro = sc.nextLine();
        if (!newCardPro.isEmpty()) target.setCardProvider(newCardPro);

        System.out.print("New User Type: ");
        String newUserType = sc.nextLine();
        if (!newUserType.isEmpty()) target.setUserType(newUserType);

        System.out.print("New Card Number : ");
        String newCardNum = sc.nextLine();
        if (!newCardNum.equals("0")) target.setCardNumber(newCardNum);

        System.out.print("New CVV : ");
        String newCvv = sc.nextLine();
        if (!newCvv.equals("0")) target.setCvv(newCvv);

        System.out.print("New Card Expiry Date: ");
        String newCardExp = sc.nextLine();
        if (!newCardExp.equals("0")) target.setCardExpiryDate(newCardExp);

        System.out.println("User updated successfully!");
    }
}

