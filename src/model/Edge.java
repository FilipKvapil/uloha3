package model;

public class Edge{
    private int x1, y1, x2, y2;
    private float k,q;

    public Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        orientate();
        calculate();
    }
    public Edge(Point p1,Point p2) {
        this.x1 = p1.getX();
        this.y1 = p1.getY();
        this.x2 = p2.getX();
        this.y2 = p2.getY();
        orientate();
        calculate();
    }
    private void calculate (){
        if (!isHorizontal()) {
            this.k = (x2 - x1) / (float)(y2 - y1);
            this.q = x1 - k * y1;
        }
    }
    public boolean isHorizontal() {
        return y1 == y2;
    }

    public void orientate() {
        if (y1 > y2) {
            int pomocna = x1;
            x1 = x2;
            x2 = pomocna;

            pomocna = y1;
            y1 = y2;
            y2 = pomocna;

        }
        if(x1 > x2){
            x2++;
        }else{
            x2--;
        }
        y2--;

    }

    public boolean isIntersection(int y) {
        return y >= this.y1 && y < this.y2;
    }

    public int getIntersection(int y) {
        return (int) (this.k * y + this.q);
    }
}