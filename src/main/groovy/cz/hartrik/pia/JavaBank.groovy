package cz.hartrik.pia

/**
 * Utility class.
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
class JavaBank {

    /**
     * JavaBank code.
     */
    static final String CODE = '1024'

    static String generateAccountNumber(Random random) {
        generateRandomNumber(random, 10) + '/' + generateRandomNumber(random, 4)
    }

    static String generateRandomPassword(Random random) {
        generateRandomNumber(random, 4)
    }

    private static String generateRandomNumber(Random random, int digits) {
        def chars = '0123456789'.chars
        return (0..<digits).collect { chars[random.nextInt(chars.size())] }.join('')
    }
}
