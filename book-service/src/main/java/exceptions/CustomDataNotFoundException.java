package exceptions;

public class CustomDataNotFoundException extends RuntimeException {
    public CustomDataNotFoundException(String message) {
        super(message);
    }
}