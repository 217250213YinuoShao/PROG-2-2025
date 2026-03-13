

public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

public Feedback(String firstName,String lastName,String email){
this.firstName=firstName;
this.lastName=lastName;
this.email=email;
}
private String feedbackUsingConcatenation(String sent1,String sent2,String sent3,String sent4,String sent5){
    String concatenatedFeedback="";
    concatenatedFeedback+=sent1+"";
    concatenatedFeedback+=sent2+"";
    concatenatedFeedback+=sent3+"";
    concatenatedFeedback+=sent4+"";
    concatenatedFeedback+=sent5;
    return concatenatedFeedback;
}

    private boolean checkFeedbackLength(String feedback) {
        if (feedback.length() > 500) {
            longFeedback = true; 
        } 
        else {
            longFeedback = false; 
        }
        return longFeedback;
    }

private void createReviewID(String firstName, String lastName, String feedback) {
   String namePart = (firstName + lastName).substring(2, 6).toUpperCase().replace("","");
String feedbackPart = feedback.substring(10, 15).toLowerCase().replace("","");
this.reviewID = namePart + feedbackPart + completeFeedback.length() + "_" + System.currentTimeMillis(); 
}

    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
        }
        checkFeedbackLength(completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }
    private String feedbackUsingStringBuilder(String sent1, String sent2, String sent3, String sent4, String sent5) {
    StringBuilder sb = new StringBuilder();
    sb.append(sent1);
    sb.append(sent2);
    sb.append(sent3);
    sb.append(sent4);
    sb.append(sent5);
    return sb.toString();
}

public String toString() {
    return "Feedback from " + firstName + " " + lastName + " (" + email + ")\n\n" +
           "Feedback: " + completeFeedback + "\n\n" +
           "Long Feedback: " + longFeedback + "\n" +
           "Review ID: " + reviewID;
}
}

