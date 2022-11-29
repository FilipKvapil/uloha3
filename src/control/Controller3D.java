package control;

import model.Cube;
import renderer.WireRenderer;
import rasterize.LineRasterizerGraphics;
import rasterize.Raster;
import transforms.*;
import view.Panel;

import java.awt.event.*;

public class Controller3D implements Controller {

    private final Panel panel;

    private WireRenderer wireRenderer;
    private Cube cube = new Cube();
    Mat4 mat4Scale = new Mat4Scale(0.5f);
    Mat4 proj ;

    Camera camera = new Camera(
                    new Vec3D(-3.,0.,0.),
                    //azimuth
                    Math.toRadians(0),
                    //zenith
                    Math.toRadians(0),
                    1,true
                    );


    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
        draw();
    }

    public void initObjects(Raster raster) {
        LineRasterizerGraphics lineRasterizer = new LineRasterizerGraphics(raster);
        proj = new Mat4PerspRH(Math.toRadians(60),raster.getHeight()/(float)raster.getWidth(), 0.1f, 200f);
        wireRenderer = new WireRenderer(raster, lineRasterizer, proj, camera.getViewMatrix());
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            //Stisknutí tlačítka myši
            @Override
            public void mousePressed(MouseEvent e) {
            }
            //Uvolnění tlačítka myši
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            //Stisknutí tlačítka myši a přetažení
            @Override
            public void mouseDragged(MouseEvent e) {

            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // na klávesu C vymazat plátno

            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

            }
        });
    }

    private void draw (){
        cube.setModel(mat4Scale);
        panel.clear();
        wireRenderer.render(cube);
        panel.repaint();
    }
    private void update() {
//        panel.clear();

    }

    private void hardClear() {
        panel.clear();
    }

}
