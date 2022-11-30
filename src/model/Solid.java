package model;

import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Solid {
    protected List<Point3D> vb = new ArrayList<>();
    protected List<Integer> ib = new ArrayList<>();
    protected Color color = Color.white;

    private Mat4 model = new Mat4Identity();

    public Mat4 getModel() {
        return model;
    }

    public void setModel(Mat4 model) {
        this.model = model;
    }

    public List<Point3D> getVb() {
        return vb;
    }

    public void setVb(List<Point3D> vb) {
        this.vb = vb;
    }

    public List<Integer> getIb() {
        return ib;
    }

    public void setIb(List<Integer> ib) {
        this.ib = ib;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    protected void addIndices(Integer... integers){
        ib.addAll(Arrays.asList(integers));
    }
}
