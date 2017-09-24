package stacks_jf;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 *
 * @author flyasth3sky
 */
public class PythonTracer {
    
    public static final int SPACE_COUNT = 4;
    
    /**
     * Main method prompts the user for the name of a file containing a single
     * python function, determines its order of complexity, and prints the result
     * to the console
     * 
     * @param args 
     */
    
    public static void main (String[] args) {
        String input = "";
        
            try {
                
                    BufferedReader mainReader = new BufferedReader(new InputStreamReader(System.in)); 
                    
                    while ( true ) {
                        System.out.print("Enter a file name (or 'quit' to quit): ");

                        input = mainReader.readLine();
                        System.out.println("");



                        if (input.equalsIgnoreCase("quit")) {
                            break;
                        }

                        String file = input;
                        Complexity finalComplex = traceFile(file);
                        System.out.printf("Overall complexity of %s: %s%n%n", file.substring(0, file.length() - 3), finalComplex);
                
                    }

            } catch (IOException ioe) {
                //  ioe.printStackTrace();
                System.out.println("Invalid file name or other error");
            } 
        
        
        
        
    }
    
    /**
     * Opens the indicated file and traces through the code of the python function
     * contained within the file, returning the Big-Oh order of complexity of 
     * the function.  During operation, the stack trace should be printed to 
     * the console as code blocks are pushed to/popped from the stack.
     * 
     * <dt><b>Precondition</b><dd>
     *      filename is not null and the file it names contains a single Python 
     * function with valid syntax (Reminder: you do NOT have to check for 
     * invalid syntax).
     * 
     * @param fileName
     *          The file to be opened and read from
     * 
     * @return
     *          A Complexity object representing the total order of complexity
     * of the Python code contained within the file.
     */
    
    public static Complexity traceFile(String fileName) {
        BlockStack stack = new BlockStack();
        Complexity c = null;
        String lineToParse;
        
        
        
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String keyWord;
            int indent;
            String name = "";
            while (( lineToParse = reader.readLine()) != null ) {
                
                
                keyWord = lineToParse.trim().split(" ")[0];
                
                indent = lineToParse.indexOf(lineToParse.trim()) / SPACE_COUNT;
                
                CodeBlock codeBlock;
                
                if ( isKeyword(keyWord) ) {
                    // System.out.println(keyWord);
                    //  the indentation of the line
                    
                        
                        // if this a true, you have encountered a nested codeBlock
                        // calculate the complexity and push onto the stack
                    //System.out.println(stack.size());
                    //System.out.println(indent);
                        
                        if ( indent < stack.size() ) {
                            
                            while ( indent < stack.size() ) {
                                
                                popAndUpdate(stack);
                                
                            }
                            
                        }
                        
                        if ( indent == stack.size() ) {
                                
                                if ( stack.isEmpty() ) {
                                    name = "1";
                                } else if ( indent > stack.peek().getIndent() ) {
                                    // this is a sublock
                                    name = stack.peek().newSubBlockName();
                                }
                                
                                Complexity complex;
                                switch (keyWord) {
                                        
                                        case "while":
                                            
                                            
                                            //  This will represent the complexity of this while block
                                            complex = new Complexity();
                                            //  Get the loop variable for this while block
                                            String loopVar = lineToParse.trim().split(" ")[1];
                                            
                                            System.out.printf("Entering block %s %s:%n", name, keyWord);
                                            System.out.printf("%9s %s:%22s%2s %s%29s%2s %s%n%n", "BLOCK", name, "block complexity", "=", 
                                                    complex, "highest sub-complexity", "=",
                                                    complex);
                                            //System.out.println(loopVar);
                                            //  now search the next few lines for the update statement
                                            while (true) {
                                                String nextLines = reader.readLine();

                                                if ( nextLines.contains(loopVar + " /=") ){
                                                    //  complexity O(log N)
                                                    complex.setLogPower(1);
                                                    break;

                                                } else if ( nextLines.contains(loopVar + " -=" ) ) {
                                                    //  complexity O(n)
                                                    complex.setnPower(1);
                                                    break;

                                                } 
                                                
                                            }
                                            // System.out.println(complex);
                                            codeBlock = new CodeBlock(name, indent, complex);
                                            stack.push(codeBlock);
                                            System.out.printf("Found update statement, updating block %s:%n", name);
                                            System.out.printf("%9s %s:%22s%2s %s%29s%2s %s%n%n", "BLOCK", name, "block complexity", "=", 
                                                    codeBlock.getBlockComplexity(), "highest sub-complexity", "=",
                                                    codeBlock.getHighestSubComplexity());
                                            
                                            
                                            
                                            
                                            break;
                                            
                                        case "for":
                                            
                                            complex = new Complexity();
                                            
                                            String order = lineToParse.trim().split(" ")[3];
                                            
                                            if ( order.equalsIgnoreCase("n:") ) {
                                                
                                                complex.setnPower(1);
                                            
                                            } else if ( order.equalsIgnoreCase("log_n:") ) {
                                                
                                                complex.setLogPower(1);
                                                
                                            }
                                            
                                            codeBlock = new CodeBlock(name, indent, complex);
                                            stack.push(new CodeBlock(name, indent, complex));
                                            System.out.printf("Entering block %s %s:%n", name, keyWord);
                                            System.out.printf("%9s %s:%22s%2s %s%29s%2s %s%n%n", "BLOCK", name, "block complexity", "=", 
                                                    codeBlock.getBlockComplexity(), "highest sub-complexity", "=",
                                                    codeBlock.getHighestSubComplexity());
                                            break;
                                            
                                        default: 
                                            complex = new Complexity();
                                            
                                            codeBlock = new CodeBlock(name, indent, complex);
                                            stack.push(new CodeBlock(name, indent, complex));
                                            System.out.printf("Entering block %s %s:%n", name, keyWord);
                                            System.out.printf("%9s %s:%22s%2s %s%29s%2s %s%n%n", "BLOCK", name, "block complexity", "=", 
                                                    codeBlock.getBlockComplexity(), "highest sub-complexity", "=",
                                                    codeBlock.getHighestSubComplexity());
                                }
                        
                        } 
                        
                    //  System.out.println(stack.size());
                            
                    //  System.out.println(indent);
                    
                    // System.out.printf("Entering block %s%n", keyWord);
                
                } 
                
                
                
                // System.out.println(lineToParse);
                
                
                
                
                
                
                
            } 
            
        } catch (IOException ioe) {
            //  ioe.printStackTrace();
            System.out.println("Error opening the file");
        }
        
