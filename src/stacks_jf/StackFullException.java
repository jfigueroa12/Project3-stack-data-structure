package stacks_jf;

/**
 *
 * @author flyasth3sky
 */
public class StackFullException extends RuntimeException {

    /**
     * Creates a new instance of <code>StackFullException</code> without detail
     * message.
     */
    public StackFullException() {
    }

    /**
     * Constructs an instance of <code>StackFullException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public StackFullException(String msg) {
        super(msg);
    }
}
