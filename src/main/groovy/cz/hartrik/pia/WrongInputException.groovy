package cz.hartrik.pia

/**
 * Wrong input exception (3xx).
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
class WrongInputException extends RuntimeException {

    WrongInputException(String message) {
        super(message)
    }

}
