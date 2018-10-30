package DP;

import java.util.Comparator;

/**
 * 双调欧几里得行商问题（假设所有点的X坐标不同）
 *
 * 1 所有点按x坐标排序从小到大 ->P
 * 2 b[i,j]表示Pi点从左到右直到P1,而后在从右到左到Pj的最短路程
 * 3 d[i,j]表示Pi与Pj的欧几里得距离，即直线长度
 * 4 最优子结构(前提条件为i<=j)
 *          b[i,j-1]+d[j-1,j]     i<j-1
 * b[i,j]={ min(b[i,k]+d[k,j])    i=j-1
 *          b[j,i]                i>j
 */

public class EuclidTour{
    Point[] points;
    int n;
    public EuclidTour(Point[] points){
        this.points = points;
        this.n = points.length-1;
    }
    public  double Euclid(){
        QuickSort(1,n);
        double[][] s = new double[n+1][n+1];
        s[1][2]=d(1,2);
        int j=0;int i=0;
        for(j=3;j<=n;j++ ){
            //i<j-1
            for(i=1;i<j-1;i++){
                s[i][j]=s[i][j-1]+d(j-1,j);
            }
            //i=j-1
            s[i][j]=s[1][i]+d(1,i);//初始值
            for(int k =1;k<i;k++){
                double tmp=s[k][i]+d(k,j);
                if(tmp<s[i][j]){
                    s[i][j]=tmp;
                }
            }
        }

        s[n][n]=s[n-1][n]+d(n-1,n);
        return s[n][n];

    }

    public double d(int i,int j){
        return Math.sqrt(Math.pow(points[i].xv-points[j].xv,2)+Math.pow(points[i].yv-points[j].yv,2));
    }


    public void QuickSort(int i,int j){
        if(j-i>20) {
            //快速排序
            Point p = SplitPoint(i, j);
            int start = i + 1;
            int end = j - 2;
            while (true) {
                while (points[start].compareTo(p) <0) start++;
                while (points[end].compareTo(p) >0) end--;
                if (start <= end)
                    swap(start, end);
                else
                    break;
            }
            swap(start, j - 1);
            QuickSort(i, start - 1);
            QuickSort(start + 1, j);
        }else
            SelectSort(i,j);
    }

    public Point SplitPoint(int i,int j){
        int middle = (int)(i+j)/2;
        if(points[i].compareTo(points[middle])==1)
            swap(i,middle);
        if(points[middle].compareTo(points[j])==1)
            swap(middle,j);
        if(points[i].compareTo(points[middle])==1)
            swap(i,middle);
        swap(middle,j-1);
        return points[j-1];
    }
    public void swap(int i,int j){
        Point tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    public void SelectSort(int i,int j){
        for(int ii=i+1;ii<=j;ii++){
            int jj=0;
            Point tmp = points[ii];
            for(jj=ii;jj>i;jj--){

                if(points[jj-1].compareTo(tmp)>0)
                    points[jj]=points[jj-1];
                else
                     break;
            }
            points[jj]=tmp;
        }
    }

    public static void main(String[] args){

        Point[] ps= new Point[8];
        ps[0]=null;
        ps[2]=new Point(0.0,6.0);
        ps[1]=new Point(1.0,0.0);
        ps[3]=new Point(2.0,3.0);
        ps[4]=new Point(5.0,4.0);
        ps[5]=new Point(6.0,1.0);
        ps[6]=new Point(7.0,5.0);
        ps[7]=new Point(8.0,2.0);
        EuclidTour dp = new EuclidTour(ps);
        System.out.print(dp.Euclid());

    }

}
