import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Count;

public class BurrowsWheeler {
	public static final int R=256;
    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
    	String in = BinaryStdIn.readString();
    	int len = in.length();
    	CircularSuffixArray csa = new CircularSuffixArray(in);
    	for(int i=0;i<len;i++) {
    		if(csa.index(i)==0)
    			BinaryStdOut.write(i);
    	}
    	for(int i=0;i<len;i++) {
    		int lastid = (csa.index(i)+len-1)%len;
    		BinaryStdOut.write(in.charAt(lastid));
    	}
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
    	int first = BinaryStdIn.readInt();
        String lastCol = BinaryStdIn.readString();
        
        int len = lastCol.length();
        int[] next = new int[len];
        int[] fastid = new int[len];
        int[] count = new int[R+1];
        
        for(int i=0;i<len;i++)
        	count[lastCol.charAt(i)+1]++;
        for(int i=0;i<R;i++)
        	count[i+1]+=count[i];
        for(int i=0;i<len;i++) {
        	int k = count[lastCol.charAt(i)]++;
        	fastid[k]=lastCol.charAt(i);
        	next[k]=i;
        }
        for(int i=0;i<len;i++) {
        	BinaryStdOut.write(fastid[first]);
        	first=next[first];
        }
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    @SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
    	if(args[0].equals("-")) transform();
    	else if(args[0].equals("+")) inverseTransform();
    	else throw new IllegalArgumentException("Illegal command line argument");
    }
}