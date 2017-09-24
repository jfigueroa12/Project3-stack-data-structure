package stacks_jf;

/**
 *
 * @author flyasth3sky
 */

    /**
     * A stack object that holds CodeBlock objects in it.
     * Implemented as a stack using an array;
     * 
     */
public class BlockStack {
    
    private final int CAPACITY;
    //  stack is implemented as an array
    CodeBlock [] stack;
    //  points to the top of the stack
    int top;
    
    /**
     * Constructor
     */
    
    public BlockStack() {
        CAPACITY = 100;
        stack = new CodeBlock[CAPACITY];
        top = -1;
    }
    
    /**
     * Determines whether the stack is empty or not
     * 
     * @return
     *          true if the stack is empty, false if not empty
     */
    
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * Pushes a CodeBlock object onto the stack
     * 
     * @param codeBlock
     *          The CodeBlock object to be placed onto the stack
     * @throws
     *          StackFullException() - indicates the stack is full
     */
    
    public void push (CodeBlock codeBlock) {
        
        if (top == CAPACITY - 1) {
            throw new StackFullException("Stack is full, cannot add anymore items.");
        }
        stack[++top] = codeBlock;
    }
    
    /**
     * Pops a CodeBlock object off of the stack
     * 
     * @return  
     *          The CodeBlock object that was removed from the top of the stack
     * @throws 
     *          EmptyStackException() - indicates the stack is empty
     */
    
    public CodeBlock pop () {
        
        if (this.isEmpty()) {
            throw new EmptyStackException("Stack is empty, nothing to remove.");
        }
        return stack[top--];
    }
    
    /**
     * Looks at but does not remove the item at the top of the stack
     * 
     * @return
     *          CodeBlock object at the top of the stack
     */
    
    public CodeBlock peek () {
        
        if (this.isEmpty()) {
            throw new EmptyStackException("Stack is empty, nothing to remove.");
        }
        return stack[top];
    }
    
    /**
     * See how many items are currently in the stack
     * 
     * @return
     *          The number of items that are currently in the stack
     */
    
    public int size () {
        return top + 1;
    }
}
