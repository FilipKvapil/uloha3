package model;

import transforms.Cubic;
import transforms.Point3D;

public class CurveBezi extends Solid{

    Point3D point1 = new Point3D(0,0,0);
    Point3D point2 = new Point3D(1,1,1);
    Point3D point3 = new Point3D(0,1,.5);
    Point3D point4 = new Point3D(.5,0,.25);

    Cubic cubic = new Cubic(Cubic.BEZIER,point1,point2,point3,point4);
    public CurveBezi (){
        for (int i = 1; i < 20; i++) {
            float t = (float)i/20;
            this.vb.add(cubic.compute(t));
        }
        for (int i = 1; i < vb.size(); i++) {
            ib.add(i-1);
            ib.add(i);
        }
    }
}
