package app;

import control.Controller3D;
import model.Cube;
import view.Window;

import javax.swing.*;

public class AppStart {

    public static void main(String[] args) {
        calc(4);
        /*Cube cube = new Cube();*/
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
            new Controller3D(window.getPanel());
            window.setVisible(true);
        });
    }


    public static void calc(int n) {
        if (n-1>0){
            calc(n -3%2);
        }

    }
}
