package team.platform.exceptions;

/**
 * @author 1ommy
 * @version 24.09.2023
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String s) {
        super(s);
    }
}
