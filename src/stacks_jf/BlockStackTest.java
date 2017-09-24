package stacks_jf;

/**
 *
 * @author flyasth3sky
 */
public class BlockStackTest {
    
   
    public static void main (String[] args) {
        
        BlockStack blockSt = new BlockStack ();

        for (int i = 0; i < 10; i++) {
            blockSt.push(new CodeBlock(new Complexity(i)));
            
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.println(blockSt.pop().getBlockComplexity());
        }
    
    }
}
