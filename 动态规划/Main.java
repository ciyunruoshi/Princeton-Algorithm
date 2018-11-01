/**
 * 动态规划的题目，要注意的就是最后输出不能强制类型转换 double->int,数字发生改变，要转换成long
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        double[] value = new double[n];
        String line = in.readLine();
        String[] cs = line.split(" ");
        for(int i=0;i<n;i++)
            value[i]=Double.parseDouble(cs[i]);
        line = in.readLine();
        cs = line.split(" ");
        int K=Integer.parseInt(cs[0]);
        int d=Integer.parseInt(cs[1]);




        //algorithm start
        double[][] fmax = new double[K+1][n+1];
        double[][] fmin = new double[K+1][n+1];

        for(int i=1;i<=n;i++){
            fmax[1][i]=value[i-1];
            fmin[1][i]=value[i-1];
        }
        for(int i=0;i<=K;i++){
            fmax[i][0]=1.0;
            fmin[i][0]=1.0;
        }
        double res=Double.MIN_VALUE;
        for(int i=2;i<=K;i++){
            for(int j=i;j<=n;j++){
                double tmpMax=Double.MIN_VALUE;
                double tmpMin=Double.MAX_VALUE;
                for(int k=j-1;k>=j-d&&k>=i-1;k--){
                    if(Math.max(fmax[i-1][k]*value[j-1],fmin[i-1][k]*value[j-1])>tmpMax)
                        tmpMax=Math.max(fmax[i-1][k]*value[j-1],fmin[i-1][k]*value[j-1]);
                    if(Math.min(fmax[i-1][k]*value[j-1],fmin[i-1][k]*value[j-1])<tmpMin)
                        tmpMin=Math.min(fmax[i-1][k]*value[j-1],fmin[i-1][k]*value[j-1]);
                }
                fmax[i][j]=tmpMax;
                fmin[i][j]=tmpMin;
            }
        }
        for(int i=1;i<=n;i++)
            res=Math.max(res,fmax[K][i]);
        System.out.print((long)res);
    }
}