        while ( stack.size() > 1 ) {
            popAndUpdate(stack);
        }
        
        System.out.println("Leaving block 1.");
        System.out.println("");
        
        
        
        // System.out.println(stack.peek().totalComplexity());
        return stack.pop().totalComplexity();
    }
    
    /**
     * Determines whether the argument string is keyword
     * 
     * @param word - the string to be analyzed
     * 
     * @return
     *          true if the argument is a keyword, false otherwise
     */
    
    public static boolean isKeyword (String word) {
        
        return word.equals("def") || 
                
                word.equals("for") || 
                 
                word.equals("if") || 
                  
                word.equals("else") ||
                   
                word.equals("elif") ||
                
                word.equals("while") ;
            
    }
    
    /**
     * Calculates the complexity of the block 
     * 
     * @param block - block to be analyzed
     * 
     * @return
     *          The complexity of the code block
     */
    
    public Complexity calculateComplexity (String block) {
        Complexity complex = null;
        String key = block.split(" ")[0];
        System.out.println(key);
        
        // switch ()
        
        
        return complex;
    }
    
    /**
     * Pops the item at the top of the stack and updates the complexity of the next item
     * on the stack
     * 
     */
    
    public static void popAndUpdate (BlockStack s) {
        
        if ( s.isEmpty() ) {
            return;
        }
        
        CodeBlock top = s.pop();
        CodeBlock newTop = s.peek();
        
        Complexity subComplexity = top.totalComplexity();
        String blockName = s.peek().getName();
        
        System.out.printf("Leaving block %s, ", top.getName());
        
        
        
        if ( subComplexity.greaterThan(newTop.getHighestSubComplexity() ) ) {
            newTop.setHighestSubComplexity(subComplexity);
            System.out.printf("updating block %s:%n", newTop.getName());
        } else {
            System.out.printf("nothing to update.%n");
            
        }
        
        System.out.printf("%9s %s:%22s%2s %s%29s%2s %s%n%n", "BLOCK", newTop.getName(), "block complexity", "=", 
                                                    newTop.getBlockComplexity(), "highest sub-complexity", "=",
                                                    newTop.getHighestSubComplexity());
        
        
    }
}
