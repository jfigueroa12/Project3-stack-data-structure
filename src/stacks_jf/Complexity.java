package stacks_jf;

/**
 *
 * @author flyasth3sky
 */

    /**
     * Class that represents the Big O order complexity of some block of code.
     * CodeBlocks have a reference to the complexity.  This represent the code 
     * blocks complexity.
     * 
     */
public class Complexity {
    
    private int nPower;
    
    private int logPower;
    
    /**
     * Constructor
     */
    public Complexity () {
        
    }
    
    public Complexity (int nPower) {
        this.nPower = nPower;
    }
    
    public Complexity (int nPower, int logPower) {
        this.nPower = nPower;
        this.logPower = logPower;
    }
    
    
    /**
     * Prints a human readable Big O notation string
     * @return 
     *          a human readable Big O notation string
     */         
    
    @Override
    public String toString() {
        
        String nString = "";
        String logString = "";
        String times = "";
        
        if (nPower == 0 && logPower == 0) {
            return "O(1)";
        } else if (nPower == 1) {
            nString += "n";
        } else if (nPower > 1) {
            nString += "n^" + nPower;
        }
        
        if (logPower == 1) {
            logString += "log(n)";
        } else if (logPower > 1) {
            logString += "log(n^" + logPower + ")";
        }
        
        if (nPower > 0 && logPower > 0) {
            times += " * ";
        } 
        
        return "O(" + nString + times + logString + ")";
        
    }

    /**
     * @return the nPower
     */
    public int getnPower() {
        return nPower;
    }

    /**
     * @return the logPower
     */
    public int getLogPower() {
        return logPower;
    }

    /**
     * @param nPower the nPower to set
     */
    public void setnPower(int nPower) {
        this.nPower = nPower;
    }

    /**
     * @param logPower the logPower to set
     */
    public void setLogPower(int logPower) {
        this.logPower = logPower;
    }
    
    
    public boolean greaterThan (Complexity other) {
        
        if (this.nPower == other.nPower) {
            
            return this.logPower > other.logPower;
        
        } else {
            
            return this.nPower > other.nPower;
        }
    }
    
}
