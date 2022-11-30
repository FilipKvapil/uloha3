package control;

import model.Axis.AxisX;
import model.Axis.AxisY;
import model.Axis.AxisZ;
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
    private final Cube cube = new Cube();
    private final AxisX axisX = new AxisX();
    private final AxisY axisY = new AxisY();
    private final AxisZ axisZ = new AxisZ();
    private int mouseX;
    private int mouseY;
    Mat4 mat4Scale = new Mat4Scale(0.5f);
    Mat4 proj ;
    Vec3D position = new Vec3D(-3.,1.,1);
    double azimuth = Math.toRadians(0);
    double zenith = Math.toRadians(0);
    Camera camera = new Camera(
            position,
                    //azimuth
                    azimuth,
                    //zenith
                    zenith,
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
            public void mouseEntered(MouseEvent e) {
                mouseX=e.getX();
                mouseY=e.getY();
            }

        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            //Stisknutí tlačítka myši a přetažení
            @Override
            public void mouseDragged(MouseEvent e) {

            }
            @Override
            public void mouseMoved(MouseEvent e) {
                    if(e.getX()>mouseX){
                        azimuth -= Math.toRadians(.5);
                        cameraSetPotic();
                    }else if (e.getX()<mouseX){
                        azimuth += Math.toRadians(.5);
                        cameraSetPotic();
                    }

                    if (e.getY() > mouseY) {
                        zenith -= Math.toRadians(.5);
                        cameraSetPotic();
                    } else if (e.getY() < mouseY) {
                        zenith += Math.toRadians(.5);
                        cameraSetPotic();
                    }

                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_W: case KeyEvent.VK_UP:
                        position = new Vec3D(position.getX()+.1, position.getY(), position.getZ());
                        cameraSetPotic();
                        break;
                    case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
                        position = new Vec3D(position.getX()-.1, position.getY(), position.getZ());
                        cameraSetPotic();
                        break;
                    case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                        position = new Vec3D(position.getX(), position.getY()+.1, position.getZ());
                        cameraSetPotic();
                        break;
                    case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                        position = new Vec3D(position.getX(), position.getY()-.1, position.getZ());
                        cameraSetPotic();
                        break;
                }
            }
        });
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void draw (){
        cube.setModel(mat4Scale);
        panel.clear();
        wireRenderer.render(axisX,axisY,axisZ,cube);
        panel.repaint();
    }
    private void update() {
//        panel.clear();

    }
    private void cameraSetPotic (){
        camera = new Camera(camera, position);
        camera = new Camera(camera,azimuth,zenith);
        wireRenderer.setView(camera.getViewMatrix());
        draw();
    }
    private void hardClear() {
        panel.clear();
    }

}
