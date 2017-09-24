package stacks_jf;

/**
 *
 * @author flyasth3sky
 */

public class CodeBlock {
    
    public static final String[] BLOCK_TYPES = {"def", "for", "while", "if", "else", "elif"};
    
    private static final int DEF = 0;
    private static final int FOR = 1;
    private static final int WHILE = 2;
    private static final int IF = 3;
    private static final int ELSE = 4;
    private static final int ELIF = 5;
    
    private Complexity blockComplexity;
    private Complexity highestSubComplexity;
    
    String name;
    
    int indent;
    
    int subBlocks;
    
    
    /**
     * Constructors
     */
    
    public CodeBlock () {
        blockComplexity = new Complexity();
        highestSubComplexity = new Complexity();
    }
    
    public CodeBlock (String name, int indent, Complexity blockComplexity) {
        this.name = name;
        this.indent = indent;
        this.blockComplexity = blockComplexity;
        highestSubComplexity = new Complexity();
    }
    
    public CodeBlock (Complexity blockComplexity) {
        this.blockComplexity = blockComplexity;
        highestSubComplexity = new Complexity();
    }
    
    public CodeBlock (Complexity blockComplexity, Complexity highestSubComplexity) {
        this.blockComplexity = blockComplexity;
        this.highestSubComplexity = highestSubComplexity;
    }
    
    public int getIndent () {
        return indent;
    }
    
    public void setIndent (int indent) {
        this.indent = indent;
    }
    
    public String getName () {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    public String newSubBlockName () {
        
        return name + "." + (++subBlocks);
        
    }

    /**
     * @return the blockComplexity
     */
    public Complexity getBlockComplexity() {
        return blockComplexity;
    }

    /**
     * @return the highestSubComplexity
     */
    public Complexity getHighestSubComplexity() {
        return highestSubComplexity;
    }

    /**
     * @param blockComplexity the blockComplexity to set
     */
    public void setBlockComplexity (Complexity blockComplexity) {
        this.blockComplexity = blockComplexity;
    }

    /**
     * @param highestSubComplexity the highestSubComplexity to set
     */
    public void setHighestSubComplexity (Complexity highestSubComplexity) {
        this.highestSubComplexity = highestSubComplexity;
    }
    
    /**
     * Calculates the total complexity for this code block
     * 
     * Total complexity = blockComplexity * highestSubComplexity
     * 
     * @return 
     *          The total complexity of this codeBlock object
     */
    
    public Complexity totalComplexity () {
        return new Complexity(blockComplexity.getnPower() + highestSubComplexity.getnPower(), 
                                blockComplexity.getLogPower() + highestSubComplexity.getLogPower());
    }
    
    
}
