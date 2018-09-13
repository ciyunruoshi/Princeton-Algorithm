import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
	public static final int R = 256;
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
    	char[] aux = new char[R];
    	for(int i=0;i<R;i++) {
    		aux[i] = (char)i;
    	}
    	
    	String input = BinaryStdIn.readString();
    	boolean printChar = true;
    	int len = input.length();
    	for(int i=0;i<len;i++) {
    		Move(aux,input.charAt(i),printChar);
    	}
    	BinaryStdOut.close();
    }

    
    private static void Move(char[] aux,char x,boolean flag) {
    	for(int i=0;i<R;i++) {
    		if(aux[i]==x) {
    			swith(aux,i,0);
    			if(flag)
    				BinaryStdOut.write(i);
    			break;
    		}
    	}
    }
    
    private static void swith(char[] aux,int i,int j) {
    	char tmp = aux[i];
    	aux[i]=aux[j];
    	aux[j]=tmp;
    	
    	while(!BinaryStdIn.isEmpty()) {
    		int pos = BinaryStdIn.readInt();
    		BinaryStdOut.write(aux[pos]);
    		Move(aux, aux[pos], false);
    	}
    	BinaryStdOut.close();
    }
    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
    	char[] aux = new char[R];
    	for(int i=0;i<R;i++) {
    		aux[i] = (char)i;
    	}
    	
    	
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
    	if(args[0]=="-") encode();
    	else if(args[0]=="+") decode();
    	else throw new IllegalArgumentException("IllegaArgumentException");
    }
}