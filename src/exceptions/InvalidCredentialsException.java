package exceptions;

/**
 * @author 1ommy
 * @version 20.09.2023
 */
public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
