package us.universalpvp.igp.loader.exception;

/**
 * Created by avigh on 9/3/2016.
 */
public class InvalidDescriptionException extends Exception {

    public InvalidDescriptionException() {
        super("Invalid plugin.yml");
    }

    public InvalidDescriptionException(Throwable cause, String message) {
        super(message, cause);
    }

    public InvalidDescriptionException(Throwable cause) {
        super("Invalid plugin.yml", cause);
    }

    public InvalidDescriptionException(String message) {
        super(message);
    }

}
