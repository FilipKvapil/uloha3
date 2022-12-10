package view;

import rasterize.Raster;
import rasterize.RasterBufferedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Panel extends JPanel {
    private RasterBufferedImage raster;
    private final String[] objekty = {"Krychle" , "Jehlan" , "Mnohostěn"};
    private final String[] krivky = {"Fergusonova","Rézierova","Coonsova"};
    JComboBox CBKrivka, CBObjekt;
    private JCheckBox CHBChuze, CHBKameraPohyb;

    private final String[] transformace= {"Posunutí","Otočení","Měřítko"};
    private final String[] kamera = {"Perspektivní","Ortogonální"};
    JComboBox CBTrans, CBKamera;

    private boolean kameraPohyb=false;
    private boolean chuze=false;

    JButton JBReset;

    public Raster getRaster() {
        return raster;
    }

    private static final int FPS = 1000 / 20;
    public static final int WIDTH = 800, HEIGHT = 600;

    Panel() {
        CBObjekt = new JComboBox<>(objekty);
        this.add(CBObjekt);

        CBKrivka = new JComboBox<>(krivky);
        this.add(CBKrivka);

        CBTrans = new JComboBox(transformace);
        CBTrans.addActionListener(listener);
        this.add(CBTrans);

        CBKamera = new JComboBox(kamera);
        this.add(CBKamera);

        CHBKameraPohyb = new JCheckBox("Kamera");
        CHBKameraPohyb.addActionListener(listener);
        this.add(CHBKameraPohyb);

        CHBChuze = new JCheckBox("Chůze");
        CHBChuze.addActionListener(listener);
        this.add(CHBChuze);

        JBReset = new JButton("Reset Kamery");
        this.add(JBReset);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        raster = new RasterBufferedImage(WIDTH, HEIGHT);
        raster.setClearColor(Color.BLACK.getRGB());
        //hardClear();
        setLoop();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        raster.repaint(g);
    }

    public void resize(){
        if (this.getWidth()<1 || this.getHeight()<1)
            return;
        if (this.getWidth()<=raster.getWidth() && this.getHeight()<=raster.getHeight()) //no resize if new is smaller
            return;
        RasterBufferedImage newRaster = new RasterBufferedImage(this.getWidth(), this.getHeight());

        newRaster.draw(raster);
        raster = newRaster;
    }

    private void setLoop() {
        // časovač, který 30 krát za vteřinu obnoví obsah plátna aktuálním img
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, FPS);
    }

    public void clear() {
        raster.clear();
    }

    public void hardClear(){

        CBObjekt.setSelectedIndex(0);
        CBKamera.setSelectedIndex(0);
        CBKrivka.setSelectedIndex(0);
        CBTrans.setSelectedIndex(0);
        CHBChuze.setSelected(false);
        CHBKameraPohyb.setSelected(false);

        kameraPohyb = false;
        chuze = false;

        this.clear();
    }
    private ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (CHBChuze.equals(source)) chuze = !chuze; else if (CHBKameraPohyb.equals(source)) kameraPohyb = !kameraPohyb;
            grabFocus();
        }
    };

    public boolean isKameraPohyb() {
        return kameraPohyb;
    }

    public boolean isChuze() {
        return chuze;
    }

    public JComboBox getCBKrivka() {
        return CBKrivka;
    }

    public JComboBox getCBObjekt() {
        return CBObjekt;
    }

    public JComboBox getCBTrans() {
        return CBTrans;
    }

    public JComboBox getCBKamera() {
        return CBKamera;
    }

    public JButton getJBReset(){return JBReset;}
}
