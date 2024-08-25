package cl.smartjob.user.exception;

/**
 * The type Custom exception.
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String message;

    /**
     * Instantiates a new Custom exception.
     *
     * @param message the message
     */
    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
