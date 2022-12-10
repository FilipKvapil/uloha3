package model;

import transforms.Point3D;

import java.awt.*;

public class Cube extends Solid{

    public Cube() {
        setColor(Color.MAGENTA);
        vb.add(new Point3D( 0, 0,0));
        vb.add(new Point3D( 0,1,0));
        vb.add(new Point3D(1,1,0));
        vb.add(new Point3D(1, 0,0));

        vb.add(new Point3D( 0, 0,1));
        vb.add(new Point3D( 0,1,1));
        vb.add(new Point3D(1,1,1));
        vb.add(new Point3D(1, 0,1));

        addIndices( 0,1,1,2,2,3,3,0,
                            4,5,5,6,6,7,7,4,
                            0,4,1,5,2,6,3,7
                            );
    }
}
