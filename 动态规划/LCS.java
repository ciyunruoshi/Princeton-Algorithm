package DP;

public class LCS {
    public static void LCS_V2(String a,String b){
        int min = Integer.min(a.length(),b.length());
        int max = Integer.max(a.length(),b.length());
        int[][] c = new int[2][min+1];
        //表示前缀的最长公共序列

        for(int i=0;i<min;i++)
            c[0][i]=0;
        c[1][0]=0;
        for(int i=1;i<=max;i++) {
            for(int j=1;j<=min;j++){
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    c[1][j] = c[0][j - 1] + 1;
                } else {
                    c[1][j] = Integer.max(c[0][j], c[1][j - 1]);
                }
            }
            for(int k=1;k<=min;k++)
                c[0][k]=c[1][k];
        }
        System.out.print(c[0][min]);
    }

    public static void main(String[] args){
        String a = "ABCBDAB";
        String b = "BDCABA";
        LCS_V2(a,b);
    }
}
