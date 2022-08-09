package engine.model;

public class Feedback {

    public static final String SUCCESS_FEEDBACK = "Congratulations, you're right!";

    public static final String FAILURE_FEEDBACK = "Wrong answer! Please, try again.";

    public static Answer success() {
        return new Answer(true, SUCCESS_FEEDBACK);
    }

    public static Answer failure() {
        return new Answer(false, FAILURE_FEEDBACK);
    }
}
