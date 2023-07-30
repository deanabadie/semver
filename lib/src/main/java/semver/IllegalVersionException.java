package semver;

public class IllegalVersionException extends RuntimeException {

    public IllegalVersionException(String message) {
        super(message);
    }

}
