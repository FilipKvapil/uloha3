package model;

import transforms.Point3D;

import java.awt.*;

public class Pyramid extends Solid{
    public Pyramid() {
        setColor(Color.cyan);
        vb.add(new Point3D( .75, .75,0));
        vb.add(new Point3D( .25,.75,0));
        vb.add(new Point3D(.25,.25,0));
        vb.add(new Point3D(.75, .25,0));
        vb.add(new Point3D(.5,.5,.5));
        addIndices(0,1,1,2,2,3,3,0,0,4,1,4,2,4,3,4);
    }
}
