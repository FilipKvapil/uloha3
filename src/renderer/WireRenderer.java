package renderer;

import model.Line;
import model.Solid;
import rasterize.LineRasterizerGraphics;
import rasterize.Raster;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

public class WireRenderer {

    private final LineRasterizerGraphics lineRasterizer;
    private final Mat4 proj;
    private final Mat4 view;
    private final Raster raster;

    public WireRenderer(Raster raster, LineRasterizerGraphics lineRasterizer , Mat4 proj, Mat4 view) {
        this.lineRasterizer = lineRasterizer;
        this.proj = proj;
        this.view = view;
        this.raster = raster;
    }

    public void renderSolid(Solid solid){
        for (int i = 0; i < solid.getIb().size(); i += 2) {
            int index1 = solid.getIb().get(i);
            int index2 = solid.getIb().get(i + 1);

            Point3D point1 = solid.getVb().get(index1);
            Point3D point2 = solid.getVb().get(index2);

            Point3D point1Trans = point1.mul(solid.getModel().mul(view).mul(proj));
            Point3D point2Trans = point2.mul(solid.getModel().mul(view).mul(proj));
             // Dehomogenizace
            Point3D point1Dehomog = point1Trans.mul(1/point1Trans.getW());
            Point3D point2Dehomog = point2Trans.mul(1/point2Trans.getW());

            //Treansformace do okna obrazovky
            Vec3D v1 = transformToWindows(new Vec3D(point1Dehomog));
            Vec3D v2 = transformToWindows(new Vec3D(point2Dehomog));

            Line line = new Line(
                    (int) Math.round(v1.getX()), (int) Math.round(v1.getY()),
                    (int) Math.round(v2.getX()), (int) Math.round(v2.getY())
            );
            lineRasterizer.drawLine(line);
        }
    }
    private Vec3D transformToWindows(Vec3D v){
        return v.mul(new Vec3D(1,-1,1))
                .add(new Vec3D(1,1,0))
                .mul(new Vec3D((raster.getWidth()-1)/2., (raster.getHeight()-1)/2., 1));
    }
    public void render(Solid... solids){
        for (Solid solid: solids){
            renderSolid(solid);
        }
    }
}
