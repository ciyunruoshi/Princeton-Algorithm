import javax.net.ssl.SSLContext;

public class CircularSuffixArray {
   private String src;
   private int n;
   private int ind[];
   public static final int R = 256;
   public CircularSuffixArray(String s)    // circular suffix array of s
   {
	   ind = new int[s.length()];
	   this.src = s;
	   n= s.length();
	  String[] suffixarray = new String[s.length()];
	   for(int i=0;i<n;i++) {
		   ind[i]=i;
	   }
	   cSuffixArray(src,suffixarray);
	   LCBSort(suffixarray,n/2);
   }
   
   private void cSuffixArray(String s,String[] ss) {
	   ss[0]=s;
	   for(int i=0;i<s.length()-1;i++) {
		   ss[i+1]=ss[i].substring(1,n)+ss[i].charAt(0); 
	   }
   }
   
   private void LCBSort(String[] ss,int w) {

	   int[] count ;
	   int[] in = new int[n];
	   String[] aux = new String[ss.length];
	   for(int index=w-1;index>=0;index--) {
		   count = new int[R+1];
		   for(int i=0;i<ss.length;i++)
			   count[ss[i].charAt(index)+1]++;
		   for(int i=0;i<R-1;i++)
			   count[i+1]+=count[i];
		   for(int i=0;i<ss.length;i++) {
			   aux[count[ss[i].charAt(index)]]=ss[i];
			   in[count[ss[i].charAt(index)]]=ind[i];
			   count[ss[i].charAt(index)]++;
		   }
		   for(int i=0;i<ss.length;i++)
			   ss[i]=aux[i];
		   for(int i=0;i<n;i++)
			   ind[i]=in[i];
	   }
	   	
   }
   
   public int length()                     // length of s
   {
	   return n;
   }
   
   public int index(int i)                 // returns index of ith sorted suffix\
   {
	   return ind[i];
   }
   public static void main(String[] args)  // unit testing (required)
   {
	   String string= "ABRACADABRA!";
	   CircularSuffixArray AR = new CircularSuffixArray(string);
	   
   }
}
