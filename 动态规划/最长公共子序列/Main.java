package DP;

/**
 * 最长公共子序列LCS动态规划算法
 * 经典版本
 */
public class Main {
    public static void LCS_traditon(String a,String b){
        int n = a.length();
        int m = b.length();
        int[][] c = new int[n+1][m+1];
        //表示前缀的最长公共序列

        for(int i=0;i<n;i++)
            c[0][i]=0;
        for(int i=0;i<m;i++)
            c[i][0]=0;

        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = Integer.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }
        System.out.print(c[n][m]);
    }

    public static void main(String[] args){
        String a = "ABCBDAB";
        String b = "BDCABA";
        LCS_traditon(a,b);
    }
}
