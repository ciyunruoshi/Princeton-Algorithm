

public class SimplexAlgorithm {
	private double[][] table;
	private int m,n;
	
	public SimplexAlgorithm(double[][] a,double[] b,double[] c) {
		m = b.length;//约束条件个数
		n = c.length;//原始变量个数
		
		table = new double[m+1][n+m+1];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				table[i][j]=a[i][j];//约束条件系数载入
		for(int j=n;j<n+m;j++) table[j-n][j]=1.0;//添加偏置值m个
		for(int i=0;i<m;i++) table[i][n+m]=b[i];//载入约束条件等式右边数值
		for(int j=0;j<n;j++) table[m][j] = c[j];//载入目标函数
		sys();
		
	}
	
	public int bland() {
		int col = -1;//获取目标函数中第一个系数为正值的项，作为出项
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
		int line = -1;//获取出项的最大值最小的约束条件，当然负值排除
		for(int i=0;i<m;i++) {
			if(table[i][col]==0) continue;
			else if(table[i][n+m]/table[i][col]<0) continue;
			else if(line==-1) line=i; //作为初始赋值项
			else if(table[i][n+m]/table[i][col]<table[line][n+m]/table[line][col])
				line = i;
		}
		return line;
	}
	
	public void privot(int line,int col) {
		double tmp =table[line][col];
		for(int j=0;j<m+n+1;j++) {
			table[line][j]/=tmp;//先将选中的约束条件的出项系数变为1
		}
		for(int i=0;i<m+1;i++) {
			if(i==line) continue;//选中的约束条件已经改变，无需在操作
			tmp=table[i][col];
			for(int j=0;j<n+m+1;j++) {
				table[i][j]-=tmp*table[line][j];//用约束条件来执行行变换，使出项的列上系数为0
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
		return -table[m][n+m];//当不满足循环条件是就是已经优化结束或者输入即为优化项
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
