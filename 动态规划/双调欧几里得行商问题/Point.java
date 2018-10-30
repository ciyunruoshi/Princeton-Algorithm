package DP;

public class Point implements Comparable<Point> {
    public double xv;
    public double yv;
    public Point(double x,double y){
        this.xv = x;
        this.yv = y;
    }
    @Override
    public int compareTo(Point o) {
        if(this.xv==o.xv)
            return 0;
        else if(this.xv<o.xv)
            return -1;
        else
            return 1;
    }
}