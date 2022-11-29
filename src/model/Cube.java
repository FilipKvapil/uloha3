package model;

import transforms.Point3D;

public class Cube extends Solid{

    public Cube() {
        vb.add(new Point3D(-1,-1,1));
        vb.add(new Point3D( 1,-1,1));
        vb.add(new Point3D( 1, 1,1));
        vb.add(new Point3D(-1, 1,1));
        addIndices(0,1,1,2,2,3,3,0);
    }
}
