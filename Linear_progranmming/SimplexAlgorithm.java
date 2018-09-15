
/*
*单纯形法程序
*参考普林斯顿算法课
*/
public class SimplexAlgorithm {
	private double[][] table;
	private int m,n;
	
	public SimplexAlgorithm(double[][] a,double[] b,double[] c) {
		m = b.length;//Ô¼ÊøÌõ¼þ¸öÊý
		n = c.length;//Ô­Ê¼±äÁ¿¸öÊý
		
		table = new double[m+1][n+m+1];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				table[i][j]=a[i][j];//Ô¼ÊøÌõ¼þÏµÊýÔØÈë
		for(int j=n;j<n+m;j++) table[j-n][j]=1.0;//Ìí¼ÓÆ«ÖÃÖµm¸ö
		for(int i=0;i<m;i++) table[i][n+m]=b[i];//ÔØÈëÔ¼ÊøÌõ¼þµÈÊ½ÓÒ±ßÊýÖµ
		for(int j=0;j<n;j++) table[m][j] = c[j];//ÔØÈëÄ¿±êº¯Êý
		sys();
		
	}
	
	public int bland() {
		int col = -1;//»ñÈ¡Ä¿±êº¯ÊýÖÐµÚÒ»¸öÏµÊýÎªÕýÖµµÄÏî£¬×÷Îª³öÏî
		for(int j=0;j<n+m;j++)
			if(table[m][j]>0) {
				col = j;
				break;
			}
		return col;
	}
	
	
	public void sys() {
		for(int i=0;i<m+1;i++){
			for(int j=0;j<n+m+1;j++)
				System.out.print(table[i][j]+" ");
			System.out.println("\n");
		}	
	}
	public int minRadio(int col) {
		int line = -1;//»ñÈ¡³öÏîµÄ×î´óÖµ×îÐ¡µÄÔ¼ÊøÌõ¼þ£¬µ±È»¸ºÖµÅÅ³ý
		for(int i=0;i<m;i++) {
			if(table[i][col]==0) continue;
			else if(table[i][n+m]/table[i][col]<0) continue;
			else if(line==-1) line=i; //×÷Îª³õÊ¼¸³ÖµÏî
			else if(table[i][n+m]/table[i][col]<table[line][n+m]/table[line][col])
				line = i;
		}
		return line;
	}
	
	public void privot(int line,int col) {
		double tmp =table[line][col];
		for(int j=0;j<m+n+1;j++) {
			table[line][j]/=tmp;//ÏÈ½«Ñ¡ÖÐµÄÔ¼ÊøÌõ¼þµÄ³öÏîÏµÊý±äÎª1
		}
		for(int i=0;i<m+1;i++) {
			if(i==line) continue;//Ñ¡ÖÐµÄÔ¼ÊøÌõ¼þÒÑ¾­¸Ä±ä£¬ÎÞÐèÔÚ²Ù×÷
			tmp=table[i][col];
			for(int j=0;j<n+m+1;j++) {
				table[i][j]-=tmp*table[line][j];//ÓÃÔ¼ÊøÌõ¼þÀ´Ö´ÐÐÐÐ±ä»»£¬Ê¹³öÏîµÄÁÐÉÏÏµÊýÎª0
			}
		}
	}
	
	public double solver() {
		int col=-1;
		int line = -1;
		while(true) {
			col =bland();
			if(col==-1)
				break;
			line = minRadio(col);
			if(line==-1)
				break;
			privot(line, col);
			sys();
		}
		return -table[m][n+m];//µ±²»Âú×ãÑ­»·Ìõ¼þÊÇ¾ÍÊÇÒÑ¾­ÓÅ»¯½áÊø»òÕßÊäÈë¼´ÎªÓÅ»¯Ïî
	}
	
	public static void main(String[] args) {
		double[][] a= new double[3][2];
		double[] b = new double[3];
		double[] c= new double[2];
		c[0] = 13;
		c[1] = 23;
		a[0][0]=5;
		a[0][1]=15;
		a[1][0]=4;
		a[1][1]=4;
		a[2][0]=35;
		a[2][1]=20;
		b[0]=480;
		b[1]=160;
		b[2]=1190;
		SimplexAlgorithm sa = new SimplexAlgorithm(a, b, c);
		System.out.println(sa.solver());
	}
}
