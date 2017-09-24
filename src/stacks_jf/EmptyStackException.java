package stacks_jf;

/**
 *
 * @author flyasth3sky
 */
public class EmptyStackException extends RuntimeException {

    /**
     * Creates a new instance of <code>EmptyStackException</code> without detail
     * message.
     */
    public EmptyStackException() {
    }

    /**
     * Constructs an instance of <code>EmptyStackException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyStackException(String msg) {
        super(msg);
    }
}
