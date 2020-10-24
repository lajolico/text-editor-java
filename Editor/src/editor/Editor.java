/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * Purpose of this program is to develop a text editor in Java
 *
 * @author Logan Jolicoeur
 */
public class Editor extends JFrame implements ActionListener {

    static int WIDTH = 600;
    static int HEIGHT = 600;

    /**
     * This is where we run the main logic of the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame("Java Editor");

        frame.setSize(WIDTH, HEIGHT);

        frame.show();

    }

    /**
     *
     * @param e based on an event that occurs with the mouse or keyboard use it
     * in the program
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        e.toString();
    }

}